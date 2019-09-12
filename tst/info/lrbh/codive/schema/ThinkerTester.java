
package info.lrbh.codive.schema;

import static testing.Module.show;
import static testing.Module.panic;
import info.lrbh.codable.Schema;

public class ThinkerTester
{

  public static void main (String [] strings)
  {
    try {testThinking ();} catch (AssertionError e) {panic (e);}
    show (".");
  }

  private static void testThinking ()
  {
    Schema schema = new Schema ();
    Schema expected = new Schema ();
    Schema actual = Thinker.thinking
    (
      schema
    );
    if (expected == null) assert actual == null: "not null: " + actual;
    else assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

}
