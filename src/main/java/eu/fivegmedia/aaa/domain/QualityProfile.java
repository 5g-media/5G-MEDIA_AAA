package eu.fivegmedia.aaa.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A QualityProfile.
 */
@Entity
@Table(name = "quality_profile")
public class QualityProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "min_bitrate_kbps", nullable = false)
    private Integer minBitrateKbps;

    @NotNull
    @Column(name = "max_bitrate_kbps", nullable = false)
    private Integer maxBitrateKbps;

    @NotNull
    @Column(name = "min_compression_level", nullable = false)
    private Integer minCompressionLevel;

    @NotNull
    @Column(name = "max_compression_level", nullable = false)
    private Integer maxCompressionLevel;

    @NotNull
    @Column(name = "mean_entropy_ti", nullable = false)
    private Integer meanEntropyTI;

    @NotNull
    @Column(name = "mean_entropy_si", nullable = false)
    private Integer meanEntropySI;

    @NotNull
    @Column(name = "min_expected_qoe", nullable = false)
    private Integer minExpectedQOE;

    @NotNull
    @Column(name = "max_expected_qoe", nullable = false)
    private Integer maxExpectedQOE;

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

    public QualityProfile name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinBitrateKbps() {
        return minBitrateKbps;
    }

    public QualityProfile minBitrateKbps(Integer minBitrateKbps) {
        this.minBitrateKbps = minBitrateKbps;
        return this;
    }

    public void setMinBitrateKbps(Integer minBitrateKbps) {
        this.minBitrateKbps = minBitrateKbps;
    }

    public Integer getMaxBitrateKbps() {
        return maxBitrateKbps;
    }

    public QualityProfile maxBitrateKbps(Integer maxBitrateKbps) {
        this.maxBitrateKbps = maxBitrateKbps;
        return this;
    }

    public void setMaxBitrateKbps(Integer maxBitrateKbps) {
        this.maxBitrateKbps = maxBitrateKbps;
    }

    public Integer getMinCompressionLevel() {
        return minCompressionLevel;
    }

    public QualityProfile minCompressionLevel(Integer minCompressionLevel) {
        this.minCompressionLevel = minCompressionLevel;
        return this;
    }

    public void setMinCompressionLevel(Integer minCompressionLevel) {
        this.minCompressionLevel = minCompressionLevel;
    }

    public Integer getMaxCompressionLevel() {
        return maxCompressionLevel;
    }

    public QualityProfile maxCompressionLevel(Integer maxCompressionLevel) {
        this.maxCompressionLevel = maxCompressionLevel;
        return this;
    }

    public void setMaxCompressionLevel(Integer maxCompressionLevel) {
        this.maxCompressionLevel = maxCompressionLevel;
    }

    public Integer getMeanEntropyTI() {
        return meanEntropyTI;
    }

    public QualityProfile meanEntropyTI(Integer meanEntropyTI) {
        this.meanEntropyTI = meanEntropyTI;
        return this;
    }

    public void setMeanEntropyTI(Integer meanEntropyTI) {
        this.meanEntropyTI = meanEntropyTI;
    }

    public Integer getMeanEntropySI() {
        return meanEntropySI;
    }

    public QualityProfile meanEntropySI(Integer meanEntropySI) {
        this.meanEntropySI = meanEntropySI;
        return this;
    }

    public void setMeanEntropySI(Integer meanEntropySI) {
        this.meanEntropySI = meanEntropySI;
    }

    public Integer getMinExpectedQOE() {
        return minExpectedQOE;
    }

    public QualityProfile minExpectedQOE(Integer minExpectedQOE) {
        this.minExpectedQOE = minExpectedQOE;
        return this;
    }

    public void setMinExpectedQOE(Integer minExpectedQOE) {
        this.minExpectedQOE = minExpectedQOE;
    }

    public Integer getMaxExpectedQOE() {
        return maxExpectedQOE;
    }

    public QualityProfile maxExpectedQOE(Integer maxExpectedQOE) {
        this.maxExpectedQOE = maxExpectedQOE;
        return this;
    }

    public void setMaxExpectedQOE(Integer maxExpectedQOE) {
        this.maxExpectedQOE = maxExpectedQOE;
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
        QualityProfile qualityProfile = (QualityProfile) o;
        if (qualityProfile.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), qualityProfile.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QualityProfile{" +
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
