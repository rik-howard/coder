
package info.lrbh.codive.schema.template;

import static testing.Module.show;
import static testing.Module.panic;
import info.lrbh.codable.schema.template.Parsement;
import info.lrbh.codable.schema.template.Token;
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
    tokens.add (token ("a"));
    tokens.add (token ("[:x:b]"));
    tokens.add (token ("c"));
    tokens.add (token ("[:x:d]"));
    tokens.add (token ("e"));
    tokens.add (token ("[:]"));
    tokens.add (token ("f"));
    tokens.add (token ("[]"));
    tokens.add (token ("g"));
    tokens.add (token ("[]"));
    tokens.add (token ("h"));
    Parsement expected = new Parsement ();
    //Parsement actual = Parser.parsing
    //(
    //  tokens
    //);
    //if (expected == null) assert actual == null: "not null: " + actual;
    //else assert expected.equals (actual): "not " + expected + " equals " + actual;
    show ("testParsing");
  }
  
  private static Token token (String string)
  {
    Token token = new Token ();
    token.setString (string);
    token.setOpener ("[");
    token.setCloser ("]");
    return token;
  }

}
