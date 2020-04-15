package eu.fivegmedia.aaa.service.mapper;

import eu.fivegmedia.aaa.domain.*;
import eu.fivegmedia.aaa.service.dto.AccNsSessionNetworkDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AccNsSessionNetwork and its DTO AccNsSessionNetworkDTO.
 */
@Mapper(componentModel = "spring", uses = {AccNsSessionMapper.class})
public interface AccNsSessionNetworkMapper extends EntityMapper<AccNsSessionNetworkDTO, AccNsSessionNetwork> {

    @Mapping(source = "accNsSession.id", target = "accNsSessionId")
    AccNsSessionNetworkDTO toDto(AccNsSessionNetwork accNsSessionNetwork);

    @Mapping(source = "accNsSessionId", target = "accNsSession")
    AccNsSessionNetwork toEntity(AccNsSessionNetworkDTO accNsSessionNetworkDTO);

    default AccNsSessionNetwork fromId(Long id) {
        if (id == null) {
            return null;
        }
        AccNsSessionNetwork accNsSessionNetwork = new AccNsSessionNetwork();
        accNsSessionNetwork.setId(id);
        return accNsSessionNetwork;
    }
}
