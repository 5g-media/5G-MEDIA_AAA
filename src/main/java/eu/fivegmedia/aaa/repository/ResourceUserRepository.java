package eu.fivegmedia.aaa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eu.fivegmedia.aaa.domain.ResourceUser;
import eu.fivegmedia.aaa.domain.enumeration.ResourceEnum;


/**
 * Spring Data  repository for the ResourceUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResourceUserRepository extends JpaRepository<ResourceUser, Long> {
    public List<ResourceUser> findByNameAndTenant(String name, String tenant);
    public Page<ResourceUser> findByCatalogUserId(Long id, Pageable page);
    
    public Page<ResourceUser> findByResourceEnum(ResourceEnum resourceEnum, Pageable page);
}
