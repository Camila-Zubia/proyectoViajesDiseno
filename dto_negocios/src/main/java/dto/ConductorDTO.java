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
    
    private String id;
    private String nombre;
    private int calificación;
    private List<VehiculoDTO> vehiculos;
    private List<ViajeDTO> viajes;
    private List<AdeudoDTO> adeudos;

    public ConductorDTO() {
        this.adeudos = new ArrayList<>();
    }

    public ConductorDTO(String nombre) {
        this.nombre = nombre;
        this.calificación = 100;
        this.vehiculos = new ArrayList<>();
        this.viajes = new ArrayList<>();
        this.adeudos = new ArrayList<>();
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<AdeudoDTO> getAdeudos() {
        return adeudos;
    }

    public void setAdeudos(List<AdeudoDTO> adeudos) {
        this.adeudos = adeudos;
    }


    @Override
    public String toString() {
        return String.format("Nombre : " + nombre +
                "\nCalificaci\u00f3n : " + calificación);
    }
    
}
