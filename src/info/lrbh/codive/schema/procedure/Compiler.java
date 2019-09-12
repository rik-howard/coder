
package info.lrbh.codive.schema.procedure;

import java.util.ArrayList;
import java.util.List;
import info.lrbh.codable.fsio.Line;
import info.lrbh.codable.schema.procedure.Link;
import info.lrbh.codable.schema.procedure.Compilement;

public class Compiler
{

  public static final Compilement compilation (List <Link> linkz)
  {
    Compilement compilement = new Compilement ();
    List <Line> linez = new ArrayList <Line> ();
    for (Link link: linkz)
      linez.add (new Line (link.string ()));
    compilement.setLinez (linez);
    return compilement;
  }

}
