package eu.fivegmedia.aaa.repository;

import eu.fivegmedia.aaa.domain.QualityProfile;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the QualityProfile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QualityProfileRepository extends JpaRepository<QualityProfile, Long> {

}
