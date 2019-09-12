
package info.lrbh.codable.schema.statement;

import static testing.Module.show;
import static testing.Module.panic;
import info.lrbh.codable.schema.Statement;

public class IterationTester
{

  private static final Iteration newby = new Iteration ();

  public static void main (String [] strings)
  {
    try {testInstance ();} catch (AssertionError e) {panic (e);}
    try {testQualifier ();} catch (AssertionError e) {panic (e);}
    try {testQualifiee ();} catch (AssertionError e) {panic (e);}
    try {testHashCode ();} catch (AssertionError e) {panic (e);}
    try {testEquals ();} catch (AssertionError e) {panic (e);}
    try {testSuperString ();} catch (AssertionError e) {panic (e);}
    try {testToString ();} catch (AssertionError e) {panic (e);}
    show (".");
  }

  private static void testInstance ()
  {
    String expected = new String ();
    String actual = newby.instance ();
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

  private static void testQualifiee ()
  {
    String expected = new String ();
    String actual = newby.qualifiee ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testHashCode ()
  {
    assert newby.hashCode () == new Iteration ().hashCode (): "not " + newby.hashCode () + " == " + new Iteration ().hashCode ();
    show (".");
  }

  private static void testEquals ()
  {
    assert ! newby.equals (null): newby + " equals null";
    assert ! newby.equals (0): newby + " equals 0";
    assert newby.equals (new Iteration ()): "not " + newby + " equals " + new Iteration ();
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
    + new String () + " | "
    + new String () + " | "
    + new String () + " | "
    + newby.superString ()
    + ">";
    String actual = newby.toString ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

}
