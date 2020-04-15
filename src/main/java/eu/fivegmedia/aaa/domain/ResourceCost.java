package eu.fivegmedia.aaa.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ResourceCost.
 */
@Entity
@Table(name = "resource_cost")
public class ResourceCost implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "nfvipop_id", nullable = false)
    private String nfvipopId;

    @NotNull
    @Column(name = "billing", nullable = false)
    private String billing;

    @NotNull
    @Column(name = "cpu", nullable = false)
    private Integer cpu;

    @NotNull
    @Column(name = "memory_gb", nullable = false)
    private Integer memoryGB;

    @NotNull
    @Column(name = "disk_gb", nullable = false)
    private Integer diskGB;

    @NotNull
    @Column(name = "gpu", nullable = false)
    private Integer gpu;

    @NotNull
    @Column(name = "traffic_gb", nullable = false)
    private Integer trafficGB;

    @NotNull
    @Column(name = "total_cost", nullable = false)
    private Float totalCost;

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

    public ResourceCost name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNfvipopId() {
        return nfvipopId;
    }

    public ResourceCost nfvipopId(String nfvipopId) {
        this.nfvipopId = nfvipopId;
        return this;
    }

    public void setNfvipopId(String nfvipopId) {
        this.nfvipopId = nfvipopId;
    }

    public String getBilling() {
        return billing;
    }

    public ResourceCost billing(String billing) {
        this.billing = billing;
        return this;
    }

    public void setBilling(String billing) {
        this.billing = billing;
    }

    public Integer getCpu() {
        return cpu;
    }

    public ResourceCost cpu(Integer cpu) {
        this.cpu = cpu;
        return this;
    }

    public void setCpu(Integer cpu) {
        this.cpu = cpu;
    }

    public Integer getMemoryGB() {
        return memoryGB;
    }

    public ResourceCost memoryGB(Integer memoryGB) {
        this.memoryGB = memoryGB;
        return this;
    }

    public void setMemoryGB(Integer memoryGB) {
        this.memoryGB = memoryGB;
    }

    public Integer getDiskGB() {
        return diskGB;
    }

    public ResourceCost diskGB(Integer diskGB) {
        this.diskGB = diskGB;
        return this;
    }

    public void setDiskGB(Integer diskGB) {
        this.diskGB = diskGB;
    }

    public Integer getGpu() {
        return gpu;
    }

    public ResourceCost gpu(Integer gpu) {
        this.gpu = gpu;
        return this;
    }

    public void setGpu(Integer gpu) {
        this.gpu = gpu;
    }

    public Integer getTrafficGB() {
        return trafficGB;
    }

    public ResourceCost trafficGB(Integer trafficGB) {
        this.trafficGB = trafficGB;
        return this;
    }

    public void setTrafficGB(Integer trafficGB) {
        this.trafficGB = trafficGB;
    }

    public Float getTotalCost() {
        return totalCost;
    }

    public ResourceCost totalCost(Float totalCost) {
        this.totalCost = totalCost;
        return this;
    }

    public void setTotalCost(Float totalCost) {
        this.totalCost = totalCost;
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
        ResourceCost resourceCost = (ResourceCost) o;
        if (resourceCost.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), resourceCost.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ResourceCost{" +
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
