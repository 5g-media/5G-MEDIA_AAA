package eu.fivegmedia.aaa.repository;

import eu.fivegmedia.aaa.domain.AccNsSession;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AccNsSession entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccNsSessionRepository extends JpaRepository<AccNsSession, Long> {

}
