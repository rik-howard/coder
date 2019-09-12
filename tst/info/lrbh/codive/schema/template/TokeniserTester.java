
package info.lrbh.codive.schema.template;

import static testing.Module.show;
import static testing.Module.panic;
import info.lrbh.codable.schema.template.Cleanment;
import info.lrbh.codable.schema.template.Token;
import java.util.List;
import java.util.ArrayList;

public class TokeniserTester
{

  public static void main (String [] strings)
  {
    try {testTokenisation ();} catch (AssertionError e) {panic (e);}
    show (".");
  }

  private static void testTokenisation ()
  {
    Cleanment cleanment = new Cleanment ();
    List <Token> expected = new ArrayList <Token> ();
    List <Token> actual = Tokeniser.tokenisation
    (
      cleanment
    );
    if (expected == null) assert actual == null: "not null: " + actual;
    else assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

}
