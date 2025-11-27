
package config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import org.bson.codecs.configuration.CodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author ferch
 */
public class MongoConfig {
    private static final String CONNECTION_URI = "mongodb://localhost:27017/";
    public static final String DATABASE_NAME = "viajesCompartidosDB";
    
    private static final CodecRegistry POJO_CODEC_REGISTRY;
    
    static {
        // configuracion del driver con soporte POJO (codecs).
        // crear el proveedor de POJO.
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder()
            .automatic(true) // habilita el mapeo automatico de POJOs
            .build();
        
        // crear el registro de Codecs que incluye el registro por defecto y el de POJO.
        POJO_CODEC_REGISTRY = fromRegistries(
            MongoClientSettings.getDefaultCodecRegistry(), // registro por defecto de MongoDB para BSON, ObjectId, etc
            fromProviders(pojoCodecProvider) // el registro para las clases POJO
        );
    }

    /**
     * obtiene la configuracion completa del cliente MongoDB con soporte POJO.
     */
    public static MongoClientSettings getClientSettings() {
        return MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(CONNECTION_URI))
                .codecRegistry(POJO_CODEC_REGISTRY) // aplica el registro POJO
                .build();
    }
}
