package eu.fivegmedia.aaa.service.mapper;

import eu.fivegmedia.aaa.domain.*;
import eu.fivegmedia.aaa.service.dto.AccVnfSessionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AccVnfSession and its DTO AccVnfSessionDTO.
 */
@Mapper(componentModel = "spring", uses = {AccNsSessionMapper.class})
public interface AccVnfSessionMapper extends EntityMapper<AccVnfSessionDTO, AccVnfSession> {

    @Mapping(source = "accNsSession.id", target = "accNsSessionId")
    AccVnfSessionDTO toDto(AccVnfSession accVnfSession);

    @Mapping(target = "accVduSessionSets", ignore = true)
    @Mapping(source = "accNsSessionId", target = "accNsSession")
    AccVnfSession toEntity(AccVnfSessionDTO accVnfSessionDTO);

    default AccVnfSession fromId(Long id) {
        if (id == null) {
            return null;
        }
        AccVnfSession accVnfSession = new AccVnfSession();
        accVnfSession.setId(id);
        return accVnfSession;
    }
}
