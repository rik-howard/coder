
package info.lrbh.codive;

import info.lrbh.codable.fsio.FSO;
import info.lrbh.codable.fsio.Foldee;
import info.lrbh.codable.fsio.Folder;
import info.lrbh.codable.fsio.Line;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Coder
{

  private static final String xodingCommand = "-xode";
  private static final String xleaningCommand = "-xlean";
  private static final String sourceTemplateFlag = "-from";
  private static final String sourceDataFlag = "-with";
  private static final String targetFlag = "-to";
  private static final String thereIsNoCommandString = "there is no command, such as: " + xodingCommand + ", " + xleaningCommand;
  private static final String thereIsNoSourceTemplateFlagString = "there is no source template flag: " + sourceTemplateFlag;
  private static final String thereIsNoSourceTemplateString = "there is no source template FSO name after:" + sourceTemplateFlag;
  private static final String thereIsNoSourceTemplateFileString = "there is no source template file: ";

  public static void main (String [] args)
  throws FileNotFoundException, IOException
  {
    List <String> argumentz = Arrays.asList (args);
    String command = command (argumentz);
    List <FSO> sourceTemplateLeafz = sourceTemplateLeafz (sourceTemplateName (argumentz));
    List <Line> commonLinez = commonLinez (sourceDataNamez (argumentz));
    String targetName = targetName (argumentz);
    if (command.equals (xodingCommand)) xode (commonLinez, sourceTemplateLeafz, targetName);
    else if (command.equals (xleaningCommand)) xlean ();
    else assert false;
  }

  private static void xode (List <Line> commonLinez, List <FSO> sourceTemplateLeafz, String targetName)
  throws IOException
  {
    for (FSO leaf: sourceTemplateLeafz)
      if (leaf instanceof Folder) Writer.xrite (Reader.reading (commonLinez, ((Folder) leaf).getName (), null), targetName);
      else if (leaf instanceof Foldee)
      {
        //assert false: Reader.reading (commonLinez, ((Foldee) leaf).getName (), ((Foldee) leaf).linez ());
        Writer.xrite (Reader.reading (commonLinez, ((Foldee) leaf).getName (), ((Foldee) leaf).linez ()), targetName);
      }
      else assert false;
  }

  private static void xlean ()
  {
    throw new UnsupportedOperationException ();
  }

  private static String command (List <String> argumentz)
  {
    if (argumentz.contains (xodingCommand)) return xodingCommand;
    else if (argumentz.contains (xleaningCommand)) return xleaningCommand;
    else assert false: thereIsNoCommandString;
    return new String ();
  }

  private static String sourceTemplateName (List <String> argumentz)
  {
    Integer sourceTemplateFlagIndex = argumentz.indexOf (sourceTemplateFlag);
    assert sourceTemplateFlagIndex > -1: thereIsNoSourceTemplateFlagString;
    assert sourceTemplateFlagIndex + 1 < argumentz.size (): thereIsNoSourceTemplateString;
    return argumentz.get (sourceTemplateFlagIndex + 1);
  }

  private static List <String> sourceDataNamez (List <String> argumentz)
  {
    List <String> sdnz = new ArrayList <String> ();
    Integer sourceDataFlagIndex = argumentz.indexOf (sourceDataFlag);
    if (sourceDataFlagIndex > -1)
      while (++sourceDataFlagIndex < argumentz.size ()
      && ! (argumentz.get (sourceDataFlagIndex).equals (sourceTemplateFlag)
        || argumentz.get (sourceDataFlagIndex).equals (targetFlag)
        )
      )
      sdnz.add (argumentz.get (sourceDataFlagIndex));
    else ;  // there is no sourceDataFlag
    return sdnz;
  }

  private static String targetName (List <String> argumentz)
  {
    String tn = null;
    Integer targetFlagIndex = argumentz.indexOf (targetFlag);
    if (targetFlagIndex > -1)
      if (targetFlagIndex + 1 < argumentz.size ()) return argumentz.get (targetFlagIndex + 1);
      else ;  // there is no targetPath after the targetFlag
    else ;  // there is no targetFlag
    return tn;
  }

  private static final List <FSO> sourceTemplateLeafz (String sourceTemplateName)
  throws FileNotFoundException, IOException
  {
    assert sourceTemplateName != null;
    List <FSO> z = new ArrayList <FSO> ();
    File file = new File (sourceTemplateName);
    assert file.exists (): thereIsNoSourceTemplateFileString + sourceTemplateName;
    if (file.isDirectory ()) z.addAll (new Folder (file).leaves (""));
    else z.add (new Foldee (file).leaf (""));
    return z;
  }

  private static final List <Line> commonLinez (List <String> sourceDataNamez)
  throws FileNotFoundException, IOException
  {
    assert sourceDataNamez != null;
    List <Line> z = new ArrayList <Line> ();
    for (String name: sourceDataNamez)
      z.addAll (new Foldee (new File (name)).linez ());
    return z;
  }

}
