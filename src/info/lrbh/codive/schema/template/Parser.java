
package info.lrbh.codive.schema.template;

import static info.lrbh.codive.utility.Lister.first;
import static info.lrbh.codive.utility.Lister.rest;
import static info.lrbh.codive.utility.Lister.union;
import java.util.List;
import java.util.ArrayList;
import info.lrbh.codable.schema.template.Token;
import info.lrbh.codable.schema.template.Parsement;
import info.lrbh.codable.schema.template.parsement.Constant;
import info.lrbh.codable.schema.template.parsement.Ending;
import info.lrbh.codable.schema.template.parsement.Variable;
import info.lrbh.codable.schema.template.parsement.Iteration;
import info.lrbh.codable.schema.template.parsement.Condition;
import info.lrbh.codable.schema.template.parsement.Alternative;

public class Parser
{

  public static final Parsement parsing (List <Token> tokens)
  {
    Parsement parsement = new Parsement ();
    parsement.setParsements (parsements (tokens));
    return parsement;
  }

  private static List <Parsement> parsements (List <Token> tokens)
  {
    if (idsParsibility (tokens))
    {
      Token first = first (tokens);
      List <Token> rest = rest (tokens);
      if (first.isConstant ()) return union (constant (first), parsements (rest));
      else if (first.isVariable ()) return union (variable (first), parsements (rest));
      else if (first.isIterator ()) return union (iteration (first, iterateez (rest)), parsements (postIterateez (rest)));
      else if (first.isConditor ()) return union (condition (first, conditeez (rest)), parsements (postConditeez (rest)));
      else if (first.isAlternative ()) return union (alternative (first, conditeez (rest)), parsements (postConditeez (rest)));
      else if (first.isEnding ())  return union ((Parsement) ending (), null);
      else throw new Error ("untypical token: " + first);
    }
    else return new ArrayList <Parsement> ();
  }

  private static final Boolean idsParsibility (List <Token> tokens)
  {
    return tokens.size () > 0;
  }

  private static final Constant constant (Token token)
  {
    return constant (token.string ());
  }

  private static final Variable variable (Token token)
  {
    String interior = token.interior ();
    Integer index = interior.indexOf (".");
    assert index > -1 && index < interior.length (): "maybe";
    String qualifier = interior.substring (0, index);
    String qualifiee = interior.substring (index + 1);
    return variable (qualifier, qualifiee);
  }

  private static final Iteration iteration (Token iterator, List <Token> iterateez)
  {
    String interior = iterator.interior ();
    Integer index = interior.indexOf (":");
    String instance = interior.substring (0, index);
    String term = interior.substring (index + 1);
    if (term.contains ("."))
    {
      index = term.indexOf (".");
      String qualifier = term.substring (0, index);
      String identifier = term.substring (index + 1);
      return iteration (instance, qualifier, identifier, parsements (iterateez));
    }
    else return iteration (instance, null, term, parsements (iterateez));
  }

  private static final List <Token> iterateez (List <Token> tokenz)
  {
    if (idsParsibility (tokenz))
    {
      Token first = first (tokenz);
      List <Token> rest = rest (tokenz);
      if (first.isConstant ()) return union (first, iterateez (rest));
      else if (first.isVariable ()) return union (first, iterateez (rest));
      else if (first.isIterator ()) return union (union (first, iterateez (rest)), iterateez (postIterateez (rest)));
      else if (first.isConditor ()) return union (union (first, conditeez (rest)), iterateez (postConditeez (rest)));
      else if (first.isAlternative ()) return union (union (first, conditeez (rest)), iterateez (postConditeez (rest)));
      else if (first.isEnding ()) return union (first, null);
      else throw new Error ("What is this token?  " + first);
    }
    else throw new Error ("iteration ending not found?");
  }

  private static final List <Token> postIterateez (List <Token> tokenz)
  {
    if (idsParsibility (tokenz))
    {
      Token first = first (tokenz);
      List <Token> rest = rest (tokenz);
      if (first.isConstant ()) return postIterateez (rest);
      else if (first.isVariable ()) return postIterateez (rest);
      else if (first.isIterator ()) return postIterateez (postIterateez (rest));
      else if (first.isConditor ()) return postIterateez (postConditeez (rest));
      else if (first.isAlternative ()) return postIterateez (postConditeez (rest));
      else if (first.isEnding ()) return rest;
      else throw new Error ("What is this token?  " + first);
    }
    else throw new Error ("iteration ending not found?");
  }

  private static final Condition condition (Token conditor, List <Token> conditeez)
  {
    String interior = conditor.interior ().substring (1); /* lose the leading colon */
    Integer index = interior.indexOf (":");
    String predicate = interior.substring (0, index);
    String term = interior.substring (index + 1);
    if (term.contains ("."))
    {
      index = term.indexOf (".");
      String qualifier = term.substring (0, index);
      String identifier = term.substring (index + 1);
      return condition (predicate, qualifier, identifier, parsements (conditeez));
    }
    else return condition (predicate, term, null, parsements (conditeez));
  }

  private static final List <Token> conditeez (List <Token> tokenz)
  {
    if (idsParsibility (tokenz))
    {
      Token first = first (tokenz);
      List <Token> rest = rest (tokenz);
      if (first.isConstant ()) return union (first, conditeez (rest));
      else if (first.isVariable ()) return union (first, conditeez (rest));
      else if (first.isIterator ()) return union (union (first, iterateez (rest)), conditeez (postIterateez (rest)));
      else if (first.isConditor ()) return union (union (first, conditeez (rest)), conditeez (postConditeez (rest)));
      else if (first.isAlternative ()) return new ArrayList <Token> ();
      else if (first.isEnding ()) return union (first, null);
      else throw new Error ("What is this token?  " + first);
    }
    else throw new Error ("condition ending not found?");
  }

  private static final List <Token> postConditeez (List <Token> tokenz)
  {
    if (idsParsibility (tokenz))
    {
      Token first = first (tokenz);
      List <Token> rest = rest (tokenz);
      if (first.isConstant ()) return postConditeez (rest);
      else if (first.isVariable ()) return postConditeez (rest);
      else if (first.isIterator ()) return postConditeez (postIterateez (rest));
      else if (first.isConditor ()) return postConditeez (postConditeez (rest));
      else if (first.isAlternative ()) return tokenz;
      else if (first.isEnding ()) return rest;
      else throw new Error ("What is this token?  " + first);
    }
    else throw new Error ("condition ending not found?");
  }

  private static final Alternative alternative (Token token, List <Token> tokenz)
  {
    return alternative (null, parsements (tokenz));
  }

  private static final Constant constant (String string)
  {
    Constant constant = new Constant ();
    constant.setValue (string);
    return constant;
  }

  private static final Variable variable (String qualifier, String qualifiee)
  {
    Variable variable = new Variable ();
    variable.setQualifier (qualifier);
    variable.setIdentifier (qualifiee);
    return variable;
  }

  private static final Iteration iteration (String instance, String qualifier, String qualifiee, List <Parsement> parsements)
  {
    Iteration iteration = new Iteration ();
    iteration.setInstance (instance);
    iteration.setQualifier (qualifier);
    iteration.setQualifiee (qualifiee);
    iteration.setParsements (parsements);
    return iteration;
  }

  private static Condition condition (String predicate, String qualifier, String identifier, List <Parsement> parsements)
  {
    Condition condition = new Condition ();
    condition.setPredicate (predicate);
    condition.setQualifier (qualifier);
    condition.setIdentifier (identifier);
    condition.setParsements (parsements);
    return condition;
  }

  private static Alternative alternative (String expression, List <Parsement> parsements)
  {
    Alternative alternative = new Alternative ();
    alternative.setExpression (expression);
    alternative.setParsements (parsements);
    return alternative;
  }

  private static Ending ending ()
  {
    Ending ending = new Ending ();
    return ending;
  }


}
