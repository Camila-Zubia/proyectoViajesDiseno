/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class VehiculoDTO {
    
    private String id;
    private String numeroSerie;
    private String modelo;
    private String placas;
    private String marca;
    private String color;
    private int capacidad;

    public VehiculoDTO() {
    }

    public VehiculoDTO(String numeroSerie,String modelo, String placas, String marca, String color, int capacidad) {
        this.numeroSerie = numeroSerie;
        this.modelo = modelo;
        this.placas = placas;
        this.marca = marca;
        this.color = color;
        this.capacidad = capacidad;
    }

    public String getModelo() {
        return modelo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    @Override
    public String toString() {
        return String.format(marca + ", " + modelo + ", " + color
                + "     placas = " + placas
                + "     capacidad = " + capacidad);
    }
    
}
