alter table customer
    drop column packageId;

alter table package
    add customer_id integer not null;

alter table package
    add constraint package_customer_id_fk
        foreign key (customer_id) references customer (id);