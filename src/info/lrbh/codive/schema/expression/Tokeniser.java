
package info.lrbh.codive.schema.expression;

import static info.lrbh.codable.Schematic.expressionAmper;
import static info.lrbh.codable.Schematic.expressionCloser;
import static info.lrbh.codable.Schematic.expressionComma;
import static info.lrbh.codable.Schematic.expressionDot;
import static info.lrbh.codable.Schematic.expressionOpener;
import info.lrbh.codable.schema.expression.Token;
import java.util.ArrayList;
import java.util.List;

public class Tokeniser
{

  public static final List <Token> tokenisation (String cleanment)
  {
    List <Token> tokenz = new ArrayList <Token> ();
    for 
    (
      String substring: cleanment
      .replace (expressionOpener, " ( ")
      .replace (expressionComma, " , ")
      .replace (expressionCloser, " ) ")
      .replace (expressionDot, " . ")
      .replace (expressionAmper, " & ")
      .split (" ")
    )
      if (substring.isEmpty ()) ;
      else
      {
        Token token = new Token ();
        token.setString (substring);
        tokenz.add (token);
      }
    return tokenz;
  }

}
