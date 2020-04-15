package eu.fivegmedia.aaa.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Policy.
 */
@Entity
@Table(name = "policy")
public class Policy implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 250)
    @Column(name = "name", length = 250, nullable = false)
    private String name;

    @NotNull
    @Size(max = 250)
    @Column(name = "policy", length = 250, nullable = false)
    private String policy;

    @ManyToOne
    @JsonIgnoreProperties("policySets")
    private CatalogTenant catalogTenant;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Policy name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPolicy() {
        return policy;
    }

    public Policy policy(String policy) {
        this.policy = policy;
        return this;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public CatalogTenant getCatalogTenant() {
        return catalogTenant;
    }

    public Policy catalogTenant(CatalogTenant catalogTenant) {
        this.catalogTenant = catalogTenant;
        return this;
    }

    public void setCatalogTenant(CatalogTenant catalogTenant) {
        this.catalogTenant = catalogTenant;
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
        Policy policy = (Policy) o;
        if (policy.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), policy.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Policy{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", policy='" + getPolicy() + "'" +
            "}";
    }
}
