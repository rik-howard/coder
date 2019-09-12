


package info.lrbh.codable.schema;

import java.util.List;
import java.util.ArrayList;

public class Statement
implements Cloneable
{

  private List <Statement> statements;

  public void setStatements (List <Statement> statements) {this.statements = statements == null? new ArrayList <Statement> (): statements;}

  public List <Statement> statements () {if (this.statements == null) this.statements = new ArrayList <Statement> (); return this.statements;}

  @Override public int hashCode ()
  {
    Integer code = 0;
    code += this.statements ().hashCode ();
    return code;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Statement)
    {
      Boolean equals = true;
      Statement that = (Statement) object;
      equals &= this.statements ().equals (that.statements ());
      return equals;
    }
    else return false;
  }

  @Override public String toString ()
  {
    StringBuffer buffer = new StringBuffer ("<");
    buffer.append (this.statements ().toString ());
    return buffer.append (">").toString ();
  }

  @Override public Object clone ()
  throws CloneNotSupportedException
  {
    throw new UnsupportedOperationException ();
  }

}

