
package info.lrbh.codable.schema.statement;

import info.lrbh.codable.schema.Statement;

public class Iteration
extends Statement
implements Cloneable
{

  private String instance;
  private String qualifier;
  private String qualifiee;

  public void setInstance (String instance) {this.instance = instance == null? new String (): instance;}
  public void setQualifier (String qualifier) {this.qualifier = qualifier == null? new String (): qualifier;}
  public void setQualifiee (String qualifiee) {this.qualifiee = qualifiee == null? new String (): qualifiee;}

  public String instance () {if (this.instance == null) this.setInstance (null); return this.instance;}
  public String qualifier () {if (this.qualifier == null) this.setQualifier (null); return this.qualifier;}
  public String qualifiee () {if (this.qualifiee == null) this.setQualifiee (null); return this.qualifiee;}

  @Override public int hashCode ()
  {
    Integer code = 0;
    code += this.instance ().hashCode ();
    code += this.qualifier ().hashCode ();
    code += this.qualifiee ().hashCode ();
    code += super.statements ().hashCode ();
    return code;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Iteration)
    {
      Boolean equals = true;
      Iteration that = (Iteration) object;
      equals &= this.instance ().equals (that.instance ());
      equals &= this.qualifier ().equals (that.qualifier ());
      equals &= this.qualifiee ().equals (that.qualifiee ());
      equals &= super.statements ().equals (that.statements ());
      return equals;
    }
    else return false;
  }

  public String superString ()
  {
    return super.toString ();
  }

  @Override public String toString ()
  {
    String o = "<";
    String c = ">";
    String s = " | ";
    return new StringBuffer ()
    .append (o).append (this.instance ().toString ())
    .append (s).append (this.qualifier ().toString ())
    .append (s).append (this.qualifiee ().toString ())
    .append (s).append (this.superString ().toString ())
    .append (c)
    .toString ();
  }

  @Override public Object clone ()
  throws CloneNotSupportedException
  {
    throw new UnsupportedOperationException ();
  }

}

