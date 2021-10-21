package com.ceiba.usuario.adaptador.repositorio;

import com.ceiba.jpa.model.UsuarioJpa;
import com.ceiba.jpa.repository.ImpUsuarioJpaRepository;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

@Repository
@ConditionalOnProperty(value = "persistencia.type", havingValue = "jpa")
public class RepositorioUsuarioJPA  implements RepositorioUsuario{

    private final ImpUsuarioJpaRepository repositorio;

    @Autowired
    public RepositorioUsuarioJPA(ImpUsuarioJpaRepository repositorio){
        this.repositorio = repositorio;
    }

    @Override
    public Long crear(Usuario usuario) {
        return repositorio.save(UsuarioJpa.fromUsuario(usuario)).getId();
    }

    @Override
    public void actualizar(Usuario usuario) {
        repositorio.saveAndFlush(UsuarioJpa.fromUsuario(usuario));
    }

    @Override
    public void eliminar(Long id) {
        repositorio.delete(repositorio.getById(id));
    }

    @Override
    public boolean existe(String nombre) {
        boolean salida = Boolean.FALSE;
        try{
            UsuarioJpa usuarioJpa = repositorio.findByNombre(nombre);
            if(usuarioJpa != null)salida = Boolean.TRUE;
        }catch(NoResultException | NonUniqueResultException nr){nr.printStackTrace();}
        return salida;
    }

    @Override
    public boolean existePorId(Long id) {
        return repositorio.existsById(id);
    }
    @Override
    public Usuario buscarPorId(Long id) {
        return UsuarioJpa.toUsuario(repositorio.getById(id));
    }
}
