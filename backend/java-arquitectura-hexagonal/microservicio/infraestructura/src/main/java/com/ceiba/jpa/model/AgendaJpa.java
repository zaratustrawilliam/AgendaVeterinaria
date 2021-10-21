package com.ceiba.jpa.model;

import com.ceiba.agenda.modelo.entidad.Agenda;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = AgendaJpa.AGENDAJPA_TABLE)
public class AgendaJpa {

    static final String AGENDAJPA_TABLE = "agenda";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false,cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "idmascota")
    private MascotaJpa mascota;
    private LocalDateTime fechaAgenda;
    private BigDecimal precio;
    private String direccionMascota;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MascotaJpa getMascota() {
        return mascota;
    }

    public void setMascota(MascotaJpa mascota) {
        this.mascota = mascota;
    }

    public LocalDateTime getFechaAgenda() {
        return fechaAgenda;
    }

    public void setFechaAgenda(LocalDateTime fechaAgenda) {
        this.fechaAgenda = fechaAgenda;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDireccionMascota() {
        return direccionMascota;
    }

    public void setDireccionMascota(String direccionMascota) {
        this.direccionMascota = direccionMascota;
    }

    public AgendaJpa(){}

    public AgendaJpa(Long id, MascotaJpa mascota, LocalDateTime fechaAgenda, BigDecimal precio, String direccionMascota) {
        this.id = id;
        this.mascota = mascota;
        this.fechaAgenda = fechaAgenda;
        this.precio = precio;
        this.direccionMascota = direccionMascota;
    }

    public static Agenda fromAgenda(AgendaJpa agendaJpa){
        return new Agenda(agendaJpa.getId(),MascotaJpa.fromMascotaMapper(agendaJpa.getMascota()),
                agendaJpa.getFechaAgenda(),agendaJpa.getPrecio(),agendaJpa.getDireccionMascota());
    }

    public static AgendaJpa toAgenda(Agenda agenda){
        return new AgendaJpa(agenda.getId(),MascotaJpa.toMascota(agenda.getMascota()),
                agenda.getFechaAgenda(),agenda.getPrecio(),agenda.getDireccionMascota());
    }
}
