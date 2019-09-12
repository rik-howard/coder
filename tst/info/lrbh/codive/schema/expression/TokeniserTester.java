
package info.lrbh.codive.schema.expression;

import static testing.Module.show;
import static testing.Module.panic;
import info.lrbh.codable.schema.expression.Token;
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
    String cleanment = new String ();
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
