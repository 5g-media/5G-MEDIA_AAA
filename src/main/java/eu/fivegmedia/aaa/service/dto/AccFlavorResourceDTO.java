package eu.fivegmedia.aaa.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import eu.fivegmedia.aaa.domain.enumeration.FlavorResourceEnum;

/**
 * A DTO for the AccFlavorResource entity.
 */
public class AccFlavorResourceDTO implements Serializable {

    private Long id;

    @NotNull
    private Long value;

    private FlavorResourceEnum resourceType;

    private Long accFlavorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public FlavorResourceEnum getResourceType() {
        return resourceType;
    }

    public void setResourceType(FlavorResourceEnum resourceType) {
        this.resourceType = resourceType;
    }

    public Long getAccFlavorId() {
        return accFlavorId;
    }

    public void setAccFlavorId(Long accFlavorId) {
        this.accFlavorId = accFlavorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AccFlavorResourceDTO accFlavorResourceDTO = (AccFlavorResourceDTO) o;
        if (accFlavorResourceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), accFlavorResourceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AccFlavorResourceDTO{" +
            "id=" + getId() +
            ", value=" + getValue() +
            ", resourceType='" + getResourceType() + "'" +
            ", accFlavor=" + getAccFlavorId() +
            "}";
    }
}
