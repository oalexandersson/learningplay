# --- !Ups

CREATE TABLE user (
    email      VARCHAR(255) not null primary key,
    name       VARCHAR(255) not null,
    password   VARCHAR(255) not null
);

CREATE TABLE link (
    id                        BIGINT not null primary key,
    title                     VARCHAR(255) not null,
    url                       VARCHAR(255) not null,
    user_email                VARCHAR(255) not null,
    foreign key(user_email)   references user(email) on delete cascade
);

CREATE SEQUENCE link_seq;

INSERT INTO user (email, name, password) VALUES ('oskara@gmail.com', 'Oskar Alexanderson', 'tomte');
INSERT INTO link (id, title, url, user_email) VALUES (link_seq.nextVal, 'Test', 'http://www.osnews.com', 'oskara@gmail.com')

# --- !Downs

DROP TABLE IF EXISTS link;
DROP SEQUENCE IF EXISTS link_seq;
DROP TABLE IF EXISTS user;

