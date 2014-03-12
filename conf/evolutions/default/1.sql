# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table disciplina (
  id                        bigint not null,
  nome_cadeira              varchar(255) not null,
  creditos                  integer,
  dificuldade               integer,
  constraint uq_disciplina_nome_cadeira unique (nome_cadeira),
  constraint pk_disciplina primary key (id))
;


create table cadeira_requisito (
  fk_cadeira                     bigint not null,
  fk_requisito                   bigint not null,
  constraint pk_cadeira_requisito primary key (fk_cadeira, fk_requisito))
;
create sequence disciplina_seq;




alter table cadeira_requisito add constraint fk_cadeira_requisito_discipli_01 foreign key (fk_cadeira) references disciplina (id) on delete restrict on update restrict;

alter table cadeira_requisito add constraint fk_cadeira_requisito_discipli_02 foreign key (fk_requisito) references disciplina (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists disciplina;

drop table if exists cadeira_requisito;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists disciplina_seq;

