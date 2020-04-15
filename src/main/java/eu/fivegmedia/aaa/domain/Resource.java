package eu.fivegmedia.aaa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import eu.fivegmedia.aaa.domain.enumeration.ResourceEnum;

/**
 * A Resource.
 */
@Entity
@Table(name = "resource")
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 250)
    @Column(name = "resourceid", length = 250, nullable = false)
    private String resourceid;

    @NotNull
    @Size(max = 250)
    @Column(name = "name", length = 250, nullable = false)
    private String name;

    @Size(max = 250)
    @Column(name = "url", length = 250)
    private String url;

    @Size(max = 250)
    @Column(name = "auth_driver", length = 250)
    private String authDriver;

    @Size(max = 250)
    @Column(name = "auth_conf", length = 250)
    private String authConf;

    @Column(name = "properties")
    private String properties;

    @Enumerated(EnumType.STRING)
    @Column(name = "resource_enum")
    private ResourceEnum resourceEnum;

    @OneToOne
    @JoinColumn(unique = true)
    private ResourceAdminLogin resourceAdminLogin;

    @OneToMany(mappedBy = "resource")
    private Set<ResourceUser> resourceUserSets = new HashSet<>();

    @OneToMany(mappedBy = "resource")
    private Set<Endpoint> endpointSets = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResourceid() {
        return resourceid;
    }

    public Resource resourceid(String resourceid) {
        this.resourceid = resourceid;
        return this;
    }

    public void setResourceid(String resourceid) {
        this.resourceid = resourceid;
    }

    public String getName() {
        return name;
    }

    public Resource name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public Resource url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthDriver() {
        return authDriver;
    }

    public Resource authDriver(String authDriver) {
        this.authDriver = authDriver;
        return this;
    }

    public void setAuthDriver(String authDriver) {
        this.authDriver = authDriver;
    }

    public String getAuthConf() {
        return authConf;
    }

    public Resource authConf(String authConf) {
        this.authConf = authConf;
        return this;
    }

    public void setAuthConf(String authConf) {
        this.authConf = authConf;
    }

    public String getProperties() {
        return properties;
    }

    public Resource properties(String properties) {
        this.properties = properties;
        return this;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public ResourceEnum getResourceEnum() {
        return resourceEnum;
    }

    public Resource resourceEnum(ResourceEnum resourceEnum) {
        this.resourceEnum = resourceEnum;
        return this;
    }

    public void setResourceEnum(ResourceEnum resourceEnum) {
        this.resourceEnum = resourceEnum;
    }

    public ResourceAdminLogin getResourceAdminLogin() {
        return resourceAdminLogin;
    }

    public Resource resourceAdminLogin(ResourceAdminLogin resourceAdminLogin) {
        this.resourceAdminLogin = resourceAdminLogin;
        return this;
    }

    public void setResourceAdminLogin(ResourceAdminLogin resourceAdminLogin) {
        this.resourceAdminLogin = resourceAdminLogin;
    }

    public Set<ResourceUser> getResourceUserSets() {
        return resourceUserSets;
    }

    public Resource resourceUserSets(Set<ResourceUser> resourceUsers) {
        this.resourceUserSets = resourceUsers;
        return this;
    }

    public Resource addResourceUserSet(ResourceUser resourceUser) {
        this.resourceUserSets.add(resourceUser);
        resourceUser.setResource(this);
        return this;
    }

    public Resource removeResourceUserSet(ResourceUser resourceUser) {
        this.resourceUserSets.remove(resourceUser);
        resourceUser.setResource(null);
        return this;
    }

    public void setResourceUserSets(Set<ResourceUser> resourceUsers) {
        this.resourceUserSets = resourceUsers;
    }

    public Set<Endpoint> getEndpointSets() {
        return endpointSets;
    }

    public Resource endpointSets(Set<Endpoint> endpoints) {
        this.endpointSets = endpoints;
        return this;
    }

    public Resource addEndpointSet(Endpoint endpoint) {
        this.endpointSets.add(endpoint);
        endpoint.setResource(this);
        return this;
    }

    public Resource removeEndpointSet(Endpoint endpoint) {
        this.endpointSets.remove(endpoint);
        endpoint.setResource(null);
        return this;
    }

    public void setEndpointSets(Set<Endpoint> endpoints) {
        this.endpointSets = endpoints;
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
        Resource resource = (Resource) o;
        if (resource.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), resource.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Resource{" +
            "id=" + getId() +
            ", resourceid='" + getResourceid() + "'" +
            ", name='" + getName() + "'" +
            ", url='" + getUrl() + "'" +
            ", authDriver='" + getAuthDriver() + "'" +
            ", authConf='" + getAuthConf() + "'" +
            ", properties='" + getProperties() + "'" +
            ", resourceEnum='" + getResourceEnum() + "'" +
            "}";
    }
}
