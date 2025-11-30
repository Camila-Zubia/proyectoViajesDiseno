/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces_cancelarViaje;

import dto.AdeudoDTO;
import java.util.List;

/**
 *
 * @author Camila Zubia 00000244825
 */
public interface IAdeudoNegocio {

    /**
     * Obtiene todos los adeudos de un conductor
     * @param idConductor ID del conductor
     * @return Lista de adeudos
     */
    public List<AdeudoDTO> obtenerAdeudos(String idConductor);

    /**
     * Registra un nuevo adeudo para el conductor en sesión
     * @param adeudo Adeudo a registrar
     */
    public void registrarAdeudo(AdeudoDTO adeudo);

    /**
     * Marca un adeudo como pagado
     * @param idAdeudo ID del adeudo
     */
    public void marcarComoPagado(String idAdeudo);
}
