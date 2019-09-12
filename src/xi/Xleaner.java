
package xi;

import java.util.Arrays;
import java.util.List;

public class Xleaner
{

  //public static Boolean hasXlean (String string)
  //{
  //  return string.matches ("[^\\|]+");
  //}

  public static List <String> xleaning (List <String> stringz)
  {
    StringBuffer buffer = new StringBuffer ();
    for (String string: stringz)
      if (string.startsWith ("."))
        if (buffer.length () == 0) buffer.append (dedotted (string));
        else buffer.append ("\n").append (dedotted (string));
    return Arrays.asList (buffer.toString ().split ("\\n"));
  }

  private static String dedotted (String string)
  {
    return string.substring (1).trim ().replaceAll ("\\ *\\|\\ ?", "");
  }

}
