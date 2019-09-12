package info.lrbh.codable.schema.template.parsement;

import info.lrbh.codable.schema.template.Parsement;

public class Iteration
extends Parsement
implements Cloneable
{

  private String instance;
  private String qualifier;
  private String identifier;

  public void setInstance (String instance) {this.instance = instance == null? new String (): instance;}
  public void setQualifier (String qualifier) {this.qualifier = qualifier == null? new String (): qualifier;}
  public void setIdentifier (String identifier) {this.identifier = identifier == null? new String (): identifier;}

  public String instance () {if (this.instance == null) this.setInstance (null); return this.instance;}
  public String qualifier () {if (this.qualifier == null) this.setQualifier (null); return this.qualifier;}
  public String identifier () {if (this.identifier == null) this.setIdentifier (null); return this.identifier;}

  @Override public int hashCode ()
  {
    return
      this.instance ().hashCode () +
      this.qualifier ().hashCode () +
      this.identifier ().hashCode ()
    ;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Iteration)
    {
      Iteration that = (Iteration) object;
      return
        this.instance ().equals (that.instance ()) &&
        this.qualifier ().equals (that.qualifier ()) &&
        this.identifier ().equals (that.identifier ())
      ;
    }
    else return false;
  }

  @Override public String toString ()
  {
    return new StringBuffer ("<")
      .append (this.instance ().toString ()).append (", ")
      .append (this.qualifier ().toString ()).append (", ")
      .append (this.identifier ().toString ()).append (", ")
      .append (super.parsementz ().toString ())
      .append (">").toString ()
    ;
  }

  @Override public Object clone ()
  throws CloneNotSupportedException
  {
    Object clone = null;
      clone = super.clone ();
      ((Iteration) clone).setInstance (this.instance ());
      ((Iteration) clone).setQualifier (this.qualifier ());
      ((Iteration) clone).setIdentifier (this.identifier ());

    return clone;
  }

}
