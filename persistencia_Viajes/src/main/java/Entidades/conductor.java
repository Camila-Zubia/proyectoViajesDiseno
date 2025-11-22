/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.util.List;

/**
 *
 * @author adell
 */
public class conductor {
    String nombre;
    double calificacion;
    List<viaje> viajes;
    List<vehiculo> vehiculos;

    public conductor(String nombre, double calificacion, List<viaje> viajes, List<vehiculo> vehiculos) {
        this.nombre = nombre;
        this.calificacion = calificacion;
        this.viajes = viajes;
        this.vehiculos = vehiculos;
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

    public List<viaje> getViajes() {
        return viajes;
    }

    public void setViajes(List<viaje> viajes) {
        this.viajes = viajes;
    }

    public List<vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
    
    
    
}
