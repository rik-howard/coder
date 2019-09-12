
package info.lrbh.codive.schema.expression;

import static testing.Module.show;
import static testing.Module.panic;
import info.lrbh.codable.Schema;
import info.lrbh.codable.schema.Record;
import info.lrbh.codable.schema.Expression;
import info.lrbh.codable.schema.expression.Linkment;
import java.util.Map;
import java.util.HashMap;

public class LinkerTester
{

  public static void main (String [] strings)
  {
    try {testLinking ();} catch (AssertionError e) {panic (e);}
    show (".");
  }

  private static void testLinking ()
  {
    Expression expression = new Expression ();
    Schema schema = new Schema ();
    Map <String, Record> context = new HashMap <String, Record> ();
    Linkment expected = new Linkment ();
    Linkment actual = Linker.linking
    (
      expression,
      schema,
      context
    );
    if (expected == null) assert actual == null: "not null: " + actual;
    else assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

}
