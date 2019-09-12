
package info.lrbh.codable.fsio;

import static testing.Module.show;
import static testing.Module.panic;
import java.util.List;
import java.util.ArrayList;

public class FoldeeTester
{

  private static final Foldee newby = new Foldee ();

  public static void main (String [] strings)
  {
    try {testName ();} catch (AssertionError e) {panic (e);}
    try {testLines ();} catch (AssertionError e) {panic (e);}
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

  private static void testLines ()
  {
    List <Line> expected = new ArrayList <Line> ();
    List <Line> actual = newby.lines ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testHashCode ()
  {
    assert newby.hashCode () == new Foldee ().hashCode (): "not " + newby.hashCode () + " == " + new Foldee ().hashCode ();
    show (".");
  }

  private static void testEquals ()
  {
    assert ! newby.equals (null): newby + " equals null";
    assert ! newby.equals (0): newby + " equals 0";
    assert newby.equals (new Foldee ()): "not " + newby + " equals " + new Foldee ();
    show (".");
  }

  private static void testToString ()
  {
    String expected = "<"
    + new String () + " | "
    + new ArrayList <Line> () + ">";
    String actual = newby.toString ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

}
