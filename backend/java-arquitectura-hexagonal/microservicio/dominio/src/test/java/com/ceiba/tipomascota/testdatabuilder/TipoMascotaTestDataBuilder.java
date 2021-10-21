package com.ceiba.tipomascota.testdatabuilder;

import com.ceiba.tipomascota.modelo.entidad.TipoMascota;

public class TipoMascotaTestDataBuilder {

    private Long id;
    private String nombre;

    private final Long ID_TIPOPERRO = 1L;
    private final Long ID_TIPOGATO = 2L;
    private final Long ID_TIPOAVE = 3L;
    private final Long ID_TIPOROEDOR = 4L;

    private final String NOMBRE_TIPOPERRO = "PERRO";
    private final String NOMBRE_TIPOGATO = "GATO";
    private final String NOMBRE_TIPOAVE = "AVE";
    private final String NOMBRE_TIPOROEDOR = "ROEDOR";


    public TipoMascotaTestDataBuilder(){
        nombre = "DESCONOCIDO";
    }

    public TipoMascotaTestDataBuilder aplicarTipoPerro(){
        id = ID_TIPOPERRO;
        nombre = NOMBRE_TIPOPERRO;
        return this;
    }

    public TipoMascotaTestDataBuilder aplicarTipoGato(){
        id = ID_TIPOGATO;
        nombre = NOMBRE_TIPOGATO;
        return this;
    }

    public TipoMascotaTestDataBuilder aplicarTipoAve(){
        id = ID_TIPOAVE;
        nombre = NOMBRE_TIPOAVE;
        return this;
    }

    public TipoMascotaTestDataBuilder aplicarTipoRoedor(){
        id = ID_TIPOROEDOR;
        nombre = NOMBRE_TIPOROEDOR;
        return this;
    }

    public TipoMascota build(){ return new TipoMascota(id,nombre);}
}
