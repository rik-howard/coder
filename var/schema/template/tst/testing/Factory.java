
# xemplate
.                                                          |
.                                                          | package testing;
.                                                          |
.                                                          | import java.util.List;
.                                                          | import java.util.ArrayList;
.                                                          | import java.util.Map;
.                                                          | import java.util.HashMap;
.[pkg:pkgz][res:pkg.resz]                                  | import [pkg.name].[res.name];
.[][]                                                      |
.                                                          | public class Factory
.                                                          | {
.                                                          |
.[pkg:pkgz][res:pkg.resz][fld:res.fldz]                    |   public static final [fld.dec] [fld.eg] () {return new [fld.def];}
.[][][][pkg:pkgz][pro:pkg.proz][mtd:pro.mtdz]              |   public static final [mtd.dec] [mtd.eg] () {return new [mtd.def];}
.[][][][pkg:pkgz][pro:pkg.proz][mtd:pro.mtdz][prm:mtd.prmz]|   public static final [prm.dec] [prm.eg] () {return new [prm.def];}
.[][][][]                                                  |
.                                                          | }
.                                                          |
