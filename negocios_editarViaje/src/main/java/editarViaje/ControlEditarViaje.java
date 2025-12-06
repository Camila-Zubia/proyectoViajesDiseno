
package editarViaje;

import dto.ParadaDTO;
import dto.ViajeDTO;
import factory.FabricaBOs;
import factory.IFabricaBOs;
import interfaces_editarViaje.IEditarViajeNegocio;
import java.util.List;

/**
 * @author ferch
 * Clase que maneja el flujo de edicion de datos temporales antes de la persistencia.
 */
public class ControlEditarViaje {
    private ViajeDTO viajeTemporal;
    private final IEditarViajeNegocio editarViajeBO;

    public ControlEditarViaje() {
        IFabricaBOs fabrica = new FabricaBOs();
        this.editarViajeBO = fabrica.crearEditarViajeNegocio();
    }
    
    public void setViajeTemporal(ViajeDTO viaje) {
        this.viajeTemporal = viaje;
    }

    public ViajeDTO getViajeTemporal() {
        return viajeTemporal;
    }

    public void guardarDatosGenerales(ViajeDTO viajeModificado) {
        if (viajeTemporal == null || !viajeTemporal.getId().equals(viajeModificado.getId())) {
             throw new IllegalStateException("No se puede guardar: el viaje temporal no coincide o es nulo.");
        }
        
        // Actualizamos los datos generales del DTO 
        viajeTemporal.setDestino(viajeModificado.getDestino());
        viajeTemporal.setFecha(viajeModificado.getFecha());
        viajeTemporal.setHora(viajeModificado.getHora());
        viajeTemporal.setPrecioTotal(viajeModificado.getPrecioTotal());
    }

    public void guardarParadasTemporales(List<ParadaDTO> paradas) {
         if (viajeTemporal == null) {
             throw new IllegalStateException("No se puede guardar: el viaje temporal es nulo.");
        }
        
        // Actualizamos la lista de paradas en el DTO
        viajeTemporal.setParadas(paradas);
    }
    
    public ViajeDTO confirmarEdicion() {
        if (viajeTemporal == null || viajeTemporal.getId() == null) {
            throw new IllegalStateException("El viaje temporal no esta listo para persistir.");
        }
        
        // Persistir paradas para asegurar que la lista de paradas este actualizada en la BD
        ViajeDTO viajeConParadasActualizadas = editarViajeBO.actualizarParadas(
            viajeTemporal.getId(), 
            viajeTemporal.getParadas()
        );
        
        // Persistir Datos Generales (la capa BO se encargara de las validaciones de seguridad)
        ViajeDTO viajeActualizado = editarViajeBO.actualizarDatosGenerales(viajeTemporal);
        
        // Reemplazamos el temporal con el resultado persistido
        this.viajeTemporal = viajeActualizado;
        
        return viajeActualizado;
    }
    
    public boolean validarEdicionSegura() {
        if (viajeTemporal == null || viajeTemporal.getId() == null) return false;
        return editarViajeBO.validarEdicionSegura(viajeTemporal.getId());
    }

}
