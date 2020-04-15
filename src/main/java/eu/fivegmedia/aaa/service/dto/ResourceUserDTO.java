package eu.fivegmedia.aaa.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ResourceUser entity.
 */
public class ResourceUserDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 250)
    private String name;

    @Size(max = 250)
    private String tenant;

    @NotNull
    @Size(max = 250)
    private String permissions;
    
    @Size(max = 250)
    private String groupname;

    private Boolean enabled;

    private Long resourceUserLoginId;

    private Long resourceId;

    private Long catalogUserId;

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

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
    
    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Long getResourceUserLoginId() {
        return resourceUserLoginId;
    }

    public void setResourceUserLoginId(Long resourceUserLoginId) {
        this.resourceUserLoginId = resourceUserLoginId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getCatalogUserId() {
        return catalogUserId;
    }

    public void setCatalogUserId(Long catalogUserId) {
        this.catalogUserId = catalogUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ResourceUserDTO resourceUserDTO = (ResourceUserDTO) o;
        if (resourceUserDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), resourceUserDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ResourceUserDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", tenant='" + getTenant() + "'" +
            ", permissions='" + getPermissions() + "'" +
            ", groupname='" + getGroupname() + "'" +
            ", enabled='" + isEnabled() + "'" +
            ", resourceUserLogin=" + getResourceUserLoginId() +
            ", resource=" + getResourceId() +
            ", catalogUser=" + getCatalogUserId() +
            "}";
    }
}
