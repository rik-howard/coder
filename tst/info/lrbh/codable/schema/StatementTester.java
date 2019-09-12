
package info.lrbh.codable.schema;

import static testing.Module.show;
import static testing.Module.panic;
import java.util.List;
import java.util.ArrayList;

public class StatementTester
{

  private static final Statement newby = new Statement ();

  public static void main (String [] strings)
  {
    try {testNames ();} catch (AssertionError e) {panic (e);}
    try {testHashCode ();} catch (AssertionError e) {panic (e);}
    try {testEquals ();} catch (AssertionError e) {panic (e);}
    try {testToString ();} catch (AssertionError e) {panic (e);}
    show (".");
  }

  private static void testNames ()
  {
    List <Statement> expected = new ArrayList <Statement> ();
    List <Statement> actual = newby.statements ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testHashCode ()
  {
    assert newby.hashCode () == new Statement ().hashCode (): "not " + newby.hashCode () + " == " + new Statement ().hashCode ();
    show (".");
  }

  private static void testEquals ()
  {
    assert ! newby.equals (null): newby + " equals null";
    assert ! newby.equals (0): newby + " equals 0";
    assert newby.equals (new Statement ()): "not " + newby + " equals " + new Statement ();
    show (".");
  }

  private static void testToString ()
  {
    String expected = "<"
    + new ArrayList <Statement> () + ">";
    String actual = newby.toString ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

}
