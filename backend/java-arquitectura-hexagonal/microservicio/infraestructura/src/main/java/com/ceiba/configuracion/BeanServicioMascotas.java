package com.ceiba.configuracion;

import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import com.ceiba.mascota.servicios.ServicioActualizarMascota;
import com.ceiba.mascota.servicios.ServicioCrearMascota;
import com.ceiba.mascota.servicios.ServicioEliminarMascota;
import com.ceiba.tipomascota.puerto.repositorio.RepositorioTipoMascota;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioMascotas {

    @Bean
    public ServicioCrearMascota servicioCrearMascota(RepositorioMascota repositorioMascota,
                                                     RepositorioUsuario repositorioUsuario,
                                                     RepositorioTipoMascota repositorioTipoMascota){
        return new ServicioCrearMascota(repositorioMascota, repositorioUsuario, repositorioTipoMascota);
    }

    @Bean
    public ServicioActualizarMascota servicioActualizarMascota(RepositorioMascota repositorioMascota,
                                                               RepositorioUsuario repositorioUsuario,
                                                               RepositorioTipoMascota repositorioTipoMascota){
        return new ServicioActualizarMascota(repositorioMascota,repositorioUsuario, repositorioTipoMascota);
    }

    @Bean
    public ServicioEliminarMascota servicioEliminarMascota(RepositorioMascota repositorioMascota){
        return new ServicioEliminarMascota(repositorioMascota);
    }
}
