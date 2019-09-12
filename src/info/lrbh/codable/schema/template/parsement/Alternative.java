package info.lrbh.codable.schema.template.parsement;

import info.lrbh.codable.schema.template.Parsement;

public class Alternative
extends Parsement
implements Cloneable
{

  private String expression;

  public void setExpression (String expression) {this.expression = expression == null? new String (): expression;}

  public String expression () {if (this.expression == null) this.setExpression (null); return this.expression;}

  @Override public int hashCode ()
  {
    return
      this.expression ().hashCode ()
    ;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Alternative)
    {
      Alternative that = (Alternative) object;
      return
        this.expression ().equals (that.expression ())
      ;
    }
    else return false;
  }

  @Override public String toString ()
  {
    return new StringBuffer ("<")
      .append (this.expression ().toString ()).append (", ")
      .append (super.parsementz ())
      .append (">").toString ()
    ;
  }

  @Override public Object clone ()
  throws CloneNotSupportedException
  {
    Object clone = null;
      clone = super.clone ();
      ((Alternative) clone).setExpression (this.expression ());

    return clone;
  }

  public String toTree (String padding)
  {
    StringBuffer buffer = new StringBuffer ();
    buffer.append (padding + "+ " + this.expression ()).append ("\n");
    for (Parsement parsement: super.parsementz ())
      buffer.append (parsement.toTree (padding + "| ")).append ("\n");
    return buffer.toString ();
  }

}
