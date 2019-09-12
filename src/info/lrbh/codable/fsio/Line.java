
package info.lrbh.codable.fsio;

import java.util.ArrayList;
import java.util.List;

public class Line
implements Cloneable
{



  String string;

  public void setString (String string) {this.string = string == null? new String (): string;}

  public String string () {if (this.string == null) this.setString (null); return this.string;}


  public Line (String string)
  {
    super ();
    this.setString (string);
  }





  @Override public int hashCode ()
  {
    return this.string ().hashCode ();
  }


  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Line)
    {
      Line that = (Line) object;
      return this.string ().equals (that.string ());
    }
    else return false;
  }


  @Override public String toString ()
  {
    return this.string ();
  }


  @Override public Object clone ()
  {
    Object clone = null;
    try
    {
      clone = super.clone ();
      ((Line) clone).setString (this.string ());
    }
    catch (CloneNotSupportedException e) {e.printStackTrace ();}
    return clone;
  }



  public Boolean isSector ()
  {
    return this.string ().matches ("^> [a-z]+$");
  }


  public Boolean isSymbol ()
  {
    return this.string ().contains (":")
    && this.string ().split (":") [0].matches ("(xeof|xpener|xloser|xlausal|xargin)")
    && this.string ().split (":") [1].trim ().matches ("^[^\\ ]*$");
  }


  public Boolean isRelation ()
  {
    if (this.string ().contains (":"))
    {
      Boolean ids = this.string ().split (":") [0].matches ("^[A-Z][a-zA-Z0-9]*$");
      for (String s: this.string ().split (":") [1].trim ().split (" "))
        ids &= s.matches ("^[a-z][a-zA-Z0-9\\.]*\\*?$");
      return ids;
    }
    else return false;
  }


  public Boolean isRecord ()
  {
    if (this.string ().contains (":"))
    {
      Boolean ids = this.string ().split (":") [0].matches ("^[a-z][a-zA-Z0-9]*$");
      for (String s: this.string ().split (":") [1].trim ().split (" "))
        ids &= s.matches ("^[^\\ ]*$");
      return ids;
    }
    else return false;
  }


  public Boolean isFunction ()
  {
    if (this.string ().contains (":"))
      return this.string ().split (":") [0].matches ("^[a-z][a-zA-Z0-9]* \\([a-z][a-zA-Z0-9]*\\)$")
      && this.string ().split (":") [1].trim ().matches ("^.+$");
    else return false;
  }



  public String sector ()
  {
    assert this.isSector ();
    return this.string ().substring (2);
  }

  public String symbolName ()
  {
    assert this.isSymbol ();
    return this.string ().split (":") [0].trim ();
  }

  public String symbolValue ()
  {
    assert this.isSymbol ();
    return this.string ().split (":") [1].trim ();
  }

  public String relationId ()
  {
    assert this.isRelation ();
    return this.string ().split (":") [0].trim ();
  }

  public List <String> relationNamez ()
  {
    assert this.isRelation ();
    List <String> namez = new ArrayList <String> ();
    for (String name: this.string ().split (":") [1].trim ().split (" ")) namez.add (name);
    return namez;
  }

  public String recordRef ()
  {
    assert this.isRecord ();
    return this.string ().split (":") [0].trim ();
  }

  public List <String> recordValuez ()
  {
    assert this.isRecord ();
    List <String> valuez = new ArrayList <String> ();
    for (String value: this.string ().split (":") [1].trim ().split (" ")) valuez.add (value);
    return valuez;
  }

  public String functionSignature ()
  {
    assert this.isFunction ();
    return this.string ().split (":") [0].trim ();
  }

  public String functionBody ()
  {
    assert this.isFunction ();
    return this.string ().split (":") [1].trim ();
  }



}
