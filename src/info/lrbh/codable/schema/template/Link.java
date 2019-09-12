package info.lrbh.codable.schema.template;


public class Link
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
    else if (object instanceof Link)
    {
      Link that = (Link) object;
      return
        this.value ().equals (that.value ())
      ;
    }
    else return false;
  }

  @Override public String toString ()
  {
    return new StringBuffer ("<")
      .append (this.value ().toString ())
      .append (">").toString ()
    ;
  }

  @Override public Object clone ()
  throws CloneNotSupportedException
  {
    Object clone = null;
      clone = super.clone ();
      ((Link) clone).setValue (this.value ());

    return clone;
  }

}
