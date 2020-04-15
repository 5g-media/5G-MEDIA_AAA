package eu.fivegmedia.aaa.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import eu.fivegmedia.aaa.domain.enumeration.VduResourceEnum;

/**
 * A AccVduResourceConsumption.
 */
@Entity
@Table(name = "acc_vdu_resource_consumption")
public class AccVduResourceConsumption implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "jhi_value", nullable = false)
    private Long value;

    @NotNull
    @Column(name = "jhi_timestamp", nullable = false)
    private Long timestamp;

    @Enumerated(EnumType.STRING)
    @Column(name = "resource_type")
    private VduResourceEnum resourceType;

    @ManyToOne
    @JsonIgnoreProperties("accVduResourceConsumptionSets")
    private AccVduSession accVduSession;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getValue() {
        return value;
    }

    public AccVduResourceConsumption value(Long value) {
        this.value = value;
        return this;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public AccVduResourceConsumption timestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public VduResourceEnum getResourceType() {
        return resourceType;
    }

    public AccVduResourceConsumption resourceType(VduResourceEnum resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    public void setResourceType(VduResourceEnum resourceType) {
        this.resourceType = resourceType;
    }

    public AccVduSession getAccVduSession() {
        return accVduSession;
    }

    public AccVduResourceConsumption accVduSession(AccVduSession accVduSession) {
        this.accVduSession = accVduSession;
        return this;
    }

    public void setAccVduSession(AccVduSession accVduSession) {
        this.accVduSession = accVduSession;
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
        AccVduResourceConsumption accVduResourceConsumption = (AccVduResourceConsumption) o;
        if (accVduResourceConsumption.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), accVduResourceConsumption.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AccVduResourceConsumption{" +
            "id=" + getId() +
            ", value=" + getValue() +
            ", timestamp=" + getTimestamp() +
            ", resourceType='" + getResourceType() + "'" +
            "}";
    }
}
