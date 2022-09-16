create table package
(
  id     integer not null auto_increment primary key,
  sender_name   varchar(255) not null,
  sender_phone  varchar(255) not null,
  departure_address varchar(255) not null,
  departure_date date not null,
  awb varchar(255) not null,
  delivery_address varchar(255) not null,
  delivery_date date not null,
  recipient_name varchar(255) not null,
  recipient_phone varchar(255) not null,
  car_id  integer,
  constraint pack_car_id_fk
    foreign key (car_id) references car (id)
);
