
package info.lrbh.codable.schema.template.parsement;

import info.lrbh.codable.schema.template.Parsement;

public class Iteration
extends Parsement
implements Cloneable
{

  private String instance;
  private String qualifier;
  private String qualifiee;

  public void setInstance (String instance) {this.instance = instance == null? new String (): instance;}
  public void setQualifier (String qualifier) {this.qualifier = qualifier == null? new String (): qualifier;}
  public void setQualifiee (String qualifiee) {this.qualifiee = qualifiee == null? new String (): qualifiee;}

  public String instance () {if (this.instance == null) this.setInstance (null); return this.instance;}
  public String qualifier () {if (this.qualifier == null) this.setQualifier (null); return this.qualifier;}
  public String qualifiee () {if (this.qualifiee == null) this.setQualifiee (null); return this.qualifiee;}

  @Override public int hashCode ()
  {
    return
      this.instance ().hashCode () +
      this.qualifier ().hashCode () +
      this.qualifiee ().hashCode ()
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
        this.qualifiee ().equals (that.qualifiee ()) &&
        super.equals ((Parsement) that)
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
      .append (this.instance ().toString ()).append (s)
      .append (this.qualifier ().toString ()).append (s)
      .append (this.qualifiee ().toString ()).append (s)
      .append (super.toString ())
      .append (c).toString ()
    ;
  }

  @Override public Object clone ()
  {
    Object clone = null;
    try
    {
      clone = super.clone ();
      ((Iteration) clone).setInstance (this.instance ());
      ((Iteration) clone).setQualifier (this.qualifier ());
      ((Iteration) clone).setQualifiee (this.qualifiee ());
    }
    catch (CloneNotSupportedException e) {e.printStackTrace ();}
    return clone;
  }

}
