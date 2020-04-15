package eu.fivegmedia.aaa.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the CatalogTenant entity.
 */
public class CatalogTenantDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 250)
    private String name;

    private Set<CatalogUserDTO> catalogUserSets = new HashSet<>();

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

    public Set<CatalogUserDTO> getCatalogUserSets() {
        return catalogUserSets;
    }

    public void setCatalogUserSets(Set<CatalogUserDTO> catalogUsers) {
        this.catalogUserSets = catalogUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CatalogTenantDTO catalogTenantDTO = (CatalogTenantDTO) o;
        if (catalogTenantDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), catalogTenantDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CatalogTenantDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
