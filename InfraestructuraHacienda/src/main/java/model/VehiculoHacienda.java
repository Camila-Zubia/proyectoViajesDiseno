/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import org.bson.types.ObjectId;

/**
 *
 * @author adell
 */
public class VehiculoHacienda {

    private ObjectId id;
    private String numeroSerie;
    private String placas;
    private String marca;
    private String modelo;
    private String color;
    private int capacidad;

    public VehiculoHacienda(String numeroSerie, String placas, String marca, String modelo, String color, int capacidad) {
        this.numeroSerie = numeroSerie;
        this.placas = placas;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.capacidad = capacidad;
    }

    public VehiculoHacienda(ObjectId id, String numeroSerie, String placas, String marca, String modelo, String color, int capacidad) {
        this.id = id;
        this.numeroSerie = numeroSerie;
        this.placas = placas;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.capacidad = capacidad;
    }

    public VehiculoHacienda() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
    
    
    
    
    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "VehiculoHacienda{" + "id=" + id + ", numeroSerie=" + numeroSerie + ", placas=" + placas + ", marca=" + marca + ", modelo=" + modelo + ", color=" + color + ", capacidad=" + capacidad + '}';
    }

    
}
