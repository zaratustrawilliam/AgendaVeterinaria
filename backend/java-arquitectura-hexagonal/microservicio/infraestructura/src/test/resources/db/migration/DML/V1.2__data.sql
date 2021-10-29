insert into usuario(id, nombre,clave,fecha_creacion) values(1,'test','1234',now());
insert into mascota(id,nombre,idusuario,idtipomascota) values(1,'anibal',1,1);
insert into agenda(id,idmascota,fecha_agenda,precio,direccion_mascota) values(1,1,'2021-10-25 08:00:00',20000,null);