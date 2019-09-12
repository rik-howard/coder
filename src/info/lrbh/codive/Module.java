
package info.lrbh.codive;

import static info.lrbh.codable.Schematic.space;
import static info.lrbh.codive.auxilliary.CaseChanger.bicapitalisation;
import static info.lrbh.codive.auxilliary.CaseChanger.camelBacking;
import static info.lrbh.codive.auxilliary.CaseChanger.hyphenation;
import static info.lrbh.codive.auxilliary.CaseChanger.underscoring;
import static info.lrbh.codive.auxilliary.PathSector.root;
import static info.lrbh.codive.auxilliary.PathSector.bough;
import static info.lrbh.codive.auxilliary.PathSector.branch;
import static info.lrbh.codive.auxilliary.PathSector.leaf;
import java.util.List;

public class Module
{

  public static final String modulation (String name, List <String> arguments)
  {
    assert name != null;
    assert arguments != null;

    if (name.equals ("joining"))
      if (arguments.size () == 3) return joining (arguments.get (0), arguments.get (1), arguments.get (2));
      else return newlyThrownError (name, arguments);

    else if (name.equals ("replacement"))
      if (arguments.size () == 3) return replacement (arguments.get (0), arguments.get (1), arguments.get (2));
      else return newlyThrownError (name, arguments);

    else if (name.equals ("bicap"))
      if (arguments.size () == 1) return bicapitalisation (arguments.get (0));
      else return newlyThrownError (name, arguments);

    else if (name.equals ("camel"))
      if (arguments.size () == 1) return camelBacking (arguments.get (0));
      else return newlyThrownError (name, arguments);

    else if (name.equals ("hyphe"))
      if (arguments.size () == 1) return hyphenation (arguments.get (0));
      else return newlyThrownError (name, arguments);

    else if (name.equals ("score"))
      if (arguments.size () == 1) return underscoring (arguments.get (0));
      else return newlyThrownError (name, arguments);

    else if (name.equals ("root"))
      if (arguments.size () == 2) return root (arguments.get (0), arguments.get (1));
      else return newlyThrownError (name, arguments);

    else if (name.equals ("bough"))
      if (arguments.size () == 2) return bough (arguments.get (0), arguments.get (1));
      else return newlyThrownError (name, arguments);

    else if (name.equals ("branch"))
      if (arguments.size () == 2) return branch (arguments.get (0), arguments.get (1));
      else return newlyThrownError (name, arguments);

    else if (name.equals ("leaf"))
      if (arguments.size () == 2) return leaf (arguments.get (0), arguments.get (1));
      else return newlyThrownError (name, arguments);

    else return newlyThrownError (name, null);

  }

  private static final String joining (String prefix, String suffix, String joiner)
  {
    return prefix + joiner + suffix;
  }

  private static final String replacement (String string, String replacee, String replacement)
  {
    return string.replace (replacee, replacement);
  }

  private static final String newlyThrownError (String name, List <String> arguments)
  {
    if (arguments == null) throw new Error ("no function found matching name: " + name);
    else throw new Error ("no function found for name matching arity: " + name + space + arguments.size ());
  }

}
