
package xa.xore;

import java.util.ArrayList;
import java.util.List;

public class Xtring
{

  // ------------------------------------------------------------------------------------ constant

  public static final String xmpty = "";
  public static final String end = "\n";
  public static final String space = " ";
  public static final String tip = ".";
  public static final String rib = ",";
  public static final String neck = ":";
  public static final String coccyx = ";";

  public static final String amp = "&";
  public static final String hash = "#";
  public static final String star = "*";

  public static final String topLip = "(";
  public static final String bomLip = ")";
  public static final String topTache = "{";
  public static final String bomTache = "}";
  public static final String topHedge = "[";
  public static final String bomHedge = "]";
  public static final String topChev = "<";
  public static final String bomChev = ">";

  public static final List <String> xmptyz = new ArrayList <String> ();

  // ----------------------------------------------------------------------------------- predicate

  public static final Boolean isXtring (String xtring)
  {
    return xtring != null;
  }

  public static final Boolean isXharacter (String xtring)
  {
    return isXtring (xtring)
    && xtring.length () == 1;
  }

  public static final Boolean isXmpty (String xtring)
  {
    return isXtring (xtring) && xtring.trim ().equals (new String ());
  }

  public static final Boolean isNonxmpty (String xtring)
  {
    return isXtring (xtring) && !isXmpty (xtring);
  }

  public static final Boolean has (String xtring, String xharacter)
  {
    return isNonxmpty (xtring) && isXharacter (xharacter) && xtring.trim ().contains (xharacter);
  }

  public static final Boolean has (String xtring, String xpener, String xloser)
  {
    return isNonxmpty (xtring)
    && isXharacter (xpener)
    && isXharacter (xloser)
    && xtring.trim ().contains (xpener)
    && xtring.trim ().substring (xtring.trim ().indexOf (xpener)).contains (xloser);
  }

  public static final Boolean endsIn (String xtring, String xloser)
  {
    return isNonxmpty (xtring)
    && isXharacter (xloser)
    && xtring.trim ().endsWith (xloser);
  }

  // ------------------------------------------------------------------------------------ function

  private static final String escaped (String xharacter)
  {
    return isXharacter (xharacter)?
      xharacter.equals (tip)?
        "\\.":
        xharacter:
      xharacter;
  }

  public static final String pre (String xtring, String xharacter)
  {
    return has (xtring, xharacter)? xtring.trim ().substring (0, xtring.trim ().indexOf (xharacter)): xtring.trim ();
  }

  public static final String post (String xtring, String xharacter)
  {
    return has (xtring, xharacter)? xtring.trim ().substring (xtring.trim ().indexOf (xharacter) + 1): xtring.trim ();
  }

  public static final String head (String xtring)
  {
    return has (xtring, neck)? pre (xtring.trim (), neck).trim (): xmpty;
  }

  public static final String body (String xtring)
  {
    return has (xtring, neck)?
      has (xtring, coccyx)?
        pre (post (xtring.trim (), neck).trim (), coccyx).trim ():
        post (xtring.trim (), neck).trim ():
      has (xtring, coccyx)?
        pre (xtring.trim (), coccyx).trim ():
        xtring.trim ();
  }

  public static final String tail (String xtring)
  {
    return has (xtring, coccyx)?
      has (xtring, tip)?
        pre (post (xtring.trim (), coccyx).trim (), tip).trim ():
        post (xtring.trim (), coccyx).trim ():
      has (xtring, tip)?
        pre (xtring.trim (), tip).trim ():
        xtring.trim ();
  }

  public static final String between (String xtring, String xpener, String xloser)
  {
    return has (xtring, xpener, xloser)?
      xtring.trim ().substring (xtring.trim ().indexOf (xpener) + 1, xtring.trim ().lastIndexOf (xloser)):
      xmpty;
  }

  public static String joining (List <String> xtringz, String joiner)
  {
    StringBuffer buffer = new StringBuffer ();
    for (String xtring: xtringz)
      if (buffer.length () == 0) buffer.append (xtring);
      else buffer.append (joiner).append (xtring);
    return buffer.toString ();
  }

  public static final List <String> seperation (String xtring, String xharacter)
  {
    //if (xharacter.equals (tip))
    List <String> seperation = new ArrayList <String> ();
    if (has (xtring, xharacter)) for (String x: xtring.trim ().split (escaped (xharacter))) seperation.add (x.trim ());
    else seperation.add (xtring.trim ());
    return seperation;
  }


}
