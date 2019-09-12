
package info.lrbh.codable;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import info.lrbh.codable.schema.Symbol;
import info.lrbh.codable.schema.Relation;
import info.lrbh.codable.schema.Record;
import info.lrbh.codable.schema.Function;
import info.lrbh.codable.schema.Procedure;
import info.lrbh.codable.schema.Template;
import info.lrbh.codable.schema.Vertex;

public class Schema
implements Schematic, Cloneable
{

  private String commentMarker;
  private String templateEOF;
  private String templateMargin;
  private String templateOpener;
  private String templateCloser;
  private Map <String, Symbol> symbols;
  private Map <String, Relation> relations;
  private Map <String, Record> records;
  private Map <String, Function> functions;
  private Procedure procedure;
  private Template template;
  private Vertex vertex;
  private List <String> keys;

  public void setCommentMarker (String commentMarker) {this.commentMarker = commentMarker == null? commentMarkerDefault: commentMarker;}
  public void setTemplateEOF (String templateEOF) {this.templateEOF = templateEOF == null? templateEOFDefault: templateEOF;}
  public void setTemplateMargin (String templateMargin) {this.templateMargin = templateMargin == null? templateMarginDefault: templateMargin;}
  public void setTemplateOpener (String templateOpener) {this.templateOpener = templateOpener == null? templateOpenerDefault: templateOpener;}
  public void setTemplateCloser (String templateCloser) {this.templateCloser = templateCloser == null? templateCloserDefault: templateCloser;}
  public void setSymbols (Map <String, Symbol> symbols) {this.symbols = symbols == null? new HashMap <String, Symbol> (): symbols;}
  public void setRelations (Map <String, Relation> relations) {this.relations = relations == null? new HashMap <String, Relation> (): relations;}
  public void setRecords (Map <String, Record> records) {this.records = records == null? new HashMap <String, Record> (): records;}
  public void setFunctions (Map <String, Function> functions) {this.functions = functions == null? new HashMap <String, Function> (): functions;}
  public void setProcedure (Procedure procedure) {this.procedure = procedure == null? new Procedure (): procedure;}
  public void setTemplate (Template template) {this.template = template == null? new Template (): template;}
  public void setVertex (Vertex vertex) {this.vertex = vertex == null? new Vertex (): vertex;}
  public void setKeys (List <String> keys) {this.keys = keys == null? new ArrayList <String> (): keys;}

  public String commentMarker () {if (this.commentMarker == null) this.commentMarker = commentMarkerDefault; return this.commentMarker;}
  public String templateEOF () {if (this.templateEOF == null) this.templateEOF = templateEOFDefault; return this.templateEOF;}
  public String templateMargin () {if (this.templateMargin == null) this.templateMargin = templateMarginDefault; return this.templateMargin;}
  public String templateOpener () {if (this.templateOpener == null) this.templateOpener = templateOpenerDefault; return this.templateOpener;}
  public String templateCloser () {if (this.templateCloser == null) this.templateCloser = templateCloserDefault; return this.templateCloser;}
  public Map <String, Symbol> symbols () {if (this.symbols == null) this.symbols = new HashMap <String, Symbol> (); return this.symbols;}
  public Map <String, Relation> relations () {if (this.relations == null) this.relations = new HashMap <String, Relation> (); return this.relations;}
  public Map <String, Record> records () {if (this.records == null) this.records = new HashMap <String, Record> (); return this.records;}
  public Map <String, Function> functions () {if (this.functions == null) this.functions = new HashMap <String, Function> (); return this.functions;}
  public Procedure procedure () {if (this.procedure == null) this.procedure = new Procedure (); return this.procedure;}
  public Template template () {if (this.template == null) this.template = new Template (); return this.template;}
  public Vertex vertex () {if (this.vertex == null) this.vertex = new Vertex (); return this.vertex;}
  public List <String> keys () {if (this.keys == null) this.keys = new ArrayList <String> (); return this.keys;}

  @Override public int hashCode ()
  {
    Integer code = 0;
    code += this.symbols ().hashCode ();
    code += this.templateEOF ().hashCode ();
    code += this.templateMargin ().hashCode ();
    code += this.templateOpener ().hashCode ();
    code += this.templateCloser ().hashCode ();
    code += this.symbols ().hashCode ();
    code += this.relations ().hashCode ();
    code += this.records ().hashCode ();
    code += this.functions ().hashCode ();
    code += this.procedure ().hashCode ();
    code += this.template ().hashCode ();
    code += this.vertex ().hashCode ();
    code += this.keys ().hashCode ();
    return code;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Schema)
    {
      Boolean equals = true;
      Schema that = (Schema) object;
      equals &= this.commentMarker ().equals (that.commentMarker ());
      equals &= this.templateEOF ().equals (that.templateEOF ());
      equals &= this.templateMargin ().equals (that.templateMargin ());
      equals &= this.templateOpener ().equals (that.templateOpener ());
      equals &= this.templateCloser ().equals (that.templateCloser ());
      equals &= this.symbols ().equals (that.symbols ());
      equals &= this.relations ().equals (that.relations ());
      equals &= this.records ().equals (that.records ());
      equals &= this.functions ().equals (that.functions ());
      equals &= this.procedure ().equals (that.procedure ());
      equals &= this.template ().equals (that.template ());
      equals &= this.vertex ().equals (that.vertex ());
      equals &= this.keys ().equals (that.keys ());
      return equals;
    }
    else return false;
  }

  @Override public String toString ()
  {
    String o = "<";
    String c = ">";
    String s = " | ";
    return new StringBuffer ()
    .append (o).append (this.commentMarker ().toString ())
    .append (s).append (this.templateEOF ().toString ())
    .append (s).append (this.templateMargin ().toString ())
    .append (s).append (this.templateOpener ().toString ())
    .append (s).append (this.templateCloser ().toString ())
    .append (s).append (this.symbols ().toString ())
    .append (s).append (this.relations ().toString ())
    .append (s).append (this.records ().toString ())
    .append (s).append (this.functions ().toString ())
    .append (s).append (this.procedure ().toString ())
    .append (s).append (this.template ().toString ())
    .append (s).append (this.vertex ().toString ())
    .append (s).append (this.keys ().toString ())
    .append (c)
    .toString ();
  }

  @Override public Object clone ()
  throws CloneNotSupportedException
  {
    throw new UnsupportedOperationException ();
  }

  public String dump ()
  {
    StringBuffer dump = new StringBuffer ();
    dump.append (end + sectorMarker + space + sectorSymbols + end);
    dump.append (commentMarkerName + space + this.commentMarker () + end);
    dump.append (templateEOFName + space + this.templateEOF () + end);
    dump.append (templateMarginName + space + this.templateMargin () + end);
    dump.append (templateOpenerName + space + this.templateOpener () + end);
    dump.append (templateCloserName + space + this.templateCloser () + end);
    for (Symbol symbol: this.symbols ().values ())
      dump.append (symbol.name () + space + symbol.value () + end);
    dump.append (end + sectorMarker + space + sectorRelations + end);
    for (Relation relation: this.relations ().values ())
      dump.append (relation.id () + space + relation.names () + end);
    dump.append (end + sectorMarker + space + sectorRecords + end);
    for (String key: this.keys ())
    {
      Record record = this.record (key);
      dump.append (record.ref () + space + record.names () + space + record.values () + end);
    }
    dump.append (end + sectorMarker + space + sectorFunctions + end);
    for (Function function: this.functions ().values ())
      dump.append (function.signature () + space + function.body () + end);
    dump.append (end + sectorMarker + space + sectorProcedure + end);
    dump.append (this.procedure ().statements () + end);
    dump.append (end + sectorMarker + space + sectorTemplate + end);
    dump.append (this.template () + end);
    dump.append (end + sectorMarker + space + "vertex" + end);
    dump.append (this.vertex ().toString () + end);
    return dump.toString ();
  }



  public void putSymbol (Symbol symbol)
  {
    this.symbols ().put (symbol.name (), symbol);
  }

  public void putRelation (Relation relation)
  {
    this.relations ().put (relation.id (), relation);
  }

  public void putRecord (Record record)
  {
    this.records ().put (record.key (), record);
  }

  public void putFunction (Function function)
  {
    this.functions ().put (function.key (), function);
  }

  public void addKey (String key)
  {
    if (this.keys ().contains (key)) ;
    else this.keys ().add (key);
  }



  public String symbolValue (String name)
  {
    Symbol symbol = this.symbols ().get (name);
    if (symbol == null) return name;
    else return symbol.value ();
  }

  public Relation relation (String relationId)
  {
    return this.relations ().get (relationId);
  }

  public Record record (String key)
  {
    return this.records ().get (key);
  }

  public Function function (String key)
  {
    return this.functions ().get (key);
  }

}
