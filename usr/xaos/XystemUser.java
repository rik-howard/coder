
package xaos;

import static xi.Xarser.xarsing;
import static xi.Xinker.xinking;
import static xi.Xleaner.xleaning;
import static xi.Xokeniser.xokenisation;
import static xi.Xompiler.xompilation;
import xaos.Xystem;
import info.lrbh.fsio.Foldee;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class XystemUser
{

  public static void main (String [] args)
  throws FileNotFoundException, IOException
  {
    Xystem.load (new Foldee (new File ("/home/lyndon/work/pjt/lrbh-xoder/var/hello-world")));
    log (Xystem.dump ());
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
                Xystem.xemplate
              )
            )
          )
        )
      )
    );
  }

  private static final void log (List <String> stringz) {for (String string: stringz) System.out.println (string);}

}
