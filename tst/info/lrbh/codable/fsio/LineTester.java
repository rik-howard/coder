
package info.lrbh.codable.fsio;

import static testing.Module.show;
import static testing.Module.panic;

public class LineTester
{

  private static final Line newby = new Line ();

  public static void main (String [] strings)
  {
    try {testString ();} catch (AssertionError e) {panic (e);}
    try {testHashCode ();} catch (AssertionError e) {panic (e);}
    try {testEquals ();} catch (AssertionError e) {panic (e);}
    try {testToString ();} catch (AssertionError e) {panic (e);}
    show (".");
  }

  private static void testString ()
  {
    String expected = new String ();
    String actual = newby.string ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testHashCode ()
  {
    assert newby.hashCode () == new Line ().hashCode (): "not " + newby.hashCode () + " == " + new Line ().hashCode ();
    show (".");
  }

  private static void testEquals ()
  {
    assert ! newby.equals (null): newby + " equals null";
    assert ! newby.equals (0): newby + " equals 0";
    assert newby.equals (new Line ()): "not " + newby + " equals " + new Line ();
    show (".");
  }

  private static void testToString ()
  {
    String expected = "<"
    + new String () + ">";
    String actual = newby.toString ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

}
