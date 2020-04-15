package eu.fivegmedia.aaa.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import eu.fivegmedia.aaa.domain.enumeration.RoleEnum;

/**
 * A DTO for the CatalogUser entity.
 */
public class CatalogUserDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 250)
    private String name;

    @Size(max = 250)
    private String description;
    
    private Long userId;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CatalogUserDTO catalogUserDTO = (CatalogUserDTO) o;
        if (catalogUserDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), catalogUserDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CatalogUserDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", user='" + getUserId() + "'" +
            "}";
    }
}
