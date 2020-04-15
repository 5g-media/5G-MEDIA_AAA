package eu.fivegmedia.aaa.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ResourceUserLogin entity.
 */
public class ResourceUserLoginDTO implements Serializable {

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

        ResourceUserLoginDTO resourceUserLoginDTO = (ResourceUserLoginDTO) o;
        if (resourceUserLoginDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), resourceUserLoginDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ResourceUserLoginDTO{" +
            "id=" + getId() +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", publicKey='" + getPublicKey() + "'" +
            "}";
    }
}
