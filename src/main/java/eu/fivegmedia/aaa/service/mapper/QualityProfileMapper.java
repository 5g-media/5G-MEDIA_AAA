package eu.fivegmedia.aaa.service.mapper;

import eu.fivegmedia.aaa.domain.*;
import eu.fivegmedia.aaa.service.dto.QualityProfileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity QualityProfile and its DTO QualityProfileDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QualityProfileMapper extends EntityMapper<QualityProfileDTO, QualityProfile> {



    default QualityProfile fromId(Long id) {
        if (id == null) {
            return null;
        }
        QualityProfile qualityProfile = new QualityProfile();
        qualityProfile.setId(id);
        return qualityProfile;
    }
}
