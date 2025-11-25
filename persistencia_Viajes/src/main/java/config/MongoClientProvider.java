
package config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static config.MongoConfig.DATABASE_NAME;

/**
 *
 * @author ferch
 */
public class MongoClientProvider {
    // define un provider Singleton: Instancia única
    private static MongoClientProvider instance;
    private final MongoClient mongoClient;
    private final MongoDatabase database;
    
    private MongoClientProvider(){
    //inicializar al cliente solo una vez con la configuracion POJO
    this.mongoClient = MongoClients.create(MongoConfig.getClientSettings());
    this.database = mongoClient.getDatabase(DATABASE_NAME);
    }
    
    public static synchronized MongoClientProvider getInstance(){
    if(instance == null){
    instance = new MongoClientProvider();
    }
    return instance;
    }
    
    //metodo para obtener la base de datos por defecto

    public MongoDatabase getDatabase() {
        return database;
    }

     //metodo para obtener una coleccion tipada con soporte POJO.
    
    public <T> MongoCollection<T> getCollection(String collectionName, Class<T> clazz) {
        // la coleccion ya usa el CodecRegistry configurado en el cliente.
        return database.getCollection(collectionName, clazz);
    }
    
    // metodo para cerrar la conexion
    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
