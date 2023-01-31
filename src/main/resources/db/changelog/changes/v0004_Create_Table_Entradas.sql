create table entradas (
    id              serial not null,
    liga            varchar (100) not null,
    hora            varchar (100),
    minutos         varchar (100),
    aposta          varchar (100),
    id_message       varchar (100),
    flag_finalizado      BOOLEAN NOT NULL,
    flag_grem            BOOLEAN NOT NULL,
    data                varchar (100),
    primary key (id)
)
