
package info.lrbh.codable.schema.template.parsement;

import static testing.Module.show;
import static testing.Module.panic;
import info.lrbh.codable.schema.template.Parsement;

public class ConditionTester
{

  private static final Condition newby = new Condition ();

  public static void main (String [] strings)
  {
    try {testPredicate ();} catch (AssertionError e) {panic (e);}
    try {testQualifier ();} catch (AssertionError e) {panic (e);}
    try {testIdentifier ();} catch (AssertionError e) {panic (e);}
    try {testHashCode ();} catch (AssertionError e) {panic (e);}
    try {testEquals ();} catch (AssertionError e) {panic (e);}
    try {testSuperString ();} catch (AssertionError e) {panic (e);}
    try {testToString ();} catch (AssertionError e) {panic (e);}
    show (".");
  }

  private static void testPredicate ()
  {
    String expected = new String ();
    String actual = newby.predicate ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
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
    assert newby.hashCode () == new Condition ().hashCode (): "not " + newby.hashCode () + " == " + new Condition ().hashCode ();
    show (".");
  }

  private static void testEquals ()
  {
    assert ! newby.equals (null): newby + " equals null";
    assert ! newby.equals (0): newby + " equals 0";
    assert newby.equals (new Condition ()): "not " + newby + " equals " + new Condition ();
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
    //String string = "<" + new String () + " | " + new String () + " | " + new String () + ">";
    String expected = "<" + new String () + " | " + new String () + " | " + new String () + " | " + newby.superString () + ">";
    String actual = newby.toString ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

}
