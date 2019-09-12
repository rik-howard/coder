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
    String o = "<";
    String c = ">";
    String s = " | ";
    return new StringBuffer ()
    .append (o).append (this.expression ().toString ())
    .append (s).append (super.toString ())
    .append (c).toString ();
  }

  @Override public Object clone ()
  {
    Object clone = null;
    try
    {
      clone = super.clone ();
      ((Alternative) clone).setExpression (this.expression ());
    }
    catch (CloneNotSupportedException e) {e.printStackTrace ();}
    return clone;
  }

}
