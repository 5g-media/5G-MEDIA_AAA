package eu.fivegmedia.aaa.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Endpoint entity.
 */
public class EndpointDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 250)
    private String name;

    @NotNull
    @Size(max = 250)
    private String url;

    @NotNull
    @Size(max = 250)
    private String method;

    private String properties;

    private Long resourceId;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EndpointDTO endpointDTO = (EndpointDTO) o;
        if (endpointDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), endpointDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EndpointDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", url='" + getUrl() + "'" +
            ", method='" + getMethod() + "'" +
            ", properties='" + getProperties() + "'" +
            ", resource=" + getResourceId() +
            "}";
    }
}
