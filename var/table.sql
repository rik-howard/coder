
# xymbol
@eofMarker: <eof>
@opener: [
@closer: ]
@clausal: :

# xelationz
Tb: dbName* name* idLength type
Fk: tb.dbName* tb.name* name* dbName length
Nk: tb.dbName* tb.name* name* type length extra nullness
Uk: tb.dbName* tb.name* id*
Pt: tb.dbName* tb.name* uk.id* name*

# xecordz
tb: geo continent 1 InnoDB
nk: geo continent code string 1 null not-null
nk: geo continent name string 12 null not-null
uk: geo continent 0
pt: geo continent 0 code
uk: geo continent 1
pt: geo continent 1 name
tb: geo compass 1 InnoDB
nk: geo compass code string 1 null not-null
nk: geo compass name string 16 null not-null
uk: geo compass 0
pt: geo compass 0 code
uk: geo compass 1
pt: geo compass 1 name
tb: geo subcontinent 2 InnoDB
fk: geo subcontinent continent geo 1
fk: geo subcontinent compass geo 1
uk: geo subcontinent 0
pt: geo subcontinent 0 continent_id
pt: geo subcontinent 0 compass_id

# xunctionz

# xrocedure

# xemplate
.[tb:tbz]
.                | create table if not exists [tb.dbName].[tb.name]
.                | (
.                |   id natural ([tb.idLength]) unsigned zerofill not null auto_increment,
.  [fk:tb.fkz]   |   [fk.name]_id natural ([fk.length]) unsigned zerofill not null,
.  [][nk:tb.nkz] |   [nk.name] [nk.type] ([nk.length])[:nk.extra.] [nk.extra][] [nk.nullness],
.  []            |   primary key (id),
.  [fk:tb.fkz]   |   foriegn key ([fk.name]_id) references [fk.dbName].[fk.name] (id),
.  [][uk:tb.ukz] |   unique ([pt:uk.ptz][pt.name][, ][])[,]
.  []            | ) type = [tb.type];
.[]