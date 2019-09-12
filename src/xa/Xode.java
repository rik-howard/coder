
package xa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static xa.xore.Xtring.joining;
import static xa.xemplate.Xokeniser.firstXoken;
import static xa.xemplate.Xokeniser.isAlternative;
import static xa.xemplate.Xokeniser.isXonditional;
import static xa.xemplate.Xokeniser.restXokenz;
import static xa.xemplate.Xokeniser.isXonstant;
import static xa.xemplate.Xokeniser.isXariable;
import static xa.xemplate.Xokeniser.isLooping;
import static xa.xemplate.Xokeniser.isEnding;
import static xa.xemplate.Xokeniser.xokenisation;
import static xa.xore.Xtring.xmpty;

public class Xode
{

  private static final List <String> stack = new ArrayList <String> ();
  private static final List <String> namePartz = new ArrayList <String> ();
  private static final List <String> loopTop = new ArrayList <String> ();
  private static final List <String> loopBom = new ArrayList <String> ();

  public static final String delooping (String xtring)
  {
    clear ();
    xarse (xokenisation (Arrays.asList (xtring)));
    return joining (namePartz, xmpty);
  }

  public static final String relooping (String xtring)
  {
    clear ();
    xarse (xokenisation (Arrays.asList (xtring)));
    loopTop.addAll (loopBom);
    return joining (loopTop, xmpty);
  }

  private static final void xarse (List <String> xokenz)
  {
    if (xokenz.size () > 0)
      if (isXonstant (firstXoken (xokenz))) xarseXonstant (xokenz);
      else if (isXariable (firstXoken (xokenz))) xarseXariable (xokenz);
      else if (isLooping (firstXoken (xokenz))) xarseLooping (xokenz);
      else if (isXonditional (firstXoken (xokenz))) xarseXonditional (xokenz);
      else if (isAlternative (firstXoken (xokenz))) xarseAlternative (xokenz);
      else if (isEnding (firstXoken (xokenz))) xarseEnding (xokenz);
      else System.out.println ("!!!" + firstXoken (xokenz));
    else ;
  }

  private static void xarseEnding (List <String> xokenz)
  {
    if (stack.size () > 0)
      if (stack.get (stack.size () - 1).equals ("for-loop"))
      {
        stack.remove (stack.size () - 1);
        loopBom.add (0, firstXoken (xokenz));
      }
      else if (stack.get (stack.size () - 1).equals ("if-loop"))
      {
        stack.remove (stack.size () - 1);
        namePartz.add (firstXoken (xokenz));
      }
      else assert false;
    else assert false;
    xarse (restXokenz (xokenz));
  }

  private static void xarseAlternative (List <String> xokenz)
  {
    namePartz.add (firstXoken (xokenz));
    xarse (restXokenz (xokenz));
  }

  private static void xarseXonditional (List <String> xokenz)
  {
    stack.add ("if-loop");
    namePartz.add (firstXoken (xokenz));
    xarse (restXokenz (xokenz));
  }

  private static void xarseLooping (List <String> xokenz)
  {
    stack.add ("for-loop");
    loopTop.add (firstXoken (xokenz));
    xarse (restXokenz (xokenz));
  }

  private static void xarseXariable (List <String> xokenz)
  {
    namePartz.add (firstXoken (xokenz));
    xarse (restXokenz (xokenz));
  }

  private static void xarseXonstant (List <String> xokenz)
  {
    namePartz.add (firstXoken (xokenz));
    xarse (restXokenz (xokenz));
  }

  private static void clear ()
  {
    stack.clear ();
    loopTop.clear ();
    loopBom.clear ();
    namePartz.clear ();
  }

}
