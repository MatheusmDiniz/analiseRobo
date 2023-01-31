create table analise_padroes (
    id              serial not null,
    padrao          varchar (100),
    liga            varchar (100),
    is_porcentagem_boa      BOOLEAN NOT NULL,
    cont_entradas INT,
    cont_grens INT,
    cont_reds INT,
    total INT,
    primary key (id)
)
