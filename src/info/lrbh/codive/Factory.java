
package info.lrbh.codive;

import java.util.List;
import java.util.Map;
import info.lrbh.codable.Schema;
import info.lrbh.codable.schema.Function;
import info.lrbh.codable.schema.Procedure;
import info.lrbh.codable.schema.Record;
import info.lrbh.codable.schema.Relation;
import info.lrbh.codable.schema.Symbol;
import info.lrbh.codable.schema.Template;
import info.lrbh.codable.schema.Vertex;
import info.lrbh.codable.schema.template.Cleanment;
import info.lrbh.codable.schema.template.Compilement;
import info.lrbh.codable.schema.template.Link;
import info.lrbh.codable.schema.template.Parsement;
import info.lrbh.codable.schema.template.Token;
import info.lrbh.codable.schema.template.parsement.Alternative;
import info.lrbh.codable.schema.template.parsement.Condition;
import info.lrbh.codable.schema.template.parsement.Constant;
import info.lrbh.codable.schema.template.parsement.Iteration;
import info.lrbh.codable.schema.template.parsement.Variable;

public class Factory
{

  public static Schema schema (Map <String, Symbol> symbolz, Map <String, Relation> relationz, Map <String, Record> recordz, Map <String, Function> functionz, Procedure procedure, Template template, Vertex vertex, List <String> keyz)
  {
    Schema schema = new Schema ();
    schema.setSymbolz (symbolz);
    schema.setRelationz (relationz);
    schema.setRecordz (recordz);
    schema.setFunctionz (functionz);
    schema.setProcedure (procedure);
    schema.setTemplate (template);
    schema.setVertex (vertex);
    schema.setKeyz (keyz);
    return schema;
  }



  public static Symbol symbol (String name, String value)
  {
    Symbol symbol = new Symbol ();
    symbol.setName (name);
    symbol.setValue (value);
    return symbol;
  }
  
  public static Relation relation (String id, List <String> namez)
  {
    Relation relation = new Relation ();
    relation.setId (id);
    relation.setNamez (namez);
    return relation;
  }

  public static Record record (String ref, List <String> namez, List <String> valuez)
  {
    Record record = new Record ();
    record.setRef (ref);
    record.setNamez (namez);
    record.setValuez (valuez);
    return record;
  }

  public static Function function (String signature, String body)
  {
    Function function = new Function ();
    function.setSignature (signature);
    function.setBody (body);
    return function;
  }

  public static Procedure procedure (String string)
  {
    Procedure procedure = new Procedure ();
    procedure.setString (string);
    return procedure;
  }

  public static Template template (String string)
  {
    Template template = new Template ();
    template.setString (string);
    return template;
  }

  public static Vertex vertex (String string)
  {
    Vertex vertex = new Vertex ();
    vertex.setString (string);
    return vertex;
  }



  public static Cleanment cleanment (String opener, String closer, String string)
  {
    Cleanment cleanment = new Cleanment ();
    cleanment.setOpener (opener);
    cleanment.setCloser (closer);
    cleanment.setString (string);
    return cleanment;
  }

  public static Token token (String opener, String closer, String string)
  {
    Token token = new Token ();
    token.setOpener (opener);
    token.setCloser (closer);
    token.setString (string);
    return token;
  }

  public static Parsement parsement (List <Parsement> parsementz)
  {
    Parsement parsement = new Parsement ();
    parsement.setParsementz (parsementz);
    return parsement;
  }

  public static Link link (String value)
  {
    Link link = new Link ();
    link.setValue (value);
    return link;
  }

  public static Compilement compilement (String value)
  {
    Compilement compilement = new Compilement ();
    compilement.setValue (value);
    return compilement;
  }




  public static Constant constant (String value)
  {
    Constant constant = new Constant ();
    constant.setValue (value);
    return constant;
  }


  public static Variable variable (String qualifier, String identifier)
  {
    Variable variable = new Variable ();
    variable.setQualifier (qualifier);
    variable.setIdentifier (identifier);
    return variable;
  }


  public static Iteration iteration (String instance, String qualifier, String identifier, List <Parsement> parsementz)
  {
    Iteration iteration = new Iteration ();
    iteration.setInstance (instance);
    iteration.setQualifier (qualifier);
    iteration.setIdentifier (identifier);
    iteration.setParsementz (parsementz);
    return iteration;
  }


  public static Condition condition (String predicate, Variable term, List <Parsement> parsementz)
  {
    Condition condition = new Condition ();
    condition.setPredicate (predicate);
    condition.setTerm (term);
    condition.setParsementz (parsementz);
    return condition;
  }


  public static Alternative alternative (String expression, List <Parsement> parsementz)
  {
    Alternative alternative = new Alternative ();
    alternative.setExpression (expression);
    alternative.setParsementz (parsementz);
    return alternative;
  }


}
