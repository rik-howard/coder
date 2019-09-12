
package info.lrbh.fsio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Foldee
implements Comparable <Foldee>
{

  private String name;
  private String value;

  public void setName (String name)
  {
    this.name = name == null? "foldee": name;
  }

  public void setValue (String value)
  {
    this.value = value == null? "value": value;
  }

  public void setLinez (List <String> linez)
  {
    this.setValue (this.joined (linez, "\n"));
  }

  public String getName ()
  {
    return this.name;
  }

  public String getValue ()
  {
    return this.value;
  }

  public List <String> getLinez ()
  {
    return Arrays.asList (this.getValue ().split ("\n"));
  }

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
    this (file.getName (), "");
    StringBuffer buffer = new StringBuffer ();
    BufferedReader reader = new BufferedReader (new FileReader (file));
    while (reader.ready ())
      buffer.append (reader.readLine ()).append ("\n");
    reader.close ();
    this.setValue (buffer.toString ().trim ());
  }

  @Override
  public String toString ()
  {
    return this.getName () + File.separator + this.getValue ();
  }

  @Override
  public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Foldee)
    {
      Foldee foldee = (Foldee) object;
      return this.getName ().equals (foldee.getName ()) && this.getValue ().equals (foldee.getValue ());
    }
    else return false;
  }

  @Override
  public int hashCode ()
  {
    int hash = 5;
    hash = 71 * hash + (this.getName () != null? this.getName ().hashCode (): 0);
    hash = 71 * hash + (this.getValue () != null? this.getValue ().hashCode (): 0);
    return hash;
  }

  public int compareTo (Foldee foldee)
  {
    return this.getName ().compareTo (foldee.getName ());
  }

  public void store (File file)
  throws IOException
  {
    if (file.exists ()) assert true;
    else file.createNewFile ();
    if (file.length () == 0)
    {
      FileWriter writer = new FileWriter (file);
      writer.write (this.getValue (), 0, this.getValue ().length ());
      writer.flush ();
      writer.close ();
    }
  }

  private String joined (List <String> stringz, String seperator)
  {
    StringBuffer buffer = new StringBuffer ();
    for (String string: stringz)
      if (buffer.length () == 0) buffer.append (string);
      else buffer.append (seperator).append (string);
    return buffer.toString ();
  }

}