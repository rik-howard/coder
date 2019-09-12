
package xa;

public class Xrocedure
{

  // ----------------------------------------------------------------------------------- predicate

  public static Boolean isXtatement (String xtring)
  {
    return xtring.trim ().equals ("else")
    || xtring.trim ().equals ("end")
    || xtring.trim ().startsWith ("intro")
    || xtring.trim ().startsWith ("extro")
    || xtring.trim ().startsWith ("for")
    || xtring.trim ().startsWith ("if");
  }

  // -------------------------------------------------------------------------------------- module

}
