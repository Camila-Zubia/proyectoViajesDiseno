
package interfaces_editarViaje;

import dto.ParadaDTO;
import dto.ViajeDTO;
import java.util.List;

/**
 * @author ferch
 * Interfaz que define las operaciones de negocio para la edicion de un viaje existente.
 * La logica de validacion critica (reservaciones aceptadas) reside aqui.
 */
public interface IEditarViajeNegocio {
    /**
     * Valida y actualiza los datos generales de un viaje (destino, precio, fecha/hora) en la BD.
     * @param viajeModificado El DTO con los datos actualizados.
     * @return El ViajeDTO persistido.
     */
    public ViajeDTO actualizarDatosGenerales(ViajeDTO viajeModificado);

    /**
     * Valida y actualiza la lista completa de paradas de un viaje en la BD.
     * @param viajeId ID del viaje a modificar.
     * @param paradas La nueva lista de ParadaDTOs (debe incluir el origen).
     * @return El ViajeDTO actualizado.
     */
    public ViajeDTO actualizarParadas(String viajeId, List<ParadaDTO> paradas);
    
    /**
     * Verifica que el viaje no tenga reservaciones aceptadas antes de permitir la edicion
     * de ruta o fecha/hora.
     * @param viajeId ID del viaje.
     * @return true si es seguro editar (no hay reservaciones aceptadas), false si no.
     */
    public boolean validarEdicionSegura(String viajeId);

}
