package com.ceiba.mascota.puerto.repositorio;

import com.ceiba.mascota.modelo.entidad.Mascota;

public interface RepositorioMascota {

     Long crear(Mascota Mascota);

     void actualizar(Mascota Mascota);

     void eliminar(Long id);

     boolean existePorUsuarioNombreMascota(Long idUsuario,String nombreMascota);

     boolean existePorId(Long idMascota);

     Mascota buscarPorId(Long idMascota);
}
