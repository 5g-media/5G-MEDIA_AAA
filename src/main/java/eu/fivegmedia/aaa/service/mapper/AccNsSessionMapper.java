package eu.fivegmedia.aaa.service.mapper;

import eu.fivegmedia.aaa.domain.*;
import eu.fivegmedia.aaa.service.dto.AccNsSessionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AccNsSession and its DTO AccNsSessionDTO.
 */
@Mapper(componentModel = "spring", uses = {CatalogUserMapper.class})
public interface AccNsSessionMapper extends EntityMapper<AccNsSessionDTO, AccNsSession> {

    @Mapping(source = "catalogUser.id", target = "catalogUserId")
    AccNsSessionDTO toDto(AccNsSession accNsSession);

    @Mapping(target = "accVnfSessionSets", ignore = true)
    @Mapping(target = "accNsSessionMetricsSets", ignore = true)
    @Mapping(target = "accNsSessionNetworkSets", ignore = true)
    @Mapping(source = "catalogUserId", target = "catalogUser")
    AccNsSession toEntity(AccNsSessionDTO accNsSessionDTO);

    default AccNsSession fromId(Long id) {
        if (id == null) {
            return null;
        }
        AccNsSession accNsSession = new AccNsSession();
        accNsSession.setId(id);
        return accNsSession;
    }
}
