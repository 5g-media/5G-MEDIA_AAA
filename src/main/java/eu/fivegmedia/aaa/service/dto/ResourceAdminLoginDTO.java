package eu.fivegmedia.aaa.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ResourceAdminLogin entity.
 */
public class ResourceAdminLoginDTO implements Serializable {

    private Long id;

    @Size(max = 250)
    private String username;

    @Size(max = 250)
    private String password;

    @Size(max = 500)
    private String publicKey;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ResourceAdminLoginDTO resourceAdminLoginDTO = (ResourceAdminLoginDTO) o;
        if (resourceAdminLoginDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), resourceAdminLoginDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ResourceAdminLoginDTO{" +
            "id=" + getId() +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", publicKey='" + getPublicKey() + "'" +
            "}";
    }
}
