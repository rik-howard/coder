
package info.lrbh.codable.schema;

import static testing.Module.show;
import static testing.Module.panic;

public class VertexTester
{

  private static final Vertex newby = new Vertex ();

  public static void main (String [] strings)
  {
    try {testPrefix ();} catch (AssertionError e) {panic (e);}
    try {testInfix ();} catch (AssertionError e) {panic (e);}
    try {testSuffix ();} catch (AssertionError e) {panic (e);}
    try {testSymbolValueEOF ();} catch (AssertionError e) {panic (e);}
    try {testHashCode ();} catch (AssertionError e) {panic (e);}
    try {testEquals ();} catch (AssertionError e) {panic (e);}
    try {testToString ();} catch (AssertionError e) {panic (e);}
    show (".");
  }

  private static void testPrefix ()
  {
    String expected = new String ();
    String actual = newby.prefix ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testInfix ()
  {
    String expected = new String ();
    String actual = newby.infix ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testSuffix ()
  {
    String expected = new String ();
    String actual = newby.suffix ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testSymbolValueEOF ()
  {
    String expected = new String ();
    String actual = newby.symbolValueEOF ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testHashCode ()
  {
    assert newby.hashCode () == new Vertex ().hashCode (): "not " + newby.hashCode () + " == " + new Vertex ().hashCode ();
    show (".");
  }

  private static void testEquals ()
  {
    assert ! newby.equals (null): newby + " equals null";
    assert ! newby.equals (0): newby + " equals 0";
    assert newby.equals (new Vertex ()): "not " + newby + " equals " + new Vertex ();
    show (".");
  }

  private static void testToString ()
  {
    String expected = "<"
    + new String () + " | "
    + new String () + " | "
    + new String () + " | "
    + new String () + ">";
    String actual = newby.toString ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

}
