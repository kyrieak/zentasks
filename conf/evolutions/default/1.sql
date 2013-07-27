# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table uzer (
  uid                       integer not null,
  email                     varchar(255),
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_uzer primary key (uid))
;

create sequence uzer_seq;




# --- !Downs

drop table if exists uzer cascade;

drop sequence if exists uzer_seq;

