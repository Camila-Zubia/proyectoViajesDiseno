/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDateTime;

public class CalificacionDTO {

    private String id;
    private String idViaje;
    private String idConductor;
    private String idPasajero;
    private int estrellas;  // 
    private String comentario;
    private String imagen;  
    private LocalDateTime fecha;

    public CalificacionDTO() {
        this.fecha = LocalDateTime.now();
    }

    public CalificacionDTO(String idViaje, String idConductor, String idPasajero, int estrellas, String comentario, String imagen) {

        this.idViaje = idViaje;
        this.idConductor = idConductor;
        this.idPasajero = idPasajero;
        this.estrellas = estrellas;
        this.comentario = comentario;
        this.imagen = imagen;
        this.fecha = LocalDateTime.now();
    }

    // getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(String idViaje) {
        this.idViaje = idViaje;
    }

    public String getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(String idConductor) {
        this.idConductor = idConductor;
    }

    public String getIdPasajero() {
        return idPasajero;
    }

    public void setIdPasajero(String idPasajero) {
        this.idPasajero = idPasajero;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
