
create table categoria{
	id serial primary key,
	nombre varchar(50) not null unique
}
insert into categoria (nombre) values ('cat1');

