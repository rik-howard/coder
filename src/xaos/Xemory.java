
package xaos;

import static xa.xore.Xtring.tip;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import xa.xore.Xtring;
import static xa.Xlass.isXlass;
import static xa.Xlass.xlassXey;
import static xa.Xbject.isXbject;
import static xa.Xbject.xbjectXey;
import static xa.Xunction.isXunction;
import static xa.Xunction.xunctionXey;
import static xa.Xrocedure.isXtatement;
import static xa.Xemplate.isXemplateLine;

public class Xemory
{

  public static String eofMarker = "<eof>";
  public static String opener = "${";
  public static String closer = "}";
  public static String clausal = ":";
  public static String margin = "|";

  public static final Map <String, String> xlassz = new HashMap <String, String> ();
  public static final Map <String, String> xbjectz = new HashMap <String, String> ();
  public static final List <String> xbjectzKeyz = new ArrayList <String> ();
  public static final Map <String, String> xunctionz = new HashMap <String, String> ();
  public static final List <String> xrocedure = new ArrayList <String> ();
  public static final List <String> xemplate = new ArrayList <String> ();
  public static final List <String> xceptionz = new ArrayList <String> ();
  public static final Map <String, String> xontext = new HashMap <String, String> ();

  public static void memoriseData (List <String> sourceData)
  {
    clear ();
    for (String datum: sourceData)
      if (isXemplateLine (datum)) ;
      else intro (datum);
  }

  public static void memoriseMoreData (List <String> sourceData)
  {
    for (String datum: sourceData)
      if (isXemplateLine (datum)) ;
      else intro (datum);
  }

  public static void memoriseTemplate (List <String> sourceData)
  {
    for (String datum: sourceData)
      if (isXemplateLine (datum)) intro (datum);
  }

  public static void surroundTemplate (String loopBeginning, String eofMarker, String loopEnding)
  {
    assert loopBeginning != null: "the loopBeginning may not be null";
    assert eofMarker != null: "the eofMarker may not be null";
    assert loopEnding != null: "the loopEnding may not be null";
    if (xemplate.size () > 0)
    {
      xemplate.set (0, tip + loopBeginning + xemplate.get (0).substring (1));
      xemplate.set (xemplate.size () - 1, xemplate.get (xemplate.size () - 1) + eofMarker + loopEnding);
    }
  }

  public static void intro (String xtring)
  {
    if (isXymbol (xtring)) setXymbol (xtring);
    else if (isXlass (xtring)) xlassz.put (xlassXey (xtring), xtring);
    else if (isXbject (xtring)) {xbjectzKeyz.add (xbjectXey (xtring)); xbjectz.put (xbjectXey (xtring), xtring);}
    else if (isXunction (xtring)) xunctionz.put (xunctionXey (xtring), xtring);
    else if (isXtatement (xtring)) xrocedure.add (xtring);
    else if (isXemplateLine (xtring)) xemplate.add (xtring);
    else if (Xemory.isComment (xtring)) ;
    else xceptionz.add (xtring);
  }

  public static void extro (String xtring)
  {
    if (isXlass (xtring)) xlassz.remove (xlassXey (xtring));
    else if (isXbject (xtring)) xbjectz.remove (xbjectXey (xtring));
    else if (isXunction (xtring)) xunctionz.remove (xunctionXey (xtring));
  }

  private static final void clear ()
  {
    xlassz.clear ();
    xbjectz.clear ();
    xbjectzKeyz.clear ();
    xunctionz.clear ();
    xrocedure.clear ();
    xemplate.clear ();
    xceptionz.clear ();
    xontext.clear ();
  }

  public static List <String> dump ()
  {
    List <String> dumping = new ArrayList <String> ();
    dumping.add ("## xa");
    dumping.add ("# xelationz"); for (String xlass: xlassz.values ()) dumping.add (xlass);
    dumping.add ("# xecordz"); for (String xbject: xbjectz.values ()) dumping.add (xbjectXey (xbject) + " :: " + xbject);
    dumping.add ("# xunctionz"); for (String xunction: xunctionz.values ()) dumping.add (xunction);
    dumping.add ("# xrocedure"); for (String part: xrocedure) dumping.add (part);
    dumping.add ("# xemplate"); for (String line: xemplate) dumping.add (line);
    dumping.add ("# xceptionz"); for (String xception: xceptionz) dumping.add (xception);
    dumping.add ("# xontext"); for (String x: xontext.values ()) dumping.add (x);
    dumping.add ("## xnd");
    return dumping;
  }

  public static Boolean isComment (String xtring) {return Xtring.isXtring (xtring)? Xtring.isXmpty (xtring) || xtring.trim ().startsWith (Xtring.hash): false;}

  public static Boolean isXymbol (String xtring)
  {
    assert xtring != null: "xtring is null";
    return xtring.startsWith ("@eofMarker")
    || xtring.startsWith ("@opener")
    || xtring.startsWith ("@closer")
    || xtring.startsWith ("@clausal")
    || xtring.startsWith ("@margin");
  }

  private static void setXymbol (String xtring)
  {
    String value = Xtring.post (xtring, Xtring.space);
    if (xtring.startsWith ("@eofMarker")) eofMarker = value;
    else if (xtring.startsWith ("@opener")) opener = value;
    else if (xtring.startsWith ("@closer")) closer = value;
    else if (xtring.startsWith ("@clausal")) clausal = value;
    else if (xtring.startsWith ("@margin")) margin = value;
    else ;
  }

}
