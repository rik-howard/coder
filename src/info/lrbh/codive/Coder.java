
package info.lrbh.codive;

import info.lrbh.codable.Schema;
import info.lrbh.codable.fsio.FSO;
import info.lrbh.codable.fsio.Folder;
import info.lrbh.codable.fsio.Foldee;
import info.lrbh.codable.fsio.Line;
import info.lrbh.codive.schema.Reader;
import info.lrbh.codive.schema.Thinker;
import info.lrbh.codive.schema.Writer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class Coder
{

  public static void code (List <FSO> templateLeafz, List <Line> mappingLinez, String targetName)
  throws IOException
  {
    for (FSO leaf: templateLeafz)
    {
      //System.out.println (leaf);
      Schema schema = Reader.reading (leaf.name (), leaf.linez (), linesClones (mappingLinez));
      //System.out.println (schema.dump ());
      schema = Thinker.thinking (schema);
      List <String> namez = Writer.namesWriting (schema);
      List <String> valuez = Writer.valuesWriting (schema);
      List <FSO> fsoz = new ArrayList <FSO> ();
      if (valuez == null)
        for (String name: namez)
          fsoz.add (new Folder (name, null, null));
      else if (namez.size () == valuez.size ())
        for (String name: namez)
          fsoz.add (new Foldee (name, valuez.get (namez.indexOf (name))));
      else
        for (String name: namez)
          fsoz.add (new Foldee (name, new ArrayList <String> ()));
      code (fsoz, targetName);
    }
  }

  private static void code (List <FSO> fsoz, String targetName)
  throws IOException
  {
    for (FSO fso: fsoz)
      if (fso instanceof Folder)
      {
        Folder folder = (Folder) fso;
        if (targetName == null)
          System.out.println ("-------- folder: " + folder.name () + " -- " + folder.folders () + " -- " + folder.foldees ());
        else
        {
          folder.setName (targetName + File.separator + folder.name ());
          folder.store ();
        }
      }
      else if (fso instanceof Foldee)
      {
        Foldee foldee = (Foldee) fso;
        foldee.setValue (foldee.getValue ().trim () + Schema.end);
        if (targetName == null)
        {
          System.out.println ("-------- foldee: " + foldee.name ());
          for (Line line: foldee.lines ())
            System.out.println (line.string ());
          System.out.println ();
        }
        else
        {
          foldee.setName (targetName + File.separator + foldee.name ());
          foldee.store ();
        }
      }
      else assert false;
  }

  private static final List <Line> linesClones (List <Line> lines)
  {
    if (lines == null) return null;
    else
    {
      List <Line> clones = new ArrayList <Line> ();
      for (Line line: lines) clones.add ((Line) line.clone ());
      return clones;
    }
  }

}
