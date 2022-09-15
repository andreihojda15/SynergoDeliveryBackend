create table package
(
  id     integer not null primary key,
  sender_name   varchar(255) not null,
  sender_phone  varchar(255) not null,
  departure_address varchar(255) not null,
  departure_date varchar(255) not null,
  awb varchar(255) not null,
  delivery_address varchar(255) not null,
  delivery_date varchar(255) not null,
  recipient_name varchar(255) not null,
  recipient_phone varchar(255) not null
);
