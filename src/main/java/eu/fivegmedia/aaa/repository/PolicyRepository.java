package eu.fivegmedia.aaa.repository;

import eu.fivegmedia.aaa.domain.Policy;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Policy entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {

}
