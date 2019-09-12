
package info.lrbh.codable.schema.template.parsement;

import static testing.Module.show;
import static testing.Module.panic;
import info.lrbh.codable.schema.template.Parsement;

public class VariableTester
{

  private static final Variable newby = new Variable ();

  public static void main (String [] strings)
  {
    try {testQualifier ();} catch (AssertionError e) {panic (e);}
    try {testIdentifier ();} catch (AssertionError e) {panic (e);}
    try {testHashCode ();} catch (AssertionError e) {panic (e);}
    try {testEquals ();} catch (AssertionError e) {panic (e);}
    try {testSuperString ();} catch (AssertionError e) {panic (e);}
    try {testToString ();} catch (AssertionError e) {panic (e);}
    show (".");
  }

  private static void testQualifier ()
  {
    String expected = new String ();
    String actual = newby.qualifier ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testIdentifier ()
  {
    String expected = new String ();
    String actual = newby.identifier ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testHashCode ()
  {
    assert newby.hashCode () == new Variable ().hashCode (): "not " + newby.hashCode () + " == " + new Variable ().hashCode ();
    show (".");
  }

  private static void testEquals ()
  {
    assert ! newby.equals (null): newby + " equals null";
    assert ! newby.equals (0): newby + " equals 0";
    assert newby.equals (new Variable ()): "not " + newby + " equals " + new Variable ();
    show (".");
  }

  private static void testSuperString ()
  {
    String expected = new Parsement ().toString ();
    String actual = newby.superString ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testToString ()
  {
    //String string = "<" + new String () + " | " + new String () + ">";
    String expected = "<" + new String () + " | " + new String () + " | " + newby.superString () + ">";
    String actual = newby.toString ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

}
