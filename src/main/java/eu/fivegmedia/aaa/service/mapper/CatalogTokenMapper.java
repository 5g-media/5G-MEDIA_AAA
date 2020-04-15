package eu.fivegmedia.aaa.service.mapper;

import eu.fivegmedia.aaa.domain.*;
import eu.fivegmedia.aaa.service.dto.CatalogTokenDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CatalogToken and its DTO CatalogTokenDTO.
 */
@Mapper(componentModel = "spring", uses = {CatalogUserMapper.class})
public interface CatalogTokenMapper extends EntityMapper<CatalogTokenDTO, CatalogToken> {

    @Mapping(source = "catalogUser.id", target = "catalogUserId")
    CatalogTokenDTO toDto(CatalogToken catalogToken);

    @Mapping(source = "catalogUserId", target = "catalogUser")
    CatalogToken toEntity(CatalogTokenDTO catalogTokenDTO);

    default CatalogToken fromId(Long id) {
        if (id == null) {
            return null;
        }
        CatalogToken catalogToken = new CatalogToken();
        catalogToken.setId(id);
        return catalogToken;
    }
}
