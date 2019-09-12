
package info.lrbh.codable.schema.procedure;

public class Link
implements Cloneable
{

  private String string;

  public void setString (String string) {this.string = string == null? new String (): string;}

  public String string () {if (this.string == null) this.string = new String (); return this.string;}

  @Override public int hashCode ()
  {
    Integer code = 0;
    code += this.string ().hashCode ();
    return code;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Link)
    {
      Link that = (Link) object;
      return this.string ().equals (that.string ());
    }
    else return false;
  }

  @Override public String toString ()
  {
    StringBuffer buffer = new StringBuffer ("<");
    buffer.append (this.string ().toString ());
    return buffer.append (">").toString ();
  }

  @Override public Object clone ()
  throws CloneNotSupportedException
  {
    throw new UnsupportedOperationException ();
  }

  public Boolean isEmpty ()
  {
    return this.string ().isEmpty ();
  }

}
