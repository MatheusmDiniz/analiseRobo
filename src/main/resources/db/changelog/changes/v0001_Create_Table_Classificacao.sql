create table Classificacao (
    id                  serial not null,
    time                varchar (100) not null,
    gols_feitos          integer,
    gols_levados         integer,
    quantidade_over25    integer,
    quantidade_under25   integer,
    ambas_marcam   integer,
    liga                varchar (100),
    primary key (id)
)
