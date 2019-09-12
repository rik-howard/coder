
package xa.xemplate;

import static xaos.Xemory.opener;
import static xaos.Xemory.closer;
import static xaos.Xemory.clausal;
import static xa.xore.Xtring.xmpty;
import static xa.xore.Xtring.end;
import static xa.xore.Xtring.tip;
import static xa.xore.Xoken.isXath;
import static xa.xore.Xtring.has;
import static xa.xore.Xtring.pre;
import static xa.xore.Xtring.post;
import static xa.xore.Xtring.between;
import static xa.xore.Xtring.joining;
import static xa.xore.Xord.isXord;
import java.util.ArrayList;
import java.util.List;

public class Xokeniser
{

  private static List <String> tokenz;

  public static List <String> xokenisation (List <String> xleanment)
  {
    tokenz = new ArrayList <String> ();
    String string = joining (xleanment, end);
    // assert Decommer.findsDecommic (string);
    while (string.length () > 0)
      if (hasVariableBeginning (string)) string = constant (string);
      else if (hasVariable (string)) string = variable (string);
      else string = rest (string);
    return tokenz;
  }

  private static String constant (String string)
  {
    String variable = string.substring (opener.length (), string.indexOf (closer));
    String rest = string.substring (string.indexOf (closer) + 1);
    Xokeniser.tokenz.add (new StringBuffer (opener).append (variable).append (closer).toString ());
    return rest;
  }

  private static String variable (String string)
  {
    String constant = string.substring (0, string.indexOf (opener));
    String rest = string.substring (string.indexOf (opener));
    Xokeniser.tokenz.add (constant);
    return rest;
  }

  private static String rest (String string)
  {
    Xokeniser.tokenz.add (string);
    return xmpty;
  }

  private static Boolean hasVariableBeginning (String string)
  {
    return string.startsWith (opener);
  }

  private static Boolean hasVariable (String string)
  {
    return string.indexOf (opener) > 0;
  }

  public static Boolean isXonstant (String xoken)
  {
    assert xoken != null: "xoken is null";
    //return Xtring.isXtring (xoken)? !xoken.trim ().matches ("\\[.*\\]"): false;
    return ! (xoken.startsWith (opener) && xoken.endsWith (closer));
  }

  public static Boolean isXariable (String xoken)
  {
    return has (xoken, opener, closer)
    && isXath (between (xoken, opener, closer), tip);
  }

  public static Boolean isXonditional (String xoken)
  {
    return has (xoken, opener, closer)
    && has (between (xoken, opener, closer), clausal)
    && isXath (post (between (xoken, opener, closer), clausal), tip);
  }

  public static Boolean isAlternative (String xoken)
  {
    //return has (xoken, opener, closer)
    //&& between (xoken, opener, closer).equals (clausal);
    return new StringBuffer ().append (opener).append (clausal).append (closer).toString ().equals (xoken);
  }

  public static Boolean isLooping (String xoken)
  {
    return has (xoken, opener, closer)
    && has (between (xoken, opener, closer), clausal)
    && isXord (pre (between (xoken, opener, closer), clausal))
    && isXath (post (between (xoken, opener, closer), clausal), tip);
  }

  public static Boolean isMagicSeperator (String xoken)
  {
    return has (xoken, opener, closer)
    && between (xoken, opener, closer).length () > 0;
  }

  public static Boolean isEnding (String xoken)
  {
    return has (xoken, opener, closer)
    && between (xoken, opener, closer).equals (xmpty);
  }

  public static String firstXoken (List <String> xokenisation)
  {
    if (xokenisation == null) return null;
    else if (xokenisation.size () == 0) return xmpty;
    else return xokenisation.get (0);
  }

  public static List <String> restXokenz (List <String> xokenisation)
  {
    List <String> restXokenz = new ArrayList <String> ();
    if (xokenisation == null) return null;
    else if (xokenisation.size () > 0)
      for (Integer i = 1; i < xokenisation.size (); i++)
        restXokenz.add (xokenisation.get (i));
    else return null;
    return restXokenz;
  }

}
