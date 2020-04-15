package eu.fivegmedia.aaa.service.mapper;

import eu.fivegmedia.aaa.domain.*;
import eu.fivegmedia.aaa.service.dto.AccVduSessionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AccVduSession and its DTO AccVduSessionDTO.
 */
@Mapper(componentModel = "spring", uses = {AccVnfSessionMapper.class})
public interface AccVduSessionMapper extends EntityMapper<AccVduSessionDTO, AccVduSession> {

    @Mapping(source = "accVnfSession.id", target = "accVnfSessionId")
    AccVduSessionDTO toDto(AccVduSession accVduSession);

    @Mapping(target = "accVduResourceConsumptionSets", ignore = true)
    @Mapping(source = "accVnfSessionId", target = "accVnfSession")
    AccVduSession toEntity(AccVduSessionDTO accVduSessionDTO);

    default AccVduSession fromId(Long id) {
        if (id == null) {
            return null;
        }
        AccVduSession accVduSession = new AccVduSession();
        accVduSession.setId(id);
        return accVduSession;
    }
}
