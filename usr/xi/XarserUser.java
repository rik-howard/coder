
package xi;

import static xaos.Xystem.xbjectz;
import static xaos.Xystem.xlassz;
import static xi.Xarser.xarsing;

public class XarserUser
extends Xser
{

  static
  {
    xlassz.put ("Me", "Me: name");
    xlassz.put ("Thou", "Thou: name*");
    xlassz.put ("Our", "Our: name");
    xbjectz.put ("me", "me: Rik");
    xbjectz.put ("thou Tom", "thou: Tom");
    xbjectz.put ("thou Harry", "thou: Harry");
    xbjectz.put ("our", "our: theNamed");
  }

  public static void main (String [] args)
  {
    log
    (
      xarsing
      (
        xokenment
      )
    );
  }

}
