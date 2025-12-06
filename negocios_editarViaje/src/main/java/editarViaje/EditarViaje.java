
package editarViaje;

import dto.ParadaDTO;
import dto.ViajeDTO;
import java.util.List;

/**
 * @author ferch
 * Fachada para el caso de uso Editar Viaje.
 */
public class EditarViaje implements IEditarViaje{
   private final ControlEditarViaje control;
     
    public EditarViaje() {
        this.control = new ControlEditarViaje();
    }

    @Override
    public void guardarDatosGenerales(ViajeDTO viajeModificado) {
      control.guardarDatosGenerales(viajeModificado);
    }

    @Override
    public void guardarParadasTemporales(List<ParadaDTO> paradas) {
      control.guardarParadasTemporales(paradas);
    }

    @Override
    public ViajeDTO obtenerViajeTemporal() {
      return control.getViajeTemporal();
    }

    @Override
    public ViajeDTO confirmarEdicion() {
       return control.confirmarEdicion();
    }

    @Override
    public boolean validarEdicionSegura() {
      return control.validarEdicionSegura();
    }
     
    /**
     * Metodo auxiliar para ControlPantallas: Permite inicializar el viaje temporal
     * una vez que es seleccionado desde el menu principal.
     */
   @Override
    public void setViajeTemporal(ViajeDTO viaje) {
        control.setViajeTemporal(viaje);
    }
}
