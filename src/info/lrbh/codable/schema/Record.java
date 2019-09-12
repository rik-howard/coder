


package info.lrbh.codable.schema;

import static info.lrbh.codable.Schematic.star;
import java.util.List;
import java.util.ArrayList;

public class Record
implements Cloneable
{

  private String ref;
  private List <String> names;
  private List <String> values;

  public void setRef (String ref) {this.ref = ref == null? new String (): ref;}
  public void setNames (List <String> names) {this.names = names == null? new ArrayList <String> (): names;}
  public void setValues (List <String> values) {this.values = values == null? new ArrayList <String> (): values;}

  public String ref () {if (this.ref == null) this.ref = new String (); return this.ref;}
  public List <String> names () {if (this.names == null) this.names = new ArrayList <String> (); return this.names;}
  public List <String> values () {if (this.values == null) this.values = new ArrayList <String> (); return this.values;}

  @Override public int hashCode ()
  {
    Integer code = 0;
    code += this.ref ().hashCode ();
    code += this.names ().hashCode ();
    code += this.values ().hashCode ();
    return code;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Record)
    {
      Boolean equals = true;
      Record that = (Record) object;
      equals &= this.ref ().equals (that.ref ());
      equals &= this.names ().equals (that.names ());
      equals &= this.values ().equals (that.values ());
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
    buffer.append (this.ref ().toString ()).append (s);
    buffer.append (this.names ().toString ()).append (s);
    buffer.append (this.values ().toString ());
    return buffer.append (c).toString ();
  }

  @Override public Object clone ()
  throws CloneNotSupportedException
  {
    throw new UnsupportedOperationException ();
  }



  public void setNVP (String name, String value)
  {
    assert name != null;
    assert value != null;
    while (this.names ().size () > this.values ().size ()) this.values.add (new String ());
    if (this.names ().contains (name))
      this.values ().set (this.names ().indexOf (name), value);
    else
    {
      this.names ().add (name);
      this.values ().add (value);
    }
  }

  public Boolean isStatic ()
  {
    Boolean statik = true;
    for (String name: this.names ())
      if (name.endsWith ("*"))
        statik = false;
    return statik;
  }

  public String key ()
  {
    StringBuffer buffer = new StringBuffer (this.ref ());
    for (String name: this.names ())
      if (name.endsWith (star))
        buffer.append (" ").append (this.values ().get (this.names ().indexOf (name)));
    return buffer.toString ();
  }

  public String value (String name)
  {
    Integer index = this.names ().indexOf (name);
    if (index == -1) index = this.names ().indexOf (name + star);
    return index >= 0 && index < this.values ().size ()?
      this.values ().get (index): null;
  }

}

