/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class PasajeroDTO {
    
    private String nombre;
    private double calificacion;
    private List<SolicitudDTO> reservaciones;

    public PasajeroDTO() {
    }

    public PasajeroDTO(String nombre) {
        this.nombre = nombre;
        this.calificacion = 100.0;
        this.reservaciones = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public List<SolicitudDTO> getReservaciones() {
        return reservaciones;
    }

    public void setReservaciones(List<SolicitudDTO> reservaciones) {
        this.reservaciones = reservaciones;
    }

    @Override
    public String toString() {
        return String.format("Nombre = " + nombre +
                "\nCalificaci\u00f3n = " + calificacion);
    }
    
    
    
}
