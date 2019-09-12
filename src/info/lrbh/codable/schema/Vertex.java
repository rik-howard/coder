
package info.lrbh.codable.schema;

public class Vertex
implements Cloneable
{

  private String prefix;
  private String infix;
  private String suffix;
  private String symbolValueEOF;

  public void setPrefix (String prefix) {this.prefix = prefix == null? new String (): prefix;}
  public void setInfix (String infix) {this.infix = infix == null? new String (): infix;}
  public void setSuffix (String suffix) {this.suffix = suffix == null? new String (): suffix;}
  public void setSymbolValueEOF (String symbolValueEOF) {this.symbolValueEOF = symbolValueEOF == null? new String (): symbolValueEOF;}

  public String prefix () {if (this.prefix == null) this.prefix = new String (); return this.prefix;}
  public String infix () {if (this.infix == null) this.infix = new String (); return this.infix;}
  public String suffix () {if (this.suffix == null) this.suffix = new String (); return this.suffix;}
  public String symbolValueEOF () {if (this.symbolValueEOF == null) this.symbolValueEOF = new String (); return this.symbolValueEOF;}

  @Override public int hashCode ()
  {
    Integer code = 0;
    code += this.prefix ().hashCode ();
    code += this.infix ().hashCode ();
    code += this.suffix ().hashCode ();
    code += this.symbolValueEOF ().hashCode ();
    return code;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Vertex)
    {
      Boolean equals = true;
      Vertex that = (Vertex) object;
      equals &= this.prefix ().equals (that.prefix ());
      equals &= this.infix ().equals (that.infix ());
      equals &= this.suffix ().equals (that.suffix ());
      equals &= this.symbolValueEOF ().equals (that.symbolValueEOF ());
      return equals;
    }
    else return false;
  }

  @Override public String toString ()
  {
    String o = "<";
    String c = ">";
    String s = " | ";
    StringBuffer buffer = new StringBuffer ();
    buffer.append (o).append (this.prefix ().toString ());
    buffer.append (s).append (this.infix ().toString ());
    buffer.append (s).append (this.suffix ().toString ());
    buffer.append (s).append (this.symbolValueEOF ().toString ());
    buffer.append (c);
    return buffer.toString ();
  }

  @Override public Object clone ()
  throws CloneNotSupportedException
  {
    throw new UnsupportedOperationException ();
  }

  public String cleanee ()
  {
    return this.prefix () + this.infix () + this.symbolValueEOF () + this.suffix ();
  }

}
