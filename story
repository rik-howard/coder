

2010-05-22

The following were crafted by hand
.   var/schema/template
.   var/schema/mapping/symbols

var/coder-schema.ods generates var/schema/mapping
.   relations
.   records

bin/xode-coder
.   compiles the source
.   codes the schema src as ~/desk/lrbh-coder
.   could code the schema tst as the same, but the line is commented out
.   replaces some tokens in lrbh-coder

~/desk/lrbh-coder is to be the next trunk



2010-05-15

2010-05-15 14:16:20 lrbh http://lrbh-svn.cvsdude.com/public/xoder/tag/0.2 153
2009-12-21 14:04:35 lrbh r129 preserve order of input recordz
2009-12-21 14:05:15 lrbh r130 environ xoder
2009-12-21 20:07:30 lrbh r131 process empty template files
2009-12-27 20:54:00 lrbh r132 remove debugging code
2009-12-27 20:54:40 lrbh r133 intro bash building
2009-12-28 16:45:40 lrbh r134 remove some string literals
2009-12-29 00:04:37 lrbh r135 intro xymbolz
2009-12-29 00:10:42 lrbh r136 intro sample source file
2009-12-30 12:20:44 lrbh r137 intro another user case
2010-01-17 10:46:13 lrbh r138 tweak stuff
2010-05-15 14:15:31 lrbh r151 tweak
2010-05-15 14:15:56 lrbh r152 intro
2010-05-15 14:16:20 lrbh r153 intro eclipse



2009-12-20

v   environ xoder: write a bash script to put on the path which handles the java call
v   remove magic strings: this will possiblise several improvements, viz., making specifiable
    -   eof marker
    -   non-constant token beginning and ending markers
-   intro functions and procedures
v   imple if-clause in folder name
v   preserve order of input records
-   imple -xlean
v   imple creamer-rdbms/building




There should be a variety of ways of invoking the program.

We process an input file, then display the results to the console
> xoder -xode -from $home/var/hello-world

> xoder -xode -from $home/var/sql-table -to $home/tmp

> xoder -xode -from $home/var/[tb:tbz][tb.name].sql[] -to $home/tmp/tablez

> xoder -xode -from $home/var/[pjt.name] -with $home/var/pjt-0 -to $home/tmp

>
xoder
  -xode
  -from $home/var/[pkg:pkgz][pkg.name][]
  -with $home/var/pkgz-o-beanz
  -to $home/tmp/rhoworx-scorex/src/com/rhoworx/scorex
!



[pjt.name]/building[:pjt.id.]-[pjt.id][]
[pjt.name]/[:pjt.id.]building-[pjt.id][:]building[]



