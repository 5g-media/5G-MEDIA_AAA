package eu.fivegmedia.aaa.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AccNsSessionNetwork entity.
 */
public class AccNsSessionNetworkDTO implements Serializable {

    private Long id;

    @NotNull
    private Long timestamp;

    @NotNull
    private String nfvipopId;

    @NotNull
    private Integer maxBandwidthMbps;

    @NotNull
    private String usedBandwidthMbps;

    @NotNull
    private Integer sessionLengthSec;

    private Long accNsSessionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getNfvipopId() {
        return nfvipopId;
    }

    public void setNfvipopId(String nfvipopId) {
        this.nfvipopId = nfvipopId;
    }

    public Integer getMaxBandwidthMbps() {
        return maxBandwidthMbps;
    }

    public void setMaxBandwidthMbps(Integer maxBandwidthMbps) {
        this.maxBandwidthMbps = maxBandwidthMbps;
    }

    public String getUsedBandwidthMbps() {
        return usedBandwidthMbps;
    }

    public void setUsedBandwidthMbps(String usedBandwidthMbps) {
        this.usedBandwidthMbps = usedBandwidthMbps;
    }

    public Integer getSessionLengthSec() {
        return sessionLengthSec;
    }

    public void setSessionLengthSec(Integer sessionLengthSec) {
        this.sessionLengthSec = sessionLengthSec;
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

        AccNsSessionNetworkDTO accNsSessionNetworkDTO = (AccNsSessionNetworkDTO) o;
        if (accNsSessionNetworkDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), accNsSessionNetworkDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AccNsSessionNetworkDTO{" +
            "id=" + getId() +
            ", timestamp=" + getTimestamp() +
            ", nfvipopId='" + getNfvipopId() + "'" +
            ", maxBandwidthMbps=" + getMaxBandwidthMbps() +
            ", usedBandwidthMbps='" + getUsedBandwidthMbps() + "'" +
            ", sessionLengthSec=" + getSessionLengthSec() +
            ", accNsSession=" + getAccNsSessionId() +
            "}";
    }
}
