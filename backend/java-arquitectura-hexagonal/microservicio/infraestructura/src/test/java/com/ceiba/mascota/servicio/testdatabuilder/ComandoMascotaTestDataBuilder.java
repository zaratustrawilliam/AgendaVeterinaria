package com.ceiba.mascota.servicio.testdatabuilder;

import com.ceiba.mascota.comando.ComandoMascota;

public class ComandoMascotaTestDataBuilder {

    private Long id;
    private String nombre;
    private Long usuario;
    private Long tipoMascota;

    public ComandoMascotaTestDataBuilder(){
        nombre = "Pluto";
    }

    public ComandoMascotaTestDataBuilder conNombre(String nombre){
        this.nombre=nombre;
        return this;
    }

    public ComandoMascotaTestDataBuilder conUsuario(Long idUsuario){
        this.usuario=idUsuario;
        return this;
    }

    public ComandoMascotaTestDataBuilder conTipoMascota(Long idTipoMascota){
        this.tipoMascota=idTipoMascota;
        return this;
    }

    public ComandoMascotaTestDataBuilder conId(Long id){
        this.id=id;
        return this;
    }

    public ComandoMascota build(){
        return new ComandoMascota(id,nombre,usuario,tipoMascota);
    }
}
