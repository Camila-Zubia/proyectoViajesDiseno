package inicializador;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import exceptiones.DatabaseException;
import exceptiones.ValidationException;

import daoImplementacion.PropietarioHaciendaDAO;
import daoImplementacion.VehiculoHaciendaDAO;
import java.util.ArrayList;
import java.util.List;
import model.PropietarioHacienda;
import model.VehiculoHacienda;

/**
 *
 * @author ferch calse de para la inicializacion de datos de prueba en la base
 * de datos de monogo
 */
public class InicializadorDatosPrueba {

    private static final Logger LOGGER = Logger.getLogger(InicializadorDatosPrueba.class.getName());

    private final PropietarioHaciendaDAO propietarioDAO;
    private final VehiculoHaciendaDAO vehiculoDAO;

    public InicializadorDatosPrueba() {

        this.propietarioDAO = new PropietarioHaciendaDAO();
        this.vehiculoDAO = new VehiculoHaciendaDAO();
    }

    /**
     * Agrega datos de prueba a la base de datos de MongoDB.
     */
    private void agregarDatosPrueba() {
        LOGGER.info("Iniciando insercion de datos de prueba...");
        try {

            

           // -----------------------------------------------------------------
            // --- 1. JUAN PEREZ (CON UN VEHÍCULO) ---
            // -----------------------------------------------------------------
            VehiculoHacienda v_juan = new VehiculoHacienda();
            v_juan.setNumeroSerie("JPZ001SERIEA1234");
            v_juan.setPlacas("A12-345");
            v_juan.setMarca("Toyota");
            v_juan.setModelo("Corolla 2020");
            v_juan.setColor("Rojo");
            v_juan.setCapacidad(5);
            vehiculoDAO.save(v_juan);
            
            PropietarioHacienda juan = new PropietarioHacienda();
            juan.setNombre("Juan Perez");
            juan.setCurp("PEREZJ900101HDFRFM01");
            juan.setRfc("PERJ900101FR0");
            juan.setNss("9001010010");
            juan.setVehiculos(Arrays.asList(v_juan));
            propietarioDAO.save(juan);


            // -----------------------------------------------------------------
            // --- 2. NORMA BELTRÁN (CON DOS VEHÍCULOS) ---
            // -----------------------------------------------------------------
            // Vehículo 1 de Norma
            VehiculoHacienda v_norma1 = new VehiculoHacienda();
            v_norma1.setNumeroSerie("NBR001SERIEB5678");
            v_norma1.setPlacas("B56-789");
            v_norma1.setMarca("Honda");
            v_norma1.setModelo("Civic 2019");
            v_norma1.setColor("Azul");
            v_norma1.setCapacidad(4);
            vehiculoDAO.save(v_norma1);
            // Vehículo 2 de Norma
            VehiculoHacienda v_norma2 = new VehiculoHacienda();
            v_norma2.setNumeroSerie("NBR002SERIEC9012");
            v_norma2.setPlacas("C90-123");
            v_norma2.setMarca("Ford");
            v_norma2.setModelo("Explorer 2023");
            v_norma2.setColor("Negro");
            v_norma2.setCapacidad(7);
            vehiculoDAO.save(v_norma2);
            PropietarioHacienda norma = new PropietarioHacienda();
            norma.setNombre("Norma Beltran");
            norma.setCurp("BELTRN920515MSCRRN02");
            norma.setRfc("BELT920515RRN");
            norma.setNss("9205150020");
            
            norma.setVehiculos(Arrays.asList(v_norma1, v_norma2));
            propietarioDAO.save(norma);


            // -----------------------------------------------------------------
            // --- 3. PEDRO RAMÍREZ (CON UN VEHÍCULO) ---
            // -----------------------------------------------------------------
            VehiculoHacienda v_pedro = new VehiculoHacienda();
            v_pedro.setNumeroSerie("PRZ001SERIED3456");
            v_pedro.setPlacas("D34-567");
            v_pedro.setMarca("Mazda");
            v_pedro.setModelo("CX-5 2021");
            v_pedro.setColor("Blanco");
            v_pedro.setCapacidad(5);
            vehiculoDAO.save(v_pedro);
            PropietarioHacienda pedro = new PropietarioHacienda();
            pedro.setNombre("Pedro Ramirez");
            pedro.setCurp("RAMIRE951020HDFGGM03");
            pedro.setRfc("RAMI951020GG0");
            pedro.setNss("9510200030");
            pedro.setVehiculos(Arrays.asList(v_pedro));
            propietarioDAO.save(pedro);
            
            
            // -----------------------------------------------------------------
            // --- 4. LAURA GARCÍA (CON UN VEHÍCULO) ---
            // -----------------------------------------------------------------
            VehiculoHacienda v_laura = new VehiculoHacienda();
            v_laura.setNumeroSerie("LGZ001SERIEF7890");
            v_laura.setPlacas("F78-901");
            v_laura.setMarca("BMW");
            v_laura.setModelo("X5 2022");
            v_laura.setColor("Gris");
            v_laura.setCapacidad(5);
            vehiculoDAO.save(v_laura);
            
            
            PropietarioHacienda laura = new PropietarioHacienda();
            laura.setNombre("Laura Garcia");
            laura.setCurp("GARCIA981205MSRFML04");
            laura.setRfc("GARC981205RFL");
            laura.setNss("9812050040");
            laura.setVehiculos(Arrays.asList(v_laura));
            propietarioDAO.save(laura);
            LOGGER.info("Insercion de datos de prueba finalizada con exito.");

        } catch (DatabaseException | ValidationException e) {
            LOGGER.log(Level.SEVERE, "Error al insertar datos de prueba. Asegurate de que MongoDB este corriendo y la conexion sea correcta: " + e.getMessage(), e);
        }
    }

    public static void inicializarSiEsNecesariohACIENDA() {
        InicializadorDatosPrueba initializer = new InicializadorDatosPrueba();

        try {
            // CORRECCIÓN: Llamamos al método count() del PropietarioDAO
            // NOTA: Debes agregar un método count() o countDocuments() a tu PropietarioDAO
            if (initializer.propietarioDAO.count() == 0) {
                initializer.agregarDatosPrueba();
            } else {
                LOGGER.info("La base de datos ya contiene datos. Inicialización omitida.");
            }
        } catch (DatabaseException e) {
            LOGGER.log(Level.SEVERE, "Error durante la verificación/inicialización de la BD.", e);
        }
    }

    public static void main(String[] args) {
        inicializarSiEsNecesariohACIENDA();

    }
}
