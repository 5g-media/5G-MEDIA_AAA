package eu.fivegmedia.aaa.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AccNsSession entity.
 */
public class AccNsSessionDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 250)
    private String manoId;

    @NotNull
    @Size(max = 250)
    private String manoUser;

    @NotNull
    @Size(max = 250)
    private String manoProject;

    @NotNull
    @Size(max = 250)
    private String nsId;

    @NotNull
    @Size(max = 250)
    private String nsName;

    @NotNull
    private Long timestampStart;

    private Long timestampStop;

    private String qualityProfile;

    private Float resourceCost;

    private String optimisation;

    private Long catalogUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManoId() {
        return manoId;
    }

    public void setManoId(String manoId) {
        this.manoId = manoId;
    }

    public String getManoUser() {
        return manoUser;
    }

    public void setManoUser(String manoUser) {
        this.manoUser = manoUser;
    }

    public String getManoProject() {
        return manoProject;
    }

    public void setManoProject(String manoProject) {
        this.manoProject = manoProject;
    }

    public String getNsId() {
        return nsId;
    }

    public void setNsId(String nsId) {
        this.nsId = nsId;
    }

    public String getNsName() {
        return nsName;
    }

    public void setNsName(String nsName) {
        this.nsName = nsName;
    }

    public Long getTimestampStart() {
        return timestampStart;
    }

    public void setTimestampStart(Long timestampStart) {
        this.timestampStart = timestampStart;
    }

    public Long getTimestampStop() {
        return timestampStop;
    }

    public void setTimestampStop(Long timestampStop) {
        this.timestampStop = timestampStop;
    }

    public String getQualityProfile() {
        return qualityProfile;
    }

    public void setQualityProfile(String qualityProfile) {
        this.qualityProfile = qualityProfile;
    }

    public Float getResourceCost() {
        return resourceCost;
    }

    public void setResourceCost(Float resourceCost) {
        this.resourceCost = resourceCost;
    }

    public String getOptimisation() {
        return optimisation;
    }

    public void setOptimisation(String optimisation) {
        this.optimisation = optimisation;
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

        AccNsSessionDTO accNsSessionDTO = (AccNsSessionDTO) o;
        if (accNsSessionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), accNsSessionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AccNsSessionDTO{" +
            "id=" + getId() +
            ", manoId='" + getManoId() + "'" +
            ", manoUser='" + getManoUser() + "'" +
            ", manoProject='" + getManoProject() + "'" +
            ", nsId='" + getNsId() + "'" +
            ", nsName='" + getNsName() + "'" +
            ", timestampStart=" + getTimestampStart() +
            ", timestampStop=" + getTimestampStop() +
            ", qualityProfile='" + getQualityProfile() + "'" +
            ", resourceCost=" + getResourceCost() +
            ", optimisation='" + getOptimisation() + "'" +
            ", catalogUser=" + getCatalogUserId() +
            "}";
    }
}
