
package xa;

import static xa.Xlass.areXemberNamez;
import static xa.Xlass.isXlass;
import java.util.Arrays;
import static xa.xore.Xtring.tip;

public class XlassTester
{

  public static void main (String [] args)
  {
    assert areXemberNamez (Arrays.asList ("dbName*", "name"));
    assert isXlass ("Tb: dbName* name");
    System.out.print (tip);
  }

}
