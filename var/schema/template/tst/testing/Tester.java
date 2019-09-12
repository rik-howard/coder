
# xemplate
.                             |
.                             | package testing;
.                             |
.[pkg:pkgz][res:pkg.resz]     | import [pkg.name].[res.name]Tester;
.[][][pkg:pkgz][pro:pkg.proz] | import [pkg.name].[pro.name]Tester;
.[][]                         |
.                             | public class Tester
.                             | {
.                             |
.                             |   public static void main (String @0 args)
.                             |   {
.[pkg:pkgz][res:pkg.resz]     |     [res.name]Tester.main (args);
.[][][pkg:pkgz][pro:pkg.proz] |     [pro.name]Tester.main (args);
.[][]                         |     burp ();
.                             |   }
.                             |
.                             |   public static void burp ()
.                             |   {
.                             |     System.out.print (".");
.                             |   }
.                             |
.                             |   public static void burp (Object message)
.                             |   {
.                             |     System.out.println (message);
.                             |   }
.                             |
.                             | }
.                             |
