package eu.fivegmedia.aaa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import eu.fivegmedia.aaa.domain.enumeration.RoleEnum;

/**
 * A CatalogUser.
 */
@Entity
@NamedQuery(name="CatalogUser.findByUsername", query="select c from CatalogUser c where c.user.login = ?")
@Table(name = "catalog_user")
public class CatalogUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 250)
    @Column(name = "name", length = 250, nullable = false)
    private String name;
    
    @Size(max = 250)
    @Column(name = "description", length = 250)
    private String description;
    
    @OneToOne
    private User user;

    @OneToMany(mappedBy = "catalogUser")
    private Set<ResourceUser> resourceUserSets = new HashSet<>();

    @OneToMany(mappedBy = "catalogUser")
    private Set<CatalogToken> catalogTokenSets = new HashSet<>();

    @OneToMany(mappedBy = "catalogUser")
    private Set<AccNsSession> accNsSessionSets = new HashSet<>();

    @ManyToMany(mappedBy = "catalogUserSets")
    @JsonIgnore
    private Set<CatalogTenant> catalogTenantSets = new HashSet<>();

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

    public CatalogUser name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<ResourceUser> getResourceUserSets() {
        return resourceUserSets;
    }

    public CatalogUser resourceUserSets(Set<ResourceUser> resourceUsers) {
        this.resourceUserSets = resourceUsers;
        return this;
    }

    public CatalogUser addResourceUserSet(ResourceUser resourceUser) {
        this.resourceUserSets.add(resourceUser);
        resourceUser.setCatalogUser(this);
        return this;
    }

    public CatalogUser removeResourceUserSet(ResourceUser resourceUser) {
        this.resourceUserSets.remove(resourceUser);
        resourceUser.setCatalogUser(null);
        return this;
    }

    public void setResourceUserSets(Set<ResourceUser> resourceUsers) {
        this.resourceUserSets = resourceUsers;
    }

    public Set<CatalogToken> getCatalogTokenSets() {
        return catalogTokenSets;
    }

    public CatalogUser catalogTokenSets(Set<CatalogToken> catalogTokens) {
        this.catalogTokenSets = catalogTokens;
        return this;
    }

    public CatalogUser addCatalogTokenSet(CatalogToken catalogToken) {
        this.catalogTokenSets.add(catalogToken);
        catalogToken.setCatalogUser(this);
        return this;
    }

    public CatalogUser removeCatalogTokenSet(CatalogToken catalogToken) {
        this.catalogTokenSets.remove(catalogToken);
        catalogToken.setCatalogUser(null);
        return this;
    }

    public void setCatalogTokenSets(Set<CatalogToken> catalogTokens) {
        this.catalogTokenSets = catalogTokens;
    }

    public Set<AccNsSession> getAccNsSessionSets() {
        return accNsSessionSets;
    }

    public CatalogUser accNsSessionSets(Set<AccNsSession> accNsSessions) {
        this.accNsSessionSets = accNsSessions;
        return this;
    }

    public CatalogUser addAccNsSessionSet(AccNsSession accNsSession) {
        this.accNsSessionSets.add(accNsSession);
        accNsSession.setCatalogUser(this);
        return this;
    }

    public CatalogUser removeAccNsSessionSet(AccNsSession accNsSession) {
        this.accNsSessionSets.remove(accNsSession);
        accNsSession.setCatalogUser(null);
        return this;
    }

    public void setAccNsSessionSets(Set<AccNsSession> accNsSessions) {
        this.accNsSessionSets = accNsSessions;
    }

    public Set<CatalogTenant> getCatalogTenantSets() {
        return catalogTenantSets;
    }

    public CatalogUser catalogTenantSets(Set<CatalogTenant> catalogTenants) {
        this.catalogTenantSets = catalogTenants;
        return this;
    }

    public CatalogUser addCatalogTenantSet(CatalogTenant catalogTenant) {
        this.catalogTenantSets.add(catalogTenant);
        catalogTenant.getCatalogUserSets().add(this);
        return this;
    }

    public CatalogUser removeCatalogTenantSet(CatalogTenant catalogTenant) {
        this.catalogTenantSets.remove(catalogTenant);
        catalogTenant.getCatalogUserSets().remove(this);
        return this;
    }

    public void setCatalogTenantSets(Set<CatalogTenant> catalogTenants) {
        this.catalogTenantSets = catalogTenants;
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
        CatalogUser catalogUser = (CatalogUser) o;
        if (catalogUser.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), catalogUser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CatalogUser{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
