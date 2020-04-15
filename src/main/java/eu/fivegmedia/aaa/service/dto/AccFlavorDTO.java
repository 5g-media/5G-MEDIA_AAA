package eu.fivegmedia.aaa.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AccFlavor entity.
 */
public class AccFlavorDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 250)
    private String manoId;

    private int flavorCpuCount;
    private int flavorMemoryMb;
    private int flavorDiskGb;

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

    public int getFlavorCpuCount() {
        return flavorCpuCount;
    }

    public void setFlavorCpuCount(int flavorCpuCount) {
        this.flavorCpuCount = flavorCpuCount;
    }
    

    public int getFlavorMemoryMb() {
        return flavorMemoryMb;
    }

    public void setFlavorMemoryMb(int flavorMemoryMb) {
        this.flavorMemoryMb = flavorMemoryMb;
    }


    public int getFlavorDiskGb() {
        return flavorDiskGb;
    }

    public void setFlavorDiskGb(int flavorDiskGb) {
        this.flavorDiskGb = flavorDiskGb;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AccFlavorDTO accFlavorDTO = (AccFlavorDTO) o;
        if (accFlavorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), accFlavorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AccFlavorDTO{" +
            "id=" + getId() +
            ", manoId='" + getManoId() + "'" +
            ", flavorCpuCount='" + getFlavorCpuCount() + "'" +
            ", flavorMemoryMb='" + getFlavorMemoryMb() + "'" +
            ", flavorDiskGb='" + getFlavorDiskGb() + "'" +
            "}";
    }
}
