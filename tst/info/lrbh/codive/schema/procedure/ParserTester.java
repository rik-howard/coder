
package info.lrbh.codive.schema.procedure;

import static testing.Module.show;
import static testing.Module.panic;
import info.lrbh.codable.schema.Statement;
import info.lrbh.codable.fsio.Line;
import java.util.List;
import java.util.ArrayList;

public class ParserTester
{

  public static void main (String [] strings)
  {
    try {testParsing ();} catch (AssertionError e) {panic (e);}
    show (".");
  }

  private static void testParsing ()
  {
    List <Line> lines = new ArrayList <Line> ();
    List <Statement> expected = new ArrayList <Statement> ();
    List <Statement> actual = Parser.parsing
    (
      lines
    );
    if (expected == null) assert actual == null: "not null: " + actual;
    else assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

}
