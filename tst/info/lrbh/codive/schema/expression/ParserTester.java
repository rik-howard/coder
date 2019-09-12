
package info.lrbh.codive.schema.expression;

import static testing.Module.show;
import static testing.Module.panic;
import info.lrbh.codable.schema.Expression;
import info.lrbh.codable.schema.expression.Token;
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
    List <Token> tokens = new ArrayList <Token> ();
    Expression expected = new Expression ();
    Expression actual = Parser.parsing
    (
      tokens
    );
    if (expected == null) assert actual == null: "not null: " + actual;
    else assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

}
