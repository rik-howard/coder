
package xi;

import static xa.Xtring.debracketed;
import static xa.Xtring.precolon;
import static xa.Xtring.postcolon;
import static xa.Xord.postdot;
import static xa.Xord.singular;
import static xa.Xord.predot;
import static xa.Xord.camelBacking;
import static xa.Xlass.isXtatic;
import static xa.Xlass.xlassName;
import static xa.Xbject.xemberValue;
import static xaos.Xystem.xlassz;
import static xaos.Xystem.xbjectz;
import static xi.Xokeniser.isXonstant;
import static xi.Xokeniser.isXariable;
import static xi.Xokeniser.isLooping;
import static xi.Xokeniser.isXonditional;
import static xi.Xokeniser.isAlterntive;
import static xi.Xokeniser.isEnding;
import static xi.Xokeniser.firstXoken;
import static xi.Xokeniser.restXokenz;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Xarser
{

  public static final Map <String, String> xontextXbjects = new HashMap <String, String> ();
  private static final List <String> xarsing = new ArrayList <String> ();

  public static List <String> xarsing (List <String> xokenz)
  {
    for (String xlass: xlassz.values ())
      if (isXtatic (xlass))  // works for now, maybe always
        xontextXbjects.put (camelBacking (xlassName (xlass)), xbjectz.get (camelBacking (xlassName (xlass))));
      else ;
    xarsing.clear ();
    xarse (xokenz);
    return xarsing;
  }

  private static void xarse (List <String> xokenz)
  {
    if (isXonstant (firstXoken (xokenz))) xarseXonstant (xokenz);
    else if (isXariable (firstXoken (xokenz))) xarseXariable (xokenz);
    else if (isLooping (firstXoken (xokenz))) xarseLooping (xokenz);
    else if (isXonditional (firstXoken (xokenz))) xarseXonditional (xokenz);
    else ;
  }

  private static void xarseXonstant (List <String> xokenz)
  {
    xarsing.add (firstXoken (xokenz));
    xarse (restXokenz (xokenz));
  }

  public static void xarseXariable (List <String> xokenz)
  {
    String xbjectXlassReference = predot (debracketed (firstXoken (xokenz)));
    xarsing.add (firstXoken (xokenz) + " " + xontextXbjects.get (xbjectXlassReference));
    xarse (restXokenz (xokenz));
  }

  public static void xarseLooping (List <String> xokenz)
  {
    String xnstanceName = precolon (debracketed (firstXoken (xokenz)));
    String xbjectzXlassReference = singular (postcolon (debracketed (firstXoken (xokenz))));
    Integer xount = 0;
    for (String xbject: xbjectz.values ())
      if (xbject.trim ().matches ("^" + xbjectzXlassReference + ":.*$"))
      {
        xontextXbjects.put (xnstanceName, xbject.trim () + " " + xount++);
        xarse (restXokenz (xokenz));
      }
      else ;
    xontextXbjects.remove (xbjectzXlassReference);
    xarse (afterBlock (xokenz));
  }

  public static void xarseXonditional (List <String> xokenz)
  {
    String xbjectReference = predot (postcolon (debracketed (firstXoken (xokenz))));
    String xemberName = predot (postdot (debracketed (firstXoken (xokenz))));
    if (xontextXbjects.get (xbjectReference) == null
    || xemberValue (xemberName, xontextXbjects.get (xbjectReference)).equals ("null"))
    {
      List <String> restXokenz = restXokenz (xokenz);
      while (! isAlterntive (firstXoken (restXokenz))) restXokenz = restXokenz (restXokenz);
      xarse (restXokenz (restXokenz));
    }
    else xarse (restXokenz (xokenz));;
    //System.out.println ("@@@@@");
    xarse (afterBlock (xokenz));
  }

  public static List <String> afterBlock (List <String> xokenz)
  {
    //System.out.println (xokenz + "@@@");
    List <String> stack = new ArrayList <String> ();
    for (Integer i = 0; i < xokenz.size (); i++)
      if (isXonstant (xokenz.get (i))) ;
      else if (isXariable (xokenz.get (i))) ;
      else if (isLooping (xokenz.get (i))) stack.add (xokenz.get (i));
      else if (isXonditional (xokenz.get (i))) stack.add (xokenz.get (i));
      else if (isAlterntive (xokenz.get (i))) ;
      else if (isEnding (xokenz.get (i)))
      {
        stack.remove (stack.size () - 1);
        if (stack.isEmpty ()) return xokenz.subList (i + 1, xokenz.size ());
        else ;
      }
    return null;
  }

}
