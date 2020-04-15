package eu.fivegmedia.aaa.repository;

import eu.fivegmedia.aaa.domain.ResourceAdminLogin;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ResourceAdminLogin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResourceAdminLoginRepository extends JpaRepository<ResourceAdminLogin, Long> {

}
