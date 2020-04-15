package eu.fivegmedia.aaa.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Policy entity.
 */
public class PolicyDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 250)
    private String name;

    @NotNull
    @Size(max = 250)
    private String policy;

    private Long catalogTenantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public Long getCatalogTenantId() {
        return catalogTenantId;
    }

    public void setCatalogTenantId(Long catalogTenantId) {
        this.catalogTenantId = catalogTenantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PolicyDTO policyDTO = (PolicyDTO) o;
        if (policyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), policyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PolicyDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", policy='" + getPolicy() + "'" +
            ", catalogTenant=" + getCatalogTenantId() +
            "}";
    }
}
