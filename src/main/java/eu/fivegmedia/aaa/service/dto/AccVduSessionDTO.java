package eu.fivegmedia.aaa.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import eu.fivegmedia.aaa.domain.enumeration.VduTypeEnum;

/**
 * A DTO for the AccVduSession entity.
 */
public class AccVduSessionDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 250)
    private String vduId;

    @NotNull
    @Size(max = 250)
    private String nfvipopId;

    private int flavorCpuCount;
    private int flavorMemoryMb;
    private int flavorDiskGb;

    @NotNull
    private Long timestampStart;

    private Long timestampStop;

    private VduTypeEnum vduTypeEnum;

    private Long accVnfSessionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVduId() {
        return vduId;
    }

    public void setVduId(String vduId) {
        this.vduId = vduId;
    }

    public String getNfvipopId() {
        return nfvipopId;
    }

    public void setNfvipopId(String nfvipopId) {
        this.nfvipopId = nfvipopId;
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

    public VduTypeEnum getVduTypeEnum() {
        return vduTypeEnum;
    }

    public void setVduTypeEnum(VduTypeEnum vduTypeEnum) {
        this.vduTypeEnum = vduTypeEnum;
    }

    public Long getAccVnfSessionId() {
        return accVnfSessionId;
    }

    public void setAccVnfSessionId(Long accVnfSessionId) {
        this.accVnfSessionId = accVnfSessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AccVduSessionDTO accVduSessionDTO = (AccVduSessionDTO) o;
        if (accVduSessionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), accVduSessionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AccVduSessionDTO{" +
            "id=" + getId() +
            ", vduId='" + getVduId() + "'" +
            ", nfvipopId='" + getNfvipopId() + "'" +
            ", flavorCpuCount='" + getFlavorCpuCount() + "'" +
            ", flavorMemoryMb='" + getFlavorMemoryMb() + "'" +
            ", flavorDiskGb='" + getFlavorDiskGb() + "'" +
            ", timestampStart=" + getTimestampStart() +
            ", timestampStop=" + getTimestampStop() +
            ", vduTypeEnum='" + getVduTypeEnum() + "'" +
            ", accVnfSession=" + getAccVnfSessionId() +
            "}";
    }
}
