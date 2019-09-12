
package info.lrbh.codive.schema.procedure;

import static info.lrbh.codive.utility.Lister.first;
import static info.lrbh.codive.utility.Lister.rest;
import static info.lrbh.codive.utility.Lister.union;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import info.lrbh.codable.Schema;
import info.lrbh.codable.schema.Procedure;
import info.lrbh.codable.schema.Record;
import info.lrbh.codable.schema.Statement;
import info.lrbh.codable.schema.procedure.Link;
import info.lrbh.codable.schema.statement.Ending;
import info.lrbh.codable.schema.statement.Introduction;
import info.lrbh.codable.schema.statement.Iteration;

public class Linker
{

  private static Schema schema;
  private static Map <String, Record> context;

  public static final List <Link> linking (Procedure procedure, Schema schema)
  {
    Linker.schema = schema;
    context = new HashMap <String, Record> ();
    for (Record record: schema.records ().values ())
      if (record.isStatic ())
        context.put (record.ref (), record);
    return linking (procedure.statements ());
  }

  private static final List <Link> linking (List <Statement> statements)
  {
    if (statements.size () > 0)
    {
      Statement first = first (statements);
      List <Statement> rest = rest (statements);
      if (first instanceof Introduction) return union (introduction (first), linking (rest)); 
      else if (first instanceof Iteration) return union (iteration (first), linking (rest));
      else if (first instanceof Ending) return linking (rest);
      else throw new Error ("What is this statement?  " + first);
    }
    else return new ArrayList <Link> ();
  }

  private static final Link introduction (Statement statement)
  {
    Link link = new Link ();
    String expressment = ((Introduction) statement).expression ().expressed (schema, context).string ();
    link.setString (expressment);
    return link;
  }

  private static final List <Link> iteration (Statement statement)
  {
    List <Link> links = new ArrayList <Link> ();
    Iteration iteration = ((Iteration) statement);
    List <Record> rs = records (iteration);
    Integer index = 0;
    Integer total = rs.size ();
    for (Record record: rs)
    {
      record.setNVP ("xount", index.toString ());
      record.setNVP ("xotal", total.toString ());
      index += 1;
      context.put (iteration.instance (), record);
      links.addAll (linking (iteration.statements ()));
    }
    context.remove (iteration.instance ());
    return links;
  }

  private static final List <Record> records (Iteration iteration)
  {
    List <Record> rs = new ArrayList <Record> ();
    String keyPrefix = keyPrefix (iteration);
    for (String key: schema.keys ())
      if (keyPrefix != null && key.startsWith (keyPrefix + " "))
        rs.add (schema.record (key));
    return rs;
  }

  private static final String keyPrefix (Iteration iteration)
  {
    String qualifier = iteration.qualifier ();
    String identifier = iteration.qualifiee ();
    if (qualifier.isEmpty ()) return identifier;
    else
    {
      Record record = context.get (qualifier);
      if (record == null) return null;
      else return record.key ().replaceFirst (record.ref (), identifier);
    }
  }

}
