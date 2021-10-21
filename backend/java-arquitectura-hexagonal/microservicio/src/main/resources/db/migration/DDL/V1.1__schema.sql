create table agenda(
    id int(11) not null auto_increment,
    idmascota int(11) not null,
    fechaAgenda datetime not null,
    precio decimal(15,5) not null,
    direccionMascota varchar(250) null,
    primary key (id)
);