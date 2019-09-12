
package info.lrbh.codable;

public interface Schematic
{

  public static final String space = " ";
  public static final String end = "\n";
  public static final String neck = ":";
  public static final String star = "*";
  public static final String quote = "\"";
  public static final String sectorMarker = ">";

  public static final String sectorSymbols = "symbols";
  public static final String sectorRelations = "relations";
  public static final String sectorRecords = "records";
  public static final String sectorFunctions = "functions";
  public static final String sectorProcedure = "procedure";
  public static final String sectorTemplate = "template";

  public static final String commentMarkerName = "schemaCommentMarker";
  public static final String templateCloserName = "templateCloser";
  public static final String templateEOFName = "templateEOF";
  public static final String templateMarginName = "templateMargin";
  public static final String templateOpenerName = "templateOpener";

  public static final String commentMarkerDefault = "¢";
  public static final String templateCloserDefault = "]";
  public static final String templateEOFDefault = "€";
  public static final String templateMarginDefault = "|";
  public static final String templateOpenerDefault = "[";
  public static final String symbolValueClause = ":";
  //public static final String symbolValueLitkey = "£";

  public static final String expressionDot = ".";
  public static final String expressionComma = ",";
  public static final String expressionAmper = "&";
  public static final String expressionOpener = "(";
  public static final String expressionCloser = ")";

  public static final String procedureIntroducer = "intro";
  public static final String procedureEnding = "end";

  public static final String regexSector = "^> (symbols|relations|records|functions|procedure|template)$";
  public static final String regexSymbolName = "([a-zA-Z0-9]+|[0-9]+)";
  public static final String regexSymbolValue = "^[^ ]*$";
  public static final String regexRelationId = "^[A-Z][a-zA-Z0-9]*$";
  public static final String regexRelationName = "^[a-z][a-zA-Z0-9\\.]*\\*?$";
  public static final String regexRecordRef = "^[a-z][a-zA-Z0-9]*$";
  public static final String regexRecordValue = "^[^ ]*$";
  public static final String regexExpressionLiteralKey = "^[0-9]+$";
  public static final String regexExpressionIdentifier = "[a-z][a-zA-Z0-9]*";
  public static final String regexFunctionSignature = "^[a-z][a-zA-Z0-9]* \\([a-zA-Z0-9\\, ]*\\)$";
  public static final String regexFunctionBody = "^.*$";
  public static final String regexProcedureIterator = "for +[a-z][a-zA-Z0-9]* +in +[a-z][a-zA-Z0-9]*[ *\\. *[a-z][a-zA-Z0-9]*]*z";

}
