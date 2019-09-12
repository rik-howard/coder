
package xa;

import java.io.FileNotFoundException;
import java.io.IOException;
import xa.xore.XtringTester;
import xaos.fsio.FoldeeTester;
import xaos.fsio.FolderTester;

public class Tester
{

  public static final String path = "/home/lyndon/work/pjt/lrbh-xoder";

  public static void main (String [] args)
  throws FileNotFoundException, IOException
  {
    FoldeeTester.main (args);
    FolderTester.main (args);
    XtringTester.main (args);
    XlassTester.main (args);
  }

}
