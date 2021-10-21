package com.ceiba.mascota.adaptador.repositorio;

import com.ceiba.jpa.model.MascotaJpa;
import com.ceiba.jpa.repository.ImpMascotaJpaRepository;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

@Repository
@ConditionalOnProperty(value = "persistencia.type", havingValue = "jpa")
public class RepositorioMascotaJpa implements RepositorioMascota {

    private final ImpMascotaJpaRepository impMascotaJpaRepository;

    @Autowired
    public RepositorioMascotaJpa(ImpMascotaJpaRepository impMascotaJpaRepository) {
        this.impMascotaJpaRepository = impMascotaJpaRepository;
    }

    @Override
    public Long crear(Mascota mascota) {
        return impMascotaJpaRepository.save(MascotaJpa.toMascota(mascota)).getId();
    }

    @Override
    public void actualizar(Mascota mascota) {
        impMascotaJpaRepository.saveAndFlush(MascotaJpa.toMascota(mascota));
    }

    @Override
    public void eliminar(Long id) {
        impMascotaJpaRepository.delete(impMascotaJpaRepository.getById(id));
    }

    @Override
    public boolean existePorUsuarioNombreMascota(Long idUsuario, String nombreMascota) {
        boolean salida = Boolean.FALSE;
        try{
            Long identificadorMascota = impMascotaJpaRepository.
                    buscarPorNombreMascotaUsuarioId(nombreMascota,idUsuario);
            if(identificadorMascota != null)salida = Boolean.TRUE;
        }catch(NoResultException | NonUniqueResultException ex){ex.printStackTrace();}
        return salida;
    }

    @Override
    public boolean existePorId(Long idMascota) {
        return impMascotaJpaRepository.existsById(idMascota);
    }

    @Override
    public Mascota buscarPorId(Long idMascota) {
        return MascotaJpa.fromMascota(impMascotaJpaRepository.getById(idMascota));
    }
}
