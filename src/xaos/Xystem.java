
package xaos;

import static xa.Xlass.isXlass;
import static xa.Xlass.xlassXey;
import static xa.Xbject.isXbject;
 import static xa.Xunction.isXunction;
import static xa.Xunction.xunctionXey;
import static xa.Xrocedure.isXtatement;
//import static xunk.Xemplate.xleanXariable;
import static xa.Xbject.xbjectXey;
import static xa.Xemplate.isXemplateLine;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import xa.xore.Xtring;
import xaos.fsio.Foldee;
public class Xystem
{

  public static final Map <String, String> xlassz = new HashMap <String, String> ();
  public static final Map <String, String> xbjectz = new HashMap <String, String> ();;
  public static final Map <String, String> xunctionz = new HashMap <String, String> ();
  public static final List <String> xrocedure = new ArrayList <String> ();
  public static final List <String> xemplate = new ArrayList <String> ();
  public static final List <String> xceptionz = new ArrayList <String> ();

  public static void load (Foldee foldee)
  {
    for (String line: foldee.getLinez ())
      intro (line.trim ());
  }
/*
  public static String value (String xoken)
  {
    String [] bitz = xleanXariable (xoken).split ("\\.");
    return xaos.Xao.xemberValue ("name", "my: rik-howard", "My: name");
    //return xaos.Xao.xemberValue (bitz [0], xbjectz.get (bitz [0]), xlassz.get (bicapitalisation (bitz [0])));
  }
*/
  public static List <String> dump ()
  {
    List <String> dumping = new ArrayList <String> ();
    dumping.add ("## xa");
    dumping.add ("# xlassz"); for (String xlass: xlassz.values ()) dumping.add (xlass);
    dumping.add ("# xbjectz"); for (String xbject: xbjectz.values ()) dumping.add (xbjectXey (xbject) + " :: " + xbject);
    dumping.add ("# xunctionz"); for (String xunction: xunctionz.values ()) dumping.add (xunction);
    dumping.add ("# xrocedure"); for (String part: xrocedure) dumping.add (part);
    dumping.add ("# xemplate"); for (String line: xemplate) dumping.add (line);
    dumping.add ("# xceptionz"); for (String xception: xceptionz) dumping.add (xception);
    dumping.add ("## xnd");
    return dumping;
  }

  private static void intro (String xtring)
  {
    if (isXlass (xtring)) xlassz.put (xlassXey (xtring), xtring);
    else if (isXbject (xtring)) xbjectz.put (xbjectXey (xtring), xtring);
    else if (isXunction (xtring)) xunctionz.put (xunctionXey (xtring), xtring);
    else if (isXtatement (xtring)) xrocedure.add (xtring);
    else if (isXemplateLine (xtring)) xemplate.add (xtring);
    else if (Xystem.isComment (xtring)) ;
    else xceptionz.add (xtring);
  }
/*
  private static void extro (String xtring)
  {
    if (isXlass (xtring)) xlassz.remove (xlassXey (xtring));
    else if (isXbject (xtring)) xbjectz.remove (xbjectXey (xtring, xlassz.get (xlassName (xtring))));
    else if (isXunction (xtring)) xunctionz.remove (xunctionXey (xtring));
  }
*/
  public static Boolean isComment (String xtring) {return Xtring.isXtring (xtring)? Xtring.isXmpty (xtring) || xtring.trim ().startsWith (Xtring.hash): false;}
}
