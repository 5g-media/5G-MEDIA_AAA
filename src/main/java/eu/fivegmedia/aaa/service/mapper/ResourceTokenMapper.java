package eu.fivegmedia.aaa.service.mapper;

import eu.fivegmedia.aaa.domain.*;
import eu.fivegmedia.aaa.service.dto.ResourceTokenDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ResourceToken and its DTO ResourceTokenDTO.
 */
@Mapper(componentModel = "spring", uses = {ResourceUserMapper.class})
public interface ResourceTokenMapper extends EntityMapper<ResourceTokenDTO, ResourceToken> {

    @Mapping(source = "resourceUser.id", target = "resourceUserId")
    ResourceTokenDTO toDto(ResourceToken resourceToken);

    @Mapping(source = "resourceUserId", target = "resourceUser")
    ResourceToken toEntity(ResourceTokenDTO resourceTokenDTO);

    default ResourceToken fromId(Long id) {
        if (id == null) {
            return null;
        }
        ResourceToken resourceToken = new ResourceToken();
        resourceToken.setId(id);
        return resourceToken;
    }
}
