create table goat (
    id bigint not null,
    nombre varchar(40) not null,
    producing boolean not null,
    primary key (id)
    );
create table production_event (
    id bigint not null AUTO_INCREMENT,
    version smallint not null,
    created TIMESTAMP NOT NULL,
    quantity DECIMAL(6,2) NOT NULL,
    units_id bigint not null,
    producers int not null,
    storage_id bigint,
    note varchar(255),
    disposition varchar(255),
    primary key (id)
    );
create table sample (
    id bigint not null AUTO_INCREMENT,
    version smallint not null,
    liters DECIMAL(3,2) not null,
    goat_id bigint  not null,
    sample_date  TIMESTAMP NOT NULL,
    primary key (id)
);
create table storage (
    id bigint not null AUTO_INCREMENT,
    location varchar(40) not null,
    active boolean not null,
    primary key (id)
    );
create table units (
    id bigint not null AUTO_INCREMENT,
    name varchar(40),
    active boolean not null,
    primary key (id)
);

alter table production_event add constraint units_production_event_fk foreign key (units_id) references units(id);
alter table production_event add constraint storage_production_event_fk foreign key (storage_id) references storage(id);
alter table sample add constraint goat_sample_fk foreign key (goat_id) references goat(id);