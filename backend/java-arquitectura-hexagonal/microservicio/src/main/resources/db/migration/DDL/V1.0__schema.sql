create table usuario (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 clave varchar(45) not null,
 fecha_creacion datetime null,
 primary key (id)
);

create table tipomascota(
    id int(11) not null auto_increment,
    nombre varchar(100) not null,
    primary key (id)
);

insert into tipomascota (id, nombre) values (1,'PERRO');
insert into tipomascota (id, nombre) values(4,'ROEDOR');;
insert into tipomascota (id, nombre) values(2,'GATO');
insert into tipomascota (id, nombre) values(3,'AVE');

create table mascota(
    id int(11) not null auto_increment,
    nombre varchar(100) not null,
    idusuario int(11) not null,
    idtipomascota int(11) not null,
    primary key (id)
);