package adaptadores;

import dto.AdeudoDTO;
import org.base_datos_viajes.model.Adeudo;
import org.bson.types.ObjectId;

/**
 * Adaptador para conversión entre Adeudo (entidad) y AdeudoDTO
 *
 * @author Camila Zubia 00000244825
 */
public class adaptadorAdeudo {

    /**
     * Convierte una entidad Adeudo a AdeudoDTO
     */
    public static AdeudoDTO toDTO(Adeudo entity) {
        if (entity == null) {
            return null;
        }

        AdeudoDTO dto = new AdeudoDTO(
            entity.getMonto(),
            entity.getConcepto(),
            entity.getFecha()
        );

        if (entity.getId() != null) {
            dto.setId(entity.getId().toHexString());
        }

        dto.setPagado(entity.isPagado());

        return dto;
    }

    /**
     * Convierte un AdeudoDTO a entidad Adeudo
     *
     * @param dto DTO a convertir
     * @param conductorId ID del conductor (ObjectId)
     * @return Entidad Adeudo
     */
    public static Adeudo toEntity(AdeudoDTO dto, ObjectId conductorId) {
        if (dto == null) {
            return null;
        }

        Adeudo entity = new Adeudo(
            conductorId,
            dto.getMonto(),
            dto.getConcepto(),
            dto.getFecha()
        );

        if (dto.getId() != null && !dto.getId().isEmpty()) {
            entity.setId(new ObjectId(dto.getId()));
        }

        entity.setPagado(dto.isPagado());

        return entity;
    }
}
