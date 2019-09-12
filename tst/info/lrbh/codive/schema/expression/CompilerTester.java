
package info.lrbh.codive.schema.expression;

import static testing.Module.show;
import static testing.Module.panic;
import info.lrbh.codable.Schema;
import info.lrbh.codable.schema.Record;
import info.lrbh.codable.schema.expression.Linkment;
import info.lrbh.codable.schema.expression.Compilement;
import java.util.Map;
import java.util.HashMap;

public class CompilerTester
{

  public static void main (String [] strings)
  {
    try {testCompilation ();} catch (AssertionError e) {panic (e);}
    show (".");
  }

  private static void testCompilation ()
  {
    Linkment linkment = new Linkment ();
    Schema schema = new Schema ();
    Map <String, Record> context = new HashMap <String, Record> ();
    Compilement expected = new Compilement ();
    Compilement actual = Compiler.compilation
    (
      linkment,
      schema,
      context
    );
    if (expected == null) assert actual == null: "not null: " + actual;
    else assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

}
