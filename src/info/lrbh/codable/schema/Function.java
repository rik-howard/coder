package info.lrbh.codable.schema;


public class Function
implements Cloneable
{

  private String signature;
  private String body;

  public void setSignature (String signature) {this.signature = signature == null? new String (): signature;}
  public void setBody (String body) {this.body = body == null? new String (): body;}

  public String signature () {if (this.signature == null) this.setSignature (null); return this.signature;}
  public String body () {if (this.body == null) this.setBody (null); return this.body;}

  @Override public int hashCode ()
  {
    return
      this.signature ().hashCode () +
      this.body ().hashCode ()
    ;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Function)
    {
      Function that = (Function) object;
      return
        this.signature ().equals (that.signature ()) &&
        this.body ().equals (that.body ())
      ;
    }
    else return false;
  }

  @Override public String toString ()
  {
    return new StringBuffer ("<")
      .append (this.signature ().toString ()).append (", ")
      .append (this.body ().toString ())
      .append (">").toString ()
    ;
  }

  @Override public Object clone ()
  throws CloneNotSupportedException
  {
    Object clone = null;
      clone = super.clone ();
      ((Function) clone).setSignature (this.signature ());
      ((Function) clone).setBody (this.body ());

    return clone;
  }

}
