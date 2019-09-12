
package info.lrbh.codive.schema.template;

import java.util.List;
import java.util.ArrayList;
import info.lrbh.codable.schema.template.Link;
import info.lrbh.codable.schema.template.Compilement;

public class Compiler
{


  public static final List <Compilement> compilation (List <Link> linkz, String eofMarker)
  {
    List <Compilement> compilation = new ArrayList <Compilement> ();
    StringBuffer buffer = new StringBuffer ();
    for (Link link: linkz)
      buffer.append (link.value ());
    if (buffer.length () > 0)
      for (String value: buffer.toString ().split (eofMarker))
        compilation.add (compilement (value));
    return compilation;
  }

  private static final Compilement compilement (String value)
  {
    Compilement compilement = new Compilement ();
    compilement.setValue (value);
    return compilement;
  }

}
