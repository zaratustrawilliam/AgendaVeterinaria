package com.ceiba.agenda.modelo.entidad;

import com.ceiba.dominio.GeneradorDiasFestivos;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.mascota.modelo.entidad.Mascota;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.*;

@NoArgsConstructor
@Getter
public class Agenda {

    private static final String MASCOTA_REFERENCIA_OBLIOGATORIO = "la Agenda debe estar asociada a una mascota";
    private static final String FECHA_AGENDA_OBLIGATORIA = "La agenda debe tener la fecha de reserva";
    private static final String FECHA_AGENDA_DIA_MENOR = "La fecha a agenda es menor a la fecha actual, eso es invalido";
    private static final BigDecimal PRECIO_BASE_CONSULTA = new BigDecimal(20000);
    private static final BigDecimal PRECIO_BASE_DOMICILIO = new BigDecimal(5000);
    private static final BigDecimal PRECIO_FESTIVIDAD_DOMICILIO = new BigDecimal(8000);
    private static final BigDecimal PRECIO_SANCION_MOVERCITA = new BigDecimal(10000);
    private static final String VALOR_INVALIDO_PRECIO = "Se tiene un valor invalido para el precio";
    private static final String FECHA_FUERA_HORARIO_LABORAL= "La agenda se debe hacer dentro del horario de trabajo";

    private Long id;
    private Mascota mascota;
    private LocalDateTime fechaAgenda;
    private BigDecimal precio;
    private String direccionMascota;

    public Agenda(Long id, Mascota mascota, LocalDateTime fechaAgenda, BigDecimal precio, String direccionMascota) {

        validarObligatorio(fechaAgenda,FECHA_AGENDA_OBLIGATORIA);
        validarMenor(fechaAgenda);
        validarHorarioLaboral(fechaAgenda);
        validarObligatorio(mascota,MASCOTA_REFERENCIA_OBLIOGATORIO);
        validarObligatorio(mascota.getId(),MASCOTA_REFERENCIA_OBLIOGATORIO);

        this.id = id;
        this.mascota = mascota;
        this.fechaAgenda = fechaAgenda;
        this.precio = precio;
        this.direccionMascota = direccionMascota;
    }

    public Agenda(Long id){
        this.id=id;
    }

    public Agenda(Long id, Mascota mascota, LocalDateTime fechaAgenda, BigDecimal precio, String direccionMascota,boolean mapper) {

        this.id = id;
        if(mapper){
            this.mascota = mascota;
            this.fechaAgenda = fechaAgenda;
            this.precio = precio;
            this.direccionMascota = direccionMascota;
        }
    }

    private void validarMenor(LocalDateTime fechaAgenda){
        if(fechaAgenda.getYear() < LocalDateTime.now().getYear() ||
                (validarMes(fechaAgenda)) ||
                (validarDia(fechaAgenda))){
            throw new ExcepcionValorInvalido(FECHA_AGENDA_DIA_MENOR);
        }
    }

    private boolean validarMes(LocalDateTime fechaAgenda){
        return fechaAgenda.getYear() == LocalDateTime.now().getYear() &&
                        fechaAgenda.getDayOfYear() < LocalDateTime.now().getDayOfYear();
    }

    private boolean validarDia(LocalDateTime fechaAgenda){
        return fechaAgenda.getYear() == LocalDateTime.now().getYear() &&
                fechaAgenda.getDayOfYear() == LocalDateTime.now().getDayOfYear() &&
                fechaAgenda.getHour() < LocalDateTime.now().plusHours(1).getHour();
    }

    private void validarHorarioLaboral(LocalDateTime fechaAgenda){
        if(fechaAgenda.getHour() < 7 || fechaAgenda.getHour() > 17){
            throw new ExcepcionValorInvalido(FECHA_FUERA_HORARIO_LABORAL);
        }
    }

    private boolean esFestivo(LocalDateTime fechaAgendar){
        return GeneradorDiasFestivos.daysToDaysOfYear(
                        GeneradorDiasFestivos.listarDiasFestivos(fechaAgendar.getYear())).
                contains(fechaAgendar.getDayOfYear());
    }

    public void calcularPrecio(){
        //aplicamos el precio base
        this.precio = PRECIO_BASE_CONSULTA;
        //validamos si hay domicilio
        if(direccionMascota != null && !direccionMascota.isEmpty()){
            if(fechaAgenda.getDayOfWeek().equals(DayOfWeek.SUNDAY)
                    || esFestivo(fechaAgenda)){
                precio = precio.add(PRECIO_FESTIVIDAD_DOMICILIO);
            }else{
                precio = precio.add(PRECIO_BASE_DOMICILIO);
            }
        }
    }

    public void agregarValorMultaTrasladoCita(){
        if(precio == null){
            throw new ExcepcionValorInvalido(VALOR_INVALIDO_PRECIO);
        }else{
            precio = precio.add(PRECIO_SANCION_MOVERCITA);
        }
    }

    public void eliminarMinutosFechaAgenda(){
        this.fechaAgenda = fechaAgenda.withMinute(0).withSecond(0).withNano(0);
    }

    public void ingresarValorAgenda(BigDecimal valor){
        this.precio = valor;
    }
}
