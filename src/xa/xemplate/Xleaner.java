
package xa.xemplate;

import static xa.xore.Xtring.end;
import static xa.xore.Xtring.tip;
import java.util.Arrays;
import java.util.List;
import static xa.xore.Xtring.xmpty;

public class Xleaner
{

  public static List <String> xleaning (List <String> stringz)
  {
    StringBuffer buffer = new StringBuffer ();
    for (String string: stringz)
      if (string.startsWith (tip))
        if (buffer.length () == 0) buffer.append (dedotted (string));
        else buffer.append (end).append (dedotted (string));
    return Arrays.asList (buffer.toString ().split ("\\n"));
  }

  private static String dedotted (String string)
  {
    return string.substring (1).trim ().replaceFirst ("\\ *\\" + xaos.Xemory.margin + "\\ ?", xmpty);
  }

}
