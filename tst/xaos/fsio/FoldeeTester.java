
package xaos.fsio;

import static xa.xore.Xtring.tip;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FoldeeTester
{

  static File foldeeFile = new File (xa.Tester.path + "/var/folder/foldee");
  static Foldee foldee = new Foldee ("foldee", "value");

  public static void main (String [] args)throws FileNotFoundException, IOException
  {
    testToString ();
    testEquals ();
    testClone ();
    //testStore ();
    testLeaf ();
    System.out.print (tip);
  }

  public static void testToString ()
  {
    String expected = "foldee/value";
    String actual = foldee.toString ();
    assert expected.equals (actual);
    System.out.print (tip);
  }

  public static void testEquals ()
  {
    Boolean expected = true;
    Boolean actual = foldee.equals (foldee);
    assert expected.equals (actual);
    System.out.print (tip);
  }

  public static void testClone ()
  {
    Object expected = foldee;
    Object actual = ((Foldee) expected).clone ();
    assert expected != actual: "not " + expected + " != " + actual;
    assert expected.getClass () == actual.getClass (): "not " + expected + ".getClass () == " + actual + ".getClass ()";
    assert expected.equals (actual): "not " + expected + ".equals (" + actual + ")";
    System.out.print (tip);
  }

  public static void testStore ()
  throws FileNotFoundException, IOException
  {
    File file = new File (foldee.getName ());
    Foldee expected = new Foldee (foldeeFile);
    foldee.store ();
    Foldee actual = new Foldee (file);
    assert expected.equals (actual);
    assert file.delete ();
    System.out.print (tip);
  }

  private static void testLeaf ()
  {
    FSO expected = new Foldee ("path/foldee", "value");
    FSO actual = foldee.leaf ("path");
    assert expected.equals (actual): "not " + expected + ".equals (" + actual + ")";
    System.out.print (tip);
  }

}
