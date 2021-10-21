package com.ceiba.jpa.model;


import com.ceiba.mascota.modelo.entidad.Mascota;

import javax.persistence.*;

@Entity
@Table(name = MascotaJpa.MASCOTAJPA_TABLE)
public class MascotaJpa {

    static final String MASCOTAJPA_TABLE = "mascota";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToOne(optional = false,cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "idusuario")
    private UsuarioJpa usuario;

    @ManyToOne(optional = false,cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "idtipomascota")
    private TipoMascotaJpa tipoMascota;

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

    public UsuarioJpa getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioJpa usuario) {
        this.usuario = usuario;
    }

    public TipoMascotaJpa getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(TipoMascotaJpa tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public MascotaJpa(){}

    public MascotaJpa(Long id, String nombre, UsuarioJpa usuario, TipoMascotaJpa tipoMascota) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.tipoMascota = tipoMascota;
    }

    public static Mascota fromMascota(MascotaJpa mascotaJpa){
        return new Mascota(mascotaJpa.getId(),mascotaJpa.getNombre(),
                UsuarioJpa.toUsuarioMapper(mascotaJpa.getUsuario()),
                TipoMascotaJpa.fromTipoMascotaMapper(mascotaJpa.getTipoMascota()));
    }

    public static MascotaJpa toMascota(Mascota mascota){
        return new MascotaJpa(mascota.getId(),mascota.getNombre(),
                mascota.getUsuario() != null ? UsuarioJpa.fromUsuario(mascota.getUsuario()) : null,
                mascota.getTipoMascota() != null ? TipoMascotaJpa.toTipoMascota(mascota.getTipoMascota()) : null);
    }
}
