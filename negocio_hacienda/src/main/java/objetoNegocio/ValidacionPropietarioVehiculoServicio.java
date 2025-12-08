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

import model.PropietarioHacienda;
import model.VehiculoHacienda;
import daoInterfaces.IPropietarioHaciendaDAO;

/**
 *
 * @author adell
 */
public class ValidacionPropietarioVehiculoServicio implements IValidacionPropietarioVehiculoServicio {

    private final IPropietarioHaciendaDAO propietarioDAO;

    public ValidacionPropietarioVehiculoServicio() {
        this.propietarioDAO = new PropietarioHaciendaDAO();
    }

    @Override
    public boolean verificarCoincidencia(PropietarioHaciendaDTO propietarioDTO, VehiculoHaciendaDTO vehiculoDTO) {
        System.err.println("=== INICIO DIAGNOSTICO HACIENDA ===");

        // 1. Validar entradas básicas
        if (propietarioDTO == null || vehiculoDTO == null || propietarioDTO.getCurp() == null) {
            System.err.println("FALLO: Los DTOs de entrada son nulos.");
            return false;
        }

        // 2. Buscar en BD
        System.err.println("Buscando CURP: [" + propietarioDTO.getCurp() + "]");
        Optional<PropietarioHacienda> optionalProp = propietarioDAO.findByCurp(propietarioDTO.getCurp());

        if (optionalProp.isEmpty()) {
            System.err.println("FALLO: No existe esa CURP en la Base de Datos.");
            return false;
        }

        // BLOQUE DE AUTO-INSERCIÓN PARA PRUEBAS
        if (optionalProp.isEmpty()) {
            System.out.println("⚠️ DEBUG: Usuario no encontrado. INSERTANDO DATOS DE PRUEBA AUTOMÁTICAMENTE...");

            PropietarioHacienda nuevo = new PropietarioHacienda();
            nuevo.setCurp(propietarioDTO.getCurp()); // "CURPJP001"
            nuevo.setNombre("Juan Perez");           // Asegúrate que tu DTO envíe este nombre o cámbialo
            nuevo.setRfc("RFCJP001");
            nuevo.setNss("NSSJP001");

            // Crear vehículo que coincida con lo que esperas
            VehiculoHacienda v = new VehiculoHacienda();
            v.setNumeroSerie("SERIEJP001"); // Mismo que tu DTO
            v.setPlacas("AAA-111");
            v.setMarca("Nissan");
            v.setModelo("Sentra");
            v.setColor("Negro");
            v.setCapacidad(5);

            nuevo.setVehiculos(List.of(v)); // Importar List

            try {
                propietarioDAO.save(nuevo);
                System.out.println(" Datos insertados. Re-intentando búsqueda...");
                // Volvemos a buscar
                optionalProp = propietarioDAO.findByCurp(propietarioDTO.getCurp());
            } catch (Exception e) {
                System.out.println(" Error al intentar auto-insertar: " + e.getMessage());
            }
        }
// FIN BLOQUE

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

        // IMPORTANTE: Imprimimos el tamaño para ver si Hibernate los cargó
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

// Método auxiliar OBLIGATORIO para evitar NullPointer
    private boolean safeEquals(String db, String dto) {
        String s1 = (db == null) ? "" : db.trim();
        String s2 = (dto == null) ? "" : dto.trim();
        return s1.equalsIgnoreCase(s2);
    }

    // En datosPropietarioCoinciden
    private boolean datosPropietarioCoinciden(PropietarioHacienda entity, PropietarioHaciendaDTO dto) {
        return entity.getCurp().trim().equalsIgnoreCase(dto.getCurp().trim());
    }

    private boolean vehiculoCoincideEnLista(List<VehiculoHacienda> vehiculosDB, VehiculoHaciendaDTO dto) {

        // 1. Limpiar los DTOs de entrada una sola vez
        String dtoSerie = dto.getNumeroSerie() != null ? dto.getNumeroSerie().trim() : "";
        String dtoPlacas = dto.getPlacas() != null ? dto.getPlacas().trim() : "";
        String dtoMarca = dto.getMarca() != null ? dto.getMarca().trim() : "";

        System.out.println("--- INICIANDO DEBUG DE VALIDACIÓN DE VEHÍCULO ---");

        return vehiculosDB.stream()
                .anyMatch(entidad -> {
                    // 2. Realizar las comparaciones y guardar los resultados
                    boolean serieMatch = entidad.getNumeroSerie().trim().equalsIgnoreCase(dtoSerie);
                    boolean placasMatch = entidad.getPlacas().trim().equalsIgnoreCase(dtoPlacas);
                    boolean marcaMatch = entidad.getMarca().trim().equalsIgnoreCase(dtoMarca);

                    // 3. Imprimir el resultado de CADA comparación
                    System.out.println("DEBUG SERIE: DTO=[" + dtoSerie + "] vs DB=[" + entidad.getNumeroSerie().trim() + "] -> MATCH: " + serieMatch);
                    System.out.println("DEBUG PLACAS: DTO=[" + dtoPlacas + "] vs DB=[" + entidad.getPlacas().trim() + "] -> MATCH: " + placasMatch);
                    System.out.println("DEBUG MARCA: DTO=[" + dtoMarca + "] vs DB=[" + entidad.getMarca().trim() + "] -> MATCH: " + marcaMatch);

                    return serieMatch && placasMatch && marcaMatch;
                });
    }
}
