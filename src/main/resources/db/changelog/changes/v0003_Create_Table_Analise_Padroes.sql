create table analise_padroes (
    id              serial not null,
    tipo_entrada varchar (100),
    padrao          varchar (100),
    liga            varchar (100),
    is_porcentagem_boa      BOOLEAN NOT NULL,
    cont_entradas BIGINT,
    cont_grens BIGINT,
    cont_reds BIGINT,
    total BIGINT,
    primary key (id)
)
