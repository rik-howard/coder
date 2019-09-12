


package info.lrbh.codable.schema;


public class Symbol
implements Cloneable
{

  private String name;
  private String value;

  public void setName (String name) {this.name = name == null? new String (): name;}
  public void setValue (String value) {this.value = value == null? new String (): value;}

  public String name () {if (this.name == null) this.name = new String (); return this.name;}
  public String value () {if (this.value == null) this.value = new String (); return this.value;}

  @Override public int hashCode ()
  {
    Integer code = 0;
    code += this.name ().hashCode ();
    code += this.value ().hashCode ();
    return code;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Symbol)
    {
      Boolean equals = true;
      Symbol that = (Symbol) object;
      equals &= this.name ().equals (that.name ());
      equals &= this.value ().equals (that.value ());
      return equals;
    }
    else return false;
  }

  @Override public String toString ()
  {
    String o = "<";
    String c = ">";
    String s = " | ";
    StringBuffer buffer = new StringBuffer (o);
    buffer.append (this.name ().toString ()).append (s);
    buffer.append (this.value ().toString ());
    return buffer.append (c).toString ();
  }

  @Override public Object clone ()
  throws CloneNotSupportedException
  {
    throw new UnsupportedOperationException ();
  }

}

