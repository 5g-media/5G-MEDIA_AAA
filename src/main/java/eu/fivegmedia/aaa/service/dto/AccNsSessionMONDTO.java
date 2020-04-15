package eu.fivegmedia.aaa.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AccNsSessionMON entity.
 */
public class AccNsSessionMONDTO implements Serializable {

    private Long id;

    @NotNull
    private Long timestamp;

    @NotNull
    private String qualityProfile;

    @NotNull
    private Integer qoeExpected;

    @NotNull
    private Integer qoeMeasured;

    @NotNull
    private Integer bandwidthUsedMbps;

    private Integer bandwidthAllocatedMbps;

    @NotNull
    private Integer bitrateKbps;

    @NotNull
    private Integer compressionLevel;

    @NotNull
    private Integer entropy;

    @NotNull
    private Integer meanResourceCost;

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

    public String getQualityProfile() {
        return qualityProfile;
    }

    public void setQualityProfile(String qualityProfile) {
        this.qualityProfile = qualityProfile;
    }

    public Integer getQoeExpected() {
        return qoeExpected;
    }

    public void setQoeExpected(Integer qoeExpected) {
        this.qoeExpected = qoeExpected;
    }

    public Integer getQoeMeasured() {
        return qoeMeasured;
    }

    public void setQoeMeasured(Integer qoeMeasured) {
        this.qoeMeasured = qoeMeasured;
    }

    public Integer getBandwidthUsedMbps() {
        return bandwidthUsedMbps;
    }

    public void setBandwidthUsedMbps(Integer bandwidthUsedMbps) {
        this.bandwidthUsedMbps = bandwidthUsedMbps;
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

    public Integer getCompressionLevel() {
        return compressionLevel;
    }

    public void setCompressionLevel(Integer compressionLevel) {
        this.compressionLevel = compressionLevel;
    }

    public Integer getEntropy() {
        return entropy;
    }

    public void setEntropy(Integer entropy) {
        this.entropy = entropy;
    }

    public Integer getMeanResourceCost() {
        return meanResourceCost;
    }

    public void setMeanResourceCost(Integer meanResourceCost) {
        this.meanResourceCost = meanResourceCost;
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

        AccNsSessionMONDTO accNsSessionMONDTO = (AccNsSessionMONDTO) o;
        if (accNsSessionMONDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), accNsSessionMONDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AccNsSessionMONDTO{" +
            "id=" + getId() +
            ", timestamp=" + getTimestamp() +
            ", qualityProfile='" + getQualityProfile() + "'" +
            ", qoeExpected=" + getQoeExpected() +
            ", qoeMeasured=" + getQoeMeasured() +
            ", bandwidthUsedMbps=" + getBandwidthUsedMbps() +
            ", bandwidthAllocatedMbps=" + getBandwidthAllocatedMbps() +
            ", bitrateKbps=" + getBitrateKbps() +
            ", compressionLevel=" + getCompressionLevel() +
            ", entropy=" + getEntropy() +
            ", meanResourceCost=" + getMeanResourceCost() +
            ", accNsSession=" + getAccNsSessionId() +
            "}";
    }
}
