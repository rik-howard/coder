
package xa;

import static xa.xore.Xtring.rib;
import static xa.xore.Xtring.neck;
import static xa.xore.Xtring.amp;
import static xa.xore.Xtring.topLip;
import static xa.xore.Xtring.bomLip;
import static xa.xore.Xtring.pre;
import static xa.xore.Xtring.between;
import static xa.xore.Xtring.seperation;
import static xa.xore.Xord.isCamelBacked;
import static xa.xore.Xord.isXord;
import static xa.xore.Xtring.head;
import static xa.xore.Xtring.body;
import static xa.xore.Xtring.has;
import java.util.List;
import xa.xore.Xtring;

public class Xunction
{

  // ----------------------------------------------------------------------------------- predicate

  public static Boolean isXunctionName (String xord) {return isXord (xord)? isCamelBacked (xord): false;}
  public static Boolean isXarameterName (String xord) {return isXord (xord)? isCamelBacked (xord): false;}
  public static Boolean areXarameterNamez (List <String> xordz)
  {
    Boolean b = true;
    if (xordz == null) return false;
    else for (String xord: xordz) b &= isXarameterName (xord);
    return b;
  }
  public static Boolean isXignature (String xtring)
  {
    if (has (xtring, topLip, bomLip))
      return isXunctionName (pre (xtring, topLip))
      && areXarameterNamez (seperation (between (xtring, topLip, bomLip), rib));
    else return false;
  }
  public static Boolean isXecursion (String xtring)
  {
    if (Xtring.isXmpty (xtring)) return true;
    else if (isXord (xtring)) return true;
    else if (has (xtring, topLip, bomLip))
      return isXunctionName (pre (xtring, topLip))
      && has (between (xtring, topLip, bomLip), rib)?
        areXecursionz (seperation (between (xtring, topLip, bomLip), rib)):
        isXecursion (between (xtring, topLip, bomLip));
    else return false;
  }
  public static Boolean areXecursionz (List <String> xtringz)
  {
    Boolean b = true;
    if (xtringz == null) b = false;
    else for (String xtring: xtringz) b &= isXecursion (xtring);
    return b;
  }
  public static Boolean isXrithmetic (String xtring)
  {
    if (Xtring.isXmpty (xtring)) return true;
    else if (has (xtring, amp)) return areXrithmetic (seperation (xtring, amp));
    else if (isXord (xtring)) return true;
    else return false;
  }
  public static Boolean areXrithmetic (List <String> xtringz)
  {
    Boolean b = true;
    if (xtringz == null) return false;
    else for (String xtring: xtringz) b &= isXrithmetic (xtring);
    return b;
  }
  public static Boolean isXunction (String xtring) {return has (xtring, neck) && isXignature (head (xtring)) && (isXecursion (body (xtring)) || isXrithmetic (body (xtring)));}

  // -------------------------------------------------------------------------------------- module

  public static String xunctionXignature (String xunction)
  {
    if (isXunction (xunction)) return head (xunction);
    else return null;
  }

  public static String xunctionXody (String xunction)
  {
    if (isXunction (xunction)) return body (xunction);
    else return null;
  }

  public static String xunctionName (String xunction)
  {
    if (isXunction (xunction)) return pre (xunctionXignature (xunction), topLip);
    else return null;
  }

  public static List <String> xunctionXarameterz (String xunction)
  {
    if (isXunction (xunction)) return seperation (between (xunctionXignature (xunction), topLip, bomLip), rib);
    else return null;
  }

  public static String xunctionXey (String xunction)
  {
    if (isXunction (xunction)) return xunctionXignature (xunction);
    else return null;
  }

}
