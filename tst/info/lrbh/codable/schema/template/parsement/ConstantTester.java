
package info.lrbh.codable.schema.template.parsement;

import static testing.Module.show;
import static testing.Module.panic;
import info.lrbh.codable.schema.template.Parsement;

public class ConstantTester
{

  private static final Constant newby = new Constant ();

  public static void main (String [] strings)
  {
    try {testValue ();} catch (AssertionError e) {panic (e);}
    try {testHashCode ();} catch (AssertionError e) {panic (e);}
    try {testEquals ();} catch (AssertionError e) {panic (e);}
    try {testSuperString ();} catch (AssertionError e) {panic (e);}
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
    assert newby.hashCode () == new Constant ().hashCode (): "not " + newby.hashCode () + " == " + new Constant ().hashCode ();
    show (".");
  }

  private static void testEquals ()
  {
    assert ! newby.equals (null): newby + " equals null";
    assert ! newby.equals (0): newby + " equals 0";
    assert newby.equals (new Constant ()): "not " + newby + " equals " + new Constant ();
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
    //String string = "<" + new String () + ">";
    String expected = "<" + new String () + " | " + newby.superString () + ">";
    String actual = newby.toString ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

}
