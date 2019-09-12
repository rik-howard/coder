
package xa.xore;

import java.util.List;

public class Xord
{

  // ------------------------------------------------------------------------------------ constant

  public static final String xord = "^[a-zA-Z0-9\\-\\_]+$";
  public static final String bicapital = "[A-Z][a-zA-Z0-9]*";
  public static final String camelBack = "[a-z][a-zA-Z0-9]*";
  public static final String hyphen = "[a-z][a-z0-9\\-]*";
  public static final String underscore = "[a-z][a-z0-9_]*";

  // ------------------------------------------------------------------------------------ predicate

  public static Boolean isXord (String xtring) {return Xtring.isXtring (xtring)? xtring.trim ().matches (xord): false;}
  public static Boolean isBicapitalic (String xord) {return isXord (xord)? xord.trim ().matches (bicapital): false;}
  public static Boolean isCamelBacked (String xord) {return isXord (xord)? xord.trim ().matches (camelBack): false;}
  public static Boolean isHyphenated (String xord) {return isXord (xord)? xord.trim ().matches (hyphen): false;}
  public static Boolean isUnderscored (String xord) {return isXord (xord)? xord.trim ().matches (underscore): false;}
  public static Boolean isPlural (String xord) {return isXord (xord)? xord.trim ().endsWith ("z"): false;}
  
  public static final Boolean areXordz (List <String> xordz)
  {
    Boolean areXordz = true;
    for (String xord: xordz) areXordz &= isXord (xord);
    return areXordz;
  }

  // -------------------------------------------------------------------------------------- module

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

}
