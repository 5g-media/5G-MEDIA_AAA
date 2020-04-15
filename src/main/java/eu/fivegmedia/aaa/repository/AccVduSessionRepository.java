package eu.fivegmedia.aaa.repository;

import eu.fivegmedia.aaa.domain.AccVduSession;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AccVduSession entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccVduSessionRepository extends JpaRepository<AccVduSession, Long> {

}
