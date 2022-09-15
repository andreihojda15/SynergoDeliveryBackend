alter table driver
    add car_id integer;

alter table driver
    add constraint driver_car_id_fk
        foreign key (car_id) references car (id);