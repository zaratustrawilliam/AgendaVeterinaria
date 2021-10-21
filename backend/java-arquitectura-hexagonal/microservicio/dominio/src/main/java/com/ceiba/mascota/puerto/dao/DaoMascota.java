package com.ceiba.mascota.puerto.dao;

import com.ceiba.mascota.modelo.dto.DtoMascota;

import java.util.List;

public interface DaoMascota {

    public List<DtoMascota> listar();

    public List<DtoMascota> listarPorUsuario(Long idUsuario);
}
