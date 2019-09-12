package info.lrbh.codable.schema.template.parsement;

import info.lrbh.codable.schema.template.Parsement;

public class Condition
extends Parsement
implements Cloneable
{

  private String predicate;
  private Variable term;

  public void setPredicate (String predicate) {this.predicate = predicate == null? new String (): predicate;}
  public void setTerm (Variable term) {this.term = term == null? new Variable (): term;}

  public String predicate () {if (this.predicate == null) this.setPredicate (null); return this.predicate;}
  public Variable term () {if (term == null) this.setTerm (null); return this.term;}

  @Override public int hashCode ()
  {
    return
      this.predicate ().hashCode () +
      this.term ().hashCode ()
    ;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Condition)
    {
      Condition that = (Condition) object;
      return
        this.predicate ().equals (that.predicate ()) &&
        this.term ().equals (that.term ())
      ;
    }
    else return false;
  }

  @Override public String toString ()
  {
    return new StringBuffer ("<")
    .append (this.predicate ().toString ()).append (", ")
      .append (this.term ().toString ()).append (", ")
      .append (super.parsementz ())
      .append (">").toString ()
    ;
  }

  @Override public Object clone ()
  throws CloneNotSupportedException
  {
    Object clone = null;
      clone = super.clone ();
      ((Condition) clone).setPredicate (this.predicate ());
      ((Condition) clone).setTerm (this.term ());
    return clone;
  }

}
