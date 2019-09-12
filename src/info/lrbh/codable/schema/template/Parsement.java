
package info.lrbh.codable.schema.template;

import info.lrbh.codable.schema.template.parsement.Alternative;
import info.lrbh.codable.schema.template.parsement.Condition;
import info.lrbh.codable.schema.template.parsement.Constant;
import info.lrbh.codable.schema.template.parsement.Ending;
import info.lrbh.codable.schema.template.parsement.Iteration;
import info.lrbh.codable.schema.template.parsement.Variable;
import java.util.List;
import java.util.ArrayList;

public class Parsement
implements Cloneable
{

  private List <Parsement> parsementz;

  public void setParsements (List <Parsement> parsementz) {this.parsementz = parsementz == null? new ArrayList <Parsement> (): parsementz;}

  public List <Parsement> parsements () {if (this.parsementz == null) this.setParsements (null); return this.parsementz;}



  @Override public int hashCode ()
  {
    return
      this.parsements ().hashCode ()
    ;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Parsement)
    {
      Parsement that = (Parsement) object;
      return
        this.parsements ().equals (that.parsements ())
      ;
    }
    else return false;
  }

  @Override public String toString ()
  {
    return new StringBuffer ("<")
      .append (this.parsements ().toString ())
      .append (">").toString ()
    ;
  }

  @Override public Object clone ()
  throws CloneNotSupportedException
  {
    Parsement clone = (Parsement) super.clone ();
    clone.setParsements (this.parsements ());
    return clone;
  }



  public Boolean isConstant ()
  {
    return this instanceof Constant;
  }

  public Boolean isVariable ()
  {
    return this instanceof Variable;
  }

  public Boolean isIteration ()
  {
    return this instanceof Iteration;
  }

  public Boolean isCondition ()
  {
    return this instanceof Condition;
  }

  public Boolean isAlternative ()
  {
    return this instanceof Alternative;
  }

  public Boolean isEnding ()
  {
    return this instanceof Ending;
  }

}
