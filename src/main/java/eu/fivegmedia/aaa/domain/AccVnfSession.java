package eu.fivegmedia.aaa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A AccVnfSession.
 */
@Entity
@Table(name = "acc_vnf_session")
public class AccVnfSession implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 250)
    @Column(name = "vnf_id", length = 250, nullable = false)
    private String vnfId;

    @NotNull
    @Size(max = 250)
    @Column(name = "vnf_name", length = 250, nullable = false)
    private String vnfName;

    @NotNull
    @Column(name = "timestamp_start", nullable = false)
    private Long timestampStart;

    @Column(name = "timestamp_stop")
    private Long timestampStop;

    @OneToMany(mappedBy = "accVnfSession")
    private Set<AccVduSession> accVduSessionSets = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("accVnfSessionSets")
    private AccNsSession accNsSession;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVnfId() {
        return vnfId;
    }

    public AccVnfSession vnfId(String vnfId) {
        this.vnfId = vnfId;
        return this;
    }

    public void setVnfId(String vnfId) {
        this.vnfId = vnfId;
    }

    public String getVnfName() {
        return vnfName;
    }

    public AccVnfSession vnfName(String vnfName) {
        this.vnfName = vnfName;
        return this;
    }

    public void setVnfName(String vnfName) {
        this.vnfName = vnfName;
    }

    public Long getTimestampStart() {
        return timestampStart;
    }

    public AccVnfSession timestampStart(Long timestampStart) {
        this.timestampStart = timestampStart;
        return this;
    }

    public void setTimestampStart(Long timestampStart) {
        this.timestampStart = timestampStart;
    }

    public Long getTimestampStop() {
        return timestampStop;
    }

    public AccVnfSession timestampStop(Long timestampStop) {
        this.timestampStop = timestampStop;
        return this;
    }

    public void setTimestampStop(Long timestampStop) {
        this.timestampStop = timestampStop;
    }

    public Set<AccVduSession> getAccVduSessionSets() {
        return accVduSessionSets;
    }

    public AccVnfSession accVduSessionSets(Set<AccVduSession> accVduSessions) {
        this.accVduSessionSets = accVduSessions;
        return this;
    }

    public AccVnfSession addAccVduSessionSet(AccVduSession accVduSession) {
        this.accVduSessionSets.add(accVduSession);
        accVduSession.setAccVnfSession(this);
        return this;
    }

    public AccVnfSession removeAccVduSessionSet(AccVduSession accVduSession) {
        this.accVduSessionSets.remove(accVduSession);
        accVduSession.setAccVnfSession(null);
        return this;
    }

    public void setAccVduSessionSets(Set<AccVduSession> accVduSessions) {
        this.accVduSessionSets = accVduSessions;
    }

    public AccNsSession getAccNsSession() {
        return accNsSession;
    }

    public AccVnfSession accNsSession(AccNsSession accNsSession) {
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
        AccVnfSession accVnfSession = (AccVnfSession) o;
        if (accVnfSession.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), accVnfSession.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AccVnfSession{" +
            "id=" + getId() +
            ", vnfId='" + getVnfId() + "'" +
            ", vnfName='" + getVnfName() + "'" +
            ", timestampStart=" + getTimestampStart() +
            ", timestampStop=" + getTimestampStop() +
            "}";
    }
}
