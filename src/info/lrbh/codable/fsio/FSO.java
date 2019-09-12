
package info.lrbh.codable.fsio;

import java.io.IOException;
import java.util.List;

public abstract class FSO
{

  public static final String blank = "";
  public static final String space = " ";
  public static final String neck = ":";
  public static final String quote = "\"";
  public static final String end = "\n";

  protected String name;
  public void setName (String name) {this.name = name == null? new String (): name;}
  public String name () {return this.name == null? new String (): this.name;}

  public List <Line> linez ()
  {
    if (this instanceof Foldee) return ((Foldee) this).linez ();
    else return null;
  }

  public abstract void store ()
  throws IOException;

}
