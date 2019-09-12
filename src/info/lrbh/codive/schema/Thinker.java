
package info.lrbh.codive.schema;

import java.util.List;
import info.lrbh.codable.Schema;
import info.lrbh.codable.fsio.Line;
import info.lrbh.codable.schema.Record;
import info.lrbh.codable.schema.Relation;
import info.lrbh.codable.schema.procedure.Compilement;
import info.lrbh.codable.schema.procedure.Link;
import info.lrbh.codive.schema.procedure.Compiler;
import info.lrbh.codive.schema.procedure.Linker;

public class Thinker
{

  public static final Schema thinking (Schema schema)
  {
    List <Link> linkz = Linker.linking (schema.procedure (), schema);
    Compilement compilement = Compiler.compilation (linkz);
    for (Line line: compilement.lines ())
      intro (line, schema);
    return schema;
  }

  private static final void intro (Line line, Schema schema)
  {
    assert line.isRecord (): line;
    Relation relation = schema.relation (line.recordRelationId ());
    Record record = line.record (relation);
    String key = record.key ();
    schema.putRecord (record);
    schema.addKey (key);
  }

}
