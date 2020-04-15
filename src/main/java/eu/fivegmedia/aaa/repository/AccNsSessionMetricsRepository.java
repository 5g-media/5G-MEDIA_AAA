package eu.fivegmedia.aaa.repository;

import eu.fivegmedia.aaa.domain.AccNsSessionMetrics;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AccNsSessionMetrics entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccNsSessionMetricsRepository extends JpaRepository<AccNsSessionMetrics, Long> {

}
