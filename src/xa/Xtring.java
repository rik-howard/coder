
package xa;

import static xa.Xord.isXord;
import java.util.ArrayList;
import java.util.List;

public class Xtring
{

  // ------------------------------------------------------------------------------------ constant

  public static final String space = " ";
  public static final String rib = ",";
  public static final String neck = ":";
  public static final String tip = ".";
  public static final String bone = "&";
  public static final String commer = "#";
  public static final String topLip = "(";
  public static final String bomLip = ")";
  public static final String topTache = "{";
  public static final String bomTache = "}";
  public static final String topHedge = "[";
  public static final String bomHedge = "]";
  public static final String openBrace = "\\[";
  public static final String closeBrace = "\\]";
  public static final String colon = ":";
  public static Boolean isXtring (String xtring) {return xtring != null;}
  public static Boolean isXmpty (String xtring) {return isXtring (xtring)? xtring.trim ().equals (new String ()): false;}
  public static Boolean hasSpace (String xtring) {return xtring == null? false: xtring.trim ().contains (space);}
  public static Boolean hasRib (String xtring) {return xtring == null? false: xtring.trim ().contains (rib);}
  public static Boolean hasNeck (String xtring) {return xtring == null? false: xtring.trim ().contains (neck);}
  public static Boolean isPretipped (String xtring) {return isXtring (xtring)? xtring.trim ().startsWith (tip): false;}
  public static Boolean hasBone (String xtring) {return xtring == null? false: xtring.trim ().contains (bone);}
  public static Boolean hasOutfix (String xtring) {return xtring == null? false: xtring.trim ().contains (topLip) && xtring.trim ().substring (xtring.trim ().indexOf (topLip)).contains (bomLip);}
  //public static Boolean isParenthetic (String xtring) {return xtring == null? false: xtring.trim ().startsWith (topLip) && xtring.endsWith (botLip);}
  public static Boolean isComment (String xtring) {return isXtring (xtring)? isXmpty (xtring) || xtring.trim ().startsWith (commer): false;}
  public static Boolean isBracketed (String xtring) {return isXtring (xtring)? xtring.trim ().startsWith (topHedge) && xtring.trim ().endsWith (bomHedge): false;}

  // -------------------------------------------------------------------------------------- module

  public static final String prespace (String xtring)
  {
    if (hasSpace (xtring)) return xtring.trim ().substring (0, xtring.trim ().indexOf (space)).trim ();
    else return null;
  }

  public static final String postspace (String xtring)
  {
    if (hasSpace (xtring)) return xtring.trim ().substring (xtring.trim ().indexOf (space) + 1).trim ();
    else return null;
  }

  public static final String precolon (String xtring)
  {
    if (hasNeck (xtring)) return xtring.trim ().substring (0, xtring.trim ().indexOf (neck)).trim ();
    else return null;
  }

  public static final String postcolon (String xtring)
  {
    if (hasNeck (xtring)) return xtring.trim ().substring (xtring.trim ().indexOf (neck) + 1).trim ();
    else return null;
  }

  public static List <String> spaceSeperation (String xtring)
  {
    List <String> seperation = new ArrayList <String> ();
    if (hasSpace (xtring)) for (String s: xtring.trim ().split (space)) seperation.add (s.trim ());
    else if (isXord (xtring)) seperation.add (xtring.trim ());
    else ;
    return seperation;
  }
  
  public static List <String> commaSeperation (String xtring)
  {
    List <String> seperation = new ArrayList <String> ();
    if (hasRib (xtring)) for (String s: xtring.trim ().split (rib)) seperation.add (s.trim ());
    else if (isXord (xtring)) seperation.add (xtring.trim ());
    else ;
    return seperation;
  }

  public static final String head (String xtring)
  {
    if (hasNeck (xtring)) return xtring.trim ().substring (0, xtring.indexOf (neck)).trim ();
    else return null;
  }

  public static final String body (String xtring)
  {
    if (hasNeck (xtring)) return xtring.trim ().substring (xtring.indexOf (neck) + 1).trim ();
    else return null;
  }

  public static final String preoutfix (String xtring)
  {
    if (hasOutfix (xtring)) return xtring.trim ().substring (0, xtring.indexOf (topLip)).trim ();
    else return null;
  }

  public static final String inoutfix (String xtring)
  {
    if (hasOutfix (xtring)) return xtring.trim ().substring (xtring.indexOf (topLip) + 1, xtring.lastIndexOf (bomLip)).trim ();
    else return null;
  }
  
  public static List <String> ampersandSeperation (String xtring)
  {
    List <String> seperation = new ArrayList <String> ();
    if (xtring == null) ;
    else if (xtring.trim ().indexOf ("&") > 0)
      for (String s: xtring.trim ().split ("&")) seperation.add (s.trim ());
    else ;
    return seperation;
  }

  public static String debracketed (String xtring)
  {
    return isBracketed (xtring)? xtring.trim ().substring (1, xtring.trim ().length () - 1): null;
  }

  public static String joining (List <String> xtringz, String joiner)
  {
    StringBuffer buffer = new StringBuffer ();
    for (String xtring: xtringz)
      if (buffer.length () == 0) buffer.append (xtring);
      else buffer.append (joiner).append (xtring);
    return buffer.toString ();
  }

}
