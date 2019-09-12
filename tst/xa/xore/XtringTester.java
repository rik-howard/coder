
package xa.xore;

import static xa.xore.Xtring.*;
import java.util.Arrays;

public class XtringTester
{

  public static void main (String [] args)
  {
    assert has ("Tb: dbName* name", neck);
    assert head ("Tb: dbName* name").equals ("Tb");
    assert body ("Tb: dbName* name").equals ("dbName* name");
    assert seperation ("dbName* name", space).equals (Arrays.asList ("dbName*", "name"));
    System.out.print (tip);
  }

}
