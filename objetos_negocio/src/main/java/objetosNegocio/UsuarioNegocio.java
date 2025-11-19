/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.ConductorDTO;
import dto.ParadaDTO;
import dto.UsuarioDTO;
import dto.VehiculoDTO;
import dto.ViajeDTO;
import interfaces.IUsuarioNegocio;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import utilidades.SesionUsuario;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class UsuarioNegocio implements IUsuarioNegocio{

    public UsuarioNegocio() {
    }

    @Override
    public void cerrarSesion() {
        SesionUsuario.cerrarSesion();
    }

    @Override
    public boolean validarUsuario(UsuarioDTO usuario) {
        UsuarioDTO usuarioMock = new UsuarioDTO("cperez", "1234");
        ConductorDTO conductor =  new ConductorDTO("Carlos Pérez");
        List<VehiculoDTO> vehiculos = new ArrayList<>();
        vehiculos.add(new VehiculoDTO("Civic 2020", "ABC-123", "Honda", "Blanco", 4));
        vehiculos.add(new VehiculoDTO("Corolla 2021", "XYZ-789", "Toyota", "Gris", 4));
        vehiculos.add(new VehiculoDTO("Jetta 2019", "DEF-456", "Volkswagen", "Negro", 4));
        conductor.setVehiculos(vehiculos);
        List<ViajeDTO> viajes = new ArrayList<>();
        ViajeDTO viaje1 = new ViajeDTO("Obregon", "Navojoa", LocalDate.now(), LocalTime.of(10, 30), 250.0);
        ViajeDTO viaje2 = new ViajeDTO("Obregon", "Esperanza", LocalDate.now(), LocalTime.of(14, 0), 70.0);
        viaje1.getParadas().add(new ParadaDTO("Tutuli", 50.0));
        viaje1.getParadas().add(new ParadaDTO("ITSON", 30.0));
        viaje2.getParadas().add(new ParadaDTO("Central Camiones", 40.0));
        viajes.add(viaje1);
        viajes.add(viaje2);
        conductor.setViajes(viajes);
        usuario = usuarioMock;
        usuario.setConductor(conductor);
        boolean usuarioValido = usuarioMock.getUsuario().equals(usuario.getUsuario());
        boolean contraseñaValida = usuarioMock.getContraseñaHaseada().equals(usuario.getContraseñaHaseada());
        if (contraseñaValida && usuarioValido) {
            SesionUsuario.iniciarSesion(usuarioMock);
            return true;
        }
        return false;
    }

    @Override
    public UsuarioDTO obtenerUsuarioActivo() {
        return SesionUsuario.obtenerUsuario();
    }
}
