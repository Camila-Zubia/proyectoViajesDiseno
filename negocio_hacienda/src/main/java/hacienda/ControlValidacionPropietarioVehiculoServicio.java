/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hacienda;

import DTO.PropietarioHaciendaDTO;
import DTO.VehiculoHaciendaDTO;

import java.util.List;
import java.util.Optional;
import daoImplementacion.PropietarioHaciendaDAO;
import daoImplementacion.VehiculoHaciendaDAO;

import model.PropietarioHacienda;
import model.VehiculoHacienda;
import daoInterfaces.IPropietarioHaciendaDAO;
import daoInterfaces.IVehiculoHaciendaDAO;
import exceptiones.DatabaseException;

/**
 *
 * @author adell
 */
public class ControlValidacionPropietarioVehiculoServicio {

    private final IPropietarioHaciendaDAO propietarioDAO;
    private final IVehiculoHaciendaDAO vehiculoDAO;

    public ControlValidacionPropietarioVehiculoServicio() {
        this.propietarioDAO = new PropietarioHaciendaDAO();
        this.vehiculoDAO = new VehiculoHaciendaDAO();
    }

    public boolean verificarCoincidencia(PropietarioHaciendaDTO propietarioDTO, VehiculoHaciendaDTO vehiculoDTO) {

        if (propietarioDTO == null || vehiculoDTO == null || propietarioDTO.getCurp() == null) {
            System.err.println("FALLO: Los DTOs de entrada son nulos.");
            return false;
        }

        Optional<PropietarioHacienda> optionalProp = propietarioDAO.findByCurp(propietarioDTO.getCurp());

        if (optionalProp.isEmpty()) {
            System.err.println("FALLO: No existe esa CURP en la Base de Datos.");
            return false;
        }

        PropietarioHacienda db = optionalProp.get();

        String dbNombre = db.getNombre();
        String dtoNombre = propietarioDTO.getNombre();
        String dbRfc = db.getRfc();
        String dtoRfc = propietarioDTO.getRfc();
        String dbNss = db.getNss();
        String dtoNss = propietarioDTO.getNss();

        boolean nombreOk = safeEquals(dbNombre, dtoNombre);
        boolean rfcOk = safeEquals(dbRfc, dtoRfc);
        boolean nssOk = safeEquals(dbNss, dtoNss);

        if (!nombreOk || !rfcOk || !nssOk) {
            System.err.println("FALLO: Los datos personales no coinciden.");
            return false;
        }

      
        List<VehiculoHacienda> listaVehiculos = db.getVehiculos();

        if (listaVehiculos == null) {
            System.err.println("FALLO CRITICO: La lista de vehículos es NULL.");
            return false;
        }

        if (listaVehiculos.isEmpty()) {
            System.err.println("FALLO: El usuario existe, pero NO TIENE VEHÍCULOS registrados en BD.");
            return false;
        }

        // Buscamos el vehículo
        String dtoSerie = vehiculoDTO.getNumeroSerie();
        System.err.println("Buscando Serie DTO: [" + dtoSerie + "]");

        boolean vehiculoEncontrado = listaVehiculos.stream().anyMatch(v -> {
            String dbSerie = v.getNumeroSerie();
            System.err.println("  -> Comparando con BD Serie: [" + dbSerie + "]");
            return safeEquals(dbSerie, dtoSerie);
        });

        if (vehiculoEncontrado) {
            System.err.println("=== EXITO: COINCIDENCIA ENCONTRADA ===");
            return true;
        } else {
            System.err.println("FALLO: Ningún vehículo de la lista coincide con la serie.");
            return false;
        }
    }

    private boolean safeEquals(String db, String dto) {
        String s1 = (db == null) ? "" : db.trim();
        String s2 = (dto == null) ? "" : dto.trim();
        return s1.equalsIgnoreCase(s2);
    }

    public boolean existeVehiculoEnBD(VehiculoHaciendaDTO dto) {

        String dtoSerie = dto.getNumeroSerie() != null ? dto.getNumeroSerie().trim() : "";
        String dtoPlacas = dto.getPlacas() != null ? dto.getPlacas().trim() : "";
        String dtoMarca = dto.getMarca() != null ? dto.getMarca().trim() : "";

        String dtoColor = dto.getColor() != null ? dto.getColor().trim() : "";
        String dtoModelo = dto.getModelo() != null ? dto.getModelo().trim() : "";

        int dtoCapacida = dto.getCapacidad();

        Optional<VehiculoHacienda> vehiculoOptional = vehiculoDAO.findByNumeroSerie(dtoSerie);

        if (vehiculoOptional.isEmpty()) {

            return false;
        }

      
        VehiculoHacienda vehiculoEncontrado = vehiculoOptional.get();

        String getPlacasDB = vehiculoEncontrado.getPlacas() != null ? vehiculoEncontrado.getPlacas().trim() : "";
        boolean placasCoinciden = getPlacasDB.equalsIgnoreCase(dtoPlacas);

        String getMarcaDB = vehiculoEncontrado.getMarca() != null ? vehiculoEncontrado.getMarca().trim() : "";
        boolean marcaCoincide = getMarcaDB.equalsIgnoreCase(dtoMarca);

        String getColorDB = vehiculoEncontrado.getColor() != null ? vehiculoEncontrado.getColor().trim() : "";
        boolean colorCoincide = getColorDB.equalsIgnoreCase(dtoColor);

        String getModeloDB = vehiculoEncontrado.getModelo() != null ? vehiculoEncontrado.getModelo().trim() : "";
        boolean modeloCoincide = getModeloDB.equalsIgnoreCase(dtoModelo);

        boolean capacidadCoincide = dtoCapacida == vehiculoEncontrado.getCapacidad();

        System.out.println(vehiculoEncontrado);

        return placasCoinciden && marcaCoincide && colorCoincide && modeloCoincide && capacidadCoincide;
    }
}
