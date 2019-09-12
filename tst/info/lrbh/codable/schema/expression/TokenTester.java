
package info.lrbh.codable.schema.expression;

import static testing.Module.show;
import static testing.Module.panic;

public class TokenTester
{

  private static final Token newby = new Token ();

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
    assert newby.hashCode () == new Token ().hashCode (): "not " + newby.hashCode () + " == " + new Token ().hashCode ();
    show (".");
  }

  private static void testEquals ()
  {
    assert ! newby.equals (null): newby + " equals null";
    assert ! newby.equals (0): newby + " equals 0";
    assert newby.equals (new Token ()): "not " + newby + " equals " + new Token ();
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
