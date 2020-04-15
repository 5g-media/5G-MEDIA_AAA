package eu.fivegmedia.aaa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A ResourceUser.
 */
@Entity
@NamedQuery(name="ResourceUser.findByResourceEnum", query="select r from ResourceUser r where r.resource.resourceEnum = ?")
@Table(name = "resource_user")
public class ResourceUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 250)
    @Column(name = "name", length = 250, nullable = false)
    private String name;

    @Size(max = 250)
    @Column(name = "tenant", length = 250)
    private String tenant;

    @NotNull
    @Size(max = 250)
    @Column(name = "permissions", length = 250, nullable = false)
    private String permissions;
    
    @Size(max = 250)
    @Column(name = "groupname", length = 250)
    private String groupname;

    @Column(name = "enabled")
    private Boolean enabled;

    @OneToOne
    @JoinColumn(unique = true)
    private ResourceUserLogin resourceUserLogin;

    @OneToMany(mappedBy = "resourceUser")
    private Set<ResourceToken> resourceTokenSets = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("resourceUserSets")
    private Resource resource;

    @ManyToOne
    @JsonIgnoreProperties("resourceUserSets")
    private CatalogUser catalogUser;

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

    public ResourceUser name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTenant() {
        return tenant;
    }

    public ResourceUser tenant(String tenant) {
        this.tenant = tenant;
        return this;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }
    
    public String getGroupname() {
        return groupname;
    }

    public ResourceUser groupname(String groupname) {
        this.groupname = groupname;
        return this;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getPermissions() {
        return permissions;
    }

    public ResourceUser permissions(String permissions) {
        this.permissions = permissions;
        return this;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public ResourceUser enabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public ResourceUserLogin getResourceUserLogin() {
        return resourceUserLogin;
    }

    public ResourceUser resourceUserLogin(ResourceUserLogin resourceUserLogin) {
        this.resourceUserLogin = resourceUserLogin;
        return this;
    }

    public void setResourceUserLogin(ResourceUserLogin resourceUserLogin) {
        this.resourceUserLogin = resourceUserLogin;
    }

    public Set<ResourceToken> getResourceTokenSets() {
        return resourceTokenSets;
    }

    public ResourceUser resourceTokenSets(Set<ResourceToken> resourceTokens) {
        this.resourceTokenSets = resourceTokens;
        return this;
    }

    public ResourceUser addResourceTokenSet(ResourceToken resourceToken) {
        this.resourceTokenSets.add(resourceToken);
        resourceToken.setResourceUser(this);
        return this;
    }

    public ResourceUser removeResourceTokenSet(ResourceToken resourceToken) {
        this.resourceTokenSets.remove(resourceToken);
        resourceToken.setResourceUser(null);
        return this;
    }

    public void setResourceTokenSets(Set<ResourceToken> resourceTokens) {
        this.resourceTokenSets = resourceTokens;
    }

    public Resource getResource() {
        return resource;
    }

    public ResourceUser resource(Resource resource) {
        this.resource = resource;
        return this;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public CatalogUser getCatalogUser() {
        return catalogUser;
    }

    public ResourceUser catalogUser(CatalogUser catalogUser) {
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
        ResourceUser resourceUser = (ResourceUser) o;
        if (resourceUser.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), resourceUser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ResourceUser{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", tenant='" + getTenant() + "'" +
            ", permissions='" + getPermissions() + "'" +
            ", groupname='" + getGroupname() + "'" +
            ", enabled='" + isEnabled() + "'" +
            "}";
    }
}
