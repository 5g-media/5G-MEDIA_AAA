package eu.fivegmedia.aaa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A CatalogTenant.
 */
@Entity
@Table(name = "catalog_tenant")
public class CatalogTenant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 250)
    @Column(name = "name", length = 250, nullable = false)
    private String name;

    @OneToMany(mappedBy = "catalogTenant")
    private Set<Policy> policySets = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "catalog_tenant_catalog_user_set",
               joinColumns = @JoinColumn(name = "catalog_tenants_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "catalog_user_sets_id", referencedColumnName = "id"))
    private Set<CatalogUser> catalogUserSets = new HashSet<>();

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

    public CatalogTenant name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Policy> getPolicySets() {
        return policySets;
    }

    public CatalogTenant policySets(Set<Policy> policies) {
        this.policySets = policies;
        return this;
    }

    public CatalogTenant addPolicySet(Policy policy) {
        this.policySets.add(policy);
        policy.setCatalogTenant(this);
        return this;
    }

    public CatalogTenant removePolicySet(Policy policy) {
        this.policySets.remove(policy);
        policy.setCatalogTenant(null);
        return this;
    }

    public void setPolicySets(Set<Policy> policies) {
        this.policySets = policies;
    }

    public Set<CatalogUser> getCatalogUserSets() {
        return catalogUserSets;
    }

    public CatalogTenant catalogUserSets(Set<CatalogUser> catalogUsers) {
        this.catalogUserSets = catalogUsers;
        return this;
    }

    public CatalogTenant addCatalogUserSet(CatalogUser catalogUser) {
        this.catalogUserSets.add(catalogUser);
        catalogUser.getCatalogTenantSets().add(this);
        return this;
    }

    public CatalogTenant removeCatalogUserSet(CatalogUser catalogUser) {
        this.catalogUserSets.remove(catalogUser);
        catalogUser.getCatalogTenantSets().remove(this);
        return this;
    }

    public void setCatalogUserSets(Set<CatalogUser> catalogUsers) {
        this.catalogUserSets = catalogUsers;
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
        CatalogTenant catalogTenant = (CatalogTenant) o;
        if (catalogTenant.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), catalogTenant.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CatalogTenant{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
