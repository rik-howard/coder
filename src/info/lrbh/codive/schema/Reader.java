
package info.lrbh.codive.schema;

import static info.lrbh.codable.Schematic.sectorSymbols;
import static info.lrbh.codable.Schematic.sectorRelations;
import static info.lrbh.codable.Schematic.sectorRecords;
import static info.lrbh.codable.Schematic.sectorFunctions;
import static info.lrbh.codable.Schematic.sectorProcedure;
import static info.lrbh.codable.Schematic.sectorTemplate;
import static info.lrbh.codable.Schematic.commentMarkerName;
import static info.lrbh.codable.Schematic.templateEOFName;
import static info.lrbh.codable.Schematic.templateMarginName;
import static info.lrbh.codable.Schematic.templateOpenerName;
import static info.lrbh.codable.Schematic.templateCloserName;
import static info.lrbh.codable.Schematic.end;
import info.lrbh.codable.Schema;
import info.lrbh.codable.schema.Statement;
import info.lrbh.codable.schema.Relation;
import info.lrbh.codable.schema.Record;
import info.lrbh.codable.schema.template.Cleanment;
import info.lrbh.codable.schema.template.Token;
import info.lrbh.codive.schema.procedure.Parser;
import info.lrbh.codive.schema.template.Cleaner;
import info.lrbh.codive.schema.template.Tokeniser;
import info.lrbh.codable.fsio.Line;
import java.util.ArrayList;
import java.util.List;

public class Reader
{

  private static Schema schema;
  private static List <Line> relationLines;
  private static List <Line> recordLines;
  private static List <Line> functionLines;
  private static List <Line> procedureLines;
  private static List <Line> templateLines;

  public static final Schema reading (String vertexName, List <Line> templateLines, List <Line> mappingLines)
  {
    assert vertexName != null;
    initialise ();

    if (mappingLines == null) ;
    else
    {
      introSymbols (mappingLines);
      introLines (mappingLines);
    }
    if (templateLines == null) ;
    else
    {
      introSymbols (templateLines);
      introLines (templateLines);
    }

    introRelations ();
    introRecords ();
    introFunctions ();
    introProcedure ();
    introTemplate ();
    introVertex (vertexName);
    return schema;
  }

  private static final void initialise ()
  {
    relationLines = new ArrayList <Line> ();
    recordLines = new ArrayList <Line> ();
    functionLines = new ArrayList <Line> ();
    procedureLines = new ArrayList <Line> ();
    templateLines = new ArrayList <Line> ();
    schema = new Schema ();
  }

  private static final void introSymbols (List <Line> lines)
  {
    String sector = sectorTemplate;
    for (Line line: lines)
      if (line.isSector ()) sector = line.sector ();
      else if (sector.equals (sectorSymbols))
        if (line.isSymbol ()) 
          if (line.symbol ().name ().equals (commentMarkerName)) schema.setCommentMarker (line.symbol ().value ());
          else if (line.symbol ().name ().equals (templateEOFName)) schema.setTemplateEOF (line.symbol ().value ());
          else if (line.symbol ().name ().equals (templateMarginName)) schema.setTemplateMargin (line.symbol ().value ());
          else if (line.symbol ().name ().equals (templateOpenerName)) schema.setTemplateOpener (line.symbol ().value ());
          else if (line.symbol ().name ().equals (templateCloserName)) schema.setTemplateCloser (line.symbol ().value ());
          else schema.putSymbol (line.symbol ());
        else ;
      else ;
  }

  private static final void introLines (List <Line> lines)
  {
    String sector = sectorTemplate;
    for (Line line: lines)
      if (line.isSector ()) sector = line.sector ();
      else introLine (line, sector);
  }

  private static final void introLine (Line line, String sector)
  {
    if (sector.equals (sectorSymbols)) ;
    else if (sector.equals (sectorRelations))
    {
      if (line.hasComment (schema.commentMarker ())) line.removeComment (schema.commentMarker ());
      if (line.isRelation ()) relationLines.add (line);
    }
    else if (sector.equals (sectorRecords))
    {
      if (line.hasComment (schema.commentMarker ())) line.removeComment (schema.commentMarker ());
      if (line.isRecord ()) recordLines.add (line);
    }
    else if (sector.equals (sectorFunctions))
    {
      line.clean (schema.commentMarker (), schema.symbols ());
      if (line.isFunction ()) functionLines.add (line);
    }
    else if (sector.equals (sectorProcedure))
    {
      line.clean (schema.commentMarker (), schema.symbols ());
      if (line.string ().isEmpty ()) ;
      else procedureLines.add (line);
    }
    else if (sector.equals (sectorTemplate))
    {
      if (line.hasComment (schema.commentMarker ())) line.removeComment (schema.commentMarker ());
      templateLines.add (line);
    }
    else throw new Error ("." + line + "." + sector + ".");
  }

  private static final void introRelations ()
  {
    for (Line line: relationLines)
      schema.putRelation (line.relation ());
  }

  private static final void introRecords ()
  {
    for (Line line: recordLines)
    {
      String recordRelationId = line.recordRelationId ();
      Relation relation = schema.relation (recordRelationId);
      Record record = line.record (relation);
      String key = record.key ();
      schema.putRecord (record);
      schema.addKey (key);
    }
  }

  private static final void introFunctions ()
  {
    for (Line line: functionLines)
      schema.putFunction (line.function ());
  }

  private static final void introProcedure ()
  {
    List <Statement> statements = Parser.parsing (procedureLines);
    schema.procedure ().statements ().addAll (statements);
  }

  private static final void introTemplate ()
  {
    StringBuffer buffer = new StringBuffer ();
    for (Line line: templateLines) buffer.append (line.string () + end);
    schema.template ().setString (schema.template ().string () + buffer.append (schema.templateEOF ()).toString ());
  }

  private static final void introVertex (String name)
  {
    StringBuffer prefix = new StringBuffer ();
    StringBuffer infix = new StringBuffer ();
    StringBuffer suffix = new StringBuffer ();
    Cleanment cleanment = Cleaner.cleaning (name, schema.templateOpener (), schema.templateCloser (), schema.templateMargin ());
    List <Token> tokens = Tokeniser.tokenisation (cleanment);
    for (Token token: tokens)
      if (token.isIterator ()) prefix.append (token.string ());
      else if (token.isEnding ()) suffix.append (token.string ());
      else infix.append (token.string ());
    schema.vertex ().setPrefix (prefix.toString ());
    schema.vertex ().setInfix (infix.toString ());
    schema.vertex ().setSuffix (suffix.toString ());
    schema.vertex ().setSymbolValueEOF (schema.templateEOF ());
  }

}
