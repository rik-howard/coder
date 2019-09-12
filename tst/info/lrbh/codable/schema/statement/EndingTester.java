
package info.lrbh.codable.schema.statement;

import static testing.Module.show;
import static testing.Module.panic;
import info.lrbh.codable.schema.Statement;

public class EndingTester
{

  private static final Ending newby = new Ending ();

  public static void main (String [] strings)
  {
    try {testHashCode ();} catch (AssertionError e) {panic (e);}
    try {testEquals ();} catch (AssertionError e) {panic (e);}
    try {testSuperString ();} catch (AssertionError e) {panic (e);}
    try {testToString ();} catch (AssertionError e) {panic (e);}
    show (".");
  }

  private static void testHashCode ()
  {
    assert newby.hashCode () == new Ending ().hashCode (): "not " + newby.hashCode () + " == " + new Ending ().hashCode ();
    show (".");
  }

  private static void testEquals ()
  {
    assert ! newby.equals (null): newby + " equals null";
    assert ! newby.equals (0): newby + " equals 0";
    assert newby.equals (new Ending ()): "not " + newby + " equals " + new Ending ();
    show (".");
  }

  private static void testSuperString ()
  {
    String expected = new Statement ().toString ();
    String actual = newby.superString ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testToString ()
  {
    String expected = "<"
    + newby.superString ()
    + ">";
    String actual = newby.toString ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

}
