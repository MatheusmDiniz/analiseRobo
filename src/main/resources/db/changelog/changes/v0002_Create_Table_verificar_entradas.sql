create table verificar_entradas (
    id              serial not null,
    liga            varchar (100) not null,
    hora            varchar (100),
    minutos         varchar (100),
    aposta          varchar (100),
    id_message       varchar (100),
    flag_finalizado      BOOLEAN NOT NULL,
    flag_grem            BOOLEAN NOT NULL,
    padrao       varchar (100),
    minuto1         INTEGER,
    minuto2         INTEGER,
    minuto3         INTEGER,
    minuto4         INTEGER,
    primary key (id)
)
