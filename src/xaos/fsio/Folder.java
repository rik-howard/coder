package xaos.fsio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static xa.xore.Xtring.xmpty;

public class Folder
extends FSO
implements Cloneable, Comparable <Folder>
{

  private String name;
  private List <Folder> folders;
  private List <Foldee> foldees;

  public void setName (String name) {this.name = name == null? "folder": name;}
  public void setFolders (List <Folder> folders) {this.folders = folders == null? new ArrayList <Folder> (): folders;}
  public void setFoldees (List <Foldee> foldees) {this.foldees = foldees == null? new ArrayList <Foldee> (): foldees;}

  public String getName () {return this.name;}
  public List <Folder> getFolders () {return this.folders;}
  public List <Foldee> getFoldees () {return this.foldees;}

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
    return this.getName ()
    + File.separator + this.getFolders ()
    + File.separator + this.getFoldees ();
  }

  @Override public boolean equals (Object object)
  {
    if (object == null) return false;
    else if (object instanceof Folder)
    {
      //Collections.sort (this.folders);
      //Collections.sort (this.foldees);
      Folder folder = (Folder) object;
      //Collections.sort (folder.folders);
      //Collections.sort (folder.foldees);
      /*return this.getName ().equals (folder.getName ()) &&
        this.getFolders ().equals (folder.getFolders ()) &&
        this.getFoldees ().equals (folder.getFoldees ());*/
      //
      Boolean equals = this.getName ().equals (folder.getName ()) &&
        this.getFolders ().size () == folder.getFolders ().size () &&
        this.getFoldees ().size () == folder.getFoldees ().size ();
      if (equals) {
        for (Integer i = 0; i < this.getFolders ().size (); i++)
          equals &= this.getFolders ().get (i).equals (folder.getFolders ().get (i));
        for (Integer i = 0; i < this.getFoldees ().size (); i++)
          equals &= this.getFoldees ().get (i).equals (folder.getFoldees ().get (i));
        return equals;
      }
      else return false;
    }
    else return false;
  }

  @Override public int hashCode ()
  {
    int hash = 7;
    hash = 41 * hash + (this.getName () != null? this.getName ().hashCode (): 0);
    hash = 41 * hash + (this.getFolders () != null? this.getFolders ().hashCode (): 0);
    hash = 41 * hash + (this.getFoldees () != null? this.getFoldees ().hashCode (): 0);
    return hash;
  }

  @Override public Object clone ()
  {
    Object clone = null;
    try
    {
      clone = super.clone ();
      ((Folder) clone).setName (this.getName ());
      ((Folder) clone).setFolders (this.getFolders ());
      ((Folder) clone).setFoldees (this.getFoldees ());
    }
    catch (CloneNotSupportedException e) {e.printStackTrace ();}
    return clone;
  }

  public int compareTo (Folder folder)
  {
    return this.getName ().compareTo (((Folder) folder).name);
  }

  //

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
    for (Folder folder : this.getFolders ()) folder.store (file);
    for (Foldee foldee : this.getFoldees ()) foldee.store (new File (file.getPath () + File.separator + foldee.getName ()));
  }

  private File file (File parent)
  {
    if (parent == null) return new File (this.getName ());
    else return new File (parent.getPath () + File.separator + this.getName ());
  }

  private void sort ()
  {
    if (this.getFolders () != null) for (Folder folder: this.getFolders ()) folder.sort ();
    if (this.getFolders () != null) Collections.sort (this.getFolders ());
    Collections.sort (this.getFoldees ());
  }

  public List <FSO> leaves (String path)
  {
    List <FSO> leaves = new ArrayList <FSO> ();
    Folder clone = (Folder) this.clone ();
    clone.setName ((path == null? xmpty: path.equals (xmpty)? xmpty: path + File.separator) + clone.getName ());
    if (clone.getFolders ().size () == 0 && clone.getFoldees ().size () == 0) leaves.add (clone);
    else
    {
      for (Folder folder: clone.getFolders ()) leaves.addAll (folder.leaves (clone.getName ()));
      for (Foldee foldee: clone.getFoldees ()) leaves.add (foldee.leaf (clone.getName ()));
    }
    return leaves;
  }

}