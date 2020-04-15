package eu.fivegmedia.aaa.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AccNsSessionMetrics entity.
 */
public class AccNsSessionMetricsDTO implements Serializable {

    private Long id;

    @NotNull
    private Long timestamp;

    @NotNull
    private String nfvipopId;

    @NotNull
    private Integer sessionLengthSec;

    @NotNull
    private String qualityProfile;

    @NotNull
    private Double expectedQoe;

    @NotNull
    private Double measuredQoe;

    @NotNull
    private Integer entropyTI;

    @NotNull
    private Integer entropySI;

    @NotNull
    private Integer compressionLevel;

    private Integer bandwidthAllocatedMbps;

    @NotNull
    private Integer bitrateKbps;

    @NotNull
    private Integer blur;

    private Integer userCount;

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

    public Integer getSessionLengthSec() {
        return sessionLengthSec;
    }

    public void setSessionLengthSec(Integer sessionLengthSec) {
        this.sessionLengthSec = sessionLengthSec;
    }

    public String getQualityProfile() {
        return qualityProfile;
    }

    public void setQualityProfile(String qualityProfile) {
        this.qualityProfile = qualityProfile;
    }

    public Double getExpectedQoe() {
        return expectedQoe;
    }

    public void setExpectedQoe(Double expectedQoe) {
        this.expectedQoe = expectedQoe;
    }

    public Double getMeasuredQoe() {
        return measuredQoe;
    }

    public void setMeasuredQoe(Double measuredQoe) {
        this.measuredQoe = measuredQoe;
    }

    public Integer getEntropyTI() {
        return entropyTI;
    }

    public void setEntropyTI(Integer entropyTI) {
        this.entropyTI = entropyTI;
    }

    public Integer getEntropySI() {
        return entropySI;
    }

    public void setEntropySI(Integer entropySI) {
        this.entropySI = entropySI;
    }

    public Integer getCompressionLevel() {
        return compressionLevel;
    }

    public void setCompressionLevel(Integer compressionLevel) {
        this.compressionLevel = compressionLevel;
    }

    public Integer getBandwidthAllocatedMbps() {
        return bandwidthAllocatedMbps;
    }

    public void setBandwidthAllocatedMbps(Integer bandwidthAllocatedMbps) {
        this.bandwidthAllocatedMbps = bandwidthAllocatedMbps;
    }

    public Integer getBitrateKbps() {
        return bitrateKbps;
    }

    public void setBitrateKbps(Integer bitrateKbps) {
        this.bitrateKbps = bitrateKbps;
    }

    public Integer getBlur() {
        return blur;
    }

    public void setBlur(Integer blur) {
        this.blur = blur;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
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

        AccNsSessionMetricsDTO accNsSessionMetricsDTO = (AccNsSessionMetricsDTO) o;
        if (accNsSessionMetricsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), accNsSessionMetricsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AccNsSessionMetricsDTO{" +
            "id=" + getId() +
            ", timestamp=" + getTimestamp() +
            ", nfvipopId='" + getNfvipopId() + "'" +
            ", sessionLengthSec=" + getSessionLengthSec() +
            ", qualityProfile='" + getQualityProfile() + "'" +
            ", expectedQoe=" + getExpectedQoe() +
            ", measuredQoe=" + getMeasuredQoe() +
            ", entropyTI=" + getEntropyTI() +
            ", entropySI=" + getEntropySI() +
            ", compressionLevel=" + getCompressionLevel() +
            ", bandwidthAllocatedMbps=" + getBandwidthAllocatedMbps() +
            ", bitrateKbps=" + getBitrateKbps() +
            ", blur=" + getBlur() +
            ", userCount=" + getUserCount() +
            ", accNsSession=" + getAccNsSessionId() +
            "}";
    }
}
