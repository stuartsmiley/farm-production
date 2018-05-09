create table rain (
    id bigint not null AUTO_INCREMENT,
    millimeters numeric(5,1) not null,
    recordedOn date not null,
    primary key (id)
);

alter table rain add unique index rain_date_index (recordedOn);
