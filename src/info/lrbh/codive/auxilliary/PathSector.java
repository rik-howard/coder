
package info.lrbh.codive.auxilliary;

import static info.lrbh.codable.Schematic.space;

public class PathSector
{

  public static final String root (String path, String seperator)
  {
    if (path.contains (seperator))
    {
      Integer index = path.indexOf (seperator);
      return path.substring (0, index);
    }
    else return path;  // throw new Error ("the path not contains seperator: " + path + space + seperator);
  }

  public static final String bough (String path, String seperator)
  {
    if (path.contains (seperator))
    {
      Integer index = path.lastIndexOf (seperator);
      return path.substring (0, index);
    }
    else return "";  // throw new Error ("the path not contains seperator: " + path + space + seperator);
  }

  public static final String branch (String path, String seperator)
  {
    if (path.contains (seperator))
    {
      Integer index = path.indexOf (seperator);
      return path.substring (index + 1);
    }
    else return "";  // throw new Error ("the path not contains seperator: " + path + space + seperator);
  }

  public static final String leaf (String path, String seperator)
  {
    if (path.contains (seperator))
    {
      Integer index = path.lastIndexOf (seperator);
      return path.substring (index + 1);
    }
    else return path;  // throw new Error ("the path not contains seperator: " + path + space + seperator);
  }

}
