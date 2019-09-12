
package info.lrbh.codable.schema.template;

import static testing.Module.show;
import static testing.Module.panic;

public class CleanmentTester
{

  private static final Cleanment newby = new Cleanment ();

  public static void main (String [] strings)
  {
    try {testString ();} catch (AssertionError e) {panic (e);}
    try {testOpener ();} catch (AssertionError e) {panic (e);}
    try {testCloser ();} catch (AssertionError e) {panic (e);}
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

  private static void testOpener ()
  {
    String expected = new String ();
    String actual = newby.opener ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testCloser ()
  {
    String expected = new String ();
    String actual = newby.closer ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testHashCode ()
  {
    assert newby.hashCode () == new Cleanment ().hashCode (): "not " + newby.hashCode () + " == " + new Cleanment ().hashCode ();
    show (".");
  }

  private static void testEquals ()
  {
    assert ! newby.equals (null): newby + " equals null";
    assert ! newby.equals (0): newby + " equals 0";
    assert newby.equals (new Cleanment ()): "not " + newby + " equals " + new Cleanment ();
    show (".");
  }

  private static void testToString ()
  {
    String expected = "<"
    + new String () + " | "
    + new String () + " | "
    + new String () + ">";
    String actual = newby.toString ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

}
