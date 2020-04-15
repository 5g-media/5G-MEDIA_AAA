package eu.fivegmedia.aaa.service.mapper;

import eu.fivegmedia.aaa.domain.*;
import eu.fivegmedia.aaa.service.dto.AccVduResourceConsumptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AccVduResourceConsumption and its DTO AccVduResourceConsumptionDTO.
 */
@Mapper(componentModel = "spring", uses = {AccVduSessionMapper.class})
public interface AccVduResourceConsumptionMapper extends EntityMapper<AccVduResourceConsumptionDTO, AccVduResourceConsumption> {

    @Mapping(source = "accVduSession.id", target = "accVduSessionId")
    AccVduResourceConsumptionDTO toDto(AccVduResourceConsumption accVduResourceConsumption);

    @Mapping(source = "accVduSessionId", target = "accVduSession")
    AccVduResourceConsumption toEntity(AccVduResourceConsumptionDTO accVduResourceConsumptionDTO);

    default AccVduResourceConsumption fromId(Long id) {
        if (id == null) {
            return null;
        }
        AccVduResourceConsumption accVduResourceConsumption = new AccVduResourceConsumption();
        accVduResourceConsumption.setId(id);
        return accVduResourceConsumption;
    }
}
