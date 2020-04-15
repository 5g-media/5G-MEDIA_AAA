package eu.fivegmedia.aaa.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import eu.fivegmedia.aaa.domain.enumeration.VduResourceEnum;

/**
 * A DTO for the AccVduResourceConsumption entity.
 */
public class AccVduResourceConsumptionDTO implements Serializable {

    private Long id;

    @NotNull
    private Long value;

    @NotNull
    private Long timestamp;

    private VduResourceEnum resourceType;

    private Long accVduSessionId;

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

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public VduResourceEnum getResourceType() {
        return resourceType;
    }

    public void setResourceType(VduResourceEnum resourceType) {
        this.resourceType = resourceType;
    }

    public Long getAccVduSessionId() {
        return accVduSessionId;
    }

    public void setAccVduSessionId(Long accVduSessionId) {
        this.accVduSessionId = accVduSessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AccVduResourceConsumptionDTO accVduResourceConsumptionDTO = (AccVduResourceConsumptionDTO) o;
        if (accVduResourceConsumptionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), accVduResourceConsumptionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AccVduResourceConsumptionDTO{" +
            "id=" + getId() +
            ", value=" + getValue() +
            ", timestamp=" + getTimestamp() +
            ", resourceType='" + getResourceType() + "'" +
            ", accVduSession=" + getAccVduSessionId() +
            "}";
    }
}
