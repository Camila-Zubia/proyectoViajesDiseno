package inicializador;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import exceptiones.DatabaseException;
import exceptiones.ValidationException;

import daoImplementacion.PropietarioHaciendaDAO;
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

    public InicializadorDatosPrueba() {

        this.propietarioDAO = new PropietarioHaciendaDAO();
    }

    /**
     * Agrega datos de prueba a la base de datos de MongoDB.
     */
    private void agregarDatosPrueba() {
        LOGGER.info("Iniciando insercion de datos de prueba...");
        try {

            // 1. Instanciar el DAO
            PropietarioHaciendaDAO dao = new PropietarioHaciendaDAO();

            // 2. Crear el objeto con los datos EXACTOS que usas en tu prueba
            PropietarioHacienda p = new PropietarioHacienda();
            p.setCurp("CURPJP001");
            p.setNombre("Juan"); // Ojo con mayúsculas/minúsculas
            p.setRfc("RFCJP001");
            p.setNss("NSSJP001");

            // 3. Crear el vehículo
            VehiculoHacienda v = new VehiculoHacienda();
            v.setNumeroSerie("SERIEJP001");
            v.setPlacas("AAA-111");
            v.setMarca("Nissan");
            v.setModelo("Sentra");
            v.setColor("Negro");
            v.setCapacidad(5);

            // 4. Ligar vehículo al propietario
            List<VehiculoHacienda> lista = new ArrayList<>();
            lista.add(v);
            p.setVehiculos(lista);

            // 5. GUARDAR (Esto insertará en la colección correcta con el formato correcto)
            dao.save(p);
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
