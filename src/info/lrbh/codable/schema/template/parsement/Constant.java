
package info.lrbh.codable.schema.template.parsement;

import info.lrbh.codable.schema.template.Parsement;

public class Constant
extends Parsement
implements Cloneable
{

  private String value;

  public void setValue (String value) {this.value = value == null? new String (): value;}

  public String value () {if (this.value == null) this.setValue (null); return this.value;}

  @Override public int hashCode ()
  {
    return
      this.value ().hashCode ()
    ;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Constant)
    {
      Constant that = (Constant) object;
      return
        this.value ().equals (that.value ())
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
      .append (this.value ().toString ())
      .append (s).append (super.toString ())
      .append (c).toString ()
    ;
  }

  @Override public Object clone ()
  throws CloneNotSupportedException
  {
    Constant clone = (Constant) super.clone ();
    clone.setValue (this.value ());
    return clone;
  }

}
