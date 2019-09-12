
package info.lrbh.codable.schema.template;

public class Cleanment
implements Cloneable
{

  private String string;
  private String opener;
  private String closer;

  public void setString (String string) {this.string = string == null? new String (): string;}
  public void setOpener (String opener) {this.opener = opener == null? new String (): opener;}
  public void setCloser (String closer) {this.closer = closer == null? new String (): closer;}

  public String string () {if (this.string == null) this.setString (null); return this.string;}
  public String opener () {if (this.opener == null) this.setOpener (null); return this.opener;}
  public String closer () {if (this.closer == null) this.setCloser (null); return this.closer;}

  @Override public int hashCode ()
  {
    return
      this.string ().hashCode () +
      this.opener ().hashCode () +
      this.closer ().hashCode ()
    ;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Cleanment)
    {
      Cleanment that = (Cleanment) object;
      return
        this.string ().equals (that.string ()) &&
        this.opener ().equals (that.opener ()) &&
        this.closer ().equals (that.closer ())
      ;
    }
    else return false;
  }

  @Override public String toString ()
  {
    return new StringBuffer ("<")
      .append (this.string ().toString ()).append (" | ")
      .append (this.opener ().toString ()).append (" | ")
      .append (this.closer ().toString ())
      .append (">").toString ()
    ;
  }

  @Override public Object clone ()
  {
    Object clone = null;
    try
    {
      clone = super.clone ();
      ((Cleanment) clone).setString (this.string ());
      ((Cleanment) clone).setOpener (this.opener ());
      ((Cleanment) clone).setCloser (this.closer ());
    }
    catch (CloneNotSupportedException e) {e.printStackTrace ();}
    return clone;
  }

}
