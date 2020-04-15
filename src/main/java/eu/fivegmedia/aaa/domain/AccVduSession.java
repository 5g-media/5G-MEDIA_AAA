package eu.fivegmedia.aaa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import eu.fivegmedia.aaa.domain.enumeration.VduTypeEnum;

/**
 * A AccVduSession.
 */
@Entity
@Table(name = "acc_vdu_session")
public class AccVduSession implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 250)
    @Column(name = "vdu_id", length = 250, nullable = false)
    private String vduId;

    @NotNull
    @Size(max = 250)
    @Column(name = "nfvipop_id", length = 250, nullable = false)
    private String nfvipopId;

    @Column(name = "flavor_cpu_count")
    private int flavorCpuCount;

    @Column(name = "flavor_memory_mb")
    private int flavorMemoryMb;

    @Column(name = "flavor_disk_gb")
    private int flavorDiskGb;
    
    @NotNull
    @Column(name = "timestamp_start", nullable = false)
    private Long timestampStart;

    @Column(name = "timestamp_stop")
    private Long timestampStop;

    @Enumerated(EnumType.STRING)
    @Column(name = "vdu_type_enum")
    private VduTypeEnum vduTypeEnum;

    @OneToMany(mappedBy = "accVduSession")
    private Set<AccVduResourceConsumption> accVduResourceConsumptionSets = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("accVduSessionSets")
    private AccVnfSession accVnfSession;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVduId() {
        return vduId;
    }

    public AccVduSession vduId(String vduId) {
        this.vduId = vduId;
        return this;
    }

    public void setVduId(String vduId) {
        this.vduId = vduId;
    }

    public String getNfvipopId() {
        return nfvipopId;
    }

    public AccVduSession nfvipopId(String nfvipopId) {
        this.nfvipopId = nfvipopId;
        return this;
    }

    public void setNfvipopId(String nfvipopId) {
        this.nfvipopId = nfvipopId;
    }

    public int getFlavorCpuCount() {
        return flavorCpuCount;
    }

    public AccVduSession flavorCpuCount(int flavorCpuCount) {
        this.flavorCpuCount = flavorCpuCount;
        return this;
    }

    public void setFlavorCpuCount(int flavorCpuCount) {
        this.flavorCpuCount = flavorCpuCount;
    }

    public int getFlavorMemoryMb() {
        return flavorMemoryMb;
    }

    public AccVduSession flavorMemoryMb(int flavorMemoryMb) {
        this.flavorMemoryMb = flavorMemoryMb;
        return this;
    }

    public void setFlavorMemoryMb(int flavorMemoryMb) {
        this.flavorMemoryMb = flavorMemoryMb;
    }

    public int getFlavorDiskGb() {
        return flavorDiskGb;
    }

    public AccVduSession flavorDiskGb(int flavorDiskGb) {
        this.flavorDiskGb = flavorDiskGb;
        return this;
    }

    public void setFlavorDiskGb(int flavorDiskGb) {
        this.flavorDiskGb = flavorDiskGb;
    }

    public Long getTimestampStart() {
        return timestampStart;
    }

    public AccVduSession timestampStart(Long timestampStart) {
        this.timestampStart = timestampStart;
        return this;
    }

    public void setTimestampStart(Long timestampStart) {
        this.timestampStart = timestampStart;
    }

    public Long getTimestampStop() {
        return timestampStop;
    }

    public AccVduSession timestampStop(Long timestampStop) {
        this.timestampStop = timestampStop;
        return this;
    }

    public void setTimestampStop(Long timestampStop) {
        this.timestampStop = timestampStop;
    }

    public VduTypeEnum getVduTypeEnum() {
        return vduTypeEnum;
    }

    public AccVduSession vduTypeEnum(VduTypeEnum vduTypeEnum) {
        this.vduTypeEnum = vduTypeEnum;
        return this;
    }

    public void setVduTypeEnum(VduTypeEnum vduTypeEnum) {
        this.vduTypeEnum = vduTypeEnum;
    }

    public Set<AccVduResourceConsumption> getAccVduResourceConsumptionSets() {
        return accVduResourceConsumptionSets;
    }

    public AccVduSession accVduResourceConsumptionSets(Set<AccVduResourceConsumption> accVduResourceConsumptions) {
        this.accVduResourceConsumptionSets = accVduResourceConsumptions;
        return this;
    }

    public AccVduSession addAccVduResourceConsumptionSet(AccVduResourceConsumption accVduResourceConsumption) {
        this.accVduResourceConsumptionSets.add(accVduResourceConsumption);
        accVduResourceConsumption.setAccVduSession(this);
        return this;
    }

    public AccVduSession removeAccVduResourceConsumptionSet(AccVduResourceConsumption accVduResourceConsumption) {
        this.accVduResourceConsumptionSets.remove(accVduResourceConsumption);
        accVduResourceConsumption.setAccVduSession(null);
        return this;
    }

    public void setAccVduResourceConsumptionSets(Set<AccVduResourceConsumption> accVduResourceConsumptions) {
        this.accVduResourceConsumptionSets = accVduResourceConsumptions;
    }

    public AccVnfSession getAccVnfSession() {
        return accVnfSession;
    }

    public AccVduSession accVnfSession(AccVnfSession accVnfSession) {
        this.accVnfSession = accVnfSession;
        return this;
    }

    public void setAccVnfSession(AccVnfSession accVnfSession) {
        this.accVnfSession = accVnfSession;
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
        AccVduSession accVduSession = (AccVduSession) o;
        if (accVduSession.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), accVduSession.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AccVduSession{" +
            "id=" + getId() +
            ", vduId='" + getVduId() + "'" +
            ", nfvipopId='" + getNfvipopId() + "'" +
            ", flavorCpuCount='" + getFlavorCpuCount() + "'" +
            ", flavorMemoryMb='" + getFlavorMemoryMb() + "'" +
            ", flavorDiskGb='" + getFlavorDiskGb() + "'" +
            ", timestampStart=" + getTimestampStart() +
            ", timestampStop=" + getTimestampStop() +
            ", vduTypeEnum='" + getVduTypeEnum() + "'" +
            "}";
    }
}
