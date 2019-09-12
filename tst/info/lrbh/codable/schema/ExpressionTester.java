
package info.lrbh.codable.schema;

import static testing.Module.show;
import static testing.Module.panic;

public class ExpressionTester
{

  private static final Expression newby = new Expression ();

  public static void main (String [] strings)
  {
    try {testString ();} catch (AssertionError e) {panic (e);}
    try {testLeft ();} catch (AssertionError e) {panic (e);}
    try {testRight ();} catch (AssertionError e) {panic (e);}
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

  private static void testLeft ()
  {
    Expression expected = null;
    Expression actual = newby.left ();
    assert expected == actual: "not " + expected + " == " + actual;
    show (".");
  }

  private static void testRight ()
  {
    Expression expected = null;
    Expression actual = newby.right ();
    assert expected == actual: "not " + expected + " == " + actual;
    show (".");
  }

  private static void testHashCode ()
  {
    assert newby.hashCode () == 0: "not " + newby.hashCode () + " == 0";
    show (".");
  }

  private static void testEquals ()
  {
    assert ! newby.equals (null): newby + " equals null";
    assert ! newby.equals (0): newby + " equals 0";
    assert newby.equals (new Expression ()): "not " + newby + " equals " + new Expression ();
    show (".");
  }

  private static void testToString ()
  {
    String string = "<" + new String () + " | " + null + " | " + null + ">";
    assert newby.toString ().equals (string): "not " + newby.toString () + " equals " + string;
    show (".");
  }

}
