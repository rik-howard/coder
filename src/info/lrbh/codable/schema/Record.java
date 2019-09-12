package info.lrbh.codable.schema;

import java.util.ArrayList;
import java.util.List;

public class Record
implements Cloneable
{

  private String ref;
  private List <String> namez;
  private List <String> valuez;

  public void setRef (String ref) {this.ref = ref == null? new String (): ref;}
  public void setNamez (List <String> namez) {this.namez = namez == null? new ArrayList <String> (): namez;}
  public void setValuez (List <String> valuez) {this.valuez = valuez == null? new ArrayList <String> (): valuez;}

  public String ref () {if (this.ref == null) this.setRef (null); return this.ref;}
  public List <String> namez () {if (this.namez == null) this.setNamez (null); return this.namez;} 
  public List <String> valuez () {if (this.valuez == null) this.setValuez (null); return this.valuez;}

  @Override public int hashCode ()
  {
    return
      this.ref ().hashCode () +
      this.namez ().hashCode () +
      this.valuez ().hashCode ()
    ;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Record)
    {
      Record that = (Record) object;
      return
        this.ref ().equals (that.ref ()) &&
        this.namez ().equals (that.namez ()) &&
        this.valuez ().equals (that.valuez ())
      ;
    }
    else return false;
  }

  @Override public String toString ()
  {
    return new StringBuffer ("<")
      .append (this.ref ().toString ()).append (", ")
      .append (this.namez ().toString ()).append (", ")
      .append (this.valuez ().toString ())
      .append (">").toString ()
    ;
  }

  @Override public Object clone ()
  throws CloneNotSupportedException
  {
    Object clone = null;
      clone = super.clone ();
      ((Record) clone).setRef (this.ref ());
      ((Record) clone).setNamez (this.namez ());
      ((Record) clone).setValuez (this.valuez ());
    return clone;
  }

  //

  public void setNVP (String name, String value)
  {
    assert name != null;
    assert value != null;
    if (this.namez ().contains (name))
      this.valuez ().set (this.namez ().indexOf (name), value);
    else
    {
      this.namez ().add (name);
      this.valuez ().add (value);
    }
  }
/*
  public void unsetNVP (String name)
  {
    assert name != null;
    this.valuez ().remove (this.namez ().indexOf (name));
    this.namez ().remove (name);
  }
*/
  public String key ()
  {
    StringBuffer buffer = new StringBuffer (this.ref ());
    for (String name: this.namez ())
      if (name.endsWith ("*"))
        buffer.append (" ").append (this.valuez ().get (this.namez ().indexOf (name)));
    return buffer.toString ();
  }

  public String value (String name)
  {
    Integer index = this.namez ().indexOf (name);
    return index >= 0 && index < this.valuez ().size ()?
      this.valuez ().get (index): null;
  }

}
