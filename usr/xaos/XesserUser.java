
package xaos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import static xa.Tester.path;

public class XesserUser
{

  private static String sourceTemplateName = null;
  private static List <String> sourceDataNamez = null;
  private static String targetName = null;

  public static void main (String [] args)
  throws FileNotFoundException, IOException
  {
    //sourceTemplateName = path + "/var/empty";
    //sourceTemplateName = path + "/var/hello-world";
    //sourceTemplateName = path + "/var/table.sql";
    //sourceTemplateName = path + "/var/[tb:tbz][tb.name].sql[]";
    //sourceTemplateName = path + "/var/${c:classz}${c.name}.java${}";
    //
    //sourceTemplateName = path + "/var/[pjt.name]";
    //sourceDataNamez = Arrays.asList (path + "/var/pjt-0", path + "/var/pjt-1");
    //targetName = path + "/tmp";
    //
    sourceTemplateName = path + "/var/${pkg:pkgz}${pkg.path}${}";
    sourceDataNamez = Arrays.asList (path + "/var/pkgz");
    use (args);
  }

  private static void use (String [] args)
  throws FileNotFoundException, IOException
  {
    Integer i = 0;
    args = new String [argsLen ()];
    args [i++] = "-xode";
    args [i++] = "-from";
    args [i++] = sourceTemplateName;
    if (sourceDataNamez == null) ;
    else
    {
      args [i++] = "-with";
      for (String sourceDataName: sourceDataNamez)
        args [i++] = sourceDataName;
    }
    if (targetName == null) ;
    else
    {
      args [i++] = "-to";
      args [i++] = targetName;
    }
    Xesser.main (args);
  }
  
  private static final Integer argsLen ()
  {
    return 3 + (sourceDataNamez == null? 0: 1 + sourceDataNamez.size ()) + (targetName == null? 0: 2);
  }

}
