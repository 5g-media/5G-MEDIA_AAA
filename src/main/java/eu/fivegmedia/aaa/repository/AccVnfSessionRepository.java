package eu.fivegmedia.aaa.repository;

import eu.fivegmedia.aaa.domain.AccVnfSession;
import eu.fivegmedia.aaa.domain.CatalogTenant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//TEST#7
/**
 * Spring Data  repository for the AccVnfSession entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccVnfSessionRepository extends JpaRepository<AccVnfSession, Long> {
	@Query(value = "from AccVnfSession accVnfSession where accVnfSession.accNsSession.id=:id")
	    Page<AccVnfSession> findAllByNS(Pageable pageable, @Param("id") Long id);
}
