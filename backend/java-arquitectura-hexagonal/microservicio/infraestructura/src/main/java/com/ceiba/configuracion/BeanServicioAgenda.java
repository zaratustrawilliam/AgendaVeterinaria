package com.ceiba.configuracion;

import com.ceiba.agenda.puerto.repositorio.RepositorioAgenda;
import com.ceiba.agenda.servicios.ServicioActualizarAgenda;
import com.ceiba.agenda.servicios.ServicioCrearAgenda;
import com.ceiba.agenda.servicios.ServicioEliminarAgenda;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioAgenda {

    @Bean
    public ServicioCrearAgenda servicioCrearAgenda(RepositorioMascota repositorioMascota,
                                                   RepositorioAgenda repositorioAgenda){
        return new ServicioCrearAgenda(repositorioMascota,repositorioAgenda);
    }

    @Bean
    public ServicioActualizarAgenda servicioActualizarAgenda(RepositorioAgenda repositorioAgenda,
                                                             RepositorioMascota repositorioMascota){
        return new ServicioActualizarAgenda(repositorioAgenda,repositorioMascota);
    }

    @Bean
    public ServicioEliminarAgenda servicioEliminarAgenda(RepositorioAgenda repositorioAgenda){
        return new ServicioEliminarAgenda(repositorioAgenda);
    }
}
