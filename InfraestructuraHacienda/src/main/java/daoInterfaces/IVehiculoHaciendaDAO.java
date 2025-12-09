/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daoInterfaces;

import java.util.Optional;
import model.VehiculoHacienda;

/**
 *
 * @author adell
 */
public interface IVehiculoHaciendaDAO  {
    public Optional<VehiculoHacienda> findByNumeroSerie(String numeroSerie);
}
