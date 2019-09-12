
.                |
.                | package testing;
.                |
.                | public class Module
.                | {
.                |
.                |   public static void show ()
.                |   {
.                |     System.out.println ();
.                |   }
.                |
.                |   public static void show (Object object)
.                |   {
.                |     if (object == null) System.out.println ();
.                |     else if (object.toString ().equals (".")) System.out.print (object.toString ());
.                |     else if (object.toString ().equals ("!")) System.out.print (object.toString ());
.                |     else System.out.println (object.toString ());
.                |   }
.                |
.                |   public static void panic (Throwable throwee)
.                |   {
.                |     show ();
.                |     throwee.printStackTrace ();
.                |   }
.                |
.                |   public static String denial (Object expected, Object actual)
.                |   {
.                |     return "not " + expected.toString () + " equals " + actual.toString ();
.                |   }
.                |
.                | }
.                |
