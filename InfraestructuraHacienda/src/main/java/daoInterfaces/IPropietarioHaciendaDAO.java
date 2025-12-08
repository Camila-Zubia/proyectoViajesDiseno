/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daoInterfaces;


import exceptiones.DatabaseException;
import java.util.Optional;
import model.PropietarioHacienda;

/**
 *
 * @author adell
 */
public interface IPropietarioHaciendaDAO {
    public Optional<PropietarioHacienda> findByCurp(String curp);
    public PropietarioHacienda save(PropietarioHacienda entity) throws DatabaseException;
}
