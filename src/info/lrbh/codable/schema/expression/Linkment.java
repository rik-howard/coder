
package info.lrbh.codable.schema.expression;

import static info.lrbh.codable.Schematic.space;
import static info.lrbh.codable.Schematic.expressionAmper;
import static info.lrbh.codable.Schematic.expressionCloser;
import static info.lrbh.codable.Schematic.expressionComma;
import static info.lrbh.codable.Schematic.regexExpressionIdentifier;
import static info.lrbh.codable.Schematic.expressionOpener;
import static info.lrbh.codive.utility.Lister.union;
import static info.lrbh.codive.utility.Lister.empty;
import java.util.List;

public class Linkment
implements Cloneable
{

  private String string;
  private Linkment left;
  private Linkment right;

  public void setString (String string) {this.string = string == null? new String (): string;}
  public void setLeft (Linkment left) {this.left = left;}
  public void setRight (Linkment right) {this.right = right;}

  public String string () {if (this.string == null) this.string = new String (); return this.string;}
  public Linkment left () {return this.left;}
  public Linkment right () {return this.right;}

  @Override public int hashCode ()
  {
    Integer code = 0;
    code += this.string ().hashCode ();
    code += this.left () == null? 0: this.left ().hashCode ();
    code += this.right () == null? 0: this.right ().hashCode ();
    return code;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Linkment)
    {
      Boolean equals = true;
      Linkment that = (Linkment) object;
      equals &= this.string ().equals (that.string ());
      equals &= this.left () == null? that.left () == null: this.left ().equals (that.left ());
      equals &= this.right () == null? that.right () == null: this.right ().equals (that.right ());
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
    buffer.append (this.string ().toString ());
    if (this.left () == null) buffer.append (s).append ("null");
    else buffer.append (s).append (this.left ().toString ());
    if (this.right () == null)buffer.append (s).append ("null");
    else buffer.append (" ").append (this.right ().toString ());
    return buffer.append (c).toString ();
  }

  @Override public Object clone ()
  throws CloneNotSupportedException
  {
    throw new UnsupportedOperationException ();
  }




  public Boolean isLiteral ()
  {
    return ! this.string ().equals (expressionAmper)
    && ! this.string ().equals (expressionOpener + expressionCloser)
    && ! this.string ().equals (expressionComma)
    && this.left () == null
    && this.right () == null;
  }

  private Boolean isIdentifier ()
  {
    return this.string ().matches (regexExpressionIdentifier)
    && this.left () == null
    && this.right () == null;
  }

  public Boolean isArgument ()
  {
    return this.string ().equals (expressionComma)
    && this.left ().isValid ()
    && (this.right () == null
    || this.right ().isArgument ());
  }

  public Boolean isInvocation ()
  {
    return this.string ().equals (expressionOpener + expressionCloser)
    && this.left ().isIdentifier ()
    && (this.right () == null
    || this.right ().isArgument ());
  }

  public Boolean isArithmetic ()
  {
    return this.string ().equals (expressionAmper)
    && (this.left ().isInvocation ()
    || this.left ().isLiteral ())
    && this.right ().isValid ();
  }

  public Boolean isValid ()
  {
    return this.isLiteral ()
    || this.isInvocation ()
    || this.isArithmetic ();
  }

  public String invokee ()
  {
    assert this.isInvocation ();
    return this.left ().string ();
  }

  public List <String> arguments (Linkment linkment)
  {
    if (linkment == null) return empty (new String ());
    else if (linkment.isArgument ())
      return union (linkment.left ().string (), this.arguments (linkment.right ()));
    else throw new Error ();
  }

  public List <String> arguments ()
  {
    assert this.isInvocation ();
    return this.arguments (this.right ());
  }

  public String functionKey ()
  {
    return this.invokee () + space + this.arguments ().size ();
  }

}
