
package xa.xemplate;

import static xa.xemplate.Xinker.xinking;
import static xaos.Xystem.xlassz;
import static xaos.Xystem.xbjectz;

public class XinkerUser
extends Xser
{

  static
  {
    xlassz.put ("Me", "Me: name");
    xlassz.put ("Thou", "Thou: name*");
    xbjectz.put ("me", "me: Rik");
    xbjectz.put ("thou Tom", "thou: Tom");
    xbjectz.put ("thou Harry", "thou: Harry");
  }

  public static void main (String [] args) {log (xinking (xarsement));}

}
