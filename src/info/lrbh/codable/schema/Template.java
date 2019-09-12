package info.lrbh.codable.schema;


public class Template
implements Cloneable
{

  private String string;

  public void setString (String string) {this.string = string == null? new String (): string;}

  public String string () {if (this.string == null) this.setString (null); return this.string;}

  @Override public int hashCode ()
  {
    return
      this.string ().hashCode ()
    ;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Template)
    {
      Template that = (Template) object;
      return
        this.string ().equals (that.string ())
      ;
    }
    else return false;
  }

  @Override public String toString ()
  {
    return new StringBuffer ("<")
      .append (this.string ().toString ())
      .append (">").toString ()
    ;
  }

  @Override public Object clone ()
  throws CloneNotSupportedException
  {
    Object clone = null;
      clone = super.clone ();
      ((Template) clone).setString (this.string ());

    return clone;
  }

  public Boolean isEmpty ()
  {
    return this.string ().isEmpty ();
  }

}
