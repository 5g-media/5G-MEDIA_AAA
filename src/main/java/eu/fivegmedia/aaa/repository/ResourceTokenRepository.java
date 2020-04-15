package eu.fivegmedia.aaa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eu.fivegmedia.aaa.domain.ResourceToken;
import eu.fivegmedia.aaa.domain.ResourceUser;


/**
 * Spring Data  repository for the ResourceToken entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResourceTokenRepository extends JpaRepository<ResourceToken, Long> {

	public List<ResourceToken> findByResourceUserAndValid(ResourceUser resourceUser, boolean valid);

}
