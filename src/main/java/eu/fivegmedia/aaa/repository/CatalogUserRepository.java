package eu.fivegmedia.aaa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eu.fivegmedia.aaa.domain.CatalogUser;
import eu.fivegmedia.aaa.domain.User;


/**
 * Spring Data  repository for the CatalogUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CatalogUserRepository extends JpaRepository<CatalogUser, Long> {
    
	public CatalogUser findByUsername(String username);
	
	public CatalogUser findByName(String name);

	public CatalogUser findByUser(User user);
}
