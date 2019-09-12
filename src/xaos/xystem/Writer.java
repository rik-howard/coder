
package xaos.xystem;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import xaos.fsio.Foldee;
import xaos.fsio.Folder;
import static xa.xore.Xtring.end;
import static xa.xore.Xtring.joining;
import static xa.xore.Xtring.splitting;
import static xa.xemplate.Xleaner.xleaning;
import static xa.xemplate.Xokeniser.xokenisation;
import static xa.xemplate.Xarser.xarsing;
import static xa.xemplate.Xinker.xinking;
import static xa.xemplate.Xompiler.xompilation;
import static xaos.Xemory.xemplate;
import static xaos.Xemory.memoriseData;
import static xaos.Xemory.memoriseMoreData;
import static xaos.Xemory.memoriseTemplate;
import static xaos.Xemory.surroundTemplate;
import static xaos.Xemory.eofMarker;
import static xa.xore.Xtring.xmpty;
//import static xaos.Xemory.dump;

public class Writer
{

  public static final void write
  (
    String targetName,
    List <String> data,
    Map <String, String> loopz,
    Map <String, String> platez
  )
  throws IOException
  {
    assert loopz.size () >= platez.size ();
    for (String key: loopz.keySet ())
    {
      memoriseData (data);
      if (platez.get (key) == null)
        for (String folderName: namez (loopz.get (key), key))
          storeFolder (targetName, folderName);
      else
      {
        List <String> plateLinez = splitting (platez.get (key), end);
        memoriseMoreData (plateLinez);
        memoriseTemplate (plateLinez);
        List <String> namez = namez (loopz.get (key), key);
        List <String> valuez = valuez (loopz.get (key));
        assert namez.size () == valuez.size (): "namez: " + namez + "\nvaluez: " + valuez;
        for (Integer i = 0; i < namez.size (); i++)
          storeFoldee (targetName, namez.get (i), valuez.get (i));
      }
    }
  }

  private static void storeFoldee (String targetName, String foldeeName, String foldeeValue)
  throws IOException
  {
    if (foldeeName == null) ;
    else if (foldeeName.equals (xmpty)) ;
    else if (foldeeName.endsWith (File.separator)) ;
    else if (targetName == null)
      System.out.println (new Foldee (foldeeName, foldeeValue));
    else
      new Foldee ((targetName.equals (xmpty)? xmpty: targetName + File.separator) + foldeeName, foldeeValue).store ();
  }

  private static void storeFolder (String targetName, String folderName)
  throws IOException
  {
    if (folderName == null) ;
    else if (folderName.equals (xmpty)) ;
    else if (folderName.endsWith (File.separator)) ;
    else if (targetName == null)
      System.out.println (new Folder (folderName, null, null));
    else
      new Folder ((targetName.equals (xmpty)? xmpty: targetName + File.separator) + folderName, null, null).store ();
  }

  private static List <String> namez (String loop, String plateName)
  {
    String loopedPlateName = loopBeginning (loop) + plateName + eofMarker + loopEnding (loop);
    List <String> xinez = Arrays.asList (loopedPlateName);
    List <String> xokenment = xokenisation (xinez);
    List <String> xarsement = xarsing (xokenment);
    //System.out.println (xokenment);
    //System.out.println (xarsement);
    List <String> xinkment = xinking (xarsement);
    List <String> xompilementz = xompilation (xinkment);
    return splitting (joining (xompilementz, xmpty), eofMarker);
  }

  private static List <String> valuez (String loop)
  {
    surroundTemplate (loopBeginning (loop), eofMarker, loopEnding (loop));
    List <String> xleanment = xleaning (xemplate);
    List <String> xokenment = xokenisation (xleanment);
    List <String> xarsement = xarsing (xokenment);
    List <String> xinkment = xinking (xarsement);
    List <String> xompilement = xompilation (xinkment);
    return splitting (joining (xompilement, end), eofMarker);
  }

  private static String loopBeginning (String loop)
  {
    StringBuffer buffer = new StringBuffer ();
    List <String> loopPartz = xokenisation (Arrays.asList (loop));
    for (Integer i = 0; i < loopPartz.size () / 2; i++)
      buffer.append (loopPartz.get (i));
    return buffer.toString ();
  }

  private static String loopEnding (String loop)
  {
    StringBuffer buffer = new StringBuffer ();
    List <String> loopPartz = xokenisation (Arrays.asList (loop));
    for (Integer i = loopPartz.size () / 2; i < loopPartz.size (); i++)
      buffer.append (loopPartz.get (i));
    return buffer.toString ();
  }

}
