# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table task (
  id                        bigint not null,
  label                     varchar(255),
  constraint pk_task primary key (id))
;

create table user (
  id                        bigint not null,
  login                     varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (id))
;

create sequence task_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists task;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists task_seq;

drop sequence if exists user_seq;

