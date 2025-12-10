/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.text.DecimalFormat;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class ParadaDTO {
    
    private String Id;
    private String dirección;
    private double precio;

    public ParadaDTO() {
    }

    public ParadaDTO(String dirección, double precio) {
        this.dirección = dirección;
        this.precio = precio;
    }

    public String getDirección() {
        return dirección;
    }

    public void setDirección(String dirección) {
        this.dirección = dirección;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return "Direcci\u00f3n = " + dirección +
                ", $" + df.format(precio);
    }
    
}
