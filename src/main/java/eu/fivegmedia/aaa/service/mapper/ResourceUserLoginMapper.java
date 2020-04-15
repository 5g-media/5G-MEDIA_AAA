package eu.fivegmedia.aaa.service.mapper;

import eu.fivegmedia.aaa.domain.*;
import eu.fivegmedia.aaa.service.dto.ResourceUserLoginDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ResourceUserLogin and its DTO ResourceUserLoginDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ResourceUserLoginMapper extends EntityMapper<ResourceUserLoginDTO, ResourceUserLogin> {



    default ResourceUserLogin fromId(Long id) {
        if (id == null) {
            return null;
        }
        ResourceUserLogin resourceUserLogin = new ResourceUserLogin();
        resourceUserLogin.setId(id);
        return resourceUserLogin;
    }
}
