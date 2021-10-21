package com.ceiba.jpa.model;

import com.ceiba.usuario.modelo.entidad.Usuario;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = UsuarioJpa.USUARIO)
public class UsuarioJpa {

    static final String USUARIO = "usuario";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String clave;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public UsuarioJpa(Long id, String nombre, String clave, LocalDateTime fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
        this.fechaCreacion = fechaCreacion;
    }

    public UsuarioJpa(){}

    public static UsuarioJpa fromUsuario(Usuario usuario) throws ClassCastException{
        return new UsuarioJpa(usuario.getId(),usuario.getNombre(),
                usuario.getClave(),usuario.getFechaCreacion());
    }

    public static Usuario toUsuario(UsuarioJpa usuarioJpa) throws  ClassCastException{
        return new Usuario(usuarioJpa.getId(),
                usuarioJpa.getNombre(), usuarioJpa.getClave(),usuarioJpa.getFechaCreacion());
    }

    public static Usuario toUsuarioMapper(UsuarioJpa usuarioJpa) throws  ClassCastException{
        return new Usuario(usuarioJpa.getId(),null,null,null,Boolean.TRUE);
    }
}
