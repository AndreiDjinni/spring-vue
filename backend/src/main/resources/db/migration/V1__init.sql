create table department
(
  id      bigint auto_increment primary key,
  created datetime     not null,
  updated datetime     not null,
  name    varchar(255) null,
  constraint UK1t68827l97cwyxo9r1u6t4p7d unique (name)
)
engine=InnoDB DEFAULT CHARSET=utf8;;

create table employee
(
  id            bigint         not null  auto_increment  primary key,
  created       timestamp      null,
  updated       timestamp      null,
  first_name    varchar(255)   null,
  last_name     varchar(255)   null,
  salary        int            null,
  department_id bigint         not null,
  active        bit(1)         null,
  constraint FKbejtwvg9bxus2mffsm3swj3u9 foreign key (department_id) references department (id)
)
engine=InnoDB DEFAULT CHARSET=utf8;

create table users
(
  id             bigint auto_increment  primary key,
  created        datetime     not null,
  updated        datetime     not null,
  email          varchar(255) not null,
  email_verified bit          not null,
  image_url      varchar(255) null,
  name           varchar(255) not null,
  password       varchar(255) null,
  provider       varchar(255) not null,
  provider_id    varchar(255) null,
  constraint UK_EMAIL unique (email)
)
engine=InnoDB DEFAULT CHARSET=utf8;