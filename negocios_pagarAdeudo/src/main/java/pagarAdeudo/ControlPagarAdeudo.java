package pagarAdeudo;

import dto.AdeudoDTO;
import dto.ViajeDTO;
import factory.FabricaBOs;
import factory.IFabricaBOs;
import interfaces.IViajeNegocio;
import interfaces_cancelarViaje.IAdeudoNegocio;
import java.util.List;
import java.util.stream.Collectors;

public class ControlPagarAdeudo {

    private final IAdeudoNegocio adeudoBO;
    private final IViajeNegocio viajeBO;

    public ControlPagarAdeudo() {
        IFabricaBOs fabrica = new FabricaBOs();
        this.adeudoBO = fabrica.crearAdeudoNegocio();
        this.viajeBO = fabrica.crearViajeNegocio();
    }

    public List<AdeudoDTO> obtenerAdeudosPendientes(String idConductor) {
        List<AdeudoDTO> todosLosAdeudos = adeudoBO.obtenerAdeudos(idConductor);
        return todosLosAdeudos.stream()
            .filter(adeudo -> !adeudo.isPagado())
            .collect(Collectors.toList());
    }

    public boolean pagarAdeudo(String idAdeudo) {
        adeudoBO.marcarComoPagado(idAdeudo);
        return true;
    }

    public ViajeDTO obtenerDetallesViaje(String idViaje) {
        return viajeBO.obtenerDetalleViaje(idViaje);
    }
}
