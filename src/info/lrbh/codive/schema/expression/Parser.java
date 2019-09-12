

package info.lrbh.codive.schema.expression;

import static info.lrbh.codive.utility.Lister.first;
import static info.lrbh.codive.utility.Lister.rest;
import static info.lrbh.codable.Schematic.expressionDot;
import static info.lrbh.codable.Schematic.expressionComma;
import static info.lrbh.codable.Schematic.expressionAmper;
import static info.lrbh.codable.Schematic.expressionOpener;
import static info.lrbh.codable.Schematic.expressionCloser;
import static info.lrbh.codable.Schematic.regexExpressionIdentifier;
import static info.lrbh.codable.Schematic.regexExpressionLiteralKey;
import info.lrbh.codable.schema.Expression;
import info.lrbh.codable.schema.expression.Token;
import java.util.List;

public class Parser
{

  private static List <Token> tokens;

  public static Expression parsing (List <Token> tokens)
  {if (tokens.isEmpty ()) return new Expression ();
    Parser.tokens = tokens;
    return arithmetion ();
  }

  static Expression arithmetion ()
  {
    Expression expression = invocation ();
    if (tokens.isEmpty ()) return expression;
    else if (first (tokens).string ().equals (expressionAmper)) return arithmetion (expression);
    else if (first (tokens).string ().equals (expressionCloser)) return expression;
    else if (first (tokens).string ().equals (expressionComma)) return expression;
    else throw new Error ("non-amper token: " + first (tokens).string ());
  }

  private static Expression arithmetion (Expression left)
  {
    tokens = rest (tokens);
    Expression expression = new Expression ();
    expression.setString (expressionAmper);
    expression.setLeft (left);
    expression.setRight (arithmetion ());
    return expression;
  }

  private static Expression invocation ()
  {
    Expression expression = virtualKey ();
    if (tokens.isEmpty ()) return expression;
    else if (first (tokens).string ().equals (expressionOpener)) return invocation (expression);
    else if (first (tokens).string ().equals (expressionCloser)) return expression;
    else if (first (tokens).string ().equals (expressionComma)) return expression;
    else if (first (tokens).string ().equals (expressionAmper)) return expression;
    else throw new Error ("non-opener token: " + first (tokens).string ());
  }

  private static Expression invocation (Expression left)
  {
    tokens = rest (tokens);
    Expression expression = new Expression ();
    expression.setString (expressionOpener + expressionCloser);
    expression.setLeft (left);
    expression.setRight (arguments ());
    return expression;
  }

  private static Expression arguments ()
  {
    if (tokens.isEmpty ()) throw new Error ("no tokens for arguments");
    else if (first (tokens).string ().equals (expressionCloser)) return nullExpression ();
    else return argument ();
  }

  private static Expression nullExpression ()
  {
    tokens = rest (tokens);
    return null;
  }

  private static Expression argument ()
  {
    Expression expression = arithmetion ();
    if (tokens.isEmpty ()) throw new Error ("no closer for argument");
    else if (first (tokens).string ().equals (expressionCloser)) return argument (expression, true);
    else if (first (tokens).string ().equals (expressionComma)) return argument (expression, false);
    else throw new Error ("non-comma, non-closer token: " + first (tokens).string ());
  }

  private static Expression argument (Expression left, Boolean isTerminal)
  {
    tokens = rest (tokens);
    Expression expression = new Expression ();
    expression.setString (expressionComma);
    expression.setLeft (left);
    expression.setRight (isTerminal? null: argument ());
    return expression;
  }

  private static Expression virtualKey ()
  {
    Expression expression = reference ();
    if (tokens.isEmpty ()) return expression;
    else if (first (tokens).string ().equals (expressionDot)) return virtualKey (expression);
    else if (first (tokens).string ().equals (expressionOpener)) return expression;
    else if (first (tokens).string ().equals (expressionCloser)) return expression;
    else if (first (tokens).string ().equals (expressionComma)) return expression;
    else if (first (tokens).string ().equals (expressionAmper)) return expression;
    else throw new Error ("non-punctuation token: " + first (tokens).string ());
  }

  private static Expression virtualKey (Expression left)
  {
    tokens = rest (tokens);
    Expression expression = new Expression ();
    expression.setString (expressionDot);
    expression.setLeft (left);
    expression.setRight (virtualKey ());
    return expression;
  }

  private static Expression reference ()
  {
    Expression expression = literalKey ();
    if (expression.string ().matches (regexExpressionLiteralKey)) return expression;
    else if (first (tokens).string ().matches (regexExpressionIdentifier)) return simple ();
    else throw new Error ("non-literal, non-identifier token: " + first (tokens).string ());
  }

  private static Expression literalKey ()
  {
    if (tokens.isEmpty ()) throw new Error ("no tokens for literal key");
    else if (first (tokens).string ().matches (regexExpressionLiteralKey)) return simple ();
    else return new Expression ();
  }

  private static Expression simple ()
  {
    Expression expression = new Expression ();
    expression.setString (first (tokens).string ());
    tokens = rest (tokens);
    return expression;
  }

}
