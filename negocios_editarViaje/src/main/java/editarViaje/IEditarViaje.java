
package editarViaje;

import dto.ParadaDTO;
import dto.ViajeDTO;
import java.util.List;

/**
 *
 * @author ferch
 */
public interface IEditarViaje {
     /**
     * Guarda los datos generales (destino, precio, fecha/hora) en el viaje temporal.
     * Esta operacion NO persiste en la BD.
     * @param viajeModificado DTO con los campos a actualizar.
     */
    public void guardarDatosGenerales(ViajeDTO viajeModificado);

    /**
     * Guarda la lista de paradas en el viaje temporal.
     * Esta operacion NO persiste en la BD.
     * @param paradas La lista de paradas a guardar temporalmente.
     */
    public void guardarParadasTemporales(List<ParadaDTO> paradas);
    
    /**
     * Obtiene el ViajeDTO temporal que se está editando.
     * @return El ViajeDTO actual.
     */
    public ViajeDTO obtenerViajeTemporal();

    /**
     * Confirma la edicion y persiste todos los cambios del viaje temporal en la BD.
     * @return El ViajeDTO actualizado.
     */
    public ViajeDTO confirmarEdicion();
    
    /**
     * Valida si la edicion de ruta o fecha/hora es segura (no hay reservaciones aceptadas).
     * @return true si es seguro editar, false si hay reservaciones.
     */
    public boolean validarEdicionSegura();
    
    public void setViajeTemporal(ViajeDTO viaje);
}

