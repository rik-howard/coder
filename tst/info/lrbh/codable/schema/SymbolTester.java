
package info.lrbh.codable.schema;

import static testing.Module.show;
import static testing.Module.panic;

public class SymbolTester
{

  private static final Symbol newby = new Symbol ();

  public static void main (String [] strings)
  {
    try {testName ();} catch (AssertionError e) {panic (e);}
    try {testValue ();} catch (AssertionError e) {panic (e);}
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

  private static void testValue ()
  {
    String expected = new String ();
    String actual = newby.value ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testHashCode ()
  {
    assert newby.hashCode () == new Symbol ().hashCode (): "not " + newby.hashCode () + " == " + new Symbol ().hashCode ();
    show (".");
  }

  private static void testEquals ()
  {
    assert ! newby.equals (null): newby + " equals null";
    assert ! newby.equals (0): newby + " equals 0";
    assert newby.equals (new Symbol ()): "not " + newby + " equals " + new Symbol ();
    show (".");
  }

  private static void testToString ()
  {
    String expected = "<"
    + new String () + " | "
    + new String () + ">";
    String actual = newby.toString ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

}
