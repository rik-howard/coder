
package xaos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import xaos.fsio.FSO;
import xaos.fsio.Foldee;
import xaos.fsio.Folder;
import static xaos.Xemory.memoriseData;
import static xaos.Xemory.memoriseMoreData;
import static xaos.xystem.Reader.templatez;
import static xaos.xystem.Reader.data;
import static xaos.xystem.Writer.write;
import static xa.Xode.delooping;
import static xa.Xode.relooping;

public class Xystem
{

  private static final Map <String, String> loopz = new HashMap <String, String> ();
  private static final Map <String, String> platez = new HashMap <String, String> ();

  public static final void process (String sourceTemplateName, List <String> sourceDataNamez, String targetName)
  throws FileNotFoundException, IOException
  {
    set (templatez (sourceTemplateName), sourceDataNamez);
    write (targetName, data (sourceDataNamez), loopz, platez);
  }

  private static void set (List <FSO> fsoz, List <String> sourceDataNamez)
  throws FileNotFoundException, IOException
  {
    for (FSO fso: fsoz)
      if (fso instanceof Foldee)
      {
        Foldee foldee = (Foldee) fso;
        memoriseData (data (sourceDataNamez));
        memoriseMoreData (foldee.getLinez ());
        introLoop (foldee.getName ());
        introValue (foldee.getName (), foldee.getValue ());
      }
      else if (fso instanceof Folder)
      {
        memoriseData (data (sourceDataNamez));
        introLoop (((Folder) fso).getName ());
      }
      else assert false: "an FSO must be a Folder or a Foldee";
  }

  private static final void introLoop (String fileName)
  {
    loopz.put (delooping (fileName), relooping (fileName));
  }

  private static final void introValue (String fileName, String fileValue)
  {
    platez.put (delooping (fileName), fileValue);
  }

}
