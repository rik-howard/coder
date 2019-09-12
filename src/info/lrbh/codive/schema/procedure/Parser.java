
package info.lrbh.codive.schema.procedure;

import static info.lrbh.codive.utility.Lister.first;
import static info.lrbh.codive.utility.Lister.rest;
import static info.lrbh.codive.utility.Lister.union;
import info.lrbh.codable.schema.Statement;
import info.lrbh.codable.fsio.Line;
import java.util.ArrayList;
import java.util.List;

public class Parser
{

  public static List <Statement> parsing (List <Line> lines)
  {
    if (lines.size () > 0)
    {
      Line first = first (lines);
      List <Line> rest = rest (lines);
      if (first.isIntroduction ()) return union (first.introduction (), parsing (rest));
      else if (first.isIteration ()) return union
      (
        first.iteration (iterable (rest)),
        parsing (postiterable (rest))
      );
      else if (first.isEnding ()) return union (first.ending (), parsing (rest));
      else throw new Error ("untypical token: " + first);
    }
    else return newStatements ();
  }

  private static final List <Line> iterable (List <Line> lines)
  {
    if (lines.size () > 0)
    {
      Line first = first (lines);
      List <Line> rest = rest (lines);
      if (first.isIntroduction ()) return union (first, iterable (rest));
      else if (first.isIteration ())return union
      (
        union (first, iterable (rest)),
        iterable (postiterable (rest))
      );
      else if (first.isEnding ()) return union (first, null);
      else throw new Error ("untypical token: " + first);
    }
    else return lines;
  }

  private static final List <Line> postiterable (List <Line> lines)
  {
    if (lines.size () > 0)
    {
      Line first = first (lines);
      List <Line> rest = rest (lines);
      if (first.isIntroduction ()) return postiterable (rest);
      else if (first.isIteration ()) return postiterable (postiterable (rest));
      else if (first.isEnding ()) return rest;
      else throw new Error ("untypical token: " + first);
    }
    else return lines;
  }

  private static final List <Statement> newStatements ()
  {
    return new ArrayList <Statement> ();
  }

}
