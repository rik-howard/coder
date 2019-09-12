
package xi;

import java.util.Arrays;
import java.util.List;

public class Xompiler
{

  public static List <String> xompilation (List <String> xinking)
  {
    StringBuffer xompilation = new StringBuffer ();
    for (String xoken: xinking)
        xompilation.append (xoken);
    return Arrays.asList (xompilation.toString ().split ("\\n"));
  }

}
