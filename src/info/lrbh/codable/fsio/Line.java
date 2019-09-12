
package info.lrbh.codable.fsio;

import static info.lrbh.codable.Schematic.regexExpressionLiteralKey;
import static info.lrbh.codable.Schematic.regexSector;
import static info.lrbh.codable.Schematic.regexSymbolName;
import static info.lrbh.codable.Schematic.regexSymbolValue;
import static info.lrbh.codable.Schematic.regexRelationId;
import static info.lrbh.codable.Schematic.regexRelationName;
import static info.lrbh.codable.Schematic.regexRecordRef;
import static info.lrbh.codable.Schematic.regexRecordValue;
import static info.lrbh.codable.Schematic.regexFunctionSignature;
import static info.lrbh.codable.Schematic.regexFunctionBody;
import static info.lrbh.codable.Schematic.procedureIntroducer;
import static info.lrbh.codable.Schematic.regexProcedureIterator;
import static info.lrbh.codable.Schematic.procedureEnding;
import static info.lrbh.codable.Schematic.expressionDot;
import info.lrbh.codable.schema.Symbol;
import info.lrbh.codable.schema.Relation;
import info.lrbh.codable.schema.Record;
import info.lrbh.codable.schema.Expression;
import info.lrbh.codable.schema.Function;
import info.lrbh.codable.schema.expression.Token;
import info.lrbh.codive.schema.expression.Tokeniser;
import info.lrbh.codive.schema.expression.Parser;
import info.lrbh.codable.schema.statement.Ending;
import info.lrbh.codable.schema.statement.Introduction;
import info.lrbh.codable.schema.statement.Iteration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Line
implements Cloneable
{

  private String string;

  public void setString (String string) {this.string = string == null? new String (): string;}

  public String string () {if (this.string == null) this.setString (null); return this.string;}

  public Line ()
  {
    super ();
  }

  public Line (String string)
  {
    super ();
    this.setString (string);
  }



  @Override public int hashCode ()
  {
    return this.string ().hashCode ();
  }

  @Override public boolean equals (Object object)
  {
    return object == null? false: object instanceof Line? this.string ().equals (((Line) object).string ()): false;
  }

  @Override public String toString ()
  {
    return "<" + this.string () + ">";
  }

  @Override public Object clone ()
  {
    Object clone = null;
    try
    {
      clone = super.clone ();
      ((Line) clone).setString (this.string ());
    }
    catch (CloneNotSupportedException e) {e.printStackTrace ();}
    return clone;
  }



  public Boolean hasComment (String commentSymbolValue)
  {
    return this.string ().indexOf (commentSymbolValue) > -1;
  }

  public Boolean hasQuotation ()
  {
    Integer here = this.string ().indexOf (FSO.quote);
    Integer there = this.string ().indexOf (FSO.quote, here + 1);
    return here < there;
  }

  public String quotation ()
  {
    assert this.hasQuotation ();
    Integer here = this.string ().indexOf (FSO.quote);
    Integer there = this.string ().indexOf (FSO.quote, here + 1);
    return this.string ().substring (here, there + 1);
  }

  public void removeComment (String commentSymbolValue)
  {
    assert this.hasComment (commentSymbolValue);
    Integer index = this.string ().indexOf (commentSymbolValue);
    this.setString (this.string ().substring (0, index).trim ());
  }

  public void replaceQuotation (String replacement)
  {
    assert this.hasQuotation ();
    Integer here = this.string ().indexOf (FSO.quote);
    Integer there = this.string ().indexOf (FSO.quote, here + 1);
    this.setString
    (
      this.string ().substring (0, here)
      + replacement
      + this.string ().substring (there + 1)
    );
  }

  public void clean (String commentMarker, Map <String, Symbol> symbols)
  {
    if (this.hasComment (commentMarker))
      this.removeComment (commentMarker);
    while (this.hasQuotation ())
    {
      String key = nextSymbolId (symbols).toString ();
      String unquoted = this.quotation ().substring (1, this.quotation ().length () - 1);
      this.replaceQuotation (key);
      Symbol symbol = new Symbol ();
      symbol.setName (key);
      symbol.setValue (unquoted);
      symbols.put (key, symbol);
    }
  }

  private static final Integer nextSymbolId (Map <String, Symbol> symbols)
  {
    String regex = regexExpressionLiteralKey;
    Integer nextId = 0;
    for (String key: symbols.keySet ())
      if (key.matches (regex))
        if (Integer.parseInt (key) > nextId)
          nextId = Integer.parseInt (key);
    return ++nextId;
  }



  private Boolean hasNeck ()
  {
    return this.string ().contains (FSO.neck);
  }

  public Boolean isSector ()
  {
    return this.string ().matches (regexSector);
  }

  public Boolean isSymbol ()
  {
    return this.hasNeck ()
    && this.head ().matches (regexSymbolName)
    && this.body ().matches (regexSymbolValue);
  }

  public Boolean isRelation ()
  {
    if (this.hasNeck ())
    {
      Boolean is = this.head ().matches (regexRelationId);
      for (String s: this.body ().split (" "))
        is &= s.matches (regexRelationName);
      return is;
    }
    else return false;
  }

  public Boolean isRecord ()
  {
    if (this.hasNeck ())
    {
      Boolean ids = this.head ().matches (regexRecordRef);
      for (String s: this.body ().split (" "))
        ids &= s.matches (regexRecordValue);
      return ids;
    }
    else return false;
  }

  public Boolean isFunction ()
  {
    if (this.hasNeck ())
      return this.head ().matches (regexFunctionSignature)
      && this.body ().matches (regexFunctionBody);
    else return false;
  }

  public Boolean isIntroduction ()
  {
    return this.string ().trim ().startsWith (procedureIntroducer);
  }

  public Boolean isIteration ()
  {
    return this.string ().trim ().matches (regexProcedureIterator);
  }

  public Boolean isEnding ()
  {
    return this.string ().trim ().equals (procedureEnding);
  }



  private String head ()
  {
    assert this.hasNeck ();
    return this.string ().substring (0, this.string ().indexOf (FSO.neck)).trim ();
  }

  private String body ()
  {
    assert this.hasNeck ();
	  return this.string ().substring (this.string ().indexOf (FSO.neck) + 1).trim ();
  }

  public String sector ()
  {
    assert this.isSector ();
    String sector = this.string ().substring (2);
    return sector;
  }

  public Symbol symbol ()
  {
    assert this.isSymbol ();
    String name = this.head ();
    String value = this.body ();
    Symbol symbol = new Symbol ();
    symbol.setName (name);
    symbol.setValue (value);
    return symbol;
  }

  public Relation relation ()
  {
    assert this.isRelation ();
    String id = this.head ();
    List <String> namez = new ArrayList <String> ();
    for (String name: this.body ().split (FSO.space)) namez.add (name);
    Relation relation = new Relation ();
    relation.setId (id);
    relation.setNames (namez);
    return relation;
  }

  public String recordRelationId ()
  {
    assert this.isRecord ();
    String ref = this.head ();
    return ref.substring (0, 1).toUpperCase ()
    + ref.substring (1, ref.length ());
  }

  public Record record (Relation relation)
  {
    assert this.isRecord (): "not Record: " + this.toString ();
    assert relation != null: "the relation is null; this is: " + this.toString ();
    String ref = this.head ();
    List <String> namez = relation.names ();
    List <String> valuez = new ArrayList <String> ();
    for (String value: this.body ().split (FSO.space)) valuez.add (value);
    Record record = new Record ();
    record.setRef (ref);
    record.setNames (namez);
    record.setValues (valuez);
    return record;
  }

  private Expression expression (String cleanment)
  {
    assert this.isFunction () || this.isIntroduction ();
    List <Token> tokens = Tokeniser.tokenisation (cleanment);
    Expression expression = Parser.parsing (tokens);
    return expression;
  }

  public Function function ()
  {
    assert this.isFunction ();
    Expression signature = expression (this.head ());
    Expression body = expression (this.body ());
    Function function = new Function ();
    function.setSignature (signature);
    function.setBody (body);
    return function;
  }

  public Introduction introduction ()
  {
    assert this.isIntroduction ();
    Expression expression = expression (this.string ().replaceFirst (procedureIntroducer, FSO.blank));
    Introduction introduction = new Introduction ();
    introduction.setExpression (expression);
    return introduction;
  }

  public Iteration iteration (List <Line> lines)
  {
    assert this.isIteration ();
    String [] sz = this.string ().trim ().replaceAll (" +", " ").split (" ");
    String instance = sz [1];
    String qualifier = sz [3].contains (expressionDot)? sz [3].substring (0, sz [3].indexOf (expressionDot)): FSO.blank;
    String qualifiee =  sz [3].contains (expressionDot)? sz [3].substring (sz [3].indexOf (expressionDot) + 1, sz [3].length ()): sz [3];
    Iteration iteration = new Iteration ();
    iteration.setInstance (instance);
    iteration.setQualifier (qualifier);
    iteration.setQualifiee (qualifiee.replaceFirst ("z$", ""));
    iteration.setStatements (info.lrbh.codive.schema.procedure.Parser.parsing (lines));
    return iteration;
  }

  public Ending ending ()
  {
    return new Ending ();
  }

}
