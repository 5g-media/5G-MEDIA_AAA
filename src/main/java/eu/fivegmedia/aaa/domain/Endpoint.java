package eu.fivegmedia.aaa.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Endpoint.
 */
@Entity
@Table(name = "endpoint")
public class Endpoint implements Serializable {

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
    @Column(name = "url", length = 250, nullable = false)
    private String url;

    @NotNull
    @Size(max = 250)
    @Column(name = "method", length = 250, nullable = false)
    private String method;

    @Column(name = "properties")
    private String properties;

    @ManyToOne
    @JsonIgnoreProperties("endpointSets")
    private Resource resource;

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

    public Endpoint name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public Endpoint url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public Endpoint method(String method) {
        this.method = method;
        return this;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getProperties() {
        return properties;
    }

    public Endpoint properties(String properties) {
        this.properties = properties;
        return this;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public Resource getResource() {
        return resource;
    }

    public Endpoint resource(Resource resource) {
        this.resource = resource;
        return this;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
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
        Endpoint endpoint = (Endpoint) o;
        if (endpoint.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), endpoint.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Endpoint{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", url='" + getUrl() + "'" +
            ", method='" + getMethod() + "'" +
            ", properties='" + getProperties() + "'" +
            "}";
    }
}
