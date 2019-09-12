
package info.lrbh.codive.schema;

import static info.lrbh.codable.Schematic.end;
import info.lrbh.codable.Schema;
import info.lrbh.codable.schema.template.Cleanment;
import info.lrbh.codable.schema.template.Compilement;
import info.lrbh.codable.schema.template.Link;
import info.lrbh.codable.schema.template.Parsement;
import info.lrbh.codable.schema.template.Token;
import info.lrbh.codive.schema.template.Cleaner;
import info.lrbh.codive.schema.template.Compiler;
import info.lrbh.codive.schema.template.Linker;
import info.lrbh.codive.schema.template.Parser;
import info.lrbh.codive.schema.template.Tokeniser;
import java.util.ArrayList;
import java.util.List;

public class Writer
{

  public static final List <String> namesWriting (Schema schema)
  {
    return values (schema, schema.vertex ().cleanee ());
  }

  public static final List <String> valuesWriting (Schema schema)
  {
    if (schema.template ().isEmpty ()) return null;
    else
    {
      String cleanee = schema.vertex ().prefix ()
      + end
      + schema.template ().string () 
      + schema.vertex ().suffix ();
      return values (schema, cleanee);
    }
  }

  public static final List <String> values (Schema schema, String cleanee)
  {if (cleanee.equals (new String ())) return new ArrayList <String> ();
    Cleanment cleanment = Cleaner.cleaning
    (
      cleanee,
      schema.templateOpener (),
      schema.templateCloser (),
      schema.templateMargin ()
    );
    List <Token> tokens = Tokeniser.tokenisation (cleanment);
    Parsement parsement = Parser.parsing (tokens);
    List <Link> links = Linker.linking (parsement, schema.keys (), schema.records ());
    List <Compilement> compilementz = Compiler.compilation (links, schema.templateEOF ());
    List <String> z = new ArrayList <String> ();
    for (Compilement compilement: compilementz)
      z.add (compilement.value ());
    return z;
  }

}
