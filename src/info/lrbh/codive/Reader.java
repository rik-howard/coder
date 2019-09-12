
package info.lrbh.codive;

import static info.lrbh.codive.Logger.*;
import info.lrbh.codable.Schema;
import info.lrbh.codable.fsio.Line;
import info.lrbh.codable.schema.Record;
import info.lrbh.codable.schema.Relation;
import java.util.ArrayList;
import java.util.List;

public class Reader
{

  public static final Schema reading (List <Line> commonLinez, String vertexName, List <Line> templateLinez)
  {
    assert vertexName != null;
    Schema schema = new Schema ();
    schema.symbolz ().put ("xeof", Factory.symbol ("xeof", "<xeof>"));
    schema.symbolz ().put ("xpener", Factory.symbol ("xpener", "["));
    schema.symbolz ().put ("xloser", Factory.symbol ("xloser", "]"));
    schema.symbolz ().put ("xargin", Factory.symbol ("xargin", "|"));
    introSymbolz (commonLinez, schema);
    introRelationz (commonLinez, schema);
    introRecordz (commonLinez, schema);
    introFunctionz (commonLinez, schema);
    introProcedure (commonLinez, schema);
    introVertex (vertexName, schema);
    if (templateLinez == null) return schema;
    else
    {
      introSymbolz (templateLinez, schema);
      introRelationz (templateLinez, schema);
      introRecordz (templateLinez, schema);
      introFunctionz (templateLinez, schema);
      introProcedure (templateLinez, schema);
      introTemplate (templateLinez, schema);
      return schema;
    }
  }

  private static final void introSymbolz (List <Line> linez, Schema schema)
  {
    Boolean storing = false;
    for (Line line: linez)
      if (line.isSector ())
        if (line.sector ().equals ("symbolz")) storing = true;
        else storing = false;
      else if (storing)
        if (line.isSymbol ())
          schema.symbolz ().put (line.symbolName (), Factory.symbol (line.symbolName (), line.symbolValue ()));
        else logNonSymbol (line.string ());
      else ;
  }

  private static final void introRelationz (List <Line> linez, Schema schema)
  {
    Boolean storing = false;
    for (Line line: linez)
      if (line.isSector ())
        if (line.sector ().equals ("relationz")) storing = true;
        else storing = false;
      else if (storing)
        if (line.isRelation ())
          schema.relationz ().put (line.relationId (), Factory.relation (line.relationId (), line.relationNamez ()));
        else logNonRelation (line.string ());
      else ;
  }

  private static final void introRecordz (List <Line> linez, Schema schema)
  {
    Boolean storing = false;
    for (Line line: linez)
      if (line.isSector ())
        if (line.sector ().equals ("recordz")) storing = true;
        else storing = false;
      else if (storing)
        if (line.isRecord ())
        {
          Relation relation = schema.relationz ().get (capitalisation (line.recordRef ()));
          Record record = Factory.record (line.recordRef (), clone (relation.namez ()), line.recordValuez ()); 
          schema.recordz ().put (record.key (), record);
          schema.keyz ().add (record.key ());
        }
        else logNonRecord (line.string ());
      else ;
  }

  private static final void introFunctionz (List <Line> linez, Schema schema)
  {
    Boolean storing = false;
    for (Line line: linez)
      if (line.isSector ())
        if (line.sector ().equals ("functionz")) storing = true;
        else storing = false;
      else if (storing)
        if (line.isFunction ())
          schema.functionz ().put (line.functionSignature (), Factory.function (line.functionSignature (), line.functionBody ()));
        else logNonFunction (line.string ());
      else ;
  }

  private static final void introProcedure (List <Line> linez, Schema schema)
  {
    StringBuffer buffer = new StringBuffer ();
    Boolean storing = false;
    for (Line line: linez)
      if (line.isSector ())
        if (line.sector ().equals ("procedure")) storing = true;
        else storing = false;
      else if (storing)
        if (buffer.length () == 0) buffer.append (line.string ());
        else buffer.append ("\n").append (line.string ());
      else ;
    schema.setProcedure (Factory.procedure (buffer.toString ()));
  }

  private static final void introTemplate (List <Line> linez, Schema schema)
  {
    StringBuffer buffer = new StringBuffer ();
    Boolean storing = true;
    for (Line line: linez)
      if (line.isSector ())
        if (line.sector ().equals ("template")) storing = true;
        else storing = false;
      else if (storing)
        if (buffer.length () == 0) buffer.append (line.string ());
        else buffer.append ("\n").append (line.string ());
      else ;
    schema.setTemplate (Factory.template (buffer.toString ()));
  }

  private static final void introVertex (String name, Schema schema)
  {
    schema.setVertex (Factory.vertex (name));
  }

  private static final String capitalisation (String string)
  {
    return string.substring (0, 1).toUpperCase ()
    + string.substring (1, string.length ());
  }

  private static final List <String> clone (List <String> original)
  {
    List <String> clone = new ArrayList <String> ();
    for (String string: original)
      clone.add (string);
    return clone;
  }

}
