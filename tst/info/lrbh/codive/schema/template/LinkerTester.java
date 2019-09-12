
package info.lrbh.codive.schema.template;

import static testing.Module.show;
import static testing.Module.panic;
import info.lrbh.codable.schema.Record;
import info.lrbh.codable.schema.template.Parsement;
import info.lrbh.codable.schema.template.Link;
import java.util.List;
import java.util.ArrayList;
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
    Parsement parsement = new Parsement ();
    List <String> keyz = new ArrayList <String> ();
    Map <String, Record> recordz = new HashMap <String, Record> ();
    List <Link> expected = new ArrayList <Link> ();
    List <Link> actual = Linker.linking
    (
      parsement,
      keyz,
      recordz
    );
    if (expected == null) assert actual == null: "not null: " + actual;
    else assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

}
