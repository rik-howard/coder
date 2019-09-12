
package info.lrbh.codable.schema.procedure;

import static testing.Module.show;
import static testing.Module.panic;
import java.util.List;
import java.util.ArrayList;
import info.lrbh.codable.fsio.Line;

public class CompilementTester
{

  private static final Compilement newby = new Compilement ();

  public static void main (String [] strings)
  {
    try {testLines ();} catch (AssertionError e) {panic (e);}
    try {testHashCode ();} catch (AssertionError e) {panic (e);}
    try {testEquals ();} catch (AssertionError e) {panic (e);}
    try {testToString ();} catch (AssertionError e) {panic (e);}
    show (".");
  }

  private static void testLines ()
  {
    List <Line> expected = new ArrayList <Line> ();
    List <Line> actual = newby.lines ();
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
    + new ArrayList <Line> () + ">";
    String actual = newby.toString ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

}
