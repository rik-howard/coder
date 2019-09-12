
package info.lrbh.codive;

import info.lrbh.codable.Schema;
import info.lrbh.codable.fsio.Foldee;
import info.lrbh.codable.fsio.Folder;
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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Writer
{

  public static final void xrite (Schema schema, String targetName)
  throws IOException
  {
    Cleanment cleanment = Cleaner.cleaning
    (
      schema.vertex ().string (),
      schema.symbolz ().get ("xpener").value (),
      schema.symbolz ().get ("xloser").value (),
      null
    );
    String prefix = "";
    String infix = "";
    String suffix = "";
    for (Token token: Tokeniser.tokenisation (cleanment))
      if (token.isIteration ()) prefix += token.string ();
      else if (token.isEnding ()) suffix += token.string ();
      else infix += token.string ();
    //assert false: prefix + "\n" + infix + "\n" + suffix;
    List <String> namez = namez (schema, prefix, infix, suffix);
    List <String> valuez = valuez (schema, prefix, suffix);
    //assert false: namez;
    //assert false: valuez;
    if (namez.size () > 0)
      if (valuez.size () > 0) xriteFoldeez (namez, valuez, targetName);
      else xriteFolderz (namez, targetName);
    else ;
  }

  private static List <String> valuez (Schema schema, String prefix, String suffix)
  {
    // TODO Auto-generated method stub
    List <String> valuez = new ArrayList <String> ();
    if (schema.template ().isEmpty ()) return valuez;
    Cleanment cleanment = Cleaner.cleaning
    (
      prefix + "\n" + schema.template ().string () + schema.symbolz ().get ("xeof").value () + suffix,
      schema.symbolz ().get ("xpener").value (),
      schema.symbolz ().get ("xloser").value (),
      schema.symbolz ().get ("xargin").value ()
    );
    //assert false: cleanment;
    List <Token> tokenz = Tokeniser.tokenisation (cleanment);
    //assert false: tokenz;
    Parsement parsement = Parser.parsing (tokenz);
    //assert false: parsement;
    List <Link> linkz = Linker.linking (parsement, schema.keyz (), schema.recordz ());
    //assert false: linkz;
    List <Compilement> compilementz = Compiler.compilation (linkz, schema.symbolz ().get ("xeof").value ());
    //assert false: compilementz;
    for (Compilement compilement: compilementz)
      valuez.add (compilement.value ());
    return valuez;
    //throw new UnsupportedOperationException ();
  }

  private static List <String> namez (Schema schema, String prefix, String infix, String suffix)
  {
    Cleanment cleanment = Cleaner.cleaning
    (
      prefix + infix + schema.symbolz ().get ("xeof").value () + suffix,
      schema.symbolz ().get ("xpener").value (),
      schema.symbolz ().get ("xloser").value (),
      null
    );
    //assert false: cleanment;
    List <Token> tokenz = Tokeniser.tokenisation (cleanment);
    //assert false: tokenz;
    Parsement parsement = Parser.parsing (tokenz);
    //assert false: parsement;
    List <Link> linkz = Linker.linking (parsement, schema.keyz (), schema.recordz ());
    //assert false: linkz;
    List <Compilement> compilementz = Compiler.compilation (linkz, schema.symbolz ().get ("xeof").value ());
    //assert false: compilementz;
    List <String> namez = new ArrayList <String> ();
    for (Compilement compilement: compilementz)
      namez.add (compilement.value ());
    return namez;
  }

  private static final void xriteFoldeez (List <String> namez, List <String> valuez, String targetName)
  throws IOException
  {
    assert namez.size () == valuez.size (): namez.toString () + valuez.toString ();
    for (String name: namez)
      if (targetName == null) xriteToUser (foldee (null, name, namez, valuez));
      else if (targetName.equals ("")) foldee (null, name, namez, valuez).store ();
      else foldee (targetName, name, namez, valuez).store ();
  }

  private static final void xriteFolderz (List <String> namez, String targetName)
  throws IOException
  {
    for (String name: namez)
      if (targetName == null) xriteToUser (folder (name));
      else if (targetName.equals ("")) folder (name).store ();
      else folder (targetName + File.separator + name).store ();
  }

  private static final void xriteToUser (Object object)
  {
    System.out.println (object);
  }

  private static final Foldee foldee (String targetName, String name, List <String> namez, List <String> valuez)
  {
    assert namez.size () == valuez.size ();
    assert namez.contains (name);
    return new Foldee ((targetName == null? "": targetName + File.separator) + name, valuez.get (namez.indexOf (name)));
  }

  private static final Folder folder (String name)
  {
    return new Folder (name, null, null);
  }

}
