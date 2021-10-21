package com.ceiba.mascota.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoMascota {

    private Long id;
    private String nombre;
    private Long usuario;
    private Long tipoMascota;
}
