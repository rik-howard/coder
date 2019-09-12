
package info.lrbh.codable.fsio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Foldee
extends FSO
implements Cloneable, Comparable <Foldee>
{

  private String value;

  public void setValue (String value) {this.value = value == null? new String (): value;}
  public void setLinez (List <String> linez) {this.setValue (this.joined (linez, end));}

  public String getValue () {return this.value;}
  public List <Line> lines ()
  {
    List <Line> lines = new ArrayList <Line> ();
    if (this.getValue ().equals (new String ())) ;
    else for (String string: Arrays.asList (this.getValue ().split (end)))
    {
      Line line = new Line ();
      line.setString (string);
      lines.add (line);
    }
    return lines;
  }

  public Foldee () {super.setName (null); this.setValue (null);}

  public Foldee (String name, List <String> linez)
  {
    this.setName (name);
    this.setLinez (linez);
  }

  public Foldee (String name, String value)
  {
    this.setName (name);
    this.setValue (value);
  }

  public Foldee (File file)
  throws FileNotFoundException, IOException
  {
    this (file.getName (), blank);
    StringBuffer buffer = new StringBuffer ();
    BufferedReader reader = new BufferedReader (new FileReader (file));
    while (reader.ready ())
      buffer.append (reader.readLine ()).append (end);
    reader.close ();
    this.setValue (buffer.toString ().trim ());
  }

  @Override
  public String toString ()
  {
    String o = "<";
    String c = ">";
    String s = " | ";
    return o + this.name () + s + this.lines () + c;
  }

  @Override
  public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Foldee)
    {
      Foldee foldee = (Foldee) object;
      return this.name ().equals (foldee.name ()) && this.getValue ().equals (foldee.getValue ());
    }
    else return false;
  }

  @Override
  public int hashCode ()
  {
    int hash = 5;
    hash = 71 * hash + (this.name () != null? this.name ().hashCode (): 0);
    hash = 71 * hash + (this.getValue () != null? this.getValue ().hashCode (): 0);
    return hash;
  }

  @Override
  public Object clone ()
  {
    Object clone = null;
    try
    {
      clone = super.clone ();
      ((Foldee) clone).setName (this.name ());
      ((Foldee) clone).setValue (this.getValue ());
    }
    catch (CloneNotSupportedException e) {e.printStackTrace ();}
    return clone;
  }

  public int compareTo (Foldee foldee)
  {
    return this.name ().compareTo (foldee.name ());
  }

  public void store ()
  throws IOException
  {
    this.store (new File (this.name ()));
  }

  void store (File file)
  throws IOException
  {
    if (file.exists ()) assert true;
    else if (file.getParentFile ().exists ()) file.createNewFile ();
    else
    {
      file.getParentFile ().mkdirs ();
      file.createNewFile ();
    }
    if (file.length () == 0)
    {
      FileWriter writer = new FileWriter (file);
      writer.write (this.getValue (), 0, this.getValue ().length ());
      writer.flush ();
      writer.close ();
    }
    else System.out.println ("the file exists: " + file.getName ());
  }

  private String joined (List <String> stringz, String seperator)
  {
    StringBuffer buffer = new StringBuffer ();
    for (String string: stringz)
      if (buffer.length () == 0) buffer.append (string);
      else buffer.append (seperator).append (string);
    return buffer.toString ();
  }

  public FSO leaf (String path)
  {
    Foldee leaf = (Foldee) this.clone ();
    leaf.setName ((path == null? blank: path.isEmpty ()? blank: path + File.separator) + leaf.name ());
    return leaf;
  }

  public List <Line> linez ()
  {
    List <Line> linez = new ArrayList <Line> ();
    for (String string: Arrays.asList (this.getValue ().split (end)))
      linez.add (new Line (string));
    return linez;
  }

}
