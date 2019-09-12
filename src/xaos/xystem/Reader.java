
package xaos.xystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import xaos.fsio.FSO;
import xaos.fsio.Foldee;
import xaos.fsio.Folder;

public class Reader
{

  public static final List <FSO> templatez (String sourceTemplateName)
  throws FileNotFoundException, IOException
  {
    assert sourceTemplateName != null;
    List <FSO> templatez = new ArrayList <FSO> ();
    File file = new File (sourceTemplateName);
    if (file.exists ())
      if (file.isDirectory ()) templatez.addAll (new Folder (file).leaves (null));
      else if (file.isFile ()) templatez.add (new Foldee (file).leaf (null));
      else assert false;
    else assert false: "the sourceTemplate, " + sourceTemplateName + " is not a file";
    return templatez;
  }

  public static final List <String> data (List <String> sourceDataNamez)
  throws FileNotFoundException, IOException
  {
    List <String> data = new ArrayList <String> ();
    if (sourceDataNamez == null) ;
    else for (String dataName: sourceDataNamez)
    {
      File file = new File (dataName);
      if (file.exists () && file.isFile ())
        data.addAll (new Foldee (file).getLinez ());
    }
    return data;
  }

}
