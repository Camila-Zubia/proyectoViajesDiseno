
package Pruebas;

import registrarViaje.RegistrarViaje;

import dto.ParadaDTO;
import dto.VehiculoDTO;
import dto.ViajeDTO;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import registrarViaje.IRegistrarViaje;

/**
 * Pruebas unitarias para la fachada MOCK: RegistrarViaje.
 * @author ferch
 */
public class RegistrarViajeTest {

    private IRegistrarViaje fachada;

    private final int MOCK_VEHICULOS_COUNT = 3;

    /**
     * Inicializa la fachada Mock antes de cada metodo de prueba.
     */
    @BeforeEach
    void setUp() {
        this.fachada = new RegistrarViaje();
        System.out.println("--- Inicializando nuevo test ---");
    }

    // pruebas del flujo
    //Prueba que obtenerVehiculos devuelva la lista MOCK.
    @Test
    void testObtenerVehiculosExitoso() {
        System.out.println("TEST: obtenerVehiculos");

        List<VehiculoDTO> vehiculos = fachada.obtenerVehiculos();

        // Verifica que el Mock retorne la cantidad fija de elementos.
        assertEquals(MOCK_VEHICULOS_COUNT, vehiculos.size(),
                "El Mock debe retornar la cantidad fija de vehículos.");

        // Verifica la marca del primer vehiculo como prueba de integridad de datos
        assertEquals("Honda", vehiculos.get(0).getMarca(), "La marca debe ser Honda.");
    }
    // Prueba que el Mock retorne el mismo VehiculoDTO que se le pasa como parAmetro.

    @Test
    void testObtenerVehiculo_RetornoParametro() {
        System.out.println("TEST: obtenerVehiculo (Verifica retorno)");

        VehiculoDTO vehiculoEntrada = new VehiculoDTO("Camaro 2022", "XYZ-000", "Chevrolet", "Rojo", 2);

        VehiculoDTO vehiculoSalida = fachada.obtenerVehiculo(vehiculoEntrada);

        // Verifica que el Mock retorne exactamente el mismo objeto que recibiO.
        assertSame(vehiculoEntrada, vehiculoSalida,
                "El Mock debe retornar la misma instancia que se le paso.");
    }

    //Prueba que la validacion Mock siempre retorne false (no existe).
    @Test
    void testValidarNoExiste() {
        System.out.println("TEST: validarNoExiste");

        boolean existe = fachada.validarNoExiste();

        // Verifica que el Mock retorne el valor esperado false
        // El Mock debe retornar false (no existe) para simular el caso de Exito de registro.
        assertFalse(existe, "El Mock debe retornar 'false' para simular que el viaje no existe.");
    }

    // Prueba que el mEtodo crearViaje se ejecute sin lanzar excepciones.
    @Test
    void testCrearViajeExitoso() {
        System.out.println("TEST: crearViaje (Registro Final)");
        ViajeDTO viaje = crearViajeValido();

        // que el metodo se ejecuta sin lanzar excepciones
        Assertions.assertDoesNotThrow(() -> {
            fachada.crearViaje(viaje);
        }, "El Mock debe simular el guardado exitoso y no lanzar excepciones.");
    }

    // Prueba que el Mock agregue una parada individual correctamente
    @Test
    void testCrearParada() {
        System.out.println("TEST: crearParada (Agrega una parada)");

        ParadaDTO parada = new ParadaDTO("Avenida Principal #123", 5.50);

        Assertions.assertDoesNotThrow(() -> {
            fachada.crearParada(parada);
        }, "La creaciOn de la parada en el Mock debe ser exitosa.");
    }

    // Prueba que agregarAListaParadas se ejecute sin errores.
    @Test
    void testAgregarAListaParadasSinError() {
        System.out.println("TEST: agregarAListaParadas");
        List<ParadaDTO> paradas = new ArrayList<>();
        paradas.add(new ParadaDTO("Gasolinera 1", 12.0));
        paradas.add(new ParadaDTO("Oxxo 2", 7.50));
        paradas.add(new ParadaDTO("Farmacia 3", 8.0));

        // Se asegura que el Mock pueda procesar la lista sin errores.
        Assertions.assertDoesNotThrow(() -> {
            fachada.agregarAListaParadas(paradas);
        }, "la lista de paradas debe ser exitosa.");
    }

    // Prueba con una lista nula para evitar NullPointerException.
    @Test
    void testAgregarAListaParadas_ListaNula() {
        System.out.println("TEST: agregarAListaParadas (Lista Nula)");

        // Aserta que al pasar null, el metodo se ejecuta sin error.
        Assertions.assertDoesNotThrow(() -> {
            fachada.agregarAListaParadas(null);
        }, "Pasar una lista nula no debe causar excepciones.");

    }

    // Metodo auxiliar para crear un DTO de Viaje de prueba. 
    private ViajeDTO crearViajeValido() {
        List<ParadaDTO> paradas = new ArrayList<>();
        paradas.add(new ParadaDTO("Parada plaza tutuli", 10.0));

        return new ViajeDTO(
                "Itson centro",
                "Itson nainari",
                new Date(),
                LocalTime.of(10, 0),
                100.0,
                paradas
        );
    }
}
