
package info.lrbh.codive.schema;

import static testing.Module.show;
import static testing.Module.panic;
import info.lrbh.codable.Schema;
import java.util.List;
import java.util.ArrayList;

public class WriterTester
{

  public static void main (String [] strings)
  {
    try {testNamesWriting ();} catch (AssertionError e) {panic (e);}
    try {testValuesWriting ();} catch (AssertionError e) {panic (e);}
    show (".");
  }

  private static void testNamesWriting ()
  {
    Schema schema = new Schema ();
    List <String> expected = new ArrayList <String> ();
    List <String> actual = Writer.namesWriting
    (
      schema
    );
    if (expected == null) assert actual == null: "not null: " + actual;
    else assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testValuesWriting ()
  {
    Schema schema = new Schema ();
    List <String> expected = null;
    List <String> actual = Writer.valuesWriting
    (
      schema
    );
    if (expected == null) assert actual == null: "not null: " + actual;
    else assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

}
