package com.ceiba.agenda.comando;

import com.ceiba.mascota.modelo.entidad.Mascota;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoAgenda {

    private Long id;
    private Long idMascota;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime fechaAgenda;
    private BigDecimal precio;
    private String direccionMascota;
}
