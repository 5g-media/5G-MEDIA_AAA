package eu.fivegmedia.aaa.repository;

import eu.fivegmedia.aaa.domain.ResourceUserLogin;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ResourceUserLogin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResourceUserLoginRepository extends JpaRepository<ResourceUserLogin, Long> {

}
