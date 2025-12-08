/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author adell
 */
public class PropietarioHacienda {
    
    private ObjectId id;
    private String curp;
    private String nombre;
    private String rfc;
    private String nss;
    private List<VehiculoHacienda> vehiculos;

    public PropietarioHacienda(String curp, String nombre, String rfc, String nss, List<VehiculoHacienda> vehiculos) {
        this.curp = curp;
        this.nombre = nombre;
        this.rfc = rfc;
        this.nss = nss;
        this.vehiculos = vehiculos;
    }

    public PropietarioHacienda() {
    }

    public PropietarioHacienda(String curp, String nombre, String rfc, String nss) {
        this.curp = curp;
        this.nombre = nombre;
        this.rfc = rfc;
        this.nss = nss;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
    
    
    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public List<VehiculoHacienda> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<VehiculoHacienda> vehiculos) {
        this.vehiculos = vehiculos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PropietarioHacienda{");
        sb.append("id=").append(id);
        sb.append(", curp=").append(curp);
        sb.append(", nombre=").append(nombre);
        sb.append(", rfc=").append(rfc);
        sb.append(", nss=").append(nss);
        sb.append(", vehiculos=").append(vehiculos);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
