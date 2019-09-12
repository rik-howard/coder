
package info.lrbh.codable.schema;

import info.lrbh.codable.Schema;
import info.lrbh.codable.Schematic;
import info.lrbh.codable.schema.Expression;
import info.lrbh.codable.schema.expression.Linkment;
import info.lrbh.codable.schema.expression.Compilement;
import info.lrbh.codive.schema.expression.Linker;
import info.lrbh.codive.schema.expression.Compiler;
import info.lrbh.codable.fsio.Line;
import java.util.Map;

public class Expression
implements Cloneable
{

  private String string;
  private Expression left;
  private Expression right;

  public void setString (String string) {this.string = string == null? new String (): string;}
  public void setLeft (Expression left) {this.left = left;}
  public void setRight (Expression right) {this.right = right;}

  public String string () {if (this.string == null) this.string = new String (); return this.string;}
  public Expression left () {return this.left;}
  public Expression right () {return this.right;}

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
    else if (object instanceof Expression)
    {
      Boolean equals = true;
      Expression that = (Expression) object;
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
    String sep = " | ";
    StringBuffer buffer = new StringBuffer (o);
    buffer.append (this.string ().toString ());
    if (this.left () == null) buffer.append (sep).append ("null");
    else buffer.append (sep).append (this.left ().toString ());
    if (this.right () == null) buffer.append (sep).append ("null");
    else buffer.append (sep).append (this.right ().toString ());
    return buffer.append (c).toString ();
  }

  @Override public Object clone ()
  throws CloneNotSupportedException
  {
    throw new UnsupportedOperationException ();
  }



  public Boolean isLiteralKey ()
  {
    return this.string ().matches (Schematic.regexExpressionLiteralKey)
    && this.left () == null
    && this.right () == null;
  }

  public Boolean isIdentifier ()
  {
    return this.string ().matches (Schematic.regexExpressionIdentifier)
    && this.left () == null
    && this.right () == null;
  }

  public Boolean isVirtualKey ()
  {
    return this.string ().equals (Schematic.expressionDot)
    && this.left ().isIdentifier ()
    && (this.right ().isIdentifier ()
    || this.right ().isVirtualKey ());
  }

  public Boolean isArgument ()
  {
    return this.string ().equals (Schematic.expressionComma)
    && this.left ().isValid ()
    && (this.right () == null
    || this.right ().isArgument ());
  }

  public Boolean isInvocation ()
  {
    return this.string ().equals (Schematic.expressionOpener + Schematic.expressionCloser)
    && this.left ().isIdentifier ()
    && (this.right () == null
    || this.right ().isArgument ());
  }

  public Boolean isArithmetic ()
  {
    return this.string ().equals (Schematic.expressionAmper)
    && (this.left ().isInvocation ()
    || this.left ().isVirtualKey ()
    || this.left ().isIdentifier ()
    || this.left ().isLiteralKey ())
    && this.right ().isValid ();
  }

  public Boolean isValid ()
  {
    return this.isLiteralKey ()
    || this.isIdentifier ()
    || this.isVirtualKey ()
    || this.isInvocation ()
    || this.isArithmetic ();
  }



  public String literalKey ()
  {
    assert this.isLiteralKey ();
    return this.string ();
  }

  public String identifier ()
  {
    assert this.isIdentifier ();
    return this.string ();
  }

  public String keyRef ()
  {
    assert this.isVirtualKey ();
    return this.left ().identifier ();
  }

  public String keyName ()
  {
    assert this.isVirtualKey ();
    if (this.right ().isIdentifier ()) return this.right ().identifier ();
    else return this.right ().keyRef ()
    + Schematic.expressionDot
    + this.right ().keyName ();
  }

  public Expression argument ()
  {
    assert this.isInvocation () || this.isArgument ();
    return this.right ();
  }



  public Line expressed (Schema schema, Map <String, Record> context)
  {
    Linkment linkment = Linker.linking (this, schema, context);
    Compilement compilement = Compiler.compilation (linkment, schema, context);
    return compilement.line ();
  }

}

