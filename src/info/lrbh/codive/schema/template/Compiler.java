package info.lrbh.codive.schema.template;

import java.util.List;
import java.util.ArrayList;
import info.lrbh.codable.schema.template.Link;
import info.lrbh.codable.schema.template.Compilement;
import info.lrbh.codive.Factory;

public class Compiler
{


  public static final List <Compilement> compilation (List <Link> linkz, String eofMarker)
  {
    List <Compilement> compilation = new ArrayList <Compilement> ();
    StringBuffer buffer = new StringBuffer ();
    for (Link link: linkz)
      buffer.append (link.value ());
    for (String string: buffer.toString ().split (eofMarker))
      compilation.add (Factory.compilement (string));
    return compilation;
    /*if (idsCompilability (linkz))
      return union
      (
        compilement (first (linkz, eofMarker)),
        compilation (rest (linkz, eofMarker), eofMarker)
      );
    else return union (null, null);*/
  }
/*
  private static final Boolean idsCompilability (List <Link> linkz)
  {
    return linkz.size () > 0;
  }
   
  private static final List <Link> first (List <Link> linkz, String eofMarker)
  {
    List <Link> first = new ArrayList <Link> ();
    Boolean seenOne = false;
    Boolean appending = true;
    for (Link link: linkz)
      if (link.value ().equals (eofMarker))
        if (seenOne) ;
        else
        {
          //first.add (link);
          seenOne = true;
          appending = false;
        }
      else if (appending) first.add (link);
      else ;
    return first;
  }
   
  private static final List <Link> rest (List <Link> linkz, String eofMarker)
  {
    List <Link> rest = new ArrayList <Link> ();
    Boolean seenOne = false;
    Boolean appending = false;
    for (Link link: linkz)
      if (link.value ().equals (eofMarker))
        if (seenOne) rest.add (link);
        else
        {
          seenOne = true;
          appending = true;
        }
      else if (appending) rest.add (link);
      else ;
    return rest;
  }
   
  private static final List <Compilement> union (Compilement compilement, List <Compilement> compilementz)
  {
    List <Compilement> union = new ArrayList <Compilement> ();
    if (compilement != null) union.add (compilement);
    if (compilementz != null) union.addAll (compilementz);
    return union;
  }
   
  private static final Compilement compilement (List <Link> linkz)
  {
    Compilement compilement = new Compilement ();
    StringBuffer buffer = new StringBuffer ();
    for (Link link: linkz) buffer.append (link.value ());
    compilement.setValue (buffer.toString ());
    return compilement;
  }
*/
}
