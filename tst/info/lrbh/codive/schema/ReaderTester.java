
package info.lrbh.codive.schema;

import static testing.Module.show;
import static testing.Module.panic;
import info.lrbh.codable.Schema;
import info.lrbh.codable.fsio.Line;
import java.util.List;
import java.util.ArrayList;

public class ReaderTester
{

  public static void main (String [] strings)
  {
    try {testReading ();} catch (AssertionError e) {panic (e);}
    show (".");
  }

  private static void testReading ()
  {
    String vertexName = new String ();
    List <Line> templateLinez = new ArrayList <Line> ();
    List <Line> mappingLinez = new ArrayList <Line> ();
    Schema expected = new Schema ();
    Schema actual = Reader.reading
    (
      vertexName,
      templateLinez,
      mappingLinez
    );
    //if (expected == null) assert actual == null: "not null: " + actual;
    //else assert expected.equals (actual): "not " + expected + " equals " + actual;
    show ("testReading");
  }

}
