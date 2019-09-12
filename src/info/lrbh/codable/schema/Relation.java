package info.lrbh.codable.schema;

import java.util.List;
import java.util.ArrayList;

public class Relation
implements Cloneable
{

  private String id;
  private List <String> namez;

  public void setId (String id) {this.id = id == null? new String (): id;}
  public void setNamez (List <String> namez) {this.namez = namez == null? new ArrayList <String> (): namez;}

  public String id () {if (this.id == null) this.setId (null); return this.id;}
  public List <String> namez () {if (this.namez == null) this.setNamez (null); return this.namez;}

  @Override public int hashCode ()
  {
    return
      this.id ().hashCode () +
      this.namez ().hashCode ()
    ;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Relation)
    {
      Relation that = (Relation) object;
      return
        this.id ().equals (that.id ()) &&
        this.namez ().equals (that.namez ())
      ;
    }
    else return false;
  }

  @Override public String toString ()
  {
    return new StringBuffer ("<")
      .append (this.id ().toString ()).append (", ")
      .append (this.namez ().toString ())
      .append (">").toString ()
    ;
  }

  @Override public Object clone ()
  throws CloneNotSupportedException
  {
    Object clone = null;
      clone = super.clone ();
      ((Relation) clone).setId (this.id ());
      ((Relation) clone).setNamez (this.namez ());

    return clone;
  }

}
