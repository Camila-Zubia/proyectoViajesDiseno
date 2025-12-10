/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetoNegocio;

import DTO.PropietarioHaciendaDTO;
import DTO.VehiculoHaciendaDTO;

import IObjetoNegocio.IValidacionPropietarioVehiculoServicio;
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
public class ControlValidacionPropietarioVehiculoServicio  {

    private final IPropietarioHaciendaDAO propietarioDAO;
    private final IVehiculoHaciendaDAO vehiculoDAO;

    public ControlValidacionPropietarioVehiculoServicio() {
        this.propietarioDAO = new PropietarioHaciendaDAO();
        this.vehiculoDAO = new VehiculoHaciendaDAO();
    }

    public boolean verificarCoincidencia(PropietarioHaciendaDTO propietarioDTO, VehiculoHaciendaDTO vehiculoDTO) {
        System.err.println("=== INICIO DIAGNOSTICO HACIENDA ===");

        if (propietarioDTO == null || vehiculoDTO == null || propietarioDTO.getCurp() == null) {
            System.err.println("FALLO: Los DTOs de entrada son nulos.");
            return false;
        }

        System.err.println("Buscando CURP: [" + propietarioDTO.getCurp() + "]");
        Optional<PropietarioHacienda> optionalProp = propietarioDAO.findByCurp(propietarioDTO.getCurp());

        if (optionalProp.isEmpty()) {
            System.err.println("FALLO: No existe esa CURP en la Base de Datos.");
            return false;
        }

        if (optionalProp.isEmpty()) {
            System.out.println(" Usuario no encontrado. INSERTANDO DATOS DE PRUEBA AUTOMÁTICAMENTE...");

            PropietarioHacienda nuevo = new PropietarioHacienda();
            nuevo.setCurp(propietarioDTO.getCurp()); // "CURPJP001"
            nuevo.setNombre("Juan Perez");           // Asegúrate que tu DTO envíe este nombre o cámbialo
            nuevo.setRfc("RFCJP001");
            nuevo.setNss("NSSJP001");

            VehiculoHacienda v = new VehiculoHacienda();
            v.setNumeroSerie("SERIEJP001");
            v.setPlacas("AAA-111");
            v.setMarca("Nissan");
            v.setModelo("Sentra");
            v.setColor("Negro");
            v.setCapacidad(5);

            nuevo.setVehiculos(List.of(v));

            try {
                propietarioDAO.save(nuevo);
                System.out.println(" Datos insertados. Re-intentando búsqueda...");
                // Volvemos a buscar
                optionalProp = propietarioDAO.findByCurp(propietarioDTO.getCurp());
            } catch (DatabaseException e) {
                System.out.println(" Error al intentar auto-insertar: " + e.getMessage());
            }
        }

        PropietarioHacienda db = optionalProp.get();

        // 3. IMPRESIÓN DE DATOS PERSONALES (Comparación visual)
        String dbNombre = db.getNombre();
        String dtoNombre = propietarioDTO.getNombre();
        String dbRfc = db.getRfc();
        String dtoRfc = propietarioDTO.getRfc();
        String dbNss = db.getNss();
        String dtoNss = propietarioDTO.getNss();

        System.err.println("--- DATOS PERSONALES ---");
        System.err.println("NOMBRE: BD=[" + dbNombre + "] vs DTO=[" + dtoNombre + "]");
        System.err.println("RFC:    BD=[" + dbRfc + "] vs DTO=[" + dtoRfc + "]");
        System.err.println("NSS:    BD=[" + dbNss + "] vs DTO=[" + dtoNss + "]");

        // Validación flexible de nulos (Null Safe)
        boolean nombreOk = safeEquals(dbNombre, dtoNombre);
        boolean rfcOk = safeEquals(dbRfc, dtoRfc);
        boolean nssOk = safeEquals(dbNss, dtoNss);

        if (!nombreOk || !rfcOk || !nssOk) {
            System.err.println("FALLO: Los datos personales no coinciden.");
            return false;
        }

        // 4. IMPRESIÓN DE VEHÍCULOS
        System.err.println("--- DATOS VEHICULOS ---");
        List<VehiculoHacienda> listaVehiculos = db.getVehiculos();

        if (listaVehiculos == null) {
            System.err.println("FALLO CRITICO: La lista de vehículos es NULL.");
            return false;
        }

        System.err.println("Cantidad de vehiculos en BD para este usuario: " + listaVehiculos.size());

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

        // 3. Si existe por serie, validar que los otros campos coincidan (Marca y Placas)
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

        // 3. DEBUG 
        System.out.println("--- RESULTADO DE VALIDACION ---");
        System.out.println("Placas DTO vs BD: [" + dtoPlacas + "] vs [" + getPlacasDB + "] -> " + placasCoinciden);
        System.out.println("Marca DTO vs BD:  [" + dtoMarca + "] vs [" + getMarcaDB + "] -> " + marcaCoincide);
        System.out.println("Color DTO vs BD:  [" + dtoColor + "] vs [" + getColorDB + "] -> " + colorCoincide);
        System.out.println("Modelo DTO vs BD: [" + dtoModelo + "] vs [" + getModeloDB + "] -> " + modeloCoincide);
        System.out.println("Capacidad: " + dtoCapacida + " vs " + vehiculoEncontrado.getCapacidad() + " -> " + capacidadCoincide);
        System.out.println("---------------------------------");
        return placasCoinciden && marcaCoincide && colorCoincide && modeloCoincide && capacidadCoincide;
    }
}
