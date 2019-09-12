


package info.lrbh.codable.schema.statement;

import info.lrbh.codable.schema.Expression;
import info.lrbh.codable.schema.Statement;

public class Introduction
extends Statement
implements Cloneable
{

  private Expression expression;

  public void setExpression (Expression expression) {this.expression = expression == null? new Expression (): expression;}

  public Expression expression () {if (this.expression == null) this.expression = new Expression (); return this.expression;}

  @Override public int hashCode ()
  {
    Integer code = 0;
    code += this.expression ().hashCode ();
    return code;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Introduction)
    {
      Boolean equals = true;
      Introduction that = (Introduction) object;
      equals &= this.expression ().equals (that.expression ());
      return equals;
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
    StringBuffer buffer = new StringBuffer ();
    buffer.append (o).append (this.expression ().toString ());
    buffer.append (s).append (this.superString ());
    buffer.append (c);
    return buffer.toString ();
  }

  @Override public Object clone ()
  throws CloneNotSupportedException
  {
    throw new UnsupportedOperationException ();
  }

}

