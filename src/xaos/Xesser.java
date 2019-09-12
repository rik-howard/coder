
package xaos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Xesser
{

  private static final String xodingCommand = "-xode";
  private static final String xleaningCommand = "-xlean";
  private static final String sourceTemplateFlag = "-from";
  private static final String sourceDataFlag = "-with";
  private static final String targetFlag = "-to";

  public static void main (String [] args)
  throws FileNotFoundException, IOException
  {
    List <String> argumentz = Arrays.asList (args);
    String command = command (argumentz);
    if (command.equals (xodingCommand))
    {
      String sourceTemplateName = sourceTemplateName (argumentz);
      if (sourceTemplateName == null)
        log ("the sourceTemplateName may not be null");
      else Xystem.process (sourceTemplateName, sourceDataNamez (argumentz), targetName (argumentz));
    }
    else log ("-xlean is not yet supported");
  }

  private static String command (List <String> argumentz)
  {
    if (argumentz.contains (xodingCommand)) return xodingCommand;
    else return xleaningCommand;
  }

  private static String sourceTemplateName (List <String> argumentz)
  {
    String stn = null;
    Integer sourceTemplateFlagIndex = argumentz.indexOf (sourceTemplateFlag);
    if (sourceTemplateFlagIndex > -1)
      if (sourceTemplateFlagIndex + 1 < argumentz.size ()) return argumentz.get (sourceTemplateFlagIndex + 1);
      else log ("there is no sourceTemplatePath after the sourceTemplateFlag: " + sourceTemplateFlag);
    else log ("there is no sourceTemplateFlag: " + sourceTemplateFlag);
    return stn;
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
    else ; //log ("there is no sourceDataFlag: " + sourceDataFlag);
    return sdnz;
  }

  private static String targetName (List <String> argumentz)
  {
    String tn = null;
    Integer targetFlagIndex = argumentz.indexOf (targetFlag);
    if (targetFlagIndex > -1)
      if (targetFlagIndex + 1 < argumentz.size ()) return argumentz.get (targetFlagIndex + 1);
      else ;  //log ("there is no targetPath after the targetFlag: " + targetFlag);
    else ;  //log ("there is no targetFlag: " + targetFlag);
    return tn;
  }

  private static void log (Object message)
  {
    System.out.println (message);
  }

}
