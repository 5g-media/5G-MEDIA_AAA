package eu.fivegmedia.aaa.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ResourceCost entity.
 */
public class ResourceCostDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String nfvipopId;

    @NotNull
    private String billing;

    @NotNull
    private Integer cpu;

    @NotNull
    private Integer memoryGB;

    @NotNull
    private Integer diskGB;

    @NotNull
    private Integer gpu;

    @NotNull
    private Integer trafficGB;

    @NotNull
    private Float totalCost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNfvipopId() {
        return nfvipopId;
    }

    public void setNfvipopId(String nfvipopId) {
        this.nfvipopId = nfvipopId;
    }

    public String getBilling() {
        return billing;
    }

    public void setBilling(String billing) {
        this.billing = billing;
    }

    public Integer getCpu() {
        return cpu;
    }

    public void setCpu(Integer cpu) {
        this.cpu = cpu;
    }

    public Integer getMemoryGB() {
        return memoryGB;
    }

    public void setMemoryGB(Integer memoryGB) {
        this.memoryGB = memoryGB;
    }

    public Integer getDiskGB() {
        return diskGB;
    }

    public void setDiskGB(Integer diskGB) {
        this.diskGB = diskGB;
    }

    public Integer getGpu() {
        return gpu;
    }

    public void setGpu(Integer gpu) {
        this.gpu = gpu;
    }

    public Integer getTrafficGB() {
        return trafficGB;
    }

    public void setTrafficGB(Integer trafficGB) {
        this.trafficGB = trafficGB;
    }

    public Float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Float totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ResourceCostDTO resourceCostDTO = (ResourceCostDTO) o;
        if (resourceCostDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), resourceCostDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ResourceCostDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", nfvipopId='" + getNfvipopId() + "'" +
            ", billing='" + getBilling() + "'" +
            ", cpu=" + getCpu() +
            ", memoryGB=" + getMemoryGB() +
            ", diskGB=" + getDiskGB() +
            ", gpu=" + getGpu() +
            ", trafficGB=" + getTrafficGB() +
            ", totalCost=" + getTotalCost() +
            "}";
    }
}
