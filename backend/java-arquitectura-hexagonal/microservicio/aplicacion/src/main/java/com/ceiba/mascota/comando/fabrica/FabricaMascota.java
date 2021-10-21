package com.ceiba.mascota.comando.fabrica;

import com.ceiba.mascota.comando.ComandoMascota;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.tipomascota.modelo.entidad.TipoMascota;
import com.ceiba.usuario.modelo.entidad.Usuario;
import org.springframework.stereotype.Component;

@Component
public class FabricaMascota {

    public Mascota crearMascota(ComandoMascota comandoMascota){

        return new Mascota(comandoMascota.getId(),comandoMascota.getNombre(),
                new Usuario(comandoMascota.getUsuario(), null,null,
                        null,Boolean.FALSE),
                new TipoMascota(comandoMascota.getTipoMascota()));
    }
}
