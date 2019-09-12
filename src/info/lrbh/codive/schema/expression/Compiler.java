
package info.lrbh.codive.schema.expression;

import static info.lrbh.codive.Module.modulation;
import java.util.List;
import java.util.Map;
import info.lrbh.codable.Schema;
import info.lrbh.codable.fsio.Line;
import info.lrbh.codable.schema.Function;
import info.lrbh.codable.schema.Record;
import info.lrbh.codable.schema.Symbol;
import info.lrbh.codable.schema.expression.Linkment;
import info.lrbh.codable.schema.expression.Compilement;

public class Compiler
{

  private static Schema schema;
  private static Map <String, Record> context;

  public static final Compilement compilation (Linkment linkment, Schema schema, Map <String, Record> context)
  {
    Compiler.schema = schema;
    Compiler.context = context;
    Compilement compilement = new Compilement ();
    compilement.setLine (new Line (compilation_ (linkment)));
    return compilement;
  }

  private static final String compilation_ (Linkment linkment)
  {
    if (linkment.isLiteral ()) return literal (linkment);
    else if (linkment.isInvocation ()) return invocation (linkment);
    else if (linkment.isArithmetic ()) return arithmetion (linkment);
    else throw new Error ("untypical linkment: " + linkment);
  }

  private static final String literal (Linkment linkment)
  {
    assert linkment.isLiteral ();
    return linkment.string ();
  }

  private static final String invocation (Linkment linkment)
  {
    assert linkment.isInvocation (): linkment;
    Function function = schema.function (linkment.functionKey ());
    if (function == null) return modulation (linkment.invokee (), linkment.arguments ());
    else
    {
      setSchemaAndContext (function, linkment);
      Line expressment = function.body ().expressed (schema, context);
      return expressment.string ();
    }
  }

  private static void setSchemaAndContext (Function function, Linkment invocation)
  {
    List <String> parameters = function.parameters ();
    List <String> arguments = invocation.arguments ();
    for (String parameter: parameters)
      if (function.vals ().contains (parameter))
        introVal (parameter, arguments.get (parameters.indexOf (parameter)));
      else if (function.vars ().contains (parameter))
        introVar (parameter, arguments.get (parameters.indexOf (parameter)));
      else throw new Error ("paramenter is neither val nor var: " + parameter);
  }

  private static void introVal (String parameter, String argument)
  {
    assert parameter != null;
    assert argument != null;
    Symbol symbol = new Symbol ();
    symbol.setName (parameter);
    symbol.setValue (argument);
    schema.putSymbol (symbol);
  }

  private static void introVar (String parameter, String argument)
  {
    assert parameter != null;
    assert argument != null: argument;
    Record record = context.get (argument);
    context.put (parameter, record);
  }

  public static final String arithmetion (Linkment linkment)
  {
    assert linkment.isArithmetic ();
    String left = compilation_ (linkment.left ());
    String right = compilation_ (linkment.right ());
    return left + " " + right;
  }

}
