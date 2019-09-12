
package info.lrbh.codable.schema;

import java.util.List;
import java.util.ArrayList;

public class Relation
implements Cloneable
{

  private String id;
  private List <String> names;

  public void setId (String id) {this.id = id == null? new String (): id;}
  public void setNames (List <String> names) {this.names = names == null? new ArrayList <String> (): names;}

  public String id () {if (this.id == null) this.id = new String (); return this.id;}
  public List <String> names () {if (this.names == null) this.names = new ArrayList <String> (); return this.names;}

  @Override public int hashCode ()
  {
    Integer code = 0;
    code += this.id ().hashCode ();
    code += this.names ().hashCode ();
    return code;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Relation)
    {
      Boolean equals = true;
      Relation that = (Relation) object;
      equals &= this.id ().equals (that.id ());
      equals &= this.names ().equals (that.names ());
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
    buffer.append (this.id ().toString ()).append (s);
    buffer.append (this.names ().toString ());
    return buffer.append (c).toString ();
  }

  @Override public Relation clone ()
  {
    Relation relation = null;
    try
    {
      relation = (Relation) super.clone ();
      relation.setId (this.id ());
      List <String> names = new ArrayList <String> ();
      for (String name: this.names ())
        names.add (name);
      relation.setNames (names);
    }
    catch (CloneNotSupportedException e) {e.printStackTrace ();}
    return relation;
  }

}
