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
implements Cloneable
{

  private Map <String, Symbol> symbolz;
  private Map <String, Relation> relationz;
  private Map <String, Record> recordz;
  private Map <String, Function> functionz;
  private Procedure procedure;
  private Template template;
  private Vertex vertex;
  private List <String> keyz;

  public void setSymbolz (Map <String, Symbol> symbolz) {this.symbolz = symbolz == null? new HashMap <String, Symbol> (): symbolz;}
  public void setRelationz (Map <String, Relation> relationz) {this.relationz = relationz == null? new HashMap <String, Relation> (): relationz;}
  public void setRecordz (Map <String, Record> recordz) {this.recordz = recordz == null? new HashMap <String, Record> (): recordz;}
  public void setFunctionz (Map <String, Function> functionz) {this.functionz = functionz == null? new HashMap <String, Function> (): functionz;}
  public void setProcedure (Procedure procedure) {this.procedure = procedure == null? new Procedure (): procedure;}
  public void setTemplate (Template template) {this.template = template == null? new Template (): template;}
  public void setVertex (Vertex vertex) {this.vertex = vertex == null? new Vertex (): vertex;}
  public void setKeyz (List <String> keyz) {this.keyz = keyz == null? new ArrayList <String> (): keyz;}

  public Map <String, Symbol> symbolz () {if (this.symbolz == null) this.setSymbolz (null); return this.symbolz;}
  public Map <String, Relation> relationz () {if (this.relationz == null) this.setRelationz (null); return this.relationz;}
  public Map <String, Record> recordz () {if (this.recordz == null) this.setRecordz (null); return this.recordz;}
  public Map <String, Function> functionz () {if (this.functionz == null) this.setFunctionz (null); return this.functionz;}
  public Procedure procedure () {if (this.procedure == null) this.setProcedure (null); return this.procedure;}
  public Template template () {if (this.template == null) this.setTemplate (null); return this.template;}
  public Vertex vertex () {if (this.vertex == null) this.setVertex (null); return this.vertex;}
  public List <String> keyz () {if (this.keyz == null) this.setKeyz (null); return this.keyz;}

  @Override public int hashCode ()
  {
    return
      this.symbolz ().hashCode () +
      this.relationz ().hashCode () +
      this.recordz ().hashCode () +
      this.functionz ().hashCode () +
      this.procedure ().hashCode () +
      this.template ().hashCode () +
      this.vertex ().hashCode () +
      this.keyz ().hashCode ()
    ;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Schema)
    {
      Schema that = (Schema) object;
      return
        this.symbolz ().equals (that.symbolz ()) &&
        this.relationz ().equals (that.relationz ()) &&
        this.recordz ().equals (that.recordz ()) &&
        this.functionz ().equals (that.functionz ()) &&
        this.procedure ().equals (that.procedure ()) &&
        this.template ().equals (that.template ()) &&
        this.vertex ().equals (that.vertex ()) &&
        this.keyz ().equals (that.keyz ())
      ;
    }
    else return false;
  }

  @Override public String toString ()
  {
    return new StringBuffer ("<")
      .append (this.symbolz ().toString ()).append (", ")
      .append (this.relationz ().toString ()).append (", ")
      .append (this.recordz ().toString ()).append (", ")
      .append (this.functionz ().toString ()).append (", ")
      .append (this.procedure ().toString ()).append (", ")
      .append (this.template ().toString ()).append (", ")
      .append (this.vertex ().toString ()).append (", ")
      .append (this.keyz ().toString ())
      .append (">").toString ()
    ;
  }

  @Override public Object clone ()
  throws CloneNotSupportedException
  {
    Object clone = null;
      clone = super.clone ();
      ((Schema) clone).setSymbolz (this.symbolz ());
      ((Schema) clone).setRelationz (this.relationz ());
      ((Schema) clone).setRecordz (this.recordz ());
      ((Schema) clone).setFunctionz (this.functionz ());
      ((Schema) clone).setProcedure (this.procedure ());
      ((Schema) clone).setTemplate (this.template ());
      ((Schema) clone).setVertex (this.vertex ());
      ((Schema) clone).setKeyz (this.keyz ());

    return clone;
  }

}
