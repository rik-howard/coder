
package xa;

import static xaos.Xystem.xlassz;
import static xa.Xord.isXord;
import static xa.Xord.xtarred;
import static xa.Xord.isCamelBacked;
import static xa.Xord.bicapitalisation;
import static xa.Xtring.body;
import static xa.Xtring.hasNeck;
import static xa.Xtring.head;
import static xa.Xtring.space;
import static xa.Xtring.spaceSeperation;
import static xa.Xlass.isXemberName;
import static xa.Xlass.isXlass;
import static xa.Xlass.xbjectXeyNamez;
import static xa.Xlass.xemberNamez;
import static xa.Xlass.xemberNamezUnxtarred;
import java.util.List;

public class Xbject
{

  // ------------------------------------------------------------------------------------ predicate

  public static Boolean isXlassReference (String string) {return isCamelBacked (string);}
  public static Boolean isXemberValue (String string) {return isXord (string);}
  public static Boolean areXemberValuez (List <String> stringz)
  {
    Boolean b = true;
    if (stringz == null) b = false;
    else for (String string: stringz) b &= isXemberValue (string);
    return b;
  }
  public static Boolean isXbject (String string) {return hasNeck (string) && isXlassReference (head (string)) && areXemberValuez (spaceSeperation (body (string)));}

  // -------------------------------------------------------------------------------------- module

  public static String xlassReference (String xbject)
  {
    if (isXbject (xbject)) return head (xbject);
    else return null;
  }

  public static List <String> xemberValuez (String xbject)
  {
    if (isXbject (xbject)) return spaceSeperation (body (xbject));
    else return null;
  }

  public static String xlassName (String xbject)
  {
    if (isXbject (xbject)) return bicapitalisation (xlassReference (xbject));
    else return null;
  }

  public static final String xemberValue (String xemberName, String xbject)
  {
    String xlassName = xlassName (xbject);
    String xlass = xlassz.get (xlassName);
    Integer xemberzCardinality = xemberNamezUnxtarred (xlass).size ();
    Integer xemberIndex = xemberNamezUnxtarred (xlass).indexOf (xemberName);
    if (xemberName.equals ("xount")) return xemberValuez (xbject).get (xemberzCardinality);
    else if (xemberIndex < 0)
    {
      System.out.println 
      (
        xemberName + " <is nXt a xember of> " + xbject + ", <which is a> " + xlass
      );
      return null;
    }
    else return xemberValuez (xbject).get (xemberIndex);
  }

  public static String xbjectXey (String xbject, String xlass)
  {
    if (isXbject (xbject))
      if (isXlass (xlass))
      {
        StringBuffer buffer = new StringBuffer (xlassReference (xbject));
        for (String xeyName: xbjectXeyNamez (xlass)) buffer.append (space).append (xemberValue (xeyName, xbject, xlass));
        return buffer.toString ();
      }
      else return null;
    else return null;
  }

  public static String xemberValue (String xemberName, String xbject, String xlass)
  {
    if (isXlass (xlass))
      if (isXbject (xbject))
        if (isXemberName (xemberName))
          if (xemberNamez (xlass).indexOf (xemberName) < 0)
            return xemberValuez (xbject).get (xemberNamez (xlass).indexOf (xtarred (xemberName)));
          else return xemberValuez (xbject).get (xemberNamez (xlass).indexOf (xemberName));
        else return null;
      else return null;
    else return null;
  }

}
