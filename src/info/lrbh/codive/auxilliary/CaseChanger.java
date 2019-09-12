
package info.lrbh.codive.auxilliary;

public class CaseChanger
{

  private static final String word = "^[^\\ ]+$";
  private static final String bicapital = "[A-Z][a-zA-Z0-9]*";
  private static final String camelBack = "[a-z][a-zA-Z0-9]*";
  private static final String hyphen = "[a-zA-Z0-9\\-]+";
  private static final String underscore = "[a-zA-Z0-9_]+";

  private static Boolean idsWord (String string) {return string != null? string.trim ().matches (word): false;}
  private static Boolean idsBicapitalic (String word) {return idsWord (word)? word.trim ().matches (bicapital): false;}
  private static Boolean idsCamelBacked (String word) {return idsWord (word)? word.trim ().matches (camelBack): false;}
  private static Boolean idsHyphenated (String word) {return idsWord (word)? word.trim ().matches (hyphen): false;}
  private static Boolean idsUnderscored (String word) {return idsWord (word)? word.trim ().matches (underscore): false;}

  public static String underscoring (String word)
  {
    if (idsUnderscored (word)) return word;
    else if (idsHyphenated (word)) return replacement (word, "-", "_");
    else if (idsBicapitalic (word)) return decapitalisation (word, "_");
    else if (idsCamelBacked (word)) return decapitalisation (word, "_");
    else throw new Error ("string form not recognised: " + word);
  }

  public static String hyphenation (String word)
  {
    if (idsUnderscored (word)) return replacement (word, "_", "-");
    else if (idsHyphenated (word)) return word;
    else if (idsBicapitalic (word)) return decapitalisation (word, "-");
    else if (idsCamelBacked (word)) return decapitalisation (word, "-"); 
    else throw new Error ("string form not recognised: " + word);
  }

  public static String bicapitalisation (String word)
  {
    if (idsUnderscored (word)) return capitalisation (word, "_", true);
    else if (idsHyphenated (word)) return capitalisation (word, "-", true);
    else if (idsBicapitalic (word)) return word;
    else if (idsCamelBacked (word)) return capitalisation (word); 
    else throw new Error ("string form not recognised: " + word);
  }

  public static String camelBacking (String word)
  {
    if (idsUnderscored (word)) return capitalisation (word, "_", false);
    else if (idsHyphenated (word)) return capitalisation (word, "-", false);
    else if (idsBicapitalic (word)) return decapitalisation (word);
    else if (idsCamelBacked (word)) return word;
    else throw new Error ("string form not recognised: " + word);
  }



  private static String decapitalisation (String word, String chr)
  {
    StringBuffer buffer = new StringBuffer (word.substring (0, 1).toLowerCase ());
    for (Integer i = 1; i < word.length (); i++)
      if (word.substring (i, i + 1).matches ("[A-Z]")) buffer.append (chr).append (word.substring (i, i + 1).toLowerCase ());
      else buffer.append (word.substring (i, i + 1));
    return buffer.toString ();
  }

  private static String decapitalisation (String word)
  {
    StringBuffer buffer = new StringBuffer (word.substring (0, 1).toLowerCase ());
    buffer.append (word.substring (1, word.length ()));
    return buffer.toString ();
  }

  private static String replacement (String word, String src, String tgt)
  {
    StringBuffer buffer = new StringBuffer (word.substring (0, 1));
    for (Integer i = 1; i < word.length (); i++)
      if (word.substring (i, i + 1).equals (src)) buffer.append (tgt);
      else buffer.append (word.substring (i, i + 1));
    return buffer.toString ();
  }

  private static String capitalisation (String word, String chr, Boolean initialToo)
  {
    String initial = null;
    if (initialToo) initial = word.substring (0, 1).toUpperCase ();
    else initial = word.substring (0, 1).toLowerCase ();
    StringBuffer buffer = new StringBuffer (initial);
    for (Integer i = 1; i < word.length (); i++)
      if (word.substring (i, i + 1).equals (chr)) buffer.append (word.substring (++i, i + 1).toUpperCase ());
      else buffer.append (word.substring (i, i + 1));
    return buffer.toString ();
  }

  private static String capitalisation (String word)
  {
    StringBuffer buffer = new StringBuffer (word.substring (0, 1).toUpperCase ());
    buffer.append (word.substring (1));
    return buffer.toString ();
  }

}
