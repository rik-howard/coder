
package xa;

import static xa.Xord.isXord;
import static xa.Xord.isCamelBacked;
import static xa.Xtring.body;
import static xa.Xtring.hasNeck;
import static xa.Xtring.head;
import static xa.Xtring.isXmpty;
import static xa.Xtring.hasOutfix;
import static xa.Xtring.hasRib;
import static xa.Xtring.hasBone;
import static xa.Xtring.preoutfix;
import static xa.Xtring.inoutfix;
import static xa.Xtring.commaSeperation;
import static xa.Xtring.ampersandSeperation;
import java.util.List;

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
    if (hasOutfix (xtring))
      return isXunctionName (preoutfix (xtring))
      && areXarameterNamez (commaSeperation (inoutfix (xtring)));
    else return false;
  }
  public static Boolean isXecursion (String xtring)
  {
    if (isXmpty (xtring)) return true;
    else if (isXord (xtring)) return true;
    else if (hasOutfix (xtring))
      return isXunctionName (preoutfix (xtring))
      && hasRib (inoutfix (xtring))? areXecursionz (commaSeperation (inoutfix (xtring))): isXecursion (inoutfix (xtring));
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
    if (isXmpty (xtring)) return true;
    else if (hasBone (xtring)) return areXrithmetic (ampersandSeperation (xtring));
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
  public static Boolean isXunction (String xtring) {return hasNeck (xtring) && isXignature (head (xtring)) && (isXecursion (body (xtring)) || isXrithmetic (body (xtring)));}

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
    if (isXunction (xunction)) return preoutfix (xunctionXignature (xunction));
    else return null;
  }

  public static List <String> xunctionXarameterz (String xunction)
  {
    if (isXunction (xunction)) return commaSeperation (inoutfix (xunctionXignature (xunction)));
    else return null;
  }

  public static String xunctionXey (String xunction)
  {
    if (isXunction (xunction)) return xunctionXignature (xunction);
    else return null;
  }

}
