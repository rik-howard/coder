
package info.lrbh.codive;

public class Logger
{

  public static final void logNonSymbol (String string)
  {
    assert string != null;
    if (string.equals ("")) ;
    else log ("this is not a Xymbol: " + string);
  }

  public static final void logNonRelation (String string)
  {
    assert string != null;
    if (string.equals ("")) ;
    else log ("this is not a Xelation: " + string);
  }

  public static final void logNonRecord (String string)
  {
    assert string != null;
    if (string.equals ("")) ;
    else log ("this is not a Xecord: " + string);
  }

  public static final void logNonFunction (String string)
  {
    assert string != null;
    if (string.equals ("")) ;
    else log ("this is not a Xunction: " + string);
  }
/*
  public static final void logNonXchemaObject (Object object)
  {
    assert object != null;
    log ("this is not a Xelation, Xecord, Xunction, Xrocedure, Xemplate: " + object);
  }
*/
  private static final void log (Object message)
  {
    System.err.println (message);
  }
/*
  public static final Boolean nullLine ()
  {
    log ("the line is null");
    return false;
  }

  public static final Boolean notContains (String container, String containee)
  {
    log ("not contains: " + container + ", " + containee);
    return false;
  }
*/
}
