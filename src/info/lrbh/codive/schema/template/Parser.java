package info.lrbh.codive.schema.template;

import java.util.List;
import java.util.ArrayList;
import info.lrbh.codable.schema.template.Token;
import info.lrbh.codable.schema.template.Parsement;
import info.lrbh.codable.schema.template.parsement.Constant;
import info.lrbh.codable.schema.template.parsement.Variable;
import info.lrbh.codable.schema.template.parsement.Iteration;
import info.lrbh.codable.schema.template.parsement.Condition;
import info.lrbh.codable.schema.template.parsement.Alternative;
import info.lrbh.codive.Factory;

public class Parser
{


  public static final Parsement parsing (List <Token> tokenz)
  {
    Parsement parsement = new Parsement ();
    parsement.setParsementz (parsementz (tokenz));
    return parsement;
  }

  private static final Boolean idsParsibility (List <Token> tokenz)
  {
    return tokenz.size () > 0;
  }

  /*  private static final Boolean idsConstant (Token token)
  {
    return ! token.string ().startsWith (token.opener ())
    || ! token.string ().endsWith (token.closer ());
  }
   
  private static final Boolean idsVariable (Token token)
  {
    return ! idsConstant (token)
    && interior (token).matches ("^[a-z][a-zA-Z0-9\\.]*$");
  }
   
  private static final Boolean idsIteration (Token token)
  {
    return ! idsConstant (token)
    && interior (token).matches ("^[a-z][a-zA-Z0-9]*:[a-z][a-zA-Z0-9\\.]*$");
  }
   
  private static final Boolean idsCondition (Token token)
  {
    return ! idsConstant (token)
    && interior (token).matches ("^:[a-z][a-zA-Z0-9]*:[a-z][a-zA-Z0-9\\.]*$");
  }
   
  private static final Boolean idsAlternative (Token token)
  {
    return ! idsConstant (token)
    && interior (token).matches ("^:$");
  }
   
  private static final Boolean idsEnding (Token token)
  {
    return ! idsConstant (token)
    && interior (token).matches ("^$");
  }*/

  private static final Token first (List <Token> tokenz)
  {
    return tokenz.get (0);
  }
   
  private static final List <Token> rest (List <Token> tokenz)
  {
    List <Token> tz = new ArrayList <Token> ();
    if (tokenz.size () > 1)
      for (Integer i = 1; i < tokenz.size (); i++)
        tz.add (tokenz.get (i));
    return tz;
  }
   
  private static final List <Token> iterateez (List <Token> tokenz)
  {
    //assert false: tokenz; // 2
    List <Token> tz = new ArrayList <Token> ();
    if (tokenz.size () > 0)
    {
      Token first = first (tokenz);
      List <Token> rest = rest (tokenz);
      if (first.isEnding ())
      {
        //assert false: first + " " + rest;  // 10
        tz.add (first);
      }
      else if (first.isIteration ())
      {
        //assert false: first + " " + rest;
        tz.add (first);
        tz.addAll (iterateez (rest));
        tz.addAll (iterateez (postIterateez (rest)));
      }
      else if (first.isCondition ())
      {
        //assert false: first + " " + rest; // 3
        tz.add (first);
        tz.addAll (conditeez (rest));
        tz.addAll (iterateez (postConditeez (rest)));
      }
      else if (first.isAlternative ())
      {
        //assert false: first + " " + rest;  // 6
        tz.add (first);
        tz.addAll (conditeez (rest));
        tz.addAll (iterateez (postConditeez (rest)));
      }
      else
      {
        //assert false: first + " " + rest;  // 9
        tz.add (first);
        tz.addAll (iterateez (rest));
      }
    }
    else assert false: "iteration ending not found?";
    return tz;
    /*List <Token> tz = new ArrayList <Token> ();
    Boolean foundEnding = false;
    for (Token token: tokenz)
      if (foundEnding) ;
      else if (idsEnding (token)) foundEnding = true;
      else tz.add (token);
    assert false: tz;
    return tz;*/
  }
   
  private static final List <Token> postIterateez (List <Token> tokenz)
  {
    //assert false: tokenz;
    if (tokenz.size () > 0)
    {
      Token first = first (tokenz);
      if (first.isEnding ()) return rest (tokenz);
      else if (first.isIteration ()) return postIterateez (postIterateez (rest (tokenz)));
      else if (first.isCondition ()) return postIterateez (postConditeez (rest (tokenz)));
      else if (first.isAlternative ()) return postIterateez (postConditeez (rest (tokenz)));
      else return postIterateez (rest (tokenz));
    }
    else return tokenz;

    /*List <Token> tz = new ArrayList <Token> ();
    Boolean foundEnding = false;
    for (Token token: tokenz)
      if (foundEnding) tz.add (token);
      else if (idsEnding (token)) foundEnding = true;
      else ;
    return tz;*/
  }
   
  private static final List <Token> conditeez (List <Token> tokenz)
  {
    //assert false: tokenz;  // 4
    List <Token> tz = new ArrayList <Token> ();
    if (tokenz.size () > 0)
    {
      Token first = first (tokenz);
      List <Token> rest = rest (tokenz);
      if (first.isEnding ())
      {
        //assert false: first + " " + rest;  // 8
        tz.add (first);
      }
      else if (first.isIteration ())
      {
        assert false: first + " " + rest;
        tz.add (first);
        tz.addAll (iterateez (rest));
        tz.addAll (conditeez (postIterateez (rest)));
      }
      else if (first.isCondition ())
      {
        assert false: first + " " + rest;
        tz.add (first);
        tz.addAll (conditeez (rest));
        tz.addAll (conditeez (postConditeez (rest)));
      }
      else if (first.isAlternative ())
      {
        //assert false: first + " " + rest;  // 5
        //tz.add (first);
        //tz.addAll (conditeez (rest));
        //tz.addAll (conditeez (postConditeez (rest)));
      }
      else
      {
        //assert false: first + " " + rest; // 7
        tz.add (first);
        tz.addAll (conditeez (rest));
      }
    }
    else assert false: "condition ending not found?";
    return tz;
    /*List <Token> tz = new ArrayList <Token> ();
    Boolean foundEnding = false;
    for (Token token: tokenz)
      if (foundEnding) ;
      else if (idsEnding (token) || idsAlternative (token)) foundEnding = true;
      else tz.add (token);
    return tz;*/
  }
   
  private static final List <Token> postConditeez (List <Token> tokenz)
  {
    //11assert false: tokenz;
    if (tokenz.size () > 0)
    {
      Token first = first (tokenz);
      if (first.isEnding ()) return rest (tokenz);
      else if (first.isIteration ()) return postConditeez (postIterateez (rest (tokenz)));
      else if (first.isCondition ()) return postConditeez (postConditeez (rest (tokenz)));
      else if (first.isAlternative ()) return tokenz;
      else return postConditeez (rest (tokenz));
    }
    else return tokenz;
    /*List <Token> tz = new ArrayList <Token> ();
    Boolean foundEnding = false;
    for (Token token: tokenz)
      if (foundEnding) tz.add (token);
      else if (idsEnding (token)) foundEnding = true;
      else if (idsAlternative (token)) {tz.add (token); foundEnding = true;}
      else ;
    return tz;*/
  }

  private static final Constant constant (Token token)
  {
    /*/ TODO use factory
    Constant constant = new Constant ();
    constant.setValue (token.string ());
    return constant;*/
    return Factory.constant (token.string ());
  }

  private static final Variable variable (Token token)
  {
    String interior = token.interior ();
    Integer index = interior.indexOf (".");
    String qualifier = interior.substring (0, index);
    String identifier = interior.substring (index + 1);
    return Factory.variable (qualifier, identifier);
    /*/ TODO use factory
    Variable variable = new Variable ();
    variable.setQualifier ();
    variable.setIdentifier (token.interior ().split ("\\.") [1]);
    return variable;*/
  }
   
  private static final Iteration iteration (Token token, List <Token> tokenz)
  {
    String interior = token.interior ();
    Integer index = interior.indexOf (":");
    String instance = interior.substring (0, index);
    String term = interior.substring (index + 1);
    if (term.contains ("."))
    {
      index = term.indexOf (".");
      String qualifier = term.substring (0, index);
      String identifier = term.substring (index + 1);
      return Factory.iteration (instance, qualifier, identifier, parsementz (tokenz));
    }
    else return Factory.iteration (instance, null, term, parsementz (tokenz));
    /*/ TODO use factory
    //assert false: token + " " + tokenz;  // 11
    String instance = token.interior ().split (":") [0];
    String list = token.interior ().split (":") [1];
    Boolean hasDot = list.indexOf (".") > 0;
    String qualifier = hasDot? list.split ("\\.") [0]: "";
    String identifier = hasDot? list.split ("\\.") [1]: list;
    Iteration iteration = new Iteration ();
    iteration.setInstance (instance);
    iteration.setQualifier (qualifier);
    iteration.setIdentifier (identifier.substring (0, identifier.length () - 1));
    iteration.setParsementz (parsementz (tokenz));
    return iteration;*/
  }
   
  private static final Condition condition (Token token, List <Token> tokenz)
  {
    String interior = token.interior ().substring (1);  // lose the leading colon
    Integer index = interior.indexOf (":");
    String predicate = interior.substring (0, index);
    String term = interior.substring (index + 1);
    if (term.contains ("."))
    {
      index = term.indexOf (".");
      String qualifier = term.substring (0, index);
      String identifier = term.substring (index + 1);
      return Factory.condition (predicate, Factory.variable (qualifier, identifier), parsementz (tokenz));
    }
    else return Factory.condition (predicate, Factory.variable (term, null), parsementz (tokenz));
    /*/ TODO use factory
    String predicate = token.interior ().split (":") [1];
    String termString = token.interior ().split (":") [2];
    if (termString.contains ("."))
      return Factory.condition (predicate, Factory.variable (termString.split ("\\.") [0], termString.split ("\\.") [1]), parsementz (tokenz));
    else
      return Factory.condition (predicate, Factory.variable (termString, null), parsementz (tokenz));*/
    /*String function = token.interior ().substring (1, token.interior ().lastIndexOf (':'));
    String qualifier = token.interior ().substring (token.interior ().lastIndexOf (':') + 1, token.interior ().indexOf ('.'));
    String identifier = token.interior ().substring (token.interior ().indexOf ('.') + 1);;
    Variable variable = new Variable ();
    variable.setQualifier (qualifier);
    variable.setIdentifier (identifier);
    Condition condition = new Condition ();
    condition.setPredicate (function);
    condition.setTerm (variable);
    condition.setParsementz (parsementz (tokenz));
    return condition;*/
  }
   
  private static final Alternative alternative (Token token, List <Token> tokenz)
  {
    Alternative alternative = new Alternative ();
    alternative.setParsementz (parsementz (tokenz));
    return alternative;
  }
   
  private static List <Parsement> parsementz (List <Token> tokenz)
  {
    //assert false: tokenz; // 1
    if (idsParsibility (tokenz))
    {
      Token first = first (tokenz);
      List <Token> rest = rest (tokenz);
      if (first.isConstant ())
        return union (constant (first), parsementz (rest));
      else if (first.isVariable ())
        return union (variable (first), parsementz (rest));
      else if (first.isIteration ())
        return union (iteration (first, iterateez (rest)), parsementz (postIterateez (rest)));
      else if (first.isCondition ())
        return union (condition (first, conditeez (rest)), parsementz (postConditeez (rest)));
      else if (first.isAlternative ())
        return union (alternative (first, conditeez (rest)), parsementz (postConditeez (rest)));
      else if (first.isEnding ()) return union (null, null);
      else throw new Error ("untypical token: " + first);  // return union (null, parsementz (rest)); // TODO
    }
    else return union (null, null);
  }
   
  private static List <Parsement> union (Parsement parsement, List <Parsement> parsementz)
  {
    List <Parsement> union = new ArrayList <Parsement> ();
    if (parsement != null) union.add (parsement);
    if (parsementz != null) union.addAll (parsementz);
    return union;
  }

}
