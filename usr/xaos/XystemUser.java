
package xaos;

import static xa.xemplate.Xarser.xarsing;
import static xa.xemplate.Xinker.xinking;
import static xa.xemplate.Xleaner.xleaning;
import static xa.xemplate.Xokeniser.xokenisation;
import static xa.xemplate.Xompiler.xompilation;
import xaos.Xystem;
import xaos.fsio.Foldee;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class XystemUser
{

  public static void main (String [] args)
  throws FileNotFoundException, IOException
  {
    //Xystem.load (new Foldee (new File ("/home/lyndon/work/pjt/lrbh-xoder/var/hello-world")));
    Xystem.load (new Foldee (new File ("/home/lyndon/work/pjt/lrbh-xoder/var/sql-table")));
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
