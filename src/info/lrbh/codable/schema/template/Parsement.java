package info.lrbh.codable.schema.template;

import java.util.List;
import java.util.ArrayList;

public class Parsement
implements Cloneable
{

  private List <Parsement> parsementz;

  public void setParsementz (List <Parsement> parsementz) {this.parsementz = parsementz == null? new ArrayList <Parsement> (): parsementz;}

  public List <Parsement> parsementz () {if (this.parsementz == null) this.setParsementz (null); return this.parsementz;}

  @Override public int hashCode ()
  {
    return
      this.parsementz ().hashCode ()
    ;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Parsement)
    {
      Parsement that = (Parsement) object;
      return
        this.parsementz ().equals (that.parsementz ())
      ;
    }
    else return false;
  }

  @Override public String toString ()
  {
    return new StringBuffer ("<")
      .append (this.parsementz ().toString ())
      .append (">").toString ()
    ;
  }

  @Override public Object clone ()
  throws CloneNotSupportedException
  {
    Object clone = null;
      clone = super.clone ();
      ((Parsement) clone).setParsementz (this.parsementz ());

    return clone;
  }

  public String toTree (String padding)
  {
    StringBuffer buffer = new StringBuffer ();
    buffer.append (padding + "+ *").append ("\n");
    for (Parsement parsement: this.parsementz ())
      buffer.append (parsement.toTree (padding + "| ")).append ("\n");
    return buffer.toString ();
  }

}
