
package info.lrbh.codive.schema.procedure;

import static testing.Module.show;
import static testing.Module.panic;
import info.lrbh.codable.Schema;
import info.lrbh.codable.schema.Procedure;
import info.lrbh.codable.schema.procedure.Link;
import java.util.List;
import java.util.ArrayList;

public class LinkerTester
{

  public static void main (String [] strings)
  {
    try {testLinking ();} catch (AssertionError e) {panic (e);}
    show (".");
  }

  private static void testLinking ()
  {
    Procedure procedure = new Procedure ();
    Schema schema = new Schema ();
    List <Link> expected = new ArrayList <Link> ();
    List <Link> actual = Linker.linking
    (
      procedure,
      schema
    );
    if (expected == null) assert actual == null: "not null: " + actual;
    else assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

}
