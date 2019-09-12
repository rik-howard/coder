
package info.lrbh.codable.fsio;

public abstract class FSO
{

  public static final String blank = "";
  public static final String end = "\n";

  protected String name;
  public void setName (String name) {this.name = name == null? this.getClass ().getSimpleName ().toLowerCase (): name;}
  public String getName () {return this.name == null? this.getClass ().getSimpleName ().toLowerCase (): this.name;}

}
