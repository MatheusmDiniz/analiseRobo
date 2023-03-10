create table Classificacao (
    id                  serial not null,
    time                varchar (100) not null,
    gols_feitos          BIGINT,
    gols_levados         BIGINT,
    quantidade_over25    BIGINT,
    quantidade_under25   BIGINT,
    ambas_marcam   BIGINT,
    liga                varchar (100),
    primary key (id)
)
