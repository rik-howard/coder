
package xa.xemplate;

import java.util.List;
import static xa.xore.Xtring.splitting;

public class Xompiler
{

  public static List <String> xompilation (List <String> xinking)
  {
    StringBuffer xompilation = new StringBuffer ();
    for (String xoken: xinking)
        xompilation.append (xoken);
    return splitting (xompilation.toString (), "\\n");
  }

}
