create table car
(
  id     integer not null auto_increment primary key,
  registration_number   varchar(255) not null,
  status  varchar(255) not null,
  pack_id  integer,
  constraint car_pack_id_fk
    foreign key (pack_id) references package (id)
);