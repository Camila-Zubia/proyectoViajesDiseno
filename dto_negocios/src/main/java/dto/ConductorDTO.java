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
public class ConductorDTO {
    
    private String nombre;
    private int calificación;
    private List<VehiculoDTO> vehiculos;
    private List<ViajeDTO> viajes;

    public ConductorDTO() {
    }

    public ConductorDTO(String nombre) {
        this.nombre = nombre;
        this.calificación = 100;
        this.vehiculos = new ArrayList<>();
        this.viajes = new ArrayList<>();
    }

    public ConductorDTO(String nombre, int calificación) {
        this.nombre = nombre;
        this.calificación = calificación;
    }
    
    
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCalificación() {
        return calificación;
    }

    public void setCalificación(int calificación) {
        this.calificación = calificación;
    }

    public List<VehiculoDTO> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<VehiculoDTO> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public List<ViajeDTO> getViajes() {
        return viajes;
    }

    public void setViajes(List<ViajeDTO> viajes) {
        this.viajes = viajes;
    }

    @Override
    public String toString() {
        return "Conductor:" + 
                "\nnombre = " + nombre + 
                "\ncalificaci\u00f3n = " + calificación;
    }
    
}
