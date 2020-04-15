package eu.fivegmedia.aaa.service.mapper;

import eu.fivegmedia.aaa.domain.*;
import eu.fivegmedia.aaa.service.dto.AccNsSessionDTO;
import eu.fivegmedia.aaa.service.dto.CatalogUserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CatalogUser and its DTO CatalogUserDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CatalogUserMapper extends EntityMapper<CatalogUserDTO, CatalogUser> {

	@Mapping(source = "user.id", target = "userId")
    CatalogUserDTO toDto(CatalogUser catalogUser);
	
	@Mapping(source = "userId", target = "user")
    CatalogUser toEntity(CatalogUserDTO catalogUserDTO);

    @Mapping(target = "resourceUserSets", ignore = true)
    @Mapping(target = "catalogTokenSets", ignore = true)
    @Mapping(target = "accNsSessionSets", ignore = true)
    @Mapping(target = "catalogTenantSets", ignore = true)

    default CatalogUser fromId(Long id) {
        if (id == null) {
            return null;
        }
        CatalogUser catalogUser = new CatalogUser();
        catalogUser.setId(id);
        return catalogUser;
    }
}
