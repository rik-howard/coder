
package info.lrbh.codable.schema.procedure;

import java.util.List;
import java.util.ArrayList;
import info.lrbh.codable.fsio.Line;

public class Compilement
implements Cloneable
{

  private List <Line> linez;

  public void setLinez (List <Line> linez) {this.linez = linez == null? new ArrayList <Line> (): linez;}

  public List <Line> lines () {if (this.linez == null) this.linez = new ArrayList <Line> (); return this.linez;}

  @Override public int hashCode ()
  {
    Integer code = 0;
    code += this.lines ().hashCode ();
    return code;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Compilement)
    {
      Compilement that = (Compilement) object;
      return this.lines ().equals (that.lines ());
    }
    else return false;
  }

  @Override public String toString ()
  {
    StringBuffer buffer = new StringBuffer ("<");
    buffer.append (this.lines ().toString ());
    return buffer.append (">").toString ();
  }

  @Override public Object clone ()
  throws CloneNotSupportedException
  {
    throw new UnsupportedOperationException ();
  }

}
