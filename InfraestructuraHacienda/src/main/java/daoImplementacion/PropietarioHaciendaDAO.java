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

            Document docToInsert = mapToDocument(entity);

            collection.insertOne(docToInsert);

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

        Document mongoDoc = collection
                .find(Filters.eq("curp", curp), Document.class)
                .first();

        if (mongoDoc != null) {

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

        List<Document> vehiculosList = (List<Document>) mongoDoc.get("vehiculos");

        if (vehiculosList != null) {
            List<VehiculoHacienda> embeddedList = vehiculosList.stream()
                    .map(this::mapToVehiculoEmbedded)
                    .collect(Collectors.toList());

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

        List<Document> vehiculosDocs;
        if (entity.getVehiculos() != null) {
            vehiculosDocs = entity.getVehiculos().stream()
                    .map(this::mapVehiculoToDocument)
                    .collect(java.util.stream.Collectors.toList());
        } else {
            vehiculosDocs = new ArrayList<>();
        }

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
