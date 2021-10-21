package com.ceiba.tipomascota.consulta;

import com.ceiba.tipomascota.modelo.dto.DtoTipoMascota;
import com.ceiba.tipomascota.puerto.dao.DaoTipoMascota;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListaTipoMascota {

    private final DaoTipoMascota daoTipoMascota;

    public ManejadorListaTipoMascota(DaoTipoMascota daoTipoMascota) {
        this.daoTipoMascota = daoTipoMascota;
    }

    public List<DtoTipoMascota> ejecutar(){return daoTipoMascota.listar();}
}
