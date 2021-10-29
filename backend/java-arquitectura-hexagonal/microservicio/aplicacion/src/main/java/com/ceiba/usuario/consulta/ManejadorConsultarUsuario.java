package com.ceiba.usuario.consulta;

import com.ceiba.usuario.modelo.dto.DtoUsuario;
import com.ceiba.usuario.puerto.dao.DaoUsuario;
import org.springframework.stereotype.Component;

@Component

public class ManejadorConsultarUsuario {

    private final DaoUsuario daoUsuario;

    public ManejadorConsultarUsuario(DaoUsuario daoUsuario) {
        this.daoUsuario = daoUsuario;
    }

    public DtoUsuario ejecutar(Long id){
        return daoUsuario.buscarPorId(id);
    }
}
