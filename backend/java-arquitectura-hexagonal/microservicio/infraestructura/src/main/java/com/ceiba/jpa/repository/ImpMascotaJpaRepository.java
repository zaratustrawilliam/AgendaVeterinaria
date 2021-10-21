package com.ceiba.jpa.repository;

import com.ceiba.jpa.model.MascotaJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImpMascotaJpaRepository extends JpaRepository<MascotaJpa,Long> {

    @Query("Select mascota From MascotaJpa mascota where mascota.usuario.id =:idUsuario")
    List<MascotaJpa> listarPorUsuario(@Param("idUsuario")Long idUsuario);

    @Query("Select mascota.id from MascotaJpa mascota where mascota.usuario.id =:idUsuario and mascota.nombre =:nombreMascota")
    Long buscarPorNombreMascotaUsuarioId(@Param("nombreMascota")String nombreMascota,@Param("idUsuario") Long idUsuario);
}
