
package info.lrbh.codable.schema.template;

import static testing.Module.show;
import static testing.Module.panic;

public class CompilementTester
{

  private static final Compilement newby = new Compilement ();

  public static void main (String [] strings)
  {
    try {testValue ();} catch (AssertionError e) {panic (e);}
    try {testHashCode ();} catch (AssertionError e) {panic (e);}
    try {testEquals ();} catch (AssertionError e) {panic (e);}
    try {testToString ();} catch (AssertionError e) {panic (e);}
    show (".");
  }

  private static void testValue ()
  {
    String expected = new String ();
    String actual = newby.value ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testHashCode ()
  {
    assert newby.hashCode () == new Compilement ().hashCode (): "not " + newby.hashCode () + " == " + new Compilement ().hashCode ();
    show (".");
  }

  private static void testEquals ()
  {
    assert ! newby.equals (null): newby + " equals null";
    assert ! newby.equals (0): newby + " equals 0";
    assert newby.equals (new Compilement ()): "not " + newby + " equals " + new Compilement ();
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
