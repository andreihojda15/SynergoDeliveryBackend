create table customer
(
  id     integer not null primary key,
  name   varchar(255) not null,
  phone_number  varchar(255) not null,
  address  varchar(255) not null,
  packageId  varchar(255) not null
);