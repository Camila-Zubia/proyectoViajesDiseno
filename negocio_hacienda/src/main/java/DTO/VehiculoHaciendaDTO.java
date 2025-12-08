/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author adell
 */
public class VehiculoHaciendaDTO {

    private String id;
    private String modelo;
    private String placas;
    private String marca;
    private String color;
    private String numeroSerie;
    private int capacidad;

    public VehiculoHaciendaDTO(String modelo, String placas, String marca, String color, int capacidad) {
        this.modelo = modelo;
        this.placas = placas;
        this.marca = marca;
        this.color = color;
        this.capacidad = capacidad;
    }

    public VehiculoHaciendaDTO(String modelo, String placas, String marca, String color, String numeroSerie, int capacidad) {
        this.modelo = modelo;
        this.placas = placas;
        this.marca = marca;
        this.color = color;
        this.numeroSerie = numeroSerie;
        this.capacidad = capacidad;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public VehiculoHaciendaDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
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
    
    
}
