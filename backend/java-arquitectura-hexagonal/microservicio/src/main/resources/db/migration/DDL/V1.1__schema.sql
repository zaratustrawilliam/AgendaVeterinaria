create table agenda(
    id int(11) not null auto_increment,
    idmascota int(11) not null,
    fecha_agenda datetime not null,
    precio decimal(15,5) not null,
    direccion_mascota varchar(250) null,
    primary key (id)
);