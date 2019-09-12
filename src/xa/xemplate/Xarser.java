
package xa.xemplate;

import static xa.xore.Xtring.tip;
import static xa.xore.Xtring.topHedge;
import static xa.xore.Xtring.bomHedge;
import static xa.xore.Xtring.has;
import static xa.xore.Xtring.pre;
import static xa.xore.Xtring.post;
import static xa.xore.Xtring.head;
import static xa.xore.Xtring.body;
import static xa.xore.Xtring.between;
import static xa.xore.Xoken.branch;
import static xa.xore.Xoken.leaf;
import static xa.Xlass.isXtatic;
import static xa.Xlass.xlassName;
import static xa.Xbject.xemberValue;
import static xa.Xbject.xbjectXey;
import static xa.xemplate.Xokeniser.firstXoken;
import static xa.xemplate.Xokeniser.isAlternative;
import static xa.xemplate.Xokeniser.isEnding;
import static xa.xemplate.Xokeniser.isLooping;
import static xa.xemplate.Xokeniser.isMagicSeperator;
import static xa.xemplate.Xokeniser.isXariable;
import static xa.xemplate.Xokeniser.isXonditional;
import static xa.xemplate.Xokeniser.isXonstant;
import static xa.xemplate.Xokeniser.restXokenz;
import static xa.xore.Xord.camelBacking;
import static xa.xore.Xord.singular;
import static xaos.Xystem.xlassz;
import static xaos.Xystem.xbjectz;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Xarser
{

  private static final Map <String, String> xontextXbjects = new HashMap <String, String> ();
  private static final List <String> xarsing = new ArrayList <String> ();

  public static List <String> xarsing (List <String> xokenz)
  {
    for (String xlass: xlassz.values ())
      if (isXtatic (xlass))  // works for now, maybe always
        xontextXbjects.put (camelBacking (xlassName (xlass)), xbjectz.get (camelBacking (xlassName (xlass))));
      else ;
    xarsing.clear ();
    xarse (xokenz, true);
    return xarsing;
  }

  private static void xarse (List <String> xokenz, Boolean listEnding)
  {
    if (isXonstant (firstXoken (xokenz))) xarseXonstant (xokenz, listEnding);
    else if (isXariable (firstXoken (xokenz))) xarseXariable (xokenz, listEnding);
    else if (isLooping (firstXoken (xokenz))) xarseLooping (xokenz, listEnding);
    else if (isXonditional (firstXoken (xokenz))) xarseXonditional (xokenz, listEnding);
    else if (isAlternative (firstXoken (xokenz))) ;
    else if (isEnding (firstXoken (xokenz))) ;
    else if (isMagicSeperator (firstXoken (xokenz))) xarseMagicSeperator (xokenz, listEnding);
    else ;//System.out.println (firstXoken (xokenz));
  }

  private static void xarseXonstant (List <String> xokenz, Boolean listEnding)
  {
    xarsing.add (firstXoken (xokenz));
    xarse (restXokenz (xokenz), listEnding);
  }

  public static void xarseXariable (List <String> xokenz, Boolean listEnding)
  {
    String xbjectXlassReference = pre (between (firstXoken (xokenz), topHedge, bomHedge), tip);
    xarsing.add (firstXoken (xokenz) + " " + xontextXbjects.get (xbjectXlassReference));
    xarse (restXokenz (xokenz), listEnding);
  }

  public static void xarseLooping (List <String> xokenz, Boolean lastItem)
  {
    String instance = head (between (firstXoken (xokenz), topHedge, bomHedge));
    String lister = singular (leaf (body (between (firstXoken (xokenz), topHedge, bomHedge)), tip));
    String xbjectzXeyPrefix;
    if (has (body (between (firstXoken (xokenz), topHedge, bomHedge)), tip))
    {
      String qualifier = branch (body (between (firstXoken (xokenz), topHedge, bomHedge)), tip);
      xbjectzXeyPrefix = xbjectXey (xontextXbjects.get (qualifier)).replaceFirst (qualifier, lister);      
    }
    else xbjectzXeyPrefix = lister;
    Integer xount = 0;
    //System.out.println (xbjectzXeyPrefix + "@" + xbjectzWithXeyPrefix (xbjectzXeyPrefix));
    for (String xbject: xbjectzWithXeyPrefix (xbjectzXeyPrefix))
    {
      Boolean newLastItem = xount >= xbjectzWithXeyPrefix (xbjectzXeyPrefix).size () - 1;
      xontextXbjects.put (instance, xbject + " " + xount++);
      xarse (restXokenz (xokenz), newLastItem);
    }
    xontextXbjects.remove (instance);
    xarse (afterBlock (xokenz), lastItem);
  }

  public static void xarseMagicSeperator (List <String> xokenz, Boolean listEnding)
  {
    xarsing.add (firstXoken (xokenz) + " " + (listEnding? "": "use"));
    xarse (restXokenz (xokenz), listEnding);
  }

  public static void xarseXonditional (List <String> xokenz, Boolean listEnding)
  {
    String xbjectReference = pre (body (between (firstXoken (xokenz), topHedge, bomHedge)), tip);
    String xemberName = pre (post (between (firstXoken (xokenz), topHedge, bomHedge), tip), tip);
    if (xontextXbjects.get (xbjectReference) == null
    || xemberValue (xemberName, xontextXbjects.get (xbjectReference)).equals ("null"))
    {
      List <String> restXokenz = restXokenz (xokenz);
      while (! isAlternative (firstXoken (restXokenz)) && ! isEnding (firstXoken (restXokenz))) restXokenz = restXokenz (restXokenz);
      if (isAlternative (firstXoken (restXokenz))) xarse (restXokenz (restXokenz), listEnding);
    }
    else xarse (restXokenz (xokenz), listEnding);
    //System.out.println ("@@@@@");
    xarse (afterBlock (xokenz), listEnding);
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
      else if (isAlternative (xokenz.get (i))) ;
      else if (isEnding (xokenz.get (i)))
      {
        stack.remove (stack.size () - 1);
        if (stack.isEmpty ()) return xokenz.subList (i + 1, xokenz.size ());
        else ;
      }
    return null;
  }

  private static final List <String> xbjectzWithXeyPrefix (String prefix)
  {
    List <String> xbjectzW = new ArrayList <String> ();
    for (String x: xbjectz.keySet ())
      if (x.startsWith (prefix)) xbjectzW.add (xbjectz.get (x));
    return xbjectzW;
  }

}
