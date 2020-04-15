package eu.fivegmedia.aaa.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A AccNsSessionMetrics.
 */
@Entity
@Table(name = "acc_ns_session_metrics")
public class AccNsSessionMetrics implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "jhi_timestamp", nullable = false)
    private Long timestamp;

    @NotNull
    @Column(name = "nfvipop_id", nullable = false)
    private String nfvipopId;

    @NotNull
    @Column(name = "session_length_sec", nullable = false)
    private Integer sessionLengthSec;

    @NotNull
    @Column(name = "quality_profile", nullable = false)
    private String qualityProfile;

    @NotNull
    @Column(name = "expected_qoe", nullable = false)
    private Double expectedQoe;

    @NotNull
    @Column(name = "measured_qoe", nullable = false)
    private Double measuredQoe;

    @NotNull
    @Column(name = "entropy_ti", nullable = false)
    private Integer entropyTI;

    @NotNull
    @Column(name = "entropy_si", nullable = false)
    private Integer entropySI;

    @NotNull
    @Column(name = "compression_level", nullable = false)
    private Integer compressionLevel;

    @Column(name = "bandwidth_allocated_mbps")
    private Integer bandwidthAllocatedMbps;

    @NotNull
    @Column(name = "bitrate_kbps", nullable = false)
    private Integer bitrateKbps;

    @NotNull
    @Column(name = "blur", nullable = false)
    private Integer blur;

    @Column(name = "user_count")
    private Integer userCount;

    @ManyToOne
    @JsonIgnoreProperties("accNsSessionMetricsSets")
    private AccNsSession accNsSession;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public AccNsSessionMetrics timestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getNfvipopId() {
        return nfvipopId;
    }

    public AccNsSessionMetrics nfvipopId(String nfvipopId) {
        this.nfvipopId = nfvipopId;
        return this;
    }

    public void setNfvipopId(String nfvipopId) {
        this.nfvipopId = nfvipopId;
    }

    public Integer getSessionLengthSec() {
        return sessionLengthSec;
    }

    public AccNsSessionMetrics sessionLengthSec(Integer sessionLengthSec) {
        this.sessionLengthSec = sessionLengthSec;
        return this;
    }

    public void setSessionLengthSec(Integer sessionLengthSec) {
        this.sessionLengthSec = sessionLengthSec;
    }

    public String getQualityProfile() {
        return qualityProfile;
    }

    public AccNsSessionMetrics qualityProfile(String qualityProfile) {
        this.qualityProfile = qualityProfile;
        return this;
    }

    public void setQualityProfile(String qualityProfile) {
        this.qualityProfile = qualityProfile;
    }

    public Double getExpectedQoe() {
        return expectedQoe;
    }

    public AccNsSessionMetrics expectedQoe(Double expectedQoe) {
        this.expectedQoe = expectedQoe;
        return this;
    }

    public void setExpectedQoe(Double expectedQoe) {
        this.expectedQoe = expectedQoe;
    }

    public Double getMeasuredQoe() {
        return measuredQoe;
    }

    public AccNsSessionMetrics measuredQoe(Double measuredQoe) {
        this.measuredQoe = measuredQoe;
        return this;
    }

    public void setMeasuredQoe(Double measuredQoe) {
        this.measuredQoe = measuredQoe;
    }

    public Integer getEntropyTI() {
        return entropyTI;
    }

    public AccNsSessionMetrics entropyTI(Integer entropyTI) {
        this.entropyTI = entropyTI;
        return this;
    }

    public void setEntropyTI(Integer entropyTI) {
        this.entropyTI = entropyTI;
    }

    public Integer getEntropySI() {
        return entropySI;
    }

    public AccNsSessionMetrics entropySI(Integer entropySI) {
        this.entropySI = entropySI;
        return this;
    }

    public void setEntropySI(Integer entropySI) {
        this.entropySI = entropySI;
    }

    public Integer getCompressionLevel() {
        return compressionLevel;
    }

    public AccNsSessionMetrics compressionLevel(Integer compressionLevel) {
        this.compressionLevel = compressionLevel;
        return this;
    }

    public void setCompressionLevel(Integer compressionLevel) {
        this.compressionLevel = compressionLevel;
    }

    public Integer getBandwidthAllocatedMbps() {
        return bandwidthAllocatedMbps;
    }

    public AccNsSessionMetrics bandwidthAllocatedMbps(Integer bandwidthAllocatedMbps) {
        this.bandwidthAllocatedMbps = bandwidthAllocatedMbps;
        return this;
    }

    public void setBandwidthAllocatedMbps(Integer bandwidthAllocatedMbps) {
        this.bandwidthAllocatedMbps = bandwidthAllocatedMbps;
    }

    public Integer getBitrateKbps() {
        return bitrateKbps;
    }

    public AccNsSessionMetrics bitrateKbps(Integer bitrateKbps) {
        this.bitrateKbps = bitrateKbps;
        return this;
    }

    public void setBitrateKbps(Integer bitrateKbps) {
        this.bitrateKbps = bitrateKbps;
    }

    public Integer getBlur() {
        return blur;
    }

    public AccNsSessionMetrics blur(Integer blur) {
        this.blur = blur;
        return this;
    }

    public void setBlur(Integer blur) {
        this.blur = blur;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public AccNsSessionMetrics userCount(Integer userCount) {
        this.userCount = userCount;
        return this;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public AccNsSession getAccNsSession() {
        return accNsSession;
    }

    public AccNsSessionMetrics accNsSession(AccNsSession accNsSession) {
        this.accNsSession = accNsSession;
        return this;
    }

    public void setAccNsSession(AccNsSession accNsSession) {
        this.accNsSession = accNsSession;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccNsSessionMetrics accNsSessionMetrics = (AccNsSessionMetrics) o;
        if (accNsSessionMetrics.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), accNsSessionMetrics.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AccNsSessionMetrics{" +
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
            "}";
    }
}
