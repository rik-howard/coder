
package info.lrbh.codable.schema.template;

import static testing.Module.show;
import static testing.Module.panic;
import java.util.List;
import java.util.ArrayList;

public class ParsementTester
{

  private static final Parsement newby = new Parsement ();

  public static void main (String [] strings)
  {
    try {testParsements ();} catch (AssertionError e) {panic (e);}
    try {testHashCode ();} catch (AssertionError e) {panic (e);}
    try {testEquals ();} catch (AssertionError e) {panic (e);}
    try {testToString ();} catch (AssertionError e) {panic (e);}
    show (".");
  }

  private static void testParsements ()
  {
    List <Parsement> expected = new ArrayList <Parsement> ();
    List <Parsement> actual = newby.parsements ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testHashCode ()
  {
    assert newby.hashCode () == new Parsement ().hashCode (): "not " + newby.hashCode () + " == " + new Parsement ().hashCode ();
    show (".");
  }

  private static void testEquals ()
  {
    assert ! newby.equals (null): newby + " equals null";
    assert ! newby.equals (0): newby + " equals 0";
    assert newby.equals (new Parsement ()): "not " + newby + " equals " + new Parsement ();
    show (".");
  }

  private static void testToString ()
  {
    String expected = "<"
    + new ArrayList <Parsement> () + ">";
    String actual = newby.toString ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

}
