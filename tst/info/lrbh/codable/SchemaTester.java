
package info.lrbh.codable;

import static testing.Module.show;
import static testing.Module.panic;
import info.lrbh.codable.schema.Symbol;
import info.lrbh.codable.schema.Relation;
import info.lrbh.codable.schema.Record;
import info.lrbh.codable.schema.Function;
import info.lrbh.codable.schema.Procedure;
import info.lrbh.codable.schema.Template;
import info.lrbh.codable.schema.Vertex;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class SchemaTester
{

  private static final Schema newby = new Schema ();

  public static void main (String [] strings)
  {
    try {testCommentMarker ();} catch (AssertionError e) {panic (e);}
    try {testTemplateEOF ();} catch (AssertionError e) {panic (e);}
    try {testTemplateMargin ();} catch (AssertionError e) {panic (e);}
    try {testTemplateOpener ();} catch (AssertionError e) {panic (e);}
    try {testTemplateCloser ();} catch (AssertionError e) {panic (e);}
    try {testSymbols ();} catch (AssertionError e) {panic (e);}
    try {testRelations ();} catch (AssertionError e) {panic (e);}
    try {testRecords ();} catch (AssertionError e) {panic (e);}
    try {testFunctions ();} catch (AssertionError e) {panic (e);}
    try {testProcedure ();} catch (AssertionError e) {panic (e);}
    try {testTemplate ();} catch (AssertionError e) {panic (e);}
    try {testVertex ();} catch (AssertionError e) {panic (e);}
    try {testKeys ();} catch (AssertionError e) {panic (e);}
    try {testHashCode ();} catch (AssertionError e) {panic (e);}
    try {testEquals ();} catch (AssertionError e) {panic (e);}
    try {testToString ();} catch (AssertionError e) {panic (e);}
    show (".");
  }

  private static void testCommentMarker ()
  {
    String expected = new String ("¢");
    String actual = newby.commentMarker ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testTemplateEOF ()
  {
    String expected = new String ("€");
    String actual = newby.templateEOF ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testTemplateMargin ()
  {
    String expected = new String ("|");
    String actual = newby.templateMargin ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testTemplateOpener ()
  {
    String expected = new String ("[");
    String actual = newby.templateOpener ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testTemplateCloser ()
  {
    String expected = new String ("]");
    String actual = newby.templateCloser ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testSymbols ()
  {
    Map <String, Symbol> expected = new HashMap <String, Symbol> ();
    Map <String, Symbol> actual = newby.symbols ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testRelations ()
  {
    Map <String, Relation> expected = new HashMap <String, Relation> ();
    Map <String, Relation> actual = newby.relations ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testRecords ()
  {
    Map <String, Record> expected = new HashMap <String, Record> ();
    Map <String, Record> actual = newby.records ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testFunctions ()
  {
    Map <String, Function> expected = new HashMap <String, Function> ();
    Map <String, Function> actual = newby.functions ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testProcedure ()
  {
    Procedure expected = new Procedure ();
    Procedure actual = newby.procedure ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testTemplate ()
  {
    Template expected = new Template ();
    Template actual = newby.template ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testVertex ()
  {
    Vertex expected = new Vertex ();
    Vertex actual = newby.vertex ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testKeys ()
  {
    List <String> expected = new ArrayList <String> ();
    List <String> actual = newby.keys ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

  private static void testHashCode ()
  {
    assert newby.hashCode () == new Schema ().hashCode (): "not " + newby.hashCode () + " == " + new Schema ().hashCode ();
    show (".");
  }

  private static void testEquals ()
  {
    assert ! newby.equals (null): newby + " equals null";
    assert ! newby.equals (0): newby + " equals 0";
    assert newby.equals (new Schema ()): "not " + newby + " equals " + new Schema ();
    show (".");
  }

  private static void testToString ()
  {
    String expected = "<"
    + new String ("¢") + " | "
    + new String ("€") + " | "
    + new String ("|") + " | "
    + new String ("[") + " | "
    + new String ("]") + " | "
    + new HashMap <String, Symbol> () + " | "
    + new HashMap <String, Relation> () + " | "
    + new HashMap <String, Record> () + " | "
    + new HashMap <String, Function> () + " | "
    + new Procedure () + " | "
    + new Template () + " | "
    + new Vertex () + " | "
    + new ArrayList <String> () + ">";
    String actual = newby.toString ();
    assert expected.equals (actual): "not " + expected + " equals " + actual;
    show (".");
  }

}
