package eu.fivegmedia.aaa.repository;

import eu.fivegmedia.aaa.domain.AccVduResourceConsumption;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AccVduResourceConsumption entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccVduResourceConsumptionRepository extends JpaRepository<AccVduResourceConsumption, Long> {

}
