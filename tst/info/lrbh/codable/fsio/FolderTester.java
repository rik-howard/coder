
package info.lrbh.codable.fsio;

import static testing.Module.show;
import static testing.Module.panic;
import java.util.List;
import java.util.ArrayList;

public class FolderTester
{

  private static final Folder newby = new Folder ();

  public static void main (String [] strings)
  {
    try {testName ();} catch (AssertionError e) {panic (e);}
    try {testFolders ();} catch (AssertionError e) {panic (e);}
    try {testFoldees ();} catch (AssertionError e) {panic (e);}
    try {testHashCode ();} catch (AssertionError e) {panic (e);}
    try {testEquals ();} catch (AssertionError e) {panic (e);}
    try {testToString ();} catch (AssertionError e) {panic (e);}
    show (".");
  }

  private static void testName ()
  {
    String expected = new String ();
    String actual = newby.name ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testFolders ()
  {
    List <Folder> expected = new ArrayList <Folder> ();
    List <Folder> actual = newby.folders ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testFoldees ()
  {
    List <Foldee> expected = new ArrayList <Foldee> ();
    List <Foldee> actual = newby.foldees ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testHashCode ()
  {
    assert newby.hashCode () == new Folder ().hashCode (): "not " + newby.hashCode () + " == " + new Folder ().hashCode ();
    show (".");
  }

  private static void testEquals ()
  {
    assert ! newby.equals (null): newby + " equals null";
    assert ! newby.equals (0): newby + " equals 0";
    assert newby.equals (new Folder ()): "not " + newby + " equals " + new Folder ();
    show (".");
  }

  private static void testToString ()
  {
    String expected = "<"
    + new String () + " | "
    + new ArrayList <Folder> () + " | "
    + new ArrayList <Foldee> () + ">";
    String actual = newby.toString ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

}
