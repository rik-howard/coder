
package xa.xemplate;

import static xa.xore.Xtring.space;
import static xa.xore.Xtring.tip;
import static xa.xore.Xtring.topHedge;
import static xa.xore.Xtring.bomHedge;
import static xa.xore.Xtring.pre;
import static xa.xore.Xtring.post;
import static xa.xore.Xtring.between;
import static xa.xore.Xoken.isXath;
import static xa.Xbject.xemberValue;
import java.util.ArrayList;
import java.util.List;

public class Xinker
{

  public static final List <String> xinking (List <String> xinkeez)
  {
    List <String> xinking = new ArrayList <String> ();
    for (String xinkee: xinkeez)
      if (xinkee.trim ().startsWith (topHedge))
        if (isXath (between (pre (xinkee, space), topHedge, bomHedge), tip))
          xinking.add (xemberValue (post (between (pre (xinkee, space), topHedge, bomHedge), tip), post (xinkee, space)));
        else if (xinkee.trim ().endsWith ("use")) xinking.add (between (xinkee, topHedge, bomHedge));
        else ;//System.out.println (xinkee);
      else xinking.add (xinkee);
    return xinking;
  }

}
