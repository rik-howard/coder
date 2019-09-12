
package info.lrbh.codive.schema.procedure;

import static testing.Module.show;
import static testing.Module.panic;
import info.lrbh.codable.schema.procedure.Link;
import info.lrbh.codable.schema.procedure.Compilement;
import java.util.List;
import java.util.ArrayList;

public class CompilerTester
{

  public static void main (String [] strings)
  {
    try {testCompilation ();} catch (AssertionError e) {panic (e);}
    show (".");
  }

  private static void testCompilation ()
  {
    List <Link> linkz = new ArrayList <Link> ();
    Compilement expected = new Compilement ();
    Compilement actual = Compiler.compilation
    (
      linkz
    );
    if (expected == null) assert actual == null: "not null: " + actual;
    else assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

}
