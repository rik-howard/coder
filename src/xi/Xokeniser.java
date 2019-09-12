
package xi;

import static xa.Xord.camelBack;
import static xa.Xord.isXord;
import static xa.Xord.variable;
import static xa.Xtring.closeBrace;
import static xa.Xtring.colon;
import static xa.Xtring.isXtring;
import static xa.Xtring.openBrace;
import static xa.Xtring.joining;
import java.util.ArrayList;
import java.util.List;

public class Xokeniser
{

  private static final List <String> tokenz = new ArrayList <String> ();
  public static final String o = "[";
  public static final String c = "]";

  public static List <String> xokenisation (List <String> xleanment)
  {
    String string = joining (xleanment, "\n");
    //assert Decommer.findsDecommic (string);
    while (string.length () > 0)
      if (Xokeniser.findsVariableAtBeginning (string)) string = Xokeniser.constant (string);
      else if (Xokeniser.findsVariable (string)) string = Xokeniser.variable (string);
      else string = Xokeniser.rest (string);
    return Xokeniser.tokenz;
  }

  private static String constant (String string)
  {
    String variable = string.substring (1, string.indexOf (c));
    String rest = string.substring (string.indexOf (c) + 1);
    Xokeniser.tokenz.add (new StringBuffer (o).append (variable).append (c).toString ());
    return rest;
  }

  private static String variable (String string)
  {
    String constant = string.substring (0, string.indexOf (o));
    String rest = string.substring (string.indexOf (o));
    Xokeniser.tokenz.add (constant);
    return rest;
  }

  private static String rest (String string)
  {
    Xokeniser.tokenz.add (string);
    return "";
  }

  private static Boolean findsVariableAtBeginning (String string) {return string.startsWith (o);}
  private static Boolean findsVariable (String string) {return string.indexOf (o) > 0;}

  public static Boolean isXonstant (String xoken) {return isXtring (xoken)? ! xoken.trim ().matches (openBrace + ".*" + closeBrace): false;}
  public static Boolean isXariable (String xoken) {return isXord (xoken)? xoken.trim ().matches (openBrace + variable + closeBrace): false;}
  public static Boolean isXonditional (String xoken) {return isXord (xoken)? xoken.trim ().matches (openBrace + colon + variable + closeBrace): false;}
  public static Boolean isAlterntive (String xoken) {return isXord (xoken)? xoken.trim ().matches (openBrace + colon + closeBrace): false;}
  public static Boolean isLooping (String xoken) {return isXord (xoken)? xoken.trim ().matches (openBrace + camelBack + colon + variable + closeBrace): false;}
  public static Boolean isEnding (String xoken) {return isXord (xoken)? xoken.trim ().matches (openBrace + closeBrace): false;}

  public static String firstXoken (List <String> xokenisation)
  {
    if (xokenisation == null) return null;
    else if (xokenisation.size () == 0) return new String ();
    else return xokenisation.get (0);
  }

  public static List <String> restXokenz (List <String> xokenisation)
  {
    if (xokenisation == null) return null;
    else if (xokenisation.size () > 0) return xokenisation.subList (1, xokenisation.size ());
    else return null;
    //else return new ArrayList <String> ();
  }

}
