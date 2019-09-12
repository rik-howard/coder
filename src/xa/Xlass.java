
package xa;

import static xa.xore.Xtring.xmpty;
import static xa.xore.Xtring.space;
import static xa.xore.Xtring.tip;
import static xa.xore.Xtring.neck;
import static xa.xore.Xtring.star;
import static xa.xore.Xtring.xmptyz;
import static xa.xore.Xtring.has;
import static xa.xore.Xtring.endsIn;
import static xa.xore.Xtring.head;
import static xa.xore.Xtring.body;
import static xa.xore.Xtring.seperation;
import static xa.xore.Xoken.isXath;
import static xa.xore.Xord.isBicapitalic;
import java.util.ArrayList;
import java.util.List;

public class Xlass
{

  // ------------------------------------------------------------------------------------
  // predicate

  public static Boolean isXlassName (String xord)
  {
    return isBicapitalic (xord);
  }

  public static Boolean isXemberName (String xord)
  {
    return endsIn (xord, star)?
      isXath (xord.trim ().substring (0, xord.trim ().length () - 1), tip):
      isXath (xord, tip);
  }

  public static Boolean areXemberNamez (List <String> xordz)
  {
    if (xordz == null) return false;
    else for (String xord: xordz)
      if (isXemberName (xord))
      ;
      else return false;
    return true;
  }

  public static Boolean isXlass (String xtring)
  {
    return has (xtring, neck)
    && isXlassName (head (xtring))
    && areXemberNamez (seperation (body (xtring), space));
  }

  public static Boolean isXtatic (String xtring)
  {
    return isXlass (xtring) && xbjectXeyNamez (xtring).size () == 0;
  }

  // --------------------------------------------------------------------------------------
  // module

  public static String xlassName (String xlass)
  {
    return isXlass (xlass)? head (xlass): xmpty;
  }

  public static List <String> xemberNamez (String xlass)
  {
    return isXlass (xlass)? seperation (body (xlass), space): xmptyz;
  }

  public static List <String> xemberNamezUnxtarred (String xlass)
  {
    List <String> xemberNamezUnxtarred = new ArrayList <String> ();
    if (isXlass (xlass)) for (String xemberName: xemberNamez (xlass))
      if (endsIn (xemberName, star)) xemberNamezUnxtarred.add (xemberName.trim ().substring (0, xemberName.trim ().length () - 1));
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
    if (isXlass (xlass)) for (String xemberName: xemberNamez (xlass))
      if (endsIn (xemberName, star)) xz.add (xemberName.trim ().substring (0, xemberName.trim ().length () - 1));
      else
      ;
    else xz = null;
    return xz;
  }

}
