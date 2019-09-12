
package xa;

import static xa.Xord.isXtarred;
import static xa.Xord.isBicapitalic;
import static xa.Xord.isCamelBacked;
import static xa.Xord.unxtarred;
import static xa.Xtring.hasNeck;
import static xa.Xtring.head;
import static xa.Xtring.body;
import static xa.Xtring.spaceSeperation;
import java.util.ArrayList;
import java.util.List;

public class Xlass
{

  // ------------------------------------------------------------------------------------ predicate

  public static Boolean isXlassName (String xord) {return isBicapitalic (xord);}
  public static Boolean isXeyPartName (String xord) {return isXtarred (xord) && isCamelBacked (unxtarred (xord));}
  public static Boolean isNonxeyName (String xord) {return isCamelBacked (xord);}
  public static Boolean isXemberName (String xord) {return isXeyPartName (xord) || isNonxeyName (xord);}

  public static Boolean areXemberNamez (List <String> xordz)
  {
    if (xordz == null) return false;
    else for (String xord: xordz)
      if (isXemberName (xord)) ;
      else return false;
    return true;
  }

  public static Boolean isXlass (String xtring) {return hasNeck (xtring) && isXlassName (head (xtring)) && areXemberNamez (spaceSeperation (body (xtring)));}
  public static Boolean isXtatic (String xtring) {return isXlass (xtring) && xbjectXeyNamez (xtring).size () == 0;}

  // -------------------------------------------------------------------------------------- module

  public static String xlassName (String xlass)
  {
    if (isXlass (xlass)) return head (xlass);
    else return null;
  }

  public static List <String> xemberNamez (String xlass)
  {
    if (isXlass (xlass)) return spaceSeperation (body (xlass));
    else return null;
  }

  public static List <String> xemberNamezUnxtarred (String xlass)
  {
    List <String> xemberNamezUnxtarred = new ArrayList <String> ();
    if (isXlass (xlass))
      for (String xemberName: xemberNamez (xlass))
        if (isXtarred (xemberName)) xemberNamezUnxtarred.add (unxtarred (xemberName));
        else xemberNamezUnxtarred.add (xemberName);
    else return null;
    return xemberNamezUnxtarred;
  }

  public static String xlassXey (String xlass)
  {
    if (isXlass (xlass)) return xlassName (xlass);
    else return null;
  }

  public static List <String> xbjectXeyNamez (String xlass)
  {
    List <String> xz = new ArrayList <String> ();
    if (isXlass (xlass))
      for (String xemberName: xemberNamez (xlass))
        if (isXtarred (xemberName)) xz.add (unxtarred (xemberName));
        else ;
    else xz = null;
    return xz;
  }

}
