
package xi;

import static xa.Xord.postdot;
import static xa.Xtring.prespace;
import static xa.Xtring.postspace;
import static xa.Xtring.debracketed;
import static xa.Xbject.xemberValue;
import java.util.ArrayList;
import java.util.List;

public class Xinker
{

  public static final List <String> xinking (List <String> xarsement)
  {
    List <String> xinking = new ArrayList <String> ();
    for (String sing: xarsement)
      if (sing.trim ().matches ("^\\[.+$"))
        xinking.add (xemberValue (postdot (debracketed (prespace (sing))), postspace (sing)));
      else xinking.add (sing);
    return xinking;
  }

}
