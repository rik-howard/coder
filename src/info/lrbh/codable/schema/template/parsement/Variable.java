
package info.lrbh.codable.schema.template.parsement;

import info.lrbh.codable.schema.template.Parsement;

public class Variable
extends Parsement
implements Cloneable
{

  private String qualifier;
  private String identifier;

  public void setQualifier (String qualifier) {this.qualifier = qualifier == null? new String (): qualifier;}
  public void setIdentifier (String identifier) {this.identifier = identifier == null? new String (): identifier;}

  public String qualifier () {if (this.qualifier == null) this.setQualifier (null); return this.qualifier;}
  public String identifier () {if (this.identifier == null) this.setIdentifier (null); return this.identifier;}

  @Override public int hashCode ()
  {
    return
      this.qualifier ().hashCode () +
      this.identifier ().hashCode ()
    ;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Variable)
    {
      Variable that = (Variable) object;
      return
        this.qualifier ().equals (that.qualifier ()) &&
        this.identifier ().equals (that.identifier ())
      ;
    }
    else return false;
  }

  public String superString ()
  {
    return super.toString ();
  }

  @Override public String toString ()
  {
    String o = "<";
    String c = ">";
    String s = " | ";
    return new StringBuffer (o)
      .append (this.qualifier ().toString ()).append (s)
      .append (this.identifier ().toString ()).append (s)
      .append (this.superString ())
      .append (c).toString ()
    ;
  }

  @Override public Object clone ()
  {
    Object clone = null;
    try
    {
      clone = super.clone ();
      ((Variable) clone).setQualifier (this.qualifier ());
      ((Variable) clone).setIdentifier (this.identifier ());
    }
    catch (CloneNotSupportedException e) {e.printStackTrace ();}
    return clone;
  }

}
