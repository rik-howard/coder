
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

public class Main
{

  public static void main (String [] args)
  throws FileNotFoundException, IOException
  {
    arguments = Arrays.asList (args);
    if (commandIsToCode ()) Coder.code (templateLeafz (), mappingLinez (), targetName ());
    else if (commandIsToClean ()) Cleaner.clean ();
    else assert false;
  }

  private static final String codingCommand = "-code";
  private static final String cleaningCommand = "-clean";
  private static final String templateFlag = "-from";
  private static final String mappingFlag = "-with";
  private static final String targetFlag = "-to";
  private static List <String> arguments = new ArrayList <String> ();

  private static final String command ()
  {
    if (arguments.contains (codingCommand)) return codingCommand;
    else if (arguments.contains (cleaningCommand)) return cleaningCommand;
    else throw new Error ("not found: " + codingCommand + " " + cleaningCommand);
  }

  private static final Boolean commandIsToCode ()
  {
    return command ().equals (codingCommand);
  }

  private static final Boolean commandIsToClean ()
  {
    return command ().equals (cleaningCommand);
  }

  private static final String templateName ()
  {
    Integer templateFlagIndex = arguments.indexOf (templateFlag);
    assert templateFlagIndex > -1: "not found: " + templateFlag;
    assert templateFlagIndex + 1 < arguments.size (): "not found: templateName";
    return arguments.get (templateFlagIndex + 1);
  }

  private static final List <FSO> templateLeafz ()
  throws FileNotFoundException, IOException
  {
    String templateName = templateName ();
    assert templateName != null;
    List <FSO> z = new ArrayList <FSO> ();
    File file = new File (templateName);
    assert file.exists (): "not found: " + templateName;
    if (file.isDirectory ())
    {
      Folder folder = new Folder (file);
      for (Folder subfolder: folder.folders ())
        z.addAll (subfolder.leaves (""));
      for (Foldee subfoldee: folder.foldees ())
        z.add (subfoldee.leaf (""));
    }
    else z.add (new Foldee (file).leaf (""));
    return z;
  }

  private static final List <String> mappingNamez ()
  {
    List <String> z = new ArrayList <String> ();
    Integer mappingFlagIndex = arguments.indexOf (mappingFlag);
    if (mappingFlagIndex > -1)
      while (++mappingFlagIndex < arguments.size ()
      && ! (arguments.get (mappingFlagIndex).equals (templateFlag)
        || arguments.get (mappingFlagIndex).equals (targetFlag)
        )
      )
      z.add (arguments.get (mappingFlagIndex));
    else z = null;  /* there is no mappingFlag */
    return z;
  }

  private static final List <Line> mappingLinez ()
  throws FileNotFoundException, IOException
  {
    List <String> mappingNamez = mappingNamez ();
    List <Line> z = new ArrayList <Line> ();
    if (mappingNamez == null) z = null;
    else for (String name: mappingNamez)
    {
      File file = new File (name);
      if (file.isDirectory ())
      {
        Folder folder = new Folder (file);
        for (Foldee foldee: folder.foldees ())
          z.addAll (foldee.linez ());
      }
      else if (file.isFile ())
        z.addAll (new Foldee (new File (name)).linez ());
      else throw new Error ("name is neither directory nor folder: " + name);
    }
    return z;
  }

  private static final String targetName ()
  {
    String tn = null;
    Integer targetFlagIndex = arguments.indexOf (targetFlag);
    if (targetFlagIndex > -1)
      if (targetFlagIndex + 1 < arguments.size ()) return arguments.get (targetFlagIndex + 1);
      else ;  /* there is no targetPath after the targetFlag */
    else ;  /* there is no targetFlag */
    return tn;
  }

}
