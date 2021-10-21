package com.ceiba.mascota.servicio.testdatabuilder;

import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.tipomascota.modelo.entidad.TipoMascota;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;

public class MascotaTestDataBuilder {

    private Long id;
    private String nombre;
    private Usuario usuario;
    private TipoMascota tipoMascota;

    public MascotaTestDataBuilder(){
        nombre = "pluto";
        usuario = new Usuario();
        tipoMascota = new TipoMascota();
    }

    public MascotaTestDataBuilder conUsuario(Usuario usuario){
        this.usuario=usuario;
        return this;
    }

    public MascotaTestDataBuilder conTipoMascota(TipoMascota tipoMascota){
        this.tipoMascota=tipoMascota;
        return this;
    }

    public MascotaTestDataBuilder conNombre(String nombre){
        this.nombre=nombre;
        return this;
    }

    public MascotaTestDataBuilder conId(Long id){
        this.id=id;
        return this;
    }

    public Mascota build(){
        return new Mascota(id,nombre,usuario,tipoMascota);
    }
}
