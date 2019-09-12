
package info.lrbh.codable.schema;

import static info.lrbh.codable.Schematic.space;
import static info.lrbh.codive.utility.Lister.union;
import static info.lrbh.codive.utility.Lister.empty;
import java.util.ArrayList;
import java.util.List;

public class Function
implements Cloneable
{

  private Expression signature;
  private Expression body;

  public void setSignature (Expression signature) {this.signature = signature == null? new Expression (): signature;}
  public void setBody (Expression body) {this.body = body == null? new Expression (): body;}

  public Expression signature () {if (this.signature == null) this.signature = new Expression (); return this.signature;}
  public Expression body () {if (this.body == null) this.body = new Expression (); return this.body;}

  @Override public int hashCode ()
  {
    Integer code = 0;
    code += this.signature ().hashCode ();
    code += this.body ().hashCode ();
    return code;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Function)
    {
      Boolean equals = true;
      Function that = (Function) object;
      equals &= this.signature ().equals (that.signature ());
      equals &= this.body ().equals (that.body ());
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
    buffer.append (o).append (this.signature ().toString ());
    buffer.append (s).append (this.body ().toString ());
    buffer.append (c);
    return buffer.toString ();
  }

  @Override public Object clone ()
  throws CloneNotSupportedException
  {
    throw new UnsupportedOperationException ();
  }

  public String name ()
  {
    return this.signature ().left ().string ();
  }

  private List <String> parameters (Expression parameter)
  {
    List <String> parameters = new ArrayList <String> ();
    if (parameter == null) return parameters;
    else if (parameter.isArgument ()) return union (parameter.left ().string (), this.parameters (parameter.right ()));
    else throw new Error ();
  }

  public List <String> parameters ()
  {
    return this.parameters (this.signature ().right ());
  }

  public String key ()
  {
    return this.name () + space + this.parameters ().size ();
  }

  public List <String> vals ()
  {
    return this.vals (this.body ());
  }

  private List <String> vals (Expression expression)
  {
    if (expression == null) return empty (new String ());
    else if (expression.isLiteralKey ()) return empty (new String ());
    else if (expression.isIdentifier ()) return union (expression.identifier (), null);
    else if (expression.isVirtualKey ()) return empty (new String ());
    else if (expression.isArgument ()) return union (this.vals (expression.left ()), this.vals (expression.argument ()));
    else if (expression.isInvocation ()) return this.vals (expression.argument ());
    else if (expression.isArithmetic ()) return union (this.vals (expression.left ()), this.vals (expression.right ()));
    else throw new Error ("untypical expression: " + expression);
  }

  public List <String> vars ()
  {
    return this.vars (this.body ());
  }

  private List <String> vars (Expression expression)
  {
    if (expression == null) return empty (new String ());
    else if (expression.isLiteralKey ()) return empty (new String ());
    else if (expression.isIdentifier ()) return empty (new String ());
    else if (expression.isVirtualKey ()) return union (expression.keyRef (), null);
    else if (expression.isArgument ()) return union (this.vars (expression.left ()), this.vars (expression.argument ()));
    else if (expression.isInvocation ()) return this.vars (expression.argument ());
    else if (expression.isArithmetic ()) return union (this.vars (expression.left ()), this.vars (expression.right ()));
    else throw new Error ("untypical expression: " + expression);
  }

}
