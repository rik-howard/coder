
package xa.xemplate;

import static xa.xore.Xtring.xmpty;
import static xa.xore.Xtring.tip;
import static xa.xemplate.Xarser.xarsing;
import static xa.xemplate.Xinker.xinking;
import static xa.xemplate.Xleaner.xleaning;
import static xa.xemplate.Xokeniser.xokenisation;
import static xa.xemplate.Xompiler.xompilation;
import static xaos.Xemory.xbjectz;
import java.util.Arrays;
import java.util.List;
import static xaos.Xemory.xlassz;

public class Xser
{

  public static List <String> xemplate = Arrays.asList
  (
    ".            | Hello, World!",
    ".            | My name is [me.name].",
    ".[thou:thouz]| Thy name is [thou.name].",
    ".[]          | Is our name [:our.name.][our.name][:]The Unnamed[]?",
    tip
  );
  public static List <String> xleanment = Arrays.asList
  (
    "Hello, World!",
    "My name is [me.name].",
    "[thou:thouz]Thy name is [thou.name].",
    "[]Is our name [:our.name.][our.name][:]The Unnamed[]?",
    xmpty
  );
  public static List <String> xokenment = Arrays.asList
  (
    "Hello, World!\nMy name is ",
    "[me.name]",
    ".\n",
    "[thou:thouz]",
    "Thy name is ",
    "[thou.name]",
    ".\n",
    "[]",
    "Is our name ",
    "[:our.name.]",
    "[our.name]",
    "[:]",
    "The Unnamed",
    "[]",
    "?\n"
  );
  public static List <String> xarsement = Arrays.asList
  (
    "Hello, World!\nMy name is ",
    "[me.name] me: Rik",
    ".\n",
    "Thy name is ",
    "[thou.name] thou: Tom",
    ".\n",
    "Thy name is ",
    "[thou.name] thou: Harry",
    ".\n"
  );
  public static List <String> xinkment = Arrays.asList
  (
    "Hello, World!\nMy name is ",
    "Rik",
    ".\n",
    "Thy name is ",
    "Tom",
    ".\n",
    "Thy name is ",
    "Harry",
    ".\n"
  );

  static
  {
    xlassz.put ("Me", "Me: name");
    xlassz.put ("Thou", "Thou: name*");
    xlassz.put ("Our", "Our: name");
    xbjectz.put ("me", "me: Rik");
    xbjectz.put ("thou Tom", "thou: Tom");
    xbjectz.put ("thou Harry", "thou: Harry");
    xbjectz.put ("our", "our: Hurry");
  }

  public static void main (String [] args)
  {
    log
    (
      xompilation
      (
        xinking
        (
          xarsing
          (
            xokenisation
            (
              xleaning
              (
                xemplate
              )
            )
          )
        )
      )
    );
  }

  protected static final void log (Object message) {System.out.println (message);}
  protected static final void log (List <String> stringz) {for (String string: stringz) System.out.println (string);}

}
