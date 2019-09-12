
package info.lrbh.codive.schema.template;

import static info.lrbh.codive.utility.Lister.first;
import static info.lrbh.codive.utility.Lister.rest;
import static info.lrbh.codive.utility.Lister.union;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import info.lrbh.codable.schema.Record;
import info.lrbh.codable.schema.template.Parsement;
import info.lrbh.codable.schema.template.parsement.Constant;
import info.lrbh.codable.schema.template.parsement.Variable;
import info.lrbh.codable.schema.template.parsement.Iteration;
import info.lrbh.codable.schema.template.parsement.Condition;
import info.lrbh.codable.schema.template.Link;

public class Linker
{

  private static List <String> keyz;
  private static Map <String, Record> recordz;
  private static Map <String, Record> context;

  public static final List <Link> linking (Parsement parsement, List <String> keyz, Map <String, Record> recordz)
  {
    Linker.keyz = keyz;
    Linker.recordz = recordz;
    Linker.context = new HashMap <String, Record> ();
    for (Record record: recordz.values ())
      if (record.isStatic ())
        Linker.context.put (record.ref (), record);
    return linking (parsement.parsements ());
  }

  private static final List <Link> linking (List <Parsement> parsementz)
  {
    if (idsLinkability (parsementz))
    {
      Parsement first = first (parsementz);
      List <Parsement> rest = rest (parsementz);
      if (first.isConstant ()) return union (constant (first), linking (rest));
      else if (first.isVariable ()) return union (variable (first), linking (rest));
      else if (first.isIteration ()) return union (iteration (first), linking (postIteration (rest)));
      else if (first.isCondition ())
        if (((Condition) first).isTrue (recordz, context)) return union (block (first), linking (postCondition (rest)));
        else return linking (rest);
      else if (first.isAlternative ()) return union (block (first), linking (postCondition (rest)));
      else if (first.isEnding ()) return new ArrayList <Link> ();
      else throw new Error ("What is this token?  " + first);
    }
    else return new ArrayList <Link> ();
  }

  private static final Boolean idsLinkability (List <Parsement> parsementz)
  {
    return parsementz != null && parsementz.size () > 0;
  }

  private static final Link constant (Parsement parsement)
  {
    return link (((Constant) parsement).value ());
  }

  private static final Link variable (Parsement parsement)
  {
    Variable variable = (Variable) parsement;
    Record record = context.get (variable.qualifier ());
    String value;
    if (record == null) value = null;
    else
    {
      String identifier = record.names ().contains (variable.identifier ())?
        variable.identifier (): variable.identifier () + "*";
      value = record.value (identifier);
    }
    return link (value == null? variable.toString (): value);
  }

  private static final List <Link> iteration (Parsement parsement)
  {
    List <Link> linkz = new ArrayList <Link> ();
    Iteration iteration = (Iteration) parsement;
    String keyPrefix = keyPrefix (iteration);
    List <Record> rz = recordz (keyPrefix, keyz, recordz);
    Integer index = 0;
    Integer total = rz.size ();
    for (Record record: rz)
    {
      record.setNVP ("xount", index.toString ());
      record.setNVP ("xotal", total.toString ());
      index += 1;
      context.put (iteration.instance (), record);
      linkz.addAll (linking (iteration.parsements ()));
    }
    context.remove (iteration.instance ());
    return linkz;
  }

  private static final List <Parsement> postIteration (List <Parsement> parsementz)
  {
    return parsementz;
  }

  private static final List <Link> block (Parsement parsement)
  {
    return linking (parsement.parsements ());
  }

  private static final List <Parsement> postCondition (List <Parsement> parsementz)
  {
    return first (parsementz).isAlternative ()?
      rest (parsementz):  /* to be: postCondition (rest (parsementz))??? */
      parsementz;
  }

  private static final String keyPrefix (Iteration iteration)
  {
    String identifier = iteration.qualifiee ();
    identifier = identifier.substring (0, identifier.length () - 1);  /* lose the final character, a 'z' */
    String qualifier = iteration.qualifier ();
    if (qualifier.isEmpty ()) return identifier;
    else
    {
      Record record = context.get (qualifier);
      if (record == null) return null;
      else return record.key ().replaceFirst (record.ref (), identifier);
    }
  }

  private static final List <Record> recordz (String keyPrefix, List <String> keyz, Map <String, Record> recordz)
  {
    List <Record> rz = new ArrayList <Record> ();
    for (String key: keyz)
      if (keyPrefix != null && key.startsWith (keyPrefix + " "))
        rz.add (recordz.get (key));
    return rz;
  }

  private static final Link link (String value)
  {
    Link link = new Link ();
    link.setValue (value);
    return link;
  }

}
