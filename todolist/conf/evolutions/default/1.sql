# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table install_state (
  id                        bigint not null,
  installation_complete     boolean,
  constraint pk_install_state primary key (id))
;

create table task (
  id                        bigint not null,
  label                     varchar(255),
  user_email                varchar(255),
  constraint pk_task primary key (id))
;

create table user (
  email                     varchar(255) not null,
  password                  varchar(255),
  is_admin                  boolean,
  constraint pk_user primary key (email))
;

create sequence install_state_seq;

create sequence task_seq;

create sequence user_seq;

alter table task add constraint fk_task_user_1 foreign key (user_email) references user (email) on delete restrict on update restrict;
create index ix_task_user_1 on task (user_email);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists install_state;

drop table if exists task;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists install_state_seq;

drop sequence if exists task_seq;

drop sequence if exists user_seq;

