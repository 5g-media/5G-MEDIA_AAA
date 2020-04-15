package eu.fivegmedia.aaa.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AccVnfSession entity.
 */
public class AccVnfSessionDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 250)
    private String vnfId;

    @NotNull
    @Size(max = 250)
    private String vnfName;

    @NotNull
    private Long timestampStart;

    private Long timestampStop;

    private Long accNsSessionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVnfId() {
        return vnfId;
    }

    public void setVnfId(String vnfId) {
        this.vnfId = vnfId;
    }

    public String getVnfName() {
        return vnfName;
    }

    public void setVnfName(String vnfName) {
        this.vnfName = vnfName;
    }

    public Long getTimestampStart() {
        return timestampStart;
    }

    public void setTimestampStart(Long timestampStart) {
        this.timestampStart = timestampStart;
    }

    public Long getTimestampStop() {
        return timestampStop;
    }

    public void setTimestampStop(Long timestampStop) {
        this.timestampStop = timestampStop;
    }

    public Long getAccNsSessionId() {
        return accNsSessionId;
    }

    public void setAccNsSessionId(Long accNsSessionId) {
        this.accNsSessionId = accNsSessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AccVnfSessionDTO accVnfSessionDTO = (AccVnfSessionDTO) o;
        if (accVnfSessionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), accVnfSessionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AccVnfSessionDTO{" +
            "id=" + getId() +
            ", vnfId='" + getVnfId() + "'" +
            ", vnfName='" + getVnfName() + "'" +
            ", timestampStart=" + getTimestampStart() +
            ", timestampStop=" + getTimestampStop() +
            ", accNsSession=" + getAccNsSessionId() +
            "}";
    }
}
