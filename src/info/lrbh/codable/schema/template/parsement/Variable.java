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

  @Override public String toString ()
  {
    return new StringBuffer ("<")
      .append (this.qualifier ().toString ()).append (", ")
      .append (this.identifier ().toString ())
      .append (">").toString ()
    ;
  }

  @Override public Object clone ()
  throws CloneNotSupportedException
  {
    Object clone = null;
      clone = super.clone ();
      ((Variable) clone).setQualifier (this.qualifier ());
      ((Variable) clone).setIdentifier (this.identifier ());

    return clone;
  }

  public String toTree (String padding)
  {
    return padding + "+ " + this.qualifier () + ": " + this.identifier () + "\n";
  }

}
