
package info.lrbh.codable.fsio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Folder
extends FSO
implements Cloneable, Comparable <Folder>
{

  private List <Folder> folders;
  private List <Foldee> foldees;

  public void setFolders (List <Folder> folders) {this.folders = folders == null? new ArrayList <Folder> (): folders;}
  public void setFoldees (List <Foldee> foldees) {this.foldees = foldees == null? new ArrayList <Foldee> (): foldees;}

  public List <Folder> folders () {return this.folders;}
  public List <Foldee> foldees () {return this.foldees;}

  public Folder () {super.setName (null); this.setFolders (null); this.setFoldees (null);}

  public Folder (String name, List <Folder> folders, List <Foldee> foldees)
  {
    this.setName (name);
    this.setFolders (folders);
    this.setFoldees (foldees);
    this.sort ();
  }

  public Folder (File file)
  throws FileNotFoundException, IOException
  {
    this (file.getName (), null, null);
    for (File subfile: file.listFiles ())
      if (subfile.isHidden ()) assert true;
      else if (subfile.isDirectory ()) this.folders.add (new Folder (subfile));
      else this.foldees.add (new Foldee (subfile));
    this.sort ();
  }

  @Override public String toString ()
  {
    String o = "<";
    String c = ">";
    String s = " | ";
    return o + this.name ()
    + s + this.folders ()
    + s + this.foldees ()
    + c;
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Folder)
    {
      Folder folder = (Folder) object;
      Boolean equals = this.name ().equals (folder.name ()) &&
        this.folders ().size () == folder.folders ().size () &&
        this.foldees ().size () == folder.foldees ().size ();
      if (equals) {
        for (Integer i = 0; i < this.folders ().size (); i++)
          equals &= this.folders ().get (i).equals (folder.folders ().get (i));
        for (Integer i = 0; i < this.foldees ().size (); i++)
          equals &= this.foldees ().get (i).equals (folder.foldees ().get (i));
        return equals;
      }
      else return false;
    }
    else return false;
  }

  @Override public int hashCode ()
  {
    int hash = 7;
    hash = 41 * hash + (this.name () != null? this.name ().hashCode (): 0);
    hash = 41 * hash + (this.folders () != null? this.folders ().hashCode (): 0);
    hash = 41 * hash + (this.foldees () != null? this.foldees ().hashCode (): 0);
    return hash;
  }

  @Override public Object clone ()
  {
    Object clone = null;
    try
    {
      clone = super.clone ();
      ((Folder) clone).setName (this.name ());
      ((Folder) clone).setFolders (this.folders ());
      ((Folder) clone).setFoldees (this.foldees ());
    }
    catch (CloneNotSupportedException e) {e.printStackTrace ();}
    return clone;
  }

  public int compareTo (Folder folder)
  {
    return this.name ().compareTo (((Folder) folder).name);
  }



  public void store ()
  throws IOException
  {
    this.store (null);
  }

  private void store (File parent)
  throws IOException
  {
    File file = this.file (parent);
    if (file.exists ()) assert true;
    else  file.mkdirs ();
    for (Folder folder : this.folders ()) folder.store (file);
    for (Foldee foldee : this.foldees ()) foldee.store (new File (file.getPath () + File.separator + foldee.name ()));
  }

  private File file (File parent)
  {
    if (parent == null) return new File (this.name ());
    else return new File (parent.getPath () + File.separator + this.name ());
  }

  private void sort ()
  {
    if (this.folders () != null) for (Folder folder: this.folders ()) folder.sort ();
    if (this.folders () != null) Collections.sort (this.folders ());
    Collections.sort (this.foldees ());
  }

  public List <FSO> leaves (String path)
  {
    List <FSO> leaves = new ArrayList <FSO> ();
    Folder clone = (Folder) this.clone ();
    clone.setName ((path == null? blank: path.isEmpty ()? blank: path + File.separator) + clone.name ());
    if (clone.folders ().size () == 0 && clone.foldees ().size () == 0) leaves.add (clone);
    else
    {
      for (Folder folder: clone.folders ()) leaves.addAll (folder.leaves (clone.name ()));
      for (Foldee foldee: clone.foldees ()) leaves.add (foldee.leaf (clone.name ()));
    }
    return leaves;
  }

}