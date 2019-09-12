
package xa;

import static xa.Xtring.isXtring;

public class Xord
{

  // ------------------------------------------------------------------------------------ constant

  public static final String dot = ".";
  public static final String xord = "^[^\\ ]+$";
  public static final String xtar = "*";
  public static final String bicapital = "[A-Z][a-zA-Z0-9]*";
  public static final String camelBack = "[a-z][a-zA-Z0-9]*";
  public static final String hyphen = "[a-z][a-z0-9\\-]*";
  public static final String underscore = "[a-z][a-z0-9_]*";

  // ------------------------------------------------------------------------------------ predicate

  public static Boolean isXord (String xtring) {return isXtring (xtring)? xtring.trim ().matches (xord): false;}
  public static Boolean isXtarred (String xord) {return isXord (xord)? xord.trim ().endsWith (xtar): false;}
  public static Boolean isBicapitalic (String xord) {return isXord (xord)? xord.trim ().matches (bicapital): false;}
  public static Boolean isCamelBacked (String xord) {return isXord (xord)? xord.trim ().matches (camelBack): false;}
  public static Boolean isHyphenated (String xord) {return isXord (xord)? xord.trim ().matches (hyphen): false;}
  public static Boolean isUnderscored (String xord) {return isXord (xord)? xord.trim ().matches (underscore): false;}
  public static Boolean isPlural (String xord) {return isXord (xord)? xord.trim ().endsWith ("z"): false;}
  public static Boolean hasDot (String xord) {return isXord (xord)? xord.trim ().contains (dot): false;}

  // -------------------------------------------------------------------------------------- module

  public static final String predot (String xtring)
  {
    if (hasDot (xtring)) return xtring.trim ().substring (0, xtring.trim ().indexOf (dot)).trim ();
    else return null;
  }

  public static final String postdot (String xtring)
  {
    if (hasDot (xtring)) return xtring.trim ().substring (xtring.trim ().indexOf (dot) + 1).trim ();
    else return null;
  }

  public static String underscoring (String xord)
  {
    if (Xord.isUnderscored (xord)) return xord;
    else if (Xord.isHyphenated (xord)) return replacement (xord, "-", "_");
    else if (Xord.isBicapitalic (xord)) return decapitalisation (xord, "_");
    else if (Xord.isCamelBacked (xord)) return decapitalisation (xord, "_");
    else return null;
  }

  public static String hyphenation (String xord)
  {
    if (Xord.isUnderscored (xord)) return replacement (xord, "_", "-");
    else if (Xord.isHyphenated (xord)) return xord;
    else if (Xord.isBicapitalic (xord)) return decapitalisation (xord, "-");
    else if (Xord.isCamelBacked (xord)) return decapitalisation (xord, "-"); 
    else return null;
  }

  public static String bicapitalisation (String xord)
  {
    if (Xord.isUnderscored (xord)) return capitalisation (xord, "_", true);
    else if (Xord.isHyphenated (xord)) return capitalisation (xord, "-", true);
    else if (Xord.isBicapitalic (xord)) return xord;
    else if (Xord.isCamelBacked (xord)) return capitalisation (xord); 
    else return null;
  }

  public static String camelBacking (String xord)
  {
    if (Xord.isUnderscored (xord)) return capitalisation (xord, "_", false);
    else if (Xord.isHyphenated (xord)) return capitalisation (xord, "-", false);
    else if (Xord.isBicapitalic (xord)) return decapitalisation (xord);
    else if (Xord.isCamelBacked (xord)) return xord;
    else return null;
  }

  public static String singular (String xord)
  {
    return xord.substring (0, xord.length () - 1);
  }

  public static String xtarred (String xord)
  {
    if (isXord (xord)) return xord + xtar;
    else return null;
  }

  public static String unxtarred (String xord)
  {
    if (isXord (xord))
      if (isXtarred (xord)) return xord.trim ().substring (0, xord.trim ().length () - 1);
      else return null;
    else return null;
  }

  private static String decapitalisation (String xord, String chr)
  {
    StringBuffer buffer = new StringBuffer (xord.substring (0, 1).toLowerCase ());
    for (Integer i = 1; i < xord.length (); i++)
      if (xord.substring (i, i + 1).matches ("[A-Z]")) buffer.append (chr).append (xord.substring (i, i + 1).toLowerCase ());
      else buffer.append (xord.substring (i, i + 1));
    return buffer.toString ();
  }

  private static String decapitalisation (String xord)
  {
    StringBuffer buffer = new StringBuffer (xord.substring (0, 1).toLowerCase ());
    buffer.append (xord.substring (1, xord.length ()));
    return buffer.toString ();
  }

  private static String replacement (String xord, String src, String tgt)
  {
    StringBuffer buffer = new StringBuffer (xord.substring (0, 1));
    for (Integer i = 1; i < xord.length (); i++)
      if (xord.substring (i, i + 1).equals (src)) buffer.append (tgt);
      else buffer.append (xord.substring (i, i + 1));
    return buffer.toString ();
  }

  private static String capitalisation (String xord, String chr, Boolean initialToo)
  {
    String initial = null;
    if (initialToo) initial = xord.substring (0, 1).toUpperCase ();
    else initial = xord.substring (0, 1).toLowerCase ();
    StringBuffer buffer = new StringBuffer (initial);
    for (Integer i = 1; i < xord.length (); i++)
      if (xord.substring (i, i + 1).equals (chr)) buffer.append (xord.substring (++i, i + 1).toUpperCase ());
      else buffer.append (xord.substring (i, i + 1));
    return buffer.toString ();
  }

  private static String capitalisation (String xord)
  {
    StringBuffer buffer = new StringBuffer (xord.substring (0, 1).toUpperCase ());
    buffer.append (xord.substring (1, xord.length () - 1));
    return buffer.toString ();
  }

  public static final String variable = "[a-z][a-zA-Z0-9\\.]*";

/*  public static Boolean areCamelBacked (List <String> xdentifieez)
  {
    if (xdentifieez == null) return false;
    else for (String xdentifiee: xdentifieez)
      if (isCamelBacked (xdentifiee)) ;
      else return false;
    return true;
  }
*/
}
