package eu.fivegmedia.aaa.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AccNsSessionTraffic entity.
 */
public class AccNsSessionTrafficDTO implements Serializable {

    private Long id;

    @NotNull
    private Long timestamp;

    @NotNull
    private String nfvipopId;

    @NotNull
    private Integer maxBandwidthMbps;

    @NotNull
    private String usedBandwidthMbps;

    private Long accNsSessionMetricsId;

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

    public Long getAccNsSessionMetricsId() {
        return accNsSessionMetricsId;
    }

    public void setAccNsSessionMetricsId(Long accNsSessionMetricsId) {
        this.accNsSessionMetricsId = accNsSessionMetricsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AccNsSessionTrafficDTO accNsSessionTrafficDTO = (AccNsSessionTrafficDTO) o;
        if (accNsSessionTrafficDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), accNsSessionTrafficDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AccNsSessionTrafficDTO{" +
            "id=" + getId() +
            ", timestamp=" + getTimestamp() +
            ", nfvipopId='" + getNfvipopId() + "'" +
            ", maxBandwidthMbps=" + getMaxBandwidthMbps() +
            ", usedBandwidthMbps='" + getUsedBandwidthMbps() + "'" +
            ", accNsSessionMetrics=" + getAccNsSessionMetricsId() +
            "}";
    }
}
