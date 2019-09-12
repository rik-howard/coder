
package info.lrbh.codive.schema.expression;

import java.util.Map;
import info.lrbh.codable.Schema;
import info.lrbh.codable.schema.Record;
import info.lrbh.codable.schema.expression.Linkment;
import info.lrbh.codable.schema.Expression;

public class Linker
implements Cloneable
{

  private static Schema schema;
  private static Map <String, Record> context;

  public static final Linkment linking (Expression parsement, Schema schema, Map <String, Record> context)
  {if (parsement.equals (new Expression ())) return new Linkment ();
    Linker.schema = schema;
    Linker.context = context;
    return linking (parsement);
  }

  private static final Linkment linking (Expression parsement)
  {
    if (parsement.isLiteralKey ()) return literal (parsement);
    else if (parsement.isIdentifier ()) return identifiee (parsement);
    else if (parsement.isVirtualKey ()) return virtual (parsement);
    else if (parsement.isInvocation ()) return evaluation (parsement);
    else if (parsement.isArithmetic ())  return arithmetion (parsement);
    else throw new Error ("untypical parsement: " + parsement);
  }

  private static final Linkment literal (Expression parsement)
  {
    Linkment expressment = new Linkment ();
    String key = parsement.literalKey ();
    String literal = schema.symbolValue (key);
    expressment.setString (literal);
    return expressment;
  }

  private static final Linkment identifiee (Expression parsement)
  {
    Linkment expressment = new Linkment ();
    String identifier = parsement.identifier ();
    String identifiee = schema.symbolValue (identifier);
    expressment.setString (identifiee);
    return expressment;
  }

  private static final Linkment virtual (Expression parsement)
  {
    Linkment linkment = new Linkment ();
    String ref = parsement.keyRef ();
    String name = parsement.keyName ();
    Record record = context.get (ref);
    String value = record.value (name);
    linkment.setString (value);
    return linkment;
  }

  private static final Linkment evaluation (Expression parsement)
  {
    String string = parsement.string ();
    Linkment left = new Linkment ();
    left.setString (parsement.left ().string ());
    Linkment linkment = new Linkment ();
    linkment.setString (string);
    linkment.setLeft (left);
    if (parsement.right () == null) linkment.setRight (null);
    else linkment.setRight (conversion (parsement.right ()));
    return linkment;
  }

  private static final Linkment conversion (Expression parsement)
  {
    assert parsement.isArgument (): parsement;
    String string = parsement.string ();
    Linkment left = new Linkment ();
    left.setString (parsement.left ().expressed (schema, context).string ());
    Linkment right;
    if (parsement.right () == null) right = null;
    else right = conversion (parsement.right ());
    Linkment linkment = new Linkment ();
    linkment.setString (string);
    linkment.setLeft (left);
    linkment.setRight (right);
    return linkment;
  }

  private static final Linkment arithmetion (Expression parsement)
  {
    assert parsement.isArithmetic (): parsement;
    Linkment linkment = new Linkment ();
    linkment.setString (parsement.string ());
    linkment.setLeft (linking (parsement.left ()));
    linkment.setRight (linking (parsement.right ()));
    return linkment;
  }

}
