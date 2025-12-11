/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoImplementacion;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import config.MongoDBConnection;
import daoInterfaces.IVehiculoHaciendaDAO;
import exceptiones.DatabaseException;
import java.util.Optional;
import model.VehiculoHacienda;
import org.bson.Document;
import util.Constants;
import util.ValidationUtil;

/**
 *
 * @author adell
 */
public class VehiculoHaciendaDAO implements IVehiculoHaciendaDAO {

    private final MongoCollection<Document> collection;

    public VehiculoHaciendaDAO() {
        this.collection = MongoDBConnection.getInstance()
                .getDatabase("hacienda")
                .getCollection(Constants.COLLECTION_VEHICULOS, Document.class);

        String dbName = collection.getNamespace().getDatabaseName();
        System.out.println("La base de datos utilizada es:" + dbName);
    }

    public VehiculoHacienda save(VehiculoHacienda entity) throws DatabaseException {
        try {
            ValidationUtil.requireNonNull(entity, "propietario");

            Document docToInsert = mapVehiculoToDocument(entity);

            collection.insertOne(docToInsert);

            return entity;
        } catch (Exception e) {
            throw new DatabaseException("Error al guardar conductor", e);
        }
    }

    @Override
    public Optional<VehiculoHacienda> findByNumeroSerie(String numeroSerie) {

        String serieLimpia = limpiarSerie(numeroSerie);

        Document vehiculoDoc = collection
                .find(Filters.eq("numeroSerie", serieLimpia))
                .first();

        if (vehiculoDoc != null) {

            return Optional.of(mapToVehiculoEntity(vehiculoDoc));
        }

        return Optional.empty();
    }

    public Document mapVehiculoToDocument(VehiculoHacienda vehiculo) {

        Document doc = new Document();

        doc.append("numeroSerie", vehiculo.getNumeroSerie());
        doc.append("placas", vehiculo.getPlacas());
        doc.append("marca", vehiculo.getMarca());
        doc.append("modelo", vehiculo.getModelo());
        doc.append("color", vehiculo.getColor());

        doc.append("capacidad", vehiculo.getCapacidad());

        return doc;
    }

    private VehiculoHacienda mapToVehiculoEntity(Document mongoDoc) {

        VehiculoHacienda entity = new VehiculoHacienda();
        entity.setNumeroSerie(mongoDoc.getString("numeroSerie"));
        entity.setPlacas(mongoDoc.getString("placas"));
        entity.setMarca(mongoDoc.getString("marca"));
        entity.setModelo(mongoDoc.getString("modelo"));
        entity.setColor(mongoDoc.getString("color"));
        entity.setCapacidad(mongoDoc.getInteger("capacidad", 0));
        return entity;
    }

    private String limpiarSerie(String serie) {
        if (serie == null) {
            return "";
        }
        return serie.trim().replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
    }
}
