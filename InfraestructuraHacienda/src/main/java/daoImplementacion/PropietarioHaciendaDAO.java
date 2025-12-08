/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoImplementacion;

/**
 *
 * @author adell
 */
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;
import config.MongoDBConnection;
import exceptiones.DatabaseException;
import model.PropietarioHacienda;
import model.VehiculoHacienda;
import util.Constants;
import util.ValidationUtil;
import daoInterfaces.IPropietarioHaciendaDAO;
import java.util.ArrayList;

public class PropietarioHaciendaDAO implements IPropietarioHaciendaDAO {

    private final MongoCollection<Document> collection;

    public PropietarioHaciendaDAO() {
        this.collection = MongoDBConnection.getInstance()
                .getDatabase("hacienda")
                .getCollection(Constants.COLLECTION_PROPIETARIOS, Document.class);

    }

    @Override
    public PropietarioHacienda save(PropietarioHacienda entity) throws DatabaseException {
        try {
            ValidationUtil.requireNonNull(entity, "propietario");

            // ⭐ CAMBIO CLAVE: Convertir la entidad a Document antes de insertar
            Document docToInsert = mapToDocument(entity);

            collection.insertOne(docToInsert);

            // Si la entidad necesita el _id generado, tendrías que leerlo de docToInsert
            // y asignarlo a entity, pero por ahora solo retornaremos la entidad original.
            return entity;
        } catch (Exception e) {
            throw new DatabaseException("Error al guardar conductor", e);
        }
    }

    public long count() throws DatabaseException {
        try {
            return collection.countDocuments();
        } catch (Exception e) {
            throw new DatabaseException("Error al contar conductores", e);
        }
    }

    /**
     * Busca un Propietario por su CURP (simulando findById).
     */
    @Override
    public Optional<PropietarioHacienda> findByCurp(String curp) {

        // 1. CORRECCIÓN CLAVE: Usamos find(filtro, Document.class) y asignamos el resultado a un Document.
        // Esto obliga al driver a no usar el Codec de POJO, sino a devolver el documento BSON.
        Document mongoDoc = collection
                .find(Filters.eq("curp", curp), Document.class)
                .first();

        // 2. Mapear el Documento de Mongo a la Entidad Java
        if (mongoDoc != null) {
            // Ahora el tipo coincide: le pasamos un Document al método mapToPropietarioDocument(Document)
            return Optional.of(mapToPropietarioDocument(mongoDoc));
        }
        return Optional.empty();
    }

// --- Métodos de Mapeo ---
    /**
     * Convierte Documento Mongo (BSON) a PropietarioHacienda (Java). Se utiliza
     *
     * @SuppressWarnings("unchecked") para manejar la conversión de
     * List<Document>
     * que se retorna desde el driver de MongoDB.
     */
    @SuppressWarnings("unchecked")
    private PropietarioHacienda mapToPropietarioDocument(Document mongoDoc) {
        PropietarioHacienda doc = new PropietarioHacienda();
        doc.setCurp(mongoDoc.getString("curp"));
        doc.setNombre(mongoDoc.getString("nombre"));
        doc.setRfc(mongoDoc.getString("rfc"));
        doc.setNss(mongoDoc.getString("nss"));

        // Mapear la lista de vehículos embebidos
        // **ADAPTACIÓN CLAVE:** Usamos la supresión de advertencia para el casting seguro de List<Document>
        List<Document> vehiculosList = (List<Document>) mongoDoc.get("vehiculos");

        if (vehiculosList != null) {
            List<VehiculoHacienda> embeddedList = vehiculosList.stream()
                    .map(this::mapToVehiculoEmbedded)
                    .collect(Collectors.toList());

            // Asumo que el setter para la lista de vehículos es setVehiculos()
            doc.setVehiculos(embeddedList);
        }
        return doc;
    }

    /**
     * Mapea Documento BSON a VehiculoHacienda.
     */
    private VehiculoHacienda mapToVehiculoEmbedded(Document mongoDoc) {
        VehiculoHacienda embedded = new VehiculoHacienda();
        embedded.setNumeroSerie(mongoDoc.getString("numeroSerie"));
        embedded.setPlacas(mongoDoc.getString("placas"));
        embedded.setMarca(mongoDoc.getString("marca"));
        embedded.setModelo(mongoDoc.getString("modelo"));
        embedded.setColor(mongoDoc.getString("color"));

        // getInteger() es el método correcto para obtener tipos numéricos
        Integer capacidad = mongoDoc.getInteger("capacidad");
        if (capacidad != null) {
            embedded.setCapacidad(capacidad);
        }

        return embedded;
    }

    private Document mapToDocument(PropietarioHacienda entity) {
        Document doc = new Document();
        doc.append("curp", entity.getCurp());
        doc.append("nombre", entity.getNombre());
        doc.append("rfc", entity.getRfc());
        doc.append("nss", entity.getNss());

        // --- CORRECCIÓN AQUÍ ---
        List<Document> vehiculosDocs;
        if (entity.getVehiculos() != null) {
            vehiculosDocs = entity.getVehiculos().stream()
                    .map(this::mapVehiculoToDocument)
                    .collect(java.util.stream.Collectors.toList());
        } else {
            vehiculosDocs = new ArrayList<>(); // Lista vacía segura
        }
        // -----------------------

        doc.append("vehiculos", vehiculosDocs);
        return doc;
    }

    private Document mapVehiculoToDocument(VehiculoHacienda vehiculo) {
        return new Document()
                .append("capacidad", vehiculo.getCapacidad())
                .append("color", vehiculo.getColor())
                .append("marca", vehiculo.getMarca())
                .append("modelo", vehiculo.getModelo())
                .append("numeroSerie", vehiculo.getNumeroSerie())
                .append("placas", vehiculo.getPlacas());
    }
}
