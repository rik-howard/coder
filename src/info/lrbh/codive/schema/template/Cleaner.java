package info.lrbh.codive.schema.template;

import java.util.Arrays;
import java.util.List;
import info.lrbh.codable.schema.template.Cleanment;

public class Cleaner
{


  public static final Cleanment cleaning (String cleanee, String opener, String closer, String margin)
  {
    Cleanment cleanment = new Cleanment ();
    if (margin == null)
    {
      cleanment.setString (cleanee);
      cleanment.setOpener (opener);
      cleanment.setCloser (closer);
    }
    else
    {
      StringBuffer buffer = new StringBuffer ();
      for (String string: cleanee.split (escaped ("\n")))
        if (buffer.length () == 0) buffer.append (unmargined (string, margin));
        else buffer.append ("\n").append (unmargined (string, margin));
      cleanment.setString (buffer.toString ());
      cleanment.setOpener (opener);
      cleanment.setCloser (closer);
    }
    return cleanment;
  }

  public static final String escaped (String string)
  {
    StringBuffer buffer = new StringBuffer ();
    List <String> escapable = Arrays.asList ("(", ")", "{", "}", "[", "]", "^", "$", "\n", "\r", "\t", "|");
    for (Integer i = 0; i < string.length (); i++)
    {
      String s = string.substring (i, i + 1);
      if (escapable.contains (s)) buffer.append ("\\").append (s);
      else buffer.append (s);
    }
    return buffer.toString ();
  }
   
  private static final String unmargined (String string, String margin)
  {
    return string.trim ().replaceFirst ("^\\.?", "").trim ().replaceFirst ("\\ *" + escaped (margin) + "\\ ?", "");
  }

}
