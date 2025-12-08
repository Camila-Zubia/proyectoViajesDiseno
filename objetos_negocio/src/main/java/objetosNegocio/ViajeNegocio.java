/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import adaptadores.adaptadorViaje;
import dto.ParadaDTO;
import dto.ReservacionDTO;
import dto.ViajeDTO;
import interfaces.IViajeNegocio;
import java.time.LocalDateTime;
import dto.PasajeroDTO;
import dto.ViajeDTO;
import interfaces.IViajeNegocio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.base_datos_viajes.dao.impl.ReservacionDAO;
import org.base_datos_viajes.dao.impl.ViajeDAO;
import org.base_datos_viajes.dao.interfaces.IConductorDAO;
import org.base_datos_viajes.dao.interfaces.IReservacionDAO;
import exceptiones.DatabaseException;
import org.base_datos_viajes.model.Conductor;
import org.base_datos_viajes.model.Reservacion;
import org.base_datos_viajes.model.Vehiculo;
import org.base_datos_viajes.model.Viaje;
import org.bson.types.ObjectId;
import utilidades.SesionUsuario;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class ViajeNegocio implements IViajeNegocio{
    private final ViajeDAO viajeDAO;  
    private final IConductorDAO conductorDAO;
    private final IReservacionDAO reservacionDAO;  
    
    public ViajeNegocio(ViajeDAO viajeDAO, IConductorDAO conductorDAO) {
        this.viajeDAO = viajeDAO;
        this.conductorDAO = conductorDAO;
        this.reservacionDAO = new ReservacionDAO();
    }
    
    @Override
    public void registrarViaje(ViajeDTO viaje){
        try {
            ObjectId idConductor = new ObjectId(SesionUsuario.obtenerConductor().getId());
            
            // Buscar Conductor en BD para obtener el id del vehiculo 
            Optional<Conductor> optionalConductor = conductorDAO.findById(idConductor);
            if (!optionalConductor.isPresent()) {
                throw new IllegalStateException("Conductor activo no encontrado.");
            }

            // Filtra y obtiene el ObjectId del vehiculo
            ObjectId idVehiculo = optionalConductor.get().getVehiculos().stream()
             
                    .filter(v -> v.getPlacas().equals(viaje.getVehiculo().getPlacas()))
                    .findFirst()
                    .map(Vehiculo::getId) // usa el ID del Vehiculo Eque fue persistido
                    .orElseThrow(() -> new IllegalStateException("Vehiculo seleccionado no tiene ID de persistencia."));
            
            

            // Validar no existencia
            if (!validarNoExisteBD(viaje, idConductor)) {
                throw new IllegalStateException("Ya existe un viaje con los mismos datos (ruta y hora).");
            }

            // Mapear DTO a entidad
            Viaje viajeEntidad = adaptadorViaje.toEntity(viaje, idConductor, idVehiculo);
            
            // Guardar la entidad en la BD 
            viajeDAO.save(viajeEntidad); 
            
        } catch (DatabaseException | IllegalStateException e) {
            throw new IllegalStateException("Error al registrar viaje: " + e.getMessage());
        }
    }
    
    @Override
    public List<ParadaDTO> obtenerParadas(ViajeDTO viaje){
        return viaje.getParadas();
    }
    
    @Override
    public void registrarParada(ViajeDTO viaje, ParadaDTO parada){
        viaje.getParadas().add(parada);
    }
    
    private boolean validarNoExiste(List<ViajeDTO> viajes, ViajeDTO viaje){
        for (ViajeDTO v : viajes) {
            if (v == viaje) {
                return false;
            }
        }
        return true;
    }
    
    private boolean validarNoExisteBD(ViajeDTO viaje, ObjectId idConductor) throws DatabaseException {

        List<Viaje> viajesExistentes = conductorDAO.obtenerViajes(idConductor.toHexString());
        //comparamos la fecha y hora del viaje
        return viajesExistentes.stream().noneMatch(v -> {
        return v.getFecha().equals(viaje.getFecha())
            && v.getHora().equals(viaje.getHora());
    });
    }
    
    // metodos para el caso de Solicitar Reservación
    @Override
    public List<ViajeDTO> obtenerTodosLosViajesDisponibles() {
        final LocalDateTime ahora = LocalDateTime.now();

        List<Viaje> todosLosViajes = viajeDAO.findAll();

        List<ViajeDTO> viajesDisponibles = todosLosViajes.stream()
                .filter(viaje -> {
                    LocalDateTime fechaHoraViaje = LocalDateTime.of(
                            viaje.getFecha(),
                            viaje.getHora());
                    return fechaHoraViaje.isAfter(ahora);
                })
                .map(v -> adaptadorViaje.toDTO(v))
                .collect(Collectors.toList());

        return viajesDisponibles;
    }

    @Override
    public List<ReservacionDTO> obtenerReservacionesPorViaje(String viajeId) {
        try {
            ObjectId idViaje = new ObjectId(viajeId);
            List<Reservacion> reservacionesEntidad = reservacionDAO.encuentraPorIdViaje(idViaje);

            List<ReservacionDTO> solicitudesPendientes = reservacionesEntidad.stream()
                    .filter(r -> r.getEstatus() == Reservacion.Estatus.ESPERA)
                    .map(adaptadores.adaptadorReservacion::toDTO)
                    .collect(Collectors.toList());

            return solicitudesPendientes;

        } catch (Exception e) {
            throw new IllegalStateException("Error al obtener reservaciones del viaje: " + e.getMessage());
        }
    }

    @Override
    public boolean eliminarViaje(String idViaje) {
        try {
            // Obtener el viaje de la base de datos
            Optional<Viaje> optionalViaje = viajeDAO.findById(new ObjectId(idViaje));

            if (!optionalViaje.isPresent()) {
                return false;
            }

            Viaje viaje = optionalViaje.get();

            // Marcar como inactivo en lugar de eliminar
            viaje.setEstaActivo(false);
            viajeDAO.update(viaje);

            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar viaje: " + e.getMessage(), e);
        }
    }

    @Override
    public ViajeDTO obtenerDetalleViaje(String idViaje) {
        try {
            Optional<Viaje> optionalViaje = viajeDAO.findById(new ObjectId(idViaje));

            if (!optionalViaje.isPresent()) {
                return null;
            }

            return adaptadorViaje.toDTO(optionalViaje.get());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener detalles del viaje: " + e.getMessage(), e);
        }
    }

    @Override
    public List<PasajeroDTO> obtenerPasajeros(String idViaje) {
        try {
            Optional<Viaje> optionalViaje = viajeDAO.findById(new ObjectId(idViaje));

            if (!optionalViaje.isPresent() || optionalViaje.get().getParadas() == null) {
                return new ArrayList<>();
            }

            // Por ahora retornar lista vacía
            // En el futuro, cuando exista relación Viaje->Reservaciones->Pasajeros
            return new ArrayList<>();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener pasajeros: " + e.getMessage(), e);
        }
    }

}
