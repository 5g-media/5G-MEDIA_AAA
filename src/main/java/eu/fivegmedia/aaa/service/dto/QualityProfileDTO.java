package eu.fivegmedia.aaa.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the QualityProfile entity.
 */
public class QualityProfileDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer minBitrateKbps;

    @NotNull
    private Integer maxBitrateKbps;

    @NotNull
    private Integer minCompressionLevel;

    @NotNull
    private Integer maxCompressionLevel;

    @NotNull
    private Integer meanEntropyTI;

    @NotNull
    private Integer meanEntropySI;

    @NotNull
    private Integer minExpectedQOE;

    @NotNull
    private Integer maxExpectedQOE;

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

    public Integer getMinBitrateKbps() {
        return minBitrateKbps;
    }

    public void setMinBitrateKbps(Integer minBitrateKbps) {
        this.minBitrateKbps = minBitrateKbps;
    }

    public Integer getMaxBitrateKbps() {
        return maxBitrateKbps;
    }

    public void setMaxBitrateKbps(Integer maxBitrateKbps) {
        this.maxBitrateKbps = maxBitrateKbps;
    }

    public Integer getMinCompressionLevel() {
        return minCompressionLevel;
    }

    public void setMinCompressionLevel(Integer minCompressionLevel) {
        this.minCompressionLevel = minCompressionLevel;
    }

    public Integer getMaxCompressionLevel() {
        return maxCompressionLevel;
    }

    public void setMaxCompressionLevel(Integer maxCompressionLevel) {
        this.maxCompressionLevel = maxCompressionLevel;
    }

    public Integer getMeanEntropyTI() {
        return meanEntropyTI;
    }

    public void setMeanEntropyTI(Integer meanEntropyTI) {
        this.meanEntropyTI = meanEntropyTI;
    }

    public Integer getMeanEntropySI() {
        return meanEntropySI;
    }

    public void setMeanEntropySI(Integer meanEntropySI) {
        this.meanEntropySI = meanEntropySI;
    }

    public Integer getMinExpectedQOE() {
        return minExpectedQOE;
    }

    public void setMinExpectedQOE(Integer minExpectedQOE) {
        this.minExpectedQOE = minExpectedQOE;
    }

    public Integer getMaxExpectedQOE() {
        return maxExpectedQOE;
    }

    public void setMaxExpectedQOE(Integer maxExpectedQOE) {
        this.maxExpectedQOE = maxExpectedQOE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QualityProfileDTO qualityProfileDTO = (QualityProfileDTO) o;
        if (qualityProfileDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), qualityProfileDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QualityProfileDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", minBitrateKbps=" + getMinBitrateKbps() +
            ", maxBitrateKbps=" + getMaxBitrateKbps() +
            ", minCompressionLevel=" + getMinCompressionLevel() +
            ", maxCompressionLevel=" + getMaxCompressionLevel() +
            ", meanEntropyTI=" + getMeanEntropyTI() +
            ", meanEntropySI=" + getMeanEntropySI() +
            ", minExpectedQOE=" + getMinExpectedQOE() +
            ", maxExpectedQOE=" + getMaxExpectedQOE() +
            "}";
    }
}
