# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table project (
  id                        bigint not null,
  name                      varchar(255),
  folder                    varchar(255),
  constraint pk_project primary key (id))
;

create table task (
  id                        bigint not null,
  title                     varchar(255),
  due_date                  timestamp,
  folder                    varchar(255),
  done                      boolean,
  uzer_uid                  integer,
  project_id                bigint,
  constraint pk_task primary key (id))
;

create table uzer (
  uid                       integer not null,
  email                     varchar(255),
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_uzer primary key (uid))
;


create table project_uzer (
  project_id                     bigint not null,
  uzer_uid                       integer not null,
  constraint pk_project_uzer primary key (project_id, uzer_uid))
;
create sequence project_seq;

create sequence task_seq;

create sequence uzer_seq;

alter table task add constraint fk_task_uzer_1 foreign key (uzer_uid) references uzer (uid) on delete restrict on update restrict;
create index ix_task_uzer_1 on task (uzer_uid);
alter table task add constraint fk_task_project_2 foreign key (project_id) references project (id) on delete restrict on update restrict;
create index ix_task_project_2 on task (project_id);



alter table project_uzer add constraint fk_project_uzer_project_01 foreign key (project_id) references project (id) on delete restrict on update restrict;

alter table project_uzer add constraint fk_project_uzer_uzer_02 foreign key (uzer_uid) references uzer (uid) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists project;

drop table if exists project_uzer;

drop table if exists task;

drop table if exists uzer;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists project_seq;

drop sequence if exists task_seq;

drop sequence if exists uzer_seq;

