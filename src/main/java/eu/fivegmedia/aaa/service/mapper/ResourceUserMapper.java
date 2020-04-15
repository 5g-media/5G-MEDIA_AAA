package eu.fivegmedia.aaa.service.mapper;

import eu.fivegmedia.aaa.domain.*;
import eu.fivegmedia.aaa.service.dto.ResourceUserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ResourceUser and its DTO ResourceUserDTO.
 */
@Mapper(componentModel = "spring", uses = {ResourceUserLoginMapper.class, ResourceMapper.class, CatalogUserMapper.class})
public interface ResourceUserMapper extends EntityMapper<ResourceUserDTO, ResourceUser> {

    @Mapping(source = "resourceUserLogin.id", target = "resourceUserLoginId")
    @Mapping(source = "resource.id", target = "resourceId")
    @Mapping(source = "catalogUser.id", target = "catalogUserId")
    ResourceUserDTO toDto(ResourceUser resourceUser);

    @Mapping(source = "resourceUserLoginId", target = "resourceUserLogin")
    @Mapping(target = "resourceTokenSets", ignore = true)
    @Mapping(source = "resourceId", target = "resource")
    @Mapping(source = "catalogUserId", target = "catalogUser")
    ResourceUser toEntity(ResourceUserDTO resourceUserDTO);

    default ResourceUser fromId(Long id) {
        if (id == null) {
            return null;
        }
        ResourceUser resourceUser = new ResourceUser();
        resourceUser.setId(id);
        return resourceUser;
    }
}
