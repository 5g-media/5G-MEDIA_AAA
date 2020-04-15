package eu.fivegmedia.aaa.service.mapper;

import eu.fivegmedia.aaa.domain.*;
import eu.fivegmedia.aaa.service.dto.ResourceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Resource and its DTO ResourceDTO.
 */
@Mapper(componentModel = "spring", uses = {ResourceAdminLoginMapper.class})
public interface ResourceMapper extends EntityMapper<ResourceDTO, Resource> {

    @Mapping(source = "resourceAdminLogin.id", target = "resourceAdminLoginId")
    ResourceDTO toDto(Resource resource);

    @Mapping(source = "resourceAdminLoginId", target = "resourceAdminLogin")
    @Mapping(target = "resourceUserSets", ignore = true)
    @Mapping(target = "endpointSets", ignore = true)
    Resource toEntity(ResourceDTO resourceDTO);

    default Resource fromId(Long id) {
        if (id == null) {
            return null;
        }
        Resource resource = new Resource();
        resource.setId(id);
        return resource;
    }
}
