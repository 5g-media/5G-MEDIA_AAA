package eu.fivegmedia.aaa.service.mapper;

import eu.fivegmedia.aaa.domain.*;
import eu.fivegmedia.aaa.service.dto.EndpointDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Endpoint and its DTO EndpointDTO.
 */
@Mapper(componentModel = "spring", uses = {ResourceMapper.class})
public interface EndpointMapper extends EntityMapper<EndpointDTO, Endpoint> {

    @Mapping(source = "resource.id", target = "resourceId")
    EndpointDTO toDto(Endpoint endpoint);

    @Mapping(source = "resourceId", target = "resource")
    Endpoint toEntity(EndpointDTO endpointDTO);

    default Endpoint fromId(Long id) {
        if (id == null) {
            return null;
        }
        Endpoint endpoint = new Endpoint();
        endpoint.setId(id);
        return endpoint;
    }
}
