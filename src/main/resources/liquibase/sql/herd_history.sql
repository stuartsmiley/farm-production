create table herd_history (
    id bigint not null AUTO_INCREMENT,
    change_date date not null,
    event varchar(20) not null,
    goat_id bigint,
    primary key (id)
);

alter table herd_history add constraint goat_herd_history_fk foreign key (goat_id) references goat(id);

insert into herd_history(change_date, event, goat_id)  select now(), 'IN', id from goat where producing = 1;
insert into herd_history(change_date, event, goat_id)  select now(), 'OUT', id from goat where producing = 0;



create view current_herd as
select h.id, h.change_date, h.event, h.goat_id
from herd_history h where not exists (
  select 1 from herd_history h2
  where h.goat_id = h2.goat_id and h2.change_date > h.change_date);
