
package info.lrbh.codive.schema.template;

import static info.lrbh.codive.utility.Lister.union;
import java.util.List;
import java.util.ArrayList;
import info.lrbh.codable.schema.template.Cleanment;
import info.lrbh.codable.schema.template.Token;

public class Tokeniser
{

  public static final List <Token> tokenisation (Cleanment cleanment)
  {
    if (idsTokenisability (cleanment))
      if (idsInitialNativity (cleanment)) return union (nativ (cleanment), tokenisation (postNative (cleanment)));
      else if (idsMedialNativity (cleanment)) return union (foreign (cleanment), tokenisation (postForeign (cleanment)));
      else return union (finalToken (cleanment), null);
    else return new ArrayList <Token> ();
  }

  private static final Boolean idsTokenisability (Cleanment cleanment)
  {
    return cleanment.string ().length () > 0;
  }

  private static final Boolean idsInitialNativity (Cleanment cleanment)
  {
    return cleanment.string ().startsWith (cleanment.opener ())
    && cleanment.string ().indexOf (cleanment.closer ()) >= cleanment.opener ().length ();
  }

  private static final Boolean idsMedialNativity (Cleanment cleanment)
  {
    return cleanment.string ().indexOf (cleanment.opener ()) > 0
    && cleanment.string ().indexOf (cleanment.closer ()) > cleanment.string ().indexOf (cleanment.opener ());
  }

  private static final Cleanment postNative (Cleanment cleanment)
  {
    Cleanment postNative = new Cleanment ();
    postNative.setString
    (
      cleanment.string ().substring
      (
        cleanment.string ().indexOf (cleanment.closer ())
        + cleanment.closer ().length ()
      )
    );
    postNative.setOpener (cleanment.opener ());
    postNative.setCloser (cleanment.closer ());
    return postNative;
  }

  private static final Cleanment postForeign (Cleanment cleanment)
  {
    Cleanment postForeign = new Cleanment ();
    postForeign.setString
    (
      cleanment.string ().substring
      (
        cleanment.string ().indexOf (cleanment.opener ())
      )
    );
    postForeign.setOpener (cleanment.opener ());
    postForeign.setCloser (cleanment.closer ());
    return postForeign;
  }

  private static final Token nativ (Cleanment cleanment)
  {
    Token token = new Token ();
    token.setString
    (
      cleanment.string ().substring
      (
        0,
        cleanment.string ().indexOf (cleanment.closer ())
        + cleanment.closer ().length ()
      )
    );
    token.setOpener (cleanment.opener ());
    token.setCloser (cleanment.closer ());
    return token;
  }

  private static final Token foreign (Cleanment cleanment)
  {
    Token token = new Token ();
    token.setString
    (
      cleanment.string ().substring
      (
        0,
        cleanment.string ().indexOf (cleanment.opener ())
      )
    );
    token.setOpener (cleanment.opener ());
    token.setCloser (cleanment.closer ());
    return token;
  }

  private static final Token finalToken (Cleanment cleanment)
  {
    Token token = new Token ();
    token.setString (cleanment.string ());
    token.setOpener (cleanment.opener ());
    token.setCloser (cleanment.closer ());
    return token;
  }

}
