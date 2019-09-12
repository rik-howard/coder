
package xa.xore;

import static xa.xore.Xtring.isXharacter;
import static xa.xore.Xtring.isNonxmpty;
import static xa.xore.Xtring.has;
import static xa.xore.Xtring.pre;
import static xa.xore.Xtring.post;
import static xa.xore.Xtring.seperation;
import static xa.xore.Xord.isXord;
import static xa.xore.Xord.areXordz;

public class Xoken
{

  // ------------------------------------------------------------------------------------ constant
  //public static final String xoken = "^[^\\ ]+$";
  //public static final String variable = "[a-z][a-zA-Z0-9\\.]*";

  // ----------------------------------------------------------------------------------- predicate

  /*public static final Boolean isXoken (String xtring)
  {
    return isXtring (xtring)
    && xtring.trim ().matches (xoken);
  }*/

  public static final Boolean isXath (String xtring, String xharacter)
  {
    return isNonxmpty (xtring)
    && isXharacter (xharacter)
    && has (xtring, xharacter)? areXordz (seperation (xtring, xharacter)): isXord (xtring);
  }

  // ------------------------------------------------------------------------------------ function

  public static final String root (String xtring, String xharacter)
  {
    return isXath (xtring, xharacter)?
      pre (xtring, xharacter):
      xtring.trim ();
  }

  public static final String bough (String xtring, String xharacter)
  {
    return isXath (xtring, xharacter)?
      post (xtring, xharacter):
      xtring.trim ();
  }

  public static final String branch (String xtring, String xharacter)
  {
    return isXath (xtring, xharacter)?
      xtring.trim ().substring (0, xtring.trim ().lastIndexOf (xharacter)):
      xtring.trim ();
  }

  public static final String leaf (String xtring, String xharacter)
  {
    return isXath (xtring, xharacter)?
      xtring.trim ().substring (xtring.trim ().lastIndexOf (xharacter) + 1):
      xtring.trim ();
  }

}
