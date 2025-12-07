/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.List;
import org.base_datos_viajes.model.Vehiculo;

/**
 *
 * @author adell
 */
public class PropietarioDTO {

    String id;
    String nombre;
    String curp;
    String rfc;
    String nss;
    List<Vehiculo> listaVehiculos;

    public PropietarioDTO() {
    }

    public PropietarioDTO(String nombre, String curp, String rfc, String nss, List<Vehiculo> listaVehiculos) {
        this.nombre = nombre;
        this.curp = curp;
        this.rfc = rfc;
        this.nss = nss;
        this.listaVehiculos = listaVehiculos;
    }

    public PropietarioDTO(String nombre, String curp, String rfc, String nss) {
        this.nombre = nombre;
        this.curp = curp;
        this.rfc = rfc;
        this.nss = nss;
    }

    public PropietarioDTO(String id, String nombre, String curp, String rfc, String nss, List<Vehiculo> listaVehiculos) {
        this.id = id;
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

    public List<Vehiculo> getListaVehiculos() {
        return listaVehiculos;
    }

    public void setListaVehiculos(List<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }

    @Override
    public String toString() {
        return "PropietarioDTO{" + "nombre=" + nombre + ", curp=" + curp + ", rfc=" + rfc + ", nss=" + nss + '}';
    }
    
    
    
}
