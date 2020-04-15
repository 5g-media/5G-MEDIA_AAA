package eu.fivegmedia.aaa.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ResourceToken.
 */
@Entity
@Table(name = "resource_token")
public class ResourceToken implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 250)
    @Column(name = "jhi_value", length = 250)
    private String value;

    @Size(max = 250)
    @Column(name = "jhi_type", length = 250)
    private String type;

    @NotNull
    @Column(name = "timestamp_created", nullable = false)
    private Long timestampCreated;

    @Column(name = "timestamp_expiration")
    private Long timestampExpiration;

    @Column(name = "valid")
    private Boolean valid;

    @ManyToOne
    @JsonIgnoreProperties("resourceTokenSets")
    private ResourceUser resourceUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public ResourceToken value(String value) {
        this.value = value;
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public ResourceToken type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getTimestampCreated() {
        return timestampCreated;
    }

    public ResourceToken timestampCreated(Long timestampCreated) {
        this.timestampCreated = timestampCreated;
        return this;
    }

    public void setTimestampCreated(Long timestampCreated) {
        this.timestampCreated = timestampCreated;
    }

    public Long getTimestampExpiration() {
        return timestampExpiration;
    }

    public ResourceToken timestampExpiration(Long timestampExpiration) {
        this.timestampExpiration = timestampExpiration;
        return this;
    }

    public void setTimestampExpiration(Long timestampExpiration) {
        this.timestampExpiration = timestampExpiration;
    }

    public Boolean isValid() {
        return valid;
    }

    public ResourceToken valid(Boolean valid) {
        this.valid = valid;
        return this;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public ResourceUser getResourceUser() {
        return resourceUser;
    }

    public ResourceToken resourceUser(ResourceUser resourceUser) {
        this.resourceUser = resourceUser;
        return this;
    }

    public void setResourceUser(ResourceUser resourceUser) {
        this.resourceUser = resourceUser;
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
        ResourceToken resourceToken = (ResourceToken) o;
        if (resourceToken.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), resourceToken.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ResourceToken{" +
            "id=" + getId() +
            ", value='" + getValue() + "'" +
            ", type='" + getType() + "'" +
            ", timestampCreated=" + getTimestampCreated() +
            ", timestampExpiration=" + getTimestampExpiration() +
            ", valid='" + isValid() + "'" +
            "}";
    }
}
