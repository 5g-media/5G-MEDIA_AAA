package eu.fivegmedia.aaa.service.mapper;

import eu.fivegmedia.aaa.domain.*;
import eu.fivegmedia.aaa.service.dto.ResourceAdminLoginDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ResourceAdminLogin and its DTO ResourceAdminLoginDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ResourceAdminLoginMapper extends EntityMapper<ResourceAdminLoginDTO, ResourceAdminLogin> {



    default ResourceAdminLogin fromId(Long id) {
        if (id == null) {
            return null;
        }
        ResourceAdminLogin resourceAdminLogin = new ResourceAdminLogin();
        resourceAdminLogin.setId(id);
        return resourceAdminLogin;
    }
}
