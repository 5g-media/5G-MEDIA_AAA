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
 * A AccNsSession.
 */
@Entity
@Table(name = "acc_ns_session")
public class AccNsSession implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 250)
    @Column(name = "mano_id", length = 250, nullable = false)
    private String manoId;

    @NotNull
    @Size(max = 250)
    @Column(name = "mano_user", length = 250, nullable = false)
    private String manoUser;

    @NotNull
    @Size(max = 250)
    @Column(name = "mano_project", length = 250, nullable = false)
    private String manoProject;

    @NotNull
    @Size(max = 250)
    @Column(name = "ns_id", length = 250, nullable = false)
    private String nsId;

    @NotNull
    @Size(max = 250)
    @Column(name = "ns_name", length = 250, nullable = false)
    private String nsName;

    @NotNull
    @Column(name = "timestamp_start", nullable = false)
    private Long timestampStart;

    @Column(name = "timestamp_stop")
    private Long timestampStop;

    @Column(name = "quality_profile")
    private String qualityProfile;

    @Column(name = "resource_cost")
    private Float resourceCost;

    @Column(name = "optimisation")
    private String optimisation;

    @OneToMany(mappedBy = "accNsSession")
    private Set<AccVnfSession> accVnfSessionSets = new HashSet<>();

    @OneToMany(mappedBy = "accNsSession")
    private Set<AccNsSessionMetrics> accNsSessionMetricsSets = new HashSet<>();

    @OneToMany(mappedBy = "accNsSession")
    private Set<AccNsSessionNetwork> accNsSessionNetworkSets = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("accNsSessionSets")
    private CatalogUser catalogUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManoId() {
        return manoId;
    }

    public AccNsSession manoId(String manoId) {
        this.manoId = manoId;
        return this;
    }

    public void setManoId(String manoId) {
        this.manoId = manoId;
    }

    public String getManoUser() {
        return manoUser;
    }

    public AccNsSession manoUser(String manoUser) {
        this.manoUser = manoUser;
        return this;
    }

    public void setManoUser(String manoUser) {
        this.manoUser = manoUser;
    }

    public String getManoProject() {
        return manoProject;
    }

    public AccNsSession manoProject(String manoProject) {
        this.manoProject = manoProject;
        return this;
    }

    public void setManoProject(String manoProject) {
        this.manoProject = manoProject;
    }

    public String getNsId() {
        return nsId;
    }

    public AccNsSession nsId(String nsId) {
        this.nsId = nsId;
        return this;
    }

    public void setNsId(String nsId) {
        this.nsId = nsId;
    }

    public String getNsName() {
        return nsName;
    }

    public AccNsSession nsName(String nsName) {
        this.nsName = nsName;
        return this;
    }

    public void setNsName(String nsName) {
        this.nsName = nsName;
    }

    public Long getTimestampStart() {
        return timestampStart;
    }

    public AccNsSession timestampStart(Long timestampStart) {
        this.timestampStart = timestampStart;
        return this;
    }

    public void setTimestampStart(Long timestampStart) {
        this.timestampStart = timestampStart;
    }

    public Long getTimestampStop() {
        return timestampStop;
    }

    public AccNsSession timestampStop(Long timestampStop) {
        this.timestampStop = timestampStop;
        return this;
    }

    public void setTimestampStop(Long timestampStop) {
        this.timestampStop = timestampStop;
    }

    public String getQualityProfile() {
        return qualityProfile;
    }

    public AccNsSession qualityProfile(String qualityProfile) {
        this.qualityProfile = qualityProfile;
        return this;
    }

    public void setQualityProfile(String qualityProfile) {
        this.qualityProfile = qualityProfile;
    }

    public Float getResourceCost() {
        return resourceCost;
    }

    public AccNsSession resourceCost(Float resourceCost) {
        this.resourceCost = resourceCost;
        return this;
    }

    public void setResourceCost(Float resourceCost) {
        this.resourceCost = resourceCost;
    }

    public String getOptimisation() {
        return optimisation;
    }

    public AccNsSession optimisation(String optimisation) {
        this.optimisation = optimisation;
        return this;
    }

    public void setOptimisation(String optimisation) {
        this.optimisation = optimisation;
    }

    public Set<AccVnfSession> getAccVnfSessionSets() {
        return accVnfSessionSets;
    }

    public AccNsSession accVnfSessionSets(Set<AccVnfSession> accVnfSessions) {
        this.accVnfSessionSets = accVnfSessions;
        return this;
    }

    public AccNsSession addAccVnfSessionSet(AccVnfSession accVnfSession) {
        this.accVnfSessionSets.add(accVnfSession);
        accVnfSession.setAccNsSession(this);
        return this;
    }

    public AccNsSession removeAccVnfSessionSet(AccVnfSession accVnfSession) {
        this.accVnfSessionSets.remove(accVnfSession);
        accVnfSession.setAccNsSession(null);
        return this;
    }

    public void setAccVnfSessionSets(Set<AccVnfSession> accVnfSessions) {
        this.accVnfSessionSets = accVnfSessions;
    }

    public Set<AccNsSessionMetrics> getAccNsSessionMetricsSets() {
        return accNsSessionMetricsSets;
    }

    public AccNsSession accNsSessionMetricsSets(Set<AccNsSessionMetrics> accNsSessionMetrics) {
        this.accNsSessionMetricsSets = accNsSessionMetrics;
        return this;
    }

    public AccNsSession addAccNsSessionMetricsSet(AccNsSessionMetrics accNsSessionMetrics) {
        this.accNsSessionMetricsSets.add(accNsSessionMetrics);
        accNsSessionMetrics.setAccNsSession(this);
        return this;
    }

    public AccNsSession removeAccNsSessionMetricsSet(AccNsSessionMetrics accNsSessionMetrics) {
        this.accNsSessionMetricsSets.remove(accNsSessionMetrics);
        accNsSessionMetrics.setAccNsSession(null);
        return this;
    }

    public void setAccNsSessionMetricsSets(Set<AccNsSessionMetrics> accNsSessionMetrics) {
        this.accNsSessionMetricsSets = accNsSessionMetrics;
    }

    public Set<AccNsSessionNetwork> getAccNsSessionNetworkSets() {
        return accNsSessionNetworkSets;
    }

    public AccNsSession accNsSessionNetworkSets(Set<AccNsSessionNetwork> accNsSessionNetworks) {
        this.accNsSessionNetworkSets = accNsSessionNetworks;
        return this;
    }

    public AccNsSession addAccNsSessionNetworkSet(AccNsSessionNetwork accNsSessionNetwork) {
        this.accNsSessionNetworkSets.add(accNsSessionNetwork);
        accNsSessionNetwork.setAccNsSession(this);
        return this;
    }

    public AccNsSession removeAccNsSessionNetworkSet(AccNsSessionNetwork accNsSessionNetwork) {
        this.accNsSessionNetworkSets.remove(accNsSessionNetwork);
        accNsSessionNetwork.setAccNsSession(null);
        return this;
    }

    public void setAccNsSessionNetworkSets(Set<AccNsSessionNetwork> accNsSessionNetworks) {
        this.accNsSessionNetworkSets = accNsSessionNetworks;
    }

    public CatalogUser getCatalogUser() {
        return catalogUser;
    }

    public AccNsSession catalogUser(CatalogUser catalogUser) {
        this.catalogUser = catalogUser;
        return this;
    }

    public void setCatalogUser(CatalogUser catalogUser) {
        this.catalogUser = catalogUser;
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
        AccNsSession accNsSession = (AccNsSession) o;
        if (accNsSession.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), accNsSession.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AccNsSession{" +
            "id=" + getId() +
            ", manoId='" + getManoId() + "'" +
            ", manoUser='" + getManoUser() + "'" +
            ", manoProject='" + getManoProject() + "'" +
            ", nsId='" + getNsId() + "'" +
            ", nsName='" + getNsName() + "'" +
            ", timestampStart=" + getTimestampStart() +
            ", timestampStop=" + getTimestampStop() +
            ", qualityProfile='" + getQualityProfile() + "'" +
            ", resourceCost=" + getResourceCost() +
            ", optimisation='" + getOptimisation() + "'" +
            "}";
    }
}
