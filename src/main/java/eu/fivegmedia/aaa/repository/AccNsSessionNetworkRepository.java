package eu.fivegmedia.aaa.repository;

import eu.fivegmedia.aaa.domain.AccNsSessionNetwork;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AccNsSessionNetwork entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccNsSessionNetworkRepository extends JpaRepository<AccNsSessionNetwork, Long> {

}
