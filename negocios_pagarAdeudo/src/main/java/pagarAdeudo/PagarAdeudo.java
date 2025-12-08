package pagarAdeudo;

import dto.AdeudoDTO;
import dto.ViajeDTO;
import java.util.List;

public class PagarAdeudo implements IPagarAdeudo {

    private final ControlPagarAdeudo control;

    public PagarAdeudo() {
        this.control = new ControlPagarAdeudo();
    }

    @Override
    public List<AdeudoDTO> obtenerAdeudosPendientes(String idConductor) {
        return control.obtenerAdeudosPendientes(idConductor);
    }

    @Override
    public boolean pagarAdeudo(String idAdeudo) {
        return control.pagarAdeudo(idAdeudo);
    }

    @Override
    public ViajeDTO obtenerDetallesViaje(String idViaje) {
        return control.obtenerDetallesViaje(idViaje);
    }
}
