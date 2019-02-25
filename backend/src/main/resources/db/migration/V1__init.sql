create table employee
(
  id         bigint         not null  auto_increment  primary key,
  created    timestamp      null,
  updated    timestamp      null,
  first_name varchar(255)   null,
  last_name  varchar(255)   null,
  salary     int            null
)
engine=InnoDB DEFAULT CHARSET=utf8;