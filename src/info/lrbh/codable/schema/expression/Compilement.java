


package info.lrbh.codable.schema.expression;

import info.lrbh.codable.fsio.Line;

public class Compilement
implements Cloneable
{

  private Line line;

  public void setLine (Line line) {this.line = line == null? new Line (): line;}

  public Line line () {if (this.line == null) this.line = new Line (); return this.line;}

  @Override public int hashCode ()
  {
    Integer code = 0;
    code += this.line ().hashCode ();
    return code;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Compilement)
    {
      Boolean equals = true;
      Compilement that = (Compilement) object;
      equals &= this.line ().equals (that.line ());
      return equals;
    }
    else return false;
  }

  @Override public String toString ()
  {
    StringBuffer buffer = new StringBuffer ("<");
    buffer.append (this.line ().toString ());
    return buffer.append (">").toString ();
  }

  @Override public Object clone ()
  throws CloneNotSupportedException
  {
    throw new UnsupportedOperationException ();
  }

}

