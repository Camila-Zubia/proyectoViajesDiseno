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
import java.util.List;
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

            // ⭐ CAMBIO CLAVE: Convertir la entidad a Document antes de insertar
            Document docToInsert = mapVehiculoToDocument(entity);

            collection.insertOne(docToInsert);

            // Si la entidad necesita el _id generado, tendrías que leerlo de docToInsert
            // y asignarlo a entity, pero por ahora solo retornaremos la entidad original.
            return entity;
        } catch (Exception e) {
            throw new DatabaseException("Error al guardar conductor", e);
        }
    }

    @Override
    public Optional<VehiculoHacienda> findByNumeroSerie(String numeroSerie) {
       

        String serieLimpia = limpiarSerie(numeroSerie);

        // 1. Ejecución de la consulta directa en la colección de vehículos
        Document vehiculoDoc = collection
                .find(Filters.eq("numeroSerie", serieLimpia)) // Búsqueda simple sin Dot Notation
                .first();

        if (vehiculoDoc != null) {
            // 2. El mapeo ahora usa el documento de vehículo completo, no un subdocumento
            return Optional.of(mapToVehiculoEntity(vehiculoDoc));
        }

        return Optional.empty();
    }

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

    public Document mapVehiculoToDocument(VehiculoHacienda vehiculo) {

        // Creamos un nuevo Documento para el vehículo
        Document doc = new Document();

        // Añadimos cada campo de la entidad al Documento.
        // Es CRÍTICO que estos nombres de campo (p. ej., "numeroSerie") coincidan
        // con los utilizados en las consultas de Dot Notation para evitar fallos.
        doc.append("numeroSerie", vehiculo.getNumeroSerie());
        doc.append("placas", vehiculo.getPlacas());
        doc.append("marca", vehiculo.getMarca());
        doc.append("modelo", vehiculo.getModelo());
        doc.append("color", vehiculo.getColor());

        // Los tipos primitivos o wrappers (como int/Integer) también se mapean directamente
        doc.append("capacidad", vehiculo.getCapacidad());

        return doc;
    }

    private VehiculoHacienda mapToVehiculoEntity(Document mongoDoc) {
        // ... (Tu lógica de mapeo de Document a VehiculoHacienda, ya no es 'Embedded')
        VehiculoHacienda entity = new VehiculoHacienda();
        entity.setNumeroSerie(mongoDoc.getString("numeroSerie"));
        entity.setPlacas(mongoDoc.getString("placas"));
        entity.setMarca(mongoDoc.getString("marca"));
        entity.setModelo(mongoDoc.getString("modelo"));
        // ... (otros campos)
        return entity;
    }

    private String limpiarSerie(String serie) {
        if (serie == null) {
            return "";
        }
        return serie.trim().replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
    }
}
