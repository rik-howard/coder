
package info.lrbh.codable.schema.template.parsement;

import java.util.Map;
import info.lrbh.codable.schema.Record;
import info.lrbh.codable.schema.template.Parsement;

public class Condition
extends Parsement
implements Cloneable
{

  private String predicate;
  private String qualifier;
  private String identifier;

  public void setPredicate (String predicate) {this.predicate = predicate == null? new String (): predicate;}
  public void setQualifier (String qualifier) {this.qualifier = qualifier == null? new String (): qualifier;}
  public void setIdentifier (String identifier) {this.identifier = identifier == null? new String (): identifier;}

  public String predicate () {if (this.predicate == null) this.setPredicate (null); return this.predicate;}
  public String qualifier () {if (this.qualifier == null) this.setQualifier (null); return this.qualifier;}
  public String identifier () {if (this.identifier == null) this.setIdentifier (null); return this.identifier;}

  @Override public int hashCode ()
  {
    return
      this.predicate ().hashCode () +
      this.qualifier ().hashCode () +
      this.identifier ().hashCode ()
    ;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Condition)
    {
      Condition that = (Condition) object;
      return
        this.predicate ().equals (that.predicate ()) &&
        this.qualifier ().equals (that.qualifier ()) &&
        this.identifier ().equals (that.identifier ())
      ;
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
    return new StringBuffer (o)
    .append (this.predicate ().toString ()).append (s)
    .append (this.qualifier ().toString ()).append (s)
    .append (this.identifier ().toString ()).append (s)
    .append (this.superString ()).append (c).toString ();
  }

  @Override public Object clone ()
  {
    Object clone = null;
    try
    {
      clone = super.clone ();
      ((Condition) clone).setPredicate (this.predicate ());
      ((Iteration) clone).setQualifier (this.qualifier ());
      ((Iteration) clone).setQualifiee (this.identifier ());
    }
    catch (CloneNotSupportedException e) {e.printStackTrace ();}
    return clone;
  }



  public Boolean isTrue (Map <String, Record> recordz, Map <String, Record> context)
  {
    String predicate = this.predicate ();
    if (predicate.equals ("exists")) return this.exists (recordz, context);
    else if (predicate.equals ("isFirst")) return this.isFirst (context.get (this.qualifier ())); 
    else if (predicate.equals ("isLast")) return this.isLast (context.get (this.qualifier ()));
    else if (predicate.equals ("nxists")) return ! this.exists (recordz, context);
    else if (predicate.equals ("nsFirst")) return ! this.isFirst (context.get (this.qualifier ())); 
    else if (predicate.equals ("nsLast")) return ! this.isLast (context.get (this.qualifier ()));
    else throw new Error ("unrecognised predicate: " + this.toString ());
  }

  private Boolean exists (Map <String, Record> recordz, Map <String, Record> context)
  {
    if (this.qualifier ().isEmpty ()) return false;
    else if (this.identifier ().isEmpty ())
      if (recordz.containsKey (this.qualifier ())) return true;
      else if (this.qualifier ().endsWith ("z"))
      {
        for (String string: recordz.keySet ())
          if (string.startsWith (this.qualifier ().substring (0, this.qualifier ().length () - 1) + " "))
            return true;
          return false;
      }
      else return false;
    else if (this.identifier ().endsWith ("z"))
    {
      Record record = context.get (this.qualifier ());
      if (record == null) return false;
      String keyPrefix = this.identifier ().substring (0, this.identifier ().length () - 1);
      //System.err.println (record+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
      keyPrefix = record.key ().replaceFirst (record.ref (), keyPrefix);
      //System.out.println (keyPrefix+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
      for (String string: recordz.keySet ())
      {
        //System.out.println (string);
        if (string.startsWith (keyPrefix + " "))
          return true;
      }
      return false;
    }
    else return this.exists (context.get (this.qualifier ()));
  }

  private Boolean exists (Record record)
  {
    if (record == null) return false;
    else
    {
      String value = record.value (this.identifier ());
      return value != null && ! (value.equals ("null") || value.equals ("x"));
    }
  }

  private Boolean isFirst (Record record)
  {
    Integer xount = Integer.valueOf (record.value ("xount"));
    return xount.equals (0);
  }

  private Boolean isLast (Record record)
  {
    Integer xount = Integer.valueOf (record.value ("xount"));
    Integer xotal = Integer.valueOf (record.value ("xotal"));
    return xount.equals (xotal - 1);
  }

}
