package eu.fivegmedia.aaa.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import eu.fivegmedia.aaa.domain.enumeration.ResourceEnum;

/**
 * A DTO for the Resource entity.
 */
public class ResourceDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 250)
    private String resourceid;

    @NotNull
    @Size(max = 250)
    private String name;

    @Size(max = 250)
    private String url;

    @Size(max = 250)
    private String authDriver;

    @Size(max = 250)
    private String authConf;

    private String properties;

    private ResourceEnum resourceEnum;

    private Long resourceAdminLoginId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResourceid() {
        return resourceid;
    }

    public void setResourceid(String resourceid) {
        this.resourceid = resourceid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthDriver() {
        return authDriver;
    }

    public void setAuthDriver(String authDriver) {
        this.authDriver = authDriver;
    }

    public String getAuthConf() {
        return authConf;
    }

    public void setAuthConf(String authConf) {
        this.authConf = authConf;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public ResourceEnum getResourceEnum() {
        return resourceEnum;
    }

    public void setResourceEnum(ResourceEnum resourceEnum) {
        this.resourceEnum = resourceEnum;
    }

    public Long getResourceAdminLoginId() {
        return resourceAdminLoginId;
    }

    public void setResourceAdminLoginId(Long resourceAdminLoginId) {
        this.resourceAdminLoginId = resourceAdminLoginId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ResourceDTO resourceDTO = (ResourceDTO) o;
        if (resourceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), resourceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ResourceDTO{" +
            "id=" + getId() +
            ", resourceid='" + getResourceid() + "'" +
            ", name='" + getName() + "'" +
            ", url='" + getUrl() + "'" +
            ", authDriver='" + getAuthDriver() + "'" +
            ", authConf='" + getAuthConf() + "'" +
            ", properties='" + getProperties() + "'" +
            ", resourceEnum='" + getResourceEnum() + "'" +
            ", resourceAdminLogin=" + getResourceAdminLoginId() +
            "}";
    }
}
