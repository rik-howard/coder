
package info.lrbh.codable.schema.template.parsement;

import info.lrbh.codable.schema.template.Parsement;

public class Ending
extends Parsement
{

  @Override public int hashCode ()
  {
    return 0;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Ending) return true;
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
    return new StringBuffer (o)
    .append (this.superString ())
    .append (c)
    .toString ();
  }

  @Override public Object clone ()
  throws CloneNotSupportedException
  {
    Ending clone = (Ending) super.clone ();
    return clone;
  }

}
