package pagarAdeudo;

import dto.AdeudoDTO;
import dto.ViajeDTO;
import java.util.List;

public interface IPagarAdeudo {

    public List<AdeudoDTO> obtenerAdeudosPendientes(String idConductor);

    public boolean pagarAdeudo(String idAdeudo);

    public ViajeDTO obtenerDetallesViaje(String idViaje);
}
