package eu.fivegmedia.aaa.service.mapper;

import eu.fivegmedia.aaa.domain.*;
import eu.fivegmedia.aaa.service.dto.AccNsSessionMetricsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AccNsSessionMetrics and its DTO AccNsSessionMetricsDTO.
 */
@Mapper(componentModel = "spring", uses = {AccNsSessionMapper.class})
public interface AccNsSessionMetricsMapper extends EntityMapper<AccNsSessionMetricsDTO, AccNsSessionMetrics> {

    @Mapping(source = "accNsSession.id", target = "accNsSessionId")
    AccNsSessionMetricsDTO toDto(AccNsSessionMetrics accNsSessionMetrics);

    @Mapping(source = "accNsSessionId", target = "accNsSession")
    AccNsSessionMetrics toEntity(AccNsSessionMetricsDTO accNsSessionMetricsDTO);

    default AccNsSessionMetrics fromId(Long id) {
        if (id == null) {
            return null;
        }
        AccNsSessionMetrics accNsSessionMetrics = new AccNsSessionMetrics();
        accNsSessionMetrics.setId(id);
        return accNsSessionMetrics;
    }
}
