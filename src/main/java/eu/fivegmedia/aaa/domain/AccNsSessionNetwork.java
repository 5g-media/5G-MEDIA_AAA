package eu.fivegmedia.aaa.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A AccNsSessionNetwork.
 */
@Entity
@Table(name = "acc_ns_session_network")
public class AccNsSessionNetwork implements Serializable {

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
    @Column(name = "max_bandwidth_mbps", nullable = false)
    private Integer maxBandwidthMbps;

    @NotNull
    @Column(name = "used_bandwidth_mbps", nullable = false)
    private String usedBandwidthMbps;

    @NotNull
    @Column(name = "session_length_sec", nullable = false)
    private Integer sessionLengthSec;

    @ManyToOne
    @JsonIgnoreProperties("accNsSessionNetworkSets")
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

    public AccNsSessionNetwork timestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getNfvipopId() {
        return nfvipopId;
    }

    public AccNsSessionNetwork nfvipopId(String nfvipopId) {
        this.nfvipopId = nfvipopId;
        return this;
    }

    public void setNfvipopId(String nfvipopId) {
        this.nfvipopId = nfvipopId;
    }

    public Integer getMaxBandwidthMbps() {
        return maxBandwidthMbps;
    }

    public AccNsSessionNetwork maxBandwidthMbps(Integer maxBandwidthMbps) {
        this.maxBandwidthMbps = maxBandwidthMbps;
        return this;
    }

    public void setMaxBandwidthMbps(Integer maxBandwidthMbps) {
        this.maxBandwidthMbps = maxBandwidthMbps;
    }

    public String getUsedBandwidthMbps() {
        return usedBandwidthMbps;
    }

    public AccNsSessionNetwork usedBandwidthMbps(String usedBandwidthMbps) {
        this.usedBandwidthMbps = usedBandwidthMbps;
        return this;
    }

    public void setUsedBandwidthMbps(String usedBandwidthMbps) {
        this.usedBandwidthMbps = usedBandwidthMbps;
    }

    public Integer getSessionLengthSec() {
        return sessionLengthSec;
    }

    public AccNsSessionNetwork sessionLengthSec(Integer sessionLengthSec) {
        this.sessionLengthSec = sessionLengthSec;
        return this;
    }

    public void setSessionLengthSec(Integer sessionLengthSec) {
        this.sessionLengthSec = sessionLengthSec;
    }

    public AccNsSession getAccNsSession() {
        return accNsSession;
    }

    public AccNsSessionNetwork accNsSession(AccNsSession accNsSession) {
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
        AccNsSessionNetwork accNsSessionNetwork = (AccNsSessionNetwork) o;
        if (accNsSessionNetwork.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), accNsSessionNetwork.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AccNsSessionNetwork{" +
            "id=" + getId() +
            ", timestamp=" + getTimestamp() +
            ", nfvipopId='" + getNfvipopId() + "'" +
            ", maxBandwidthMbps=" + getMaxBandwidthMbps() +
            ", usedBandwidthMbps='" + getUsedBandwidthMbps() + "'" +
            ", sessionLengthSec=" + getSessionLengthSec() +
            "}";
    }
}
