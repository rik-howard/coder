
package info.lrbh.codive.schema.template;

import static testing.Module.show;
import static testing.Module.panic;
import info.lrbh.codable.schema.template.Cleanment;

public class CleanerTester
{

  public static void main (String [] strings)
  {
    try {testCleaning ();} catch (AssertionError e) {panic (e);}
    show (".");
  }

  private static void testCleaning ()
  {
    String cleanee = new String ();
    String opener = new String ();
    String closer = new String ();
    String margin = new String ();
    Cleanment expected = new Cleanment ();
    Cleanment actual = Cleaner.cleaning
    (
      cleanee,
      opener,
      closer,
      margin
    );
    if (expected == null) assert actual == null: "not null: " + actual;
    else assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

}
