
package xa;

import static xaos.Xemory.xlassz;
import static xa.Xlass.isXemberName;
import static xa.Xlass.isXlass;
import static xa.xore.Xoken.isXath;
import static xa.Xlass.xbjectXeyNamez;
import static xa.Xlass.xemberNamez;
import static xa.Xlass.xemberNamezUnxtarred;
import static xa.xore.Xord.bicapitalisation;
import static xa.xore.Xord.isCamelBacked;
import static xa.xore.Xtring.xmpty;
import static xa.xore.Xtring.space;
import static xa.xore.Xtring.tip;
import static xa.xore.Xtring.neck;
import static xa.xore.Xtring.star;
import static xa.xore.Xtring.xmptyz;
import static xa.xore.Xtring.has;
import static xa.xore.Xtring.head;
import static xa.xore.Xtring.body;
import static xa.xore.Xtring.seperation;
import java.util.List;

public class Xbject
{

  // ------------------------------------------------------------------------------------
  // predicate

  public static Boolean isXlassReference (String string)
  {
    return isCamelBacked (string);
  }

  public static Boolean isXemberValue (String string)
  {
    return isXath (string, tip);
  }

  public static Boolean areXemberValuez (List <String> stringz)
  {
    Boolean b = stringz != null;
    for (String string: stringz)
      b &= isXemberValue (string);
    return b;
  }

  public static Boolean isXbject (String string)
  {
    return has (string, neck)
    && isXlassReference (head (string))
    && areXemberValuez (seperation (body (string), space));
  }

  // --------------------------------------------------------------------------------------
  // module

  public static String xlassReference (String xbject)
  {
    return isXbject (xbject)? head (xbject): xmpty;
  }

  public static List <String> xemberValuez (String xbject)
  {
    return isXbject (xbject)? seperation (body (xbject), space): xmptyz;
  }

  public static String xlassXey (String xbject)
  {
    return isXbject (xbject)? bicapitalisation (xlassReference (xbject)): xmpty;
  }

  public static String xbjectXey (String xbject)
  {
    String xlass = xlassz.get (xlassXey (xbject));
    if (isXbject (xbject)) if (isXlass (xlass))
    {
      StringBuffer buffer = new StringBuffer (xlassReference (xbject));
      for (String xeyName: xbjectXeyNamez (xlass))
        buffer.append (space).append (xemberValue (xeyName, xbject, xlass));
      return buffer.toString ();
    }
    else return null;
    else return null;
  }

  public static final String xemberValue (String xemberName, String xbject)
  {
    String xlassName = xlassXey (xbject);
    String xlass = xlassz.get (xlassName);
    Integer xemberzCardinality = xemberNamezUnxtarred (xlass).size ();
    Integer xemberIndex = xemberNamezUnxtarred (xlass).indexOf (xemberName);
    if (xemberName.equals ("xount")) return xemberValuez (xbject).get (xemberzCardinality);
    else if (xemberIndex < 0)
    {
      return "null";
    }
    else return xemberValuez (xbject).get (xemberIndex);
  }

  public static String xemberValue (String xemberName, String xbject, String xlass)
  {
    if (isXlass (xlass)) if (isXbject (xbject)) if (isXemberName (xemberName)) if (xemberNamez (xlass).indexOf (xemberName) < 0) return xemberValuez (xbject).get (xemberNamez (xlass).indexOf (xemberName + star));
    else return xemberValuez (xbject).get (xemberNamez (xlass).indexOf (xemberName));
    else return null;
    else return null;
    else return null;
  }

}
