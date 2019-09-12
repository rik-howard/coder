
package xa.xemplate;

import static xa.xore.Xtring.xmpty;
import static xa.xore.Xtring.end;
import static xa.xore.Xtring.tip;
import static xa.xore.Xtring.neck;
import static xa.xore.Xtring.topHedge;
import static xa.xore.Xtring.bomHedge;
import static xa.xore.Xoken.isXath;
import static xa.xore.Xtring.has;
import static xa.xore.Xtring.pre;
import static xa.xore.Xtring.post;
import static xa.xore.Xtring.between;
import static xa.xore.Xtring.joining;
import static xa.xore.Xord.isXord;
import java.util.ArrayList;
import java.util.List;
import xa.xore.Xtring;

public class Xokeniser
{

  private static final List <String> tokenz = new ArrayList <String> ();

  public static List <String> xokenisation (List <String> xleanment)
  {
    String string = joining (xleanment, end);
    // assert Decommer.findsDecommic (string);
    while (string.length () > 0)
      if (hasVariableBeginning (string)) string = constant (string);
      else if (hasVariable (string)) string = variable (string);
      else string = rest (string);
    return Xokeniser.tokenz;
  }

  private static String constant (String string)
  {
    String variable = string.substring (1, string.indexOf (bomHedge));
    String rest = string.substring (string.indexOf (bomHedge) + 1);
    Xokeniser.tokenz.add (new StringBuffer (topHedge).append (variable).append (bomHedge).toString ());
    return rest;
  }

  private static String variable (String string)
  {
    String constant = string.substring (0, string.indexOf (topHedge));
    String rest = string.substring (string.indexOf (topHedge));
    Xokeniser.tokenz.add (constant);
    return rest;
  }

  private static String rest (String string)
  {
    Xokeniser.tokenz.add (string);
    return "";
  }

  private static Boolean hasVariableBeginning (String string)
  {
    return string.startsWith (topHedge);
  }

  private static Boolean hasVariable (String string)
  {
    return string.indexOf (topHedge) > 0;
  }

  public static Boolean isXonstant (String xoken)
  {
    return Xtring.isXtring (xoken)? !xoken.trim ().matches ("\\[.*\\]"): false;
  }

  public static Boolean isXariable (String xoken)
  {
    //System.out.println (between (xoken, topHedge, bomHedge)+isXath (between (xoken, topHedge, bomHedge), tip));
    return has (xoken, topHedge, bomHedge)
    && isXath (between (xoken, topHedge, bomHedge), tip);
  }

  public static Boolean isXonditional (String xoken)
  {
    return has (xoken, topHedge, bomHedge)
    && has (between (xoken, topHedge, bomHedge), neck)
    && isXath (post (between (xoken, topHedge, bomHedge), neck), tip);
  }

  public static Boolean isAlternative (String xoken)
  {
    return has (xoken, topHedge, bomHedge)
    && between (xoken, topHedge, bomHedge).equals (neck);
  }

  public static Boolean isLooping (String xoken)
  {
    return has (xoken, topHedge, bomHedge)
    && has (between (xoken, topHedge, bomHedge), neck)
    && isXord (pre (between (xoken, topHedge, bomHedge), neck))
    && isXath (post (between (xoken, topHedge, bomHedge), neck), tip);
  }

  public static Boolean isMagicSeperator (String xoken)
  {
    return has (xoken, topHedge, bomHedge)
    && between (xoken, topHedge, bomHedge).length () > 0;
  }

  public static Boolean isEnding (String xoken)
  {
    return has (xoken, topHedge, bomHedge)
    && between (xoken, topHedge, bomHedge).equals (xmpty);
  }

  public static String firstXoken (List <String> xokenisation)
  {
    if (xokenisation == null) return null;
    else if (xokenisation.size () == 0) return xmpty;
    else return xokenisation.get (0);
  }

  public static List <String> restXokenz (List <String> xokenisation)
  {
    if (xokenisation == null) return null;
    else if (xokenisation.size () > 0) return xokenisation.subList (1, xokenisation.size ());
    else return null;
    // else return new ArrayList <String> ();
  }

}
