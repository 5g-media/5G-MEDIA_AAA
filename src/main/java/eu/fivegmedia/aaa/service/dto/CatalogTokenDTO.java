package eu.fivegmedia.aaa.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CatalogToken entity.
 */
public class CatalogTokenDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 250)
    private String value;

    @Size(max = 250)
    private String type;

    @NotNull
    private Long timestampCreated;

    private Long timestampExpiration;

    private Boolean valid;

    private Long catalogUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getTimestampCreated() {
        return timestampCreated;
    }

    public void setTimestampCreated(Long timestampCreated) {
        this.timestampCreated = timestampCreated;
    }

    public Long getTimestampExpiration() {
        return timestampExpiration;
    }

    public void setTimestampExpiration(Long timestampExpiration) {
        this.timestampExpiration = timestampExpiration;
    }

    public Boolean isValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Long getCatalogUserId() {
        return catalogUserId;
    }

    public void setCatalogUserId(Long catalogUserId) {
        this.catalogUserId = catalogUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CatalogTokenDTO catalogTokenDTO = (CatalogTokenDTO) o;
        if (catalogTokenDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), catalogTokenDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CatalogTokenDTO{" +
            "id=" + getId() +
            ", value='" + getValue() + "'" +
            ", type='" + getType() + "'" +
            ", timestampCreated=" + getTimestampCreated() +
            ", timestampExpiration=" + getTimestampExpiration() +
            ", valid='" + isValid() + "'" +
            ", catalogUser=" + getCatalogUserId() +
            "}";
    }
}
