package eu.fivegmedia.aaa.repository;

import eu.fivegmedia.aaa.domain.ResourceCost;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ResourceCost entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResourceCostRepository extends JpaRepository<ResourceCost, Long> {

}
