package com.ceiba.jpa.model;

import com.ceiba.tipomascota.modelo.entidad.TipoMascota;

import javax.persistence.*;

@Entity
@Table(name = TipoMascotaJpa.TIPOMASCOTAJPA_TABLE)
public class TipoMascotaJpa {

    static final String TIPOMASCOTAJPA_TABLE  = "tipomascota";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

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

    public TipoMascotaJpa(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public TipoMascotaJpa() {
    }

    public static TipoMascota fromTipoMascota(TipoMascotaJpa tipoMascotaJpa){
        return new TipoMascota(tipoMascotaJpa.getId(),tipoMascotaJpa.getNombre());
    }

    public static TipoMascotaJpa toTipoMascota(TipoMascota tipoMascota){
        return new TipoMascotaJpa(tipoMascota.getId(), tipoMascota.getNombre());
    }

    public static TipoMascota fromTipoMascotaMapper(TipoMascotaJpa tipoMascotaJpa){
        return new TipoMascota(tipoMascotaJpa.getId(),null);
    }
}
