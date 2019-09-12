package info.lrbh.codable.schema;


public class Symbol
implements Cloneable
{

  private String name;
  private String value;

  public void setName (String name) {this.name = name == null? new String (): name;}
  public void setValue (String value) {this.value = value == null? new String (): value;}

  public String name () {if (this.name == null) this.setName (null); return this.name;}
  public String value () {if (this.value == null) this.setValue (null); return this.value;}

  @Override public int hashCode ()
  {
    return
      this.name ().hashCode () +
      this.value ().hashCode ()
    ;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Symbol)
    {
      Symbol that = (Symbol) object;
      return
        this.name ().equals (that.name ()) &&
        this.value ().equals (that.value ())
      ;
    }
    else return false;
  }

  @Override public String toString ()
  {
    return new StringBuffer ("<")
      .append (this.name ().toString ()).append (", ")
      .append (this.value ().toString ())
      .append (">").toString ()
    ;
  }

  @Override public Object clone ()
  throws CloneNotSupportedException
  {
    Object clone = null;
      clone = super.clone ();
      ((Symbol) clone).setName (this.name ());
      ((Symbol) clone).setValue (this.value ());

    return clone;
  }

}
