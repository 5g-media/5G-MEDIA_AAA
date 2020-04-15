package eu.fivegmedia.aaa.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ResourceAdminLogin.
 */
@Entity
@Table(name = "resource_admin_login")
public class ResourceAdminLogin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 250)
    @Column(name = "username", length = 250)
    private String username;

    @Size(max = 250)
    @Column(name = "jhi_password", length = 250)
    private String password;

    @Size(max = 500)
    @Column(name = "public_key", length = 500)
    private String publicKey;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public ResourceAdminLogin username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public ResourceAdminLogin password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public ResourceAdminLogin publicKey(String publicKey) {
        this.publicKey = publicKey;
        return this;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
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
        ResourceAdminLogin resourceAdminLogin = (ResourceAdminLogin) o;
        if (resourceAdminLogin.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), resourceAdminLogin.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ResourceAdminLogin{" +
            "id=" + getId() +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", publicKey='" + getPublicKey() + "'" +
            "}";
    }
}
