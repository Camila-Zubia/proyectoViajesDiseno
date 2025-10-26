/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class ConductorDTO {
    
    private String nombre;
    private int calificación;

    public ConductorDTO() {
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

    @Override
    public String toString() {
        return "Conductor:" + 
                "\nnombre = " + nombre + 
                "\ncalificaci\u00f3n = " + calificación;
    }
    
}
