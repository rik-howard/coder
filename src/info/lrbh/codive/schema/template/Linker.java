package info.lrbh.codive.schema.template;

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
import info.lrbh.codable.schema.template.parsement.Alternative;
import info.lrbh.codable.schema.template.Link;

public class Linker
{

  private static Map <String, Record> context;

  public static final List <Link> linking (Parsement parsement, List <String> keyz, Map <String, Record> recordz)
  {
    context = new HashMap <String, Record> ();
    for (Record record: recordz.values ())
      if (idsStatic (record))
        context.put (record.ref (), record);
    return linking (parsement.parsementz (), keyz, recordz);
  }

  private static final Boolean idsStatic (Record record)
  {
    Boolean statik = true;
    for (String name: record.namez ())
      if (name.endsWith ("*"))
        statik = false;
    return statik;
  }
   
  private static final Boolean idsLinkability (List <Parsement> parsementz)
  {
    return parsementz != null && parsementz.size () > 0;
  }
   
  private static final Boolean idsConstant (Parsement parsement)
  {
    return parsement instanceof Constant;
  }
   
  private static final Boolean idsVariable (Parsement parsement)
  {
    //System.out.println (parsement+" "+(parsement instanceof Variable));
    return parsement instanceof Variable;
  }
   
  private static final Boolean idsIteration (Parsement parsement)
  {
    return parsement instanceof Iteration;
  }
   
  private static final Boolean idsCondition (Parsement parsement)
  {
    return parsement instanceof Condition;
  }
   
  private static final Boolean idsAlternative (Parsement parsement)
  {
    return parsement instanceof Alternative;
  }
   
  private static final Boolean idsTrue (Condition condition)
  {
    String function = condition.predicate ();
    String qualifier = condition.term ().qualifier ();
    String identifier = condition.term ().identifier ();
    if (function.equals ("exists"))
      if (context.get (qualifier) == null) return false;
      else if (context.get (qualifier).value (identifier) == null) return false;
      else if (context.get (qualifier).value (identifier).equals ("null")) return false;
      else return true;
    else
    {
      Integer xount = Integer.valueOf (context.get (qualifier).value ("xount"));
      Integer xotal = Integer.valueOf (context.get (qualifier).value ("xotal"));
      //assert false: xount.equals ("0");
      if (function.equals ("isFirst"))
        return xount.equals (0);
      else if (function.equals ("isLast"))
        return xount.equals (xotal - 1);
      else throw new Error ("unrecognised predicate: " + condition);  // TODO
    }
  }
   
  private static final Boolean idsTrue (Alternative alternative)
  {
    if (alternative.expression ().equals ("")) return true;
    Integer index = alternative.expression ().indexOf (".");
    String qualifier = alternative.expression ().substring (0, index);
    String identifier = alternative.expression ().substring (index + 1);
    if (context.get (qualifier) == null) return false;
    else if (context.get (qualifier).value (identifier) == null) return false;
    else if (context.get (qualifier).value (identifier).equals ("null")) return false;
    else return true;
  }
   
  private static final String keyPrefix (Iteration iteration)
  {
    String identifier = iteration.identifier ();
    identifier = identifier.substring (0, identifier.length () - 1);  // lose the final character, a 'z'
    String qualifier = iteration.qualifier ();
    if (qualifier.isEmpty ()) return identifier;
    else 
    {
      Record record = context.get (qualifier);
      assert record != null: "the first sighting";
      return record.key ().replaceFirst (record.ref (), identifier);
    }
    //System.out.println (context + "\n\n");
    //if (iteration.qualifier ().equals ("")) return identifier;
    //else return key (context.get (iteration.qualifier ()))
    //else return context.get (iteration.qualifier ()).key ()
    //.replaceFirst (iteration.qualifier (), identifier);
  }

  private static final List <Record> recordz (String keyPrefix, List <String> keyz, Map <String, Record> recordz)
  {
    //System.out.println (keyPrefix + "\n");
    List <Record> rz = new ArrayList <Record> ();
    for (String key: keyz)
      if (key.startsWith (keyPrefix))
        rz.add (recordz.get (key));
    return rz;
  }

  private static final Parsement first (List <Parsement> parsementz)
  {
    return parsementz.get (0);
  }

  private static final List <Parsement> rest (List <Parsement> parsementz)
  {
    List <Parsement> rest = new ArrayList <Parsement> ();
    if (parsementz.size () > 1)
      for (Integer i = 1; i < parsementz.size (); i++)
        rest.add (parsementz.get (i));
    return rest;
  }
   
  private static final List <Parsement> postIteration (List <Parsement> parsementz)
  {
    return parsementz;
  }
  
  private static final List <Parsement> postCondition (List <Parsement> parsementz)
  {
    return idsAlternative (first (parsementz))?
      rest (parsementz):  // to be: postCondition (rest (parsementz))
      parsementz;
  }
   
  private static final List <Link> constant (Parsement parsement)
  {
    List <Link> linkz = new ArrayList <Link> ();
    Link link = new Link ();
    link.setValue (((Constant) parsement).value ());
    linkz.add (link);
    return linkz;
  }
   
  private static final List <Link> variable (Parsement parsement)
  {
    //assert false: parsement;
    List <Link> linkz = new ArrayList <Link> ();
    Link link = new Link ();
    Variable variable = (Variable) parsement;
    Record record = context.get (variable.qualifier ());
    assert record != null: "a first sighting";
    String value;
    if (record == null) value = null;
    else
    {
      String identifier = record.namez ().contains (variable.identifier ())?
        variable.identifier (): variable.identifier () + "*";
      value = record.value (identifier);
    }
    link.setValue (value == null? variable.toString (): value);
    linkz.add (link);
    return linkz;
  }
   
  private static final List <Link> iteration (Parsement parsement, List <String> keyz, Map <String, Record> recordz)
  {
    List <Link> linkz = new ArrayList <Link> ();
    Iteration iteration = (Iteration) parsement;
    String keyPrefix = keyPrefix (iteration);
    List <Record> rz = recordz (keyPrefix, keyz, recordz);
    Integer index = 0;
    Integer total = rz.size ();
    //assert total > 0;
    for (Record record: rz)
    {
      //System.out.println (keyz + "\n");
      //System.out.println (recordz (keyPrefix, keyz, recordz) + "\n");
      record.setNVP ("xount", index.toString ());
      record.setNVP ("xotal", total.toString ());
      index += 1;
      context.put (iteration.instance (), record);
      //record.unsetNVP ("xotal");
      //record.unsetNVP ("xount");
      linkz.addAll (linking (iteration.parsementz (), keyz, recordz));
    }
    context.remove (iteration.instance ());
    return linkz;
  }
   
  private static final List <Link> block (Parsement parsement, List <String> keyz, Map <String, Record> recordz)
  {
    return linking (parsement.parsementz (), keyz, recordz);
  }
   
  private static final List <Link> union (List <Link> leftz, List <Link> rightz)
  {
    List <Link> union = new ArrayList <Link> ();
    if (leftz != null) union.addAll (leftz);
    if (rightz != null) union.addAll (rightz);
    return union;
  }
   
  private static final List <Link> constant (List <Parsement> parsementz, List <String> keyz, Map <String, Record> recordz)
  {
    return union (constant (first (parsementz)), linking (rest (parsementz), keyz, recordz));
  }
   
  private static final List <Link> variable (List <Parsement> parsementz, List <String> keyz, Map <String, Record> recordz)
  {
    return union (variable (first (parsementz)), linking (rest (parsementz), keyz, recordz));
  }
   
  private static final List <Link> iteration (List <Parsement> parsementz, List <String> keyz, Map <String, Record> recordz)
  {
    return union
    (
      iteration (first (parsementz), keyz, recordz),
      linking (postIteration (rest (parsementz)), keyz, recordz)
    );
  }
   
  private static final List <Link> condition (List <Parsement> parsementz, List <String> keyz, Map <String, Record> recordz)
  {
    return
      idsTrue ((Condition) first (parsementz))?
      union
      (
        block (first (parsementz), keyz, recordz),
        linking (postCondition (rest (parsementz)), keyz, recordz)
      ):
      linking (rest (parsementz), keyz, recordz)
    ;
  }
   
  private static final List <Link> alternative (List <Parsement> parsementz, List <String> keyz, Map <String, Record> recordz)
  {
    return
      idsTrue ((Alternative) first (parsementz))?
      union
      (
        block (first (parsementz), keyz, recordz),
        linking (postCondition (parsementz), keyz, recordz)
      ):
      linking (rest (parsementz), keyz, recordz)
    ;
  }
   
  private static final List <Link> linking (List <Parsement> parsementz, List <String> keyz, Map <String, Record> recordz)
  {
    if (idsLinkability (parsementz))
      if (idsConstant (first (parsementz)))
      {
        //assert false: parsementz;  // 2 4 6 8 12
        //System.out.println (parsementz);
        return constant (parsementz, keyz, recordz);
      }
      else if (idsVariable (first (parsementz)))
      {
        //assert false: parsementz;  // 3 7 11
        //System.out.println (parsementz);
        return variable (parsementz, keyz, recordz);
      }
      else if (idsIteration (first (parsementz)))
      {
        //assert false: parsementz;  // 1
        //System.out.println (parsementz);
        return iteration (parsementz, keyz, recordz);
      }
      else if (idsCondition (first (parsementz)))
      {
        //assert false: parsementz;  // 10
        //System.out.println (parsementz);
        return condition (parsementz, keyz, recordz);
      }
      else if (idsAlternative (first (parsementz)))
      {
        //assert false: parsementz;  // n
        //System.out.println (parsementz);
        return alternative (parsementz, keyz, recordz);
      }
      else
      {
        //assert false: parsementz;  // n
        //System.out.println (parsementz);  // n
        return union (null, null);
      }
    else
    {
      //assert false: parsementz;  // 5 9 13
      //System.out.println (parsementz);
      return union (null, null);
    }
  }

}
