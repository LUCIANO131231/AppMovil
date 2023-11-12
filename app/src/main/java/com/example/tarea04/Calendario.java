package com.example.tarea04;
import java.util.Date;
public class Calendario {
    Date fecha;
    public String horaInicio;
    public String horaFin;
    public String ubicacion;

    public Calendario(Date fecha, String horaInicio, String horaFin, String ubicacion) {
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.ubicacion = ubicacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public String getUbicacion() {
        return ubicacion;
    }
}
