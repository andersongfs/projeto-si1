# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table disciplina (
  id                        bigint not null,
  nome_cadeira              varchar(255) not null,
  creditos                  integer,
  dificuldade               integer,
  periodo                   integer,
  constraint uq_disciplina_nome_cadeira unique (nome_cadeira),
  constraint pk_disciplina primary key (id))
;

create table periodo (
  id                        bigint not null,
  limite_creditos           integer,
  constraint pk_periodo primary key (id))
;

create table plano_de_curso (
  id                        bigint not null,
  numero_periodos           integer,
  constraint pk_plano_de_curso primary key (id))
;

create table Usuario (
  id                        bigint not null,
  nome                      varchar(255),
  email                     varchar(255),
  senha                     varchar(255),
  periodo_atual             integer,
  plano_id                  bigint,
  constraint pk_Usuario primary key (id))
;


create table cadeira_requisito (
  fk_cadeira                     bigint not null,
  fk_requisito                   bigint not null,
  constraint pk_cadeira_requisito primary key (fk_cadeira, fk_requisito))
;

create table periodo_cadeira (
  fk_periodo                     bigint not null,
  fk_cadeira                     bigint not null,
  constraint pk_periodo_cadeira primary key (fk_periodo, fk_cadeira))
;

create table plano_periodo (
  plano_de_curso_id              bigint not null,
  periodo_id                     bigint not null,
  constraint pk_plano_periodo primary key (plano_de_curso_id, periodo_id))
;

create table disciplinas_disponiveis (
  plano_de_curso_id              bigint not null,
  disciplina_id                  bigint not null,
  constraint pk_disciplinas_disponiveis primary key (plano_de_curso_id, disciplina_id))
;
create sequence disciplina_seq;

create sequence periodo_seq;

create sequence plano_de_curso_seq;

create sequence Usuario_seq;

alter table Usuario add constraint fk_Usuario_plano_1 foreign key (plano_id) references plano_de_curso (id) on delete restrict on update restrict;
create index ix_Usuario_plano_1 on Usuario (plano_id);



alter table cadeira_requisito add constraint fk_cadeira_requisito_discipli_01 foreign key (fk_cadeira) references disciplina (id) on delete restrict on update restrict;

alter table cadeira_requisito add constraint fk_cadeira_requisito_discipli_02 foreign key (fk_requisito) references disciplina (id) on delete restrict on update restrict;

alter table periodo_cadeira add constraint fk_periodo_cadeira_periodo_01 foreign key (fk_periodo) references periodo (id) on delete restrict on update restrict;

alter table periodo_cadeira add constraint fk_periodo_cadeira_disciplina_02 foreign key (fk_cadeira) references disciplina (id) on delete restrict on update restrict;

alter table plano_periodo add constraint fk_plano_periodo_plano_de_cur_01 foreign key (plano_de_curso_id) references plano_de_curso (id) on delete restrict on update restrict;

alter table plano_periodo add constraint fk_plano_periodo_periodo_02 foreign key (periodo_id) references periodo (id) on delete restrict on update restrict;

alter table disciplinas_disponiveis add constraint fk_disciplinas_disponiveis_pl_01 foreign key (plano_de_curso_id) references plano_de_curso (id) on delete restrict on update restrict;

alter table disciplinas_disponiveis add constraint fk_disciplinas_disponiveis_di_02 foreign key (disciplina_id) references disciplina (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists disciplina;

drop table if exists cadeira_requisito;

drop table if exists periodo;

drop table if exists periodo_cadeira;

drop table if exists plano_de_curso;

drop table if exists plano_periodo;

drop table if exists disciplinas_disponiveis;

drop table if exists Usuario;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists disciplina_seq;

drop sequence if exists periodo_seq;

drop sequence if exists plano_de_curso_seq;

drop sequence if exists Usuario_seq;

