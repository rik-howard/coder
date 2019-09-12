
package info.lrbh.codable.schema.statement;

import info.lrbh.codable.schema.Statement;

public class Ending
extends Statement
implements Cloneable
{

  @Override public int hashCode ()
  {
    Integer code = 0;
    return code;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Ending)
    {
      Boolean equals = true;
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
    //String s = " | ";
    StringBuffer buffer = new StringBuffer ();
    buffer
    .append (o).append (this.superString ())
    .append (c);
    return buffer.toString ();
  }

  @Override public Object clone ()
  throws CloneNotSupportedException
  {
    throw new UnsupportedOperationException ();
  }

}
