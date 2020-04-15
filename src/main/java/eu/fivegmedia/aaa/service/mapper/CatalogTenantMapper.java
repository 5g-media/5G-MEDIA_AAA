package eu.fivegmedia.aaa.service.mapper;

import eu.fivegmedia.aaa.domain.*;
import eu.fivegmedia.aaa.service.dto.CatalogTenantDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CatalogTenant and its DTO CatalogTenantDTO.
 */
@Mapper(componentModel = "spring", uses = {CatalogUserMapper.class})
public interface CatalogTenantMapper extends EntityMapper<CatalogTenantDTO, CatalogTenant> {


    @Mapping(target = "policySets", ignore = true)
    CatalogTenant toEntity(CatalogTenantDTO catalogTenantDTO);

    default CatalogTenant fromId(Long id) {
        if (id == null) {
            return null;
        }
        CatalogTenant catalogTenant = new CatalogTenant();
        catalogTenant.setId(id);
        return catalogTenant;
    }
}
