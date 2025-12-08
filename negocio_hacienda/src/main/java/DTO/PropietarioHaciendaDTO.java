/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.List;

/**
 *
 * @author adell
 */
public class PropietarioHaciendaDTO {
    String id;
    String nombre;
    String curp;
    String rfc;
    String nss;
    List<VehiculoHaciendaDTO> listaVehiculos;

    public PropietarioHaciendaDTO() {
    }

    public PropietarioHaciendaDTO(String nombre, String curp, String rfc, String nss, List<VehiculoHaciendaDTO> listaVehiculos) {
        this.nombre = nombre;
        this.curp = curp;
        this.rfc = rfc;
        this.nss = nss;
        this.listaVehiculos = listaVehiculos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public List<VehiculoHaciendaDTO> getListaVehiculos() {
        return listaVehiculos;
    }

    public void setListaVehiculos(List<VehiculoHaciendaDTO> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }
    
    
}
