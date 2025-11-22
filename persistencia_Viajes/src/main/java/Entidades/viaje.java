/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 *
 * @author adell
 */
public class viaje {
    String nombre;
    String destino;
    String origen;
    Date fecha;
    Time hora;
    double precioTotal;
    List<paradas> paradas;

    public viaje(String nombre, String destino, String origen, Date fecha, Time hora, double precioTotal) {
        this.nombre = nombre;
        this.destino = destino;
        this.origen = origen;
        this.fecha = fecha;
        this.hora = hora;
        this.precioTotal = precioTotal;
    }
    
    
    
    
    public viaje(String nombre, String destino, String origen, Date fecha, Time hora, double precioTotal, List<paradas> paradas) {
        this.nombre = nombre;
        this.destino = destino;
        this.origen = origen;
        this.fecha = fecha;
        this.hora = hora;
        this.precioTotal = precioTotal;
        this.paradas = paradas;
    }
    
    
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public List<paradas> getParadas() {
        return paradas;
    }

    public void setParadas(List<paradas> paradas) {
        this.paradas = paradas;
    }
    
    
}
