
package xa.xore;

public class XordTester
{

  public static void main (String [] args)
  {
    assert Xord.isXord ("__&&_interior_(token).matches_(\"^[a-z][a-zA-Z0-9\\.]*$\");");
    //assert Xord.isXord ("^[a-z][a-zA-Z0-9\\.]*$");
  }

}
