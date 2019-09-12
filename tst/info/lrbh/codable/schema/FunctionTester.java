
package info.lrbh.codable.schema;

import static testing.Module.show;
import static testing.Module.panic;

public class FunctionTester
{

  private static final Function newby = new Function ();

  public static void main (String [] strings)
  {
    try {testSignature ();} catch (AssertionError e) {panic (e);}
    try {testBody ();} catch (AssertionError e) {panic (e);}
    try {testHashCode ();} catch (AssertionError e) {panic (e);}
    try {testEquals ();} catch (AssertionError e) {panic (e);}
    try {testToString ();} catch (AssertionError e) {panic (e);}
    show (".");
  }

  private static void testSignature ()
  {
    Expression expected = new Expression ();
    Expression actual = newby.signature ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testBody ()
  {
    Expression expected = new Expression ();
    Expression actual = newby.body ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testHashCode ()
  {
    assert newby.hashCode () == new Function ().hashCode (): "not " + newby.hashCode () + " == " + new Function ().hashCode ();
    show (".");
  }

  private static void testEquals ()
  {
    assert ! newby.equals (null): newby + " equals null";
    assert ! newby.equals (0): newby + " equals 0";
    assert newby.equals (new Function ()): "not " + newby + " equals " + new Function ();
    show (".");
  }

  private static void testToString ()
  {
    String expected = "<"
    + new Expression () + " | "
    + new Expression () + ">";
    String actual = newby.toString ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

}
