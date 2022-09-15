create table car
(
  id     integer not null primary key,
  registration_number   varchar(255) not null,
  status  varchar(255) not null,
  pack_id  integer,
  driver_id  integer,
  constraint car_pack_id_fk
    foreign key (pack_id) references package (id),
  constraint car_driver_id_fk
    foreign key (driver_id) references driver (id)
);