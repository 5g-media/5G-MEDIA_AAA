package eu.fivegmedia.aaa.repository;

import eu.fivegmedia.aaa.domain.Endpoint;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Endpoint entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EndpointRepository extends JpaRepository<Endpoint, Long> {

}
