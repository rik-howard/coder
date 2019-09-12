
package info.lrbh.codable.schema;

import static testing.Module.show;
import static testing.Module.panic;
import java.util.List;
import java.util.ArrayList;

public class RelationTester
{

  private static final Relation newby = new Relation ();

  public static void main (String [] strings)
  {
    try {testId ();} catch (AssertionError e) {panic (e);}
    try {testNames ();} catch (AssertionError e) {panic (e);}
    try {testHashCode ();} catch (AssertionError e) {panic (e);}
    try {testEquals ();} catch (AssertionError e) {panic (e);}
    try {testToString ();} catch (AssertionError e) {panic (e);}
    show (".");
  }

  private static void testId ()
  {
    String expected = new String ();
    String actual = newby.id ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testNames ()
  {
    List <String> expected = new ArrayList <String> ();
    List <String> actual = newby.names ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testHashCode ()
  {
    assert newby.hashCode () == new Relation ().hashCode (): "not " + newby.hashCode () + " == " + new Relation ().hashCode ();
    show (".");
  }

  private static void testEquals ()
  {
    assert ! newby.equals (null): newby + " equals null";
    assert ! newby.equals (0): newby + " equals 0";
    assert newby.equals (new Relation ()): "not " + newby + " equals " + new Relation ();
    show (".");
  }

  private static void testToString ()
  {
    String expected = "<"
    + new String () + " | "
    + new ArrayList <String> () + ">";
    String actual = newby.toString ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

}
