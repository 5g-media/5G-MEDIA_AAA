package eu.fivegmedia.aaa.service.mapper;

import eu.fivegmedia.aaa.domain.*;
import eu.fivegmedia.aaa.service.dto.PolicyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Policy and its DTO PolicyDTO.
 */
@Mapper(componentModel = "spring", uses = {CatalogTenantMapper.class})
public interface PolicyMapper extends EntityMapper<PolicyDTO, Policy> {

    @Mapping(source = "catalogTenant.id", target = "catalogTenantId")
    PolicyDTO toDto(Policy policy);

    @Mapping(source = "catalogTenantId", target = "catalogTenant")
    Policy toEntity(PolicyDTO policyDTO);

    default Policy fromId(Long id) {
        if (id == null) {
            return null;
        }
        Policy policy = new Policy();
        policy.setId(id);
        return policy;
    }
}
