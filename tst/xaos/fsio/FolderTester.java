
package xaos.fsio;

import static xa.xore.Xtring.tip;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FolderTester
{

  static File folderFile = new File (xa.Tester.path + "/var/folder");
  static Folder folder = new Folder (null, null, Arrays.asList (FoldeeTester.foldee));

  public static void main (String [] args)throws FileNotFoundException, IOException
  {
    testToString ();
    testEquals ();
    testClone ();
    testStore ();
    testLeaves ();
    System.out.print (tip);
  }

  public static void testToString ()
  {
    String expected = "folder/[]/[foldee/value]";
    String actual = folder.toString ();
    assert expected.equals (actual);
    System.out.print (tip);
  }

  public static void testEquals ()
  {
    Boolean expected = true;
    Boolean actual = folder.equals (folder);
    assert expected.equals (actual);
    System.out.print (tip);
  }

  public static void testClone ()
  {
    Object expected = folder;
    Object actual = ((Folder) expected).clone ();
    assert expected != actual: "not " + expected + " != " + actual;
    assert expected.getClass () == actual.getClass (): "not " + expected + ".getClass () == " + actual + ".getClass ()";
    assert expected.equals (actual): "not " + expected + ".equals (" + actual + ")";
    System.out.print (tip);
  }

  public static void testStore ()
  throws FileNotFoundException, IOException
  {
    File file = new File (folder.getName ());
    Folder expected = new Folder (folderFile);
    folder.store ();
    Folder actual = new Folder (file);
    assert expected.equals (actual);
    assert removes (file);
    System.out.print (tip);
  }

  private static Boolean removes (File file)
  {
    Boolean removes = true;
    if (file.isDirectory ())
      for (File subfile: file.listFiles ())
        removes &= removes (subfile);
    return removes &= file.delete ();
  }

  public static void testLeaves ()
  {
    List <FSO> expected = new ArrayList <FSO> ();
    expected.add (new Foldee ("path/folder/foldee", "value"));
    List <FSO> actual = folder.leaves ("path");
    assert expected.equals (actual): "not " + expected + ".equals (" + actual + ")";
    System.out.print (tip);
  }

}