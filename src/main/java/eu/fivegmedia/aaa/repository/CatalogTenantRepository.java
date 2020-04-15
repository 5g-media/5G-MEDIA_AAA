package eu.fivegmedia.aaa.repository;

import eu.fivegmedia.aaa.domain.CatalogTenant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the CatalogTenant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CatalogTenantRepository extends JpaRepository<CatalogTenant, Long> {

    @Query(value = "select distinct catalog_tenant from CatalogTenant catalog_tenant left join fetch catalog_tenant.catalogUserSets",
        countQuery = "select count(distinct catalog_tenant) from CatalogTenant catalog_tenant")
    Page<CatalogTenant> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct catalog_tenant from CatalogTenant catalog_tenant left join fetch catalog_tenant.catalogUserSets")
    List<CatalogTenant> findAllWithEagerRelationships();

    @Query("select catalog_tenant from CatalogTenant catalog_tenant left join fetch catalog_tenant.catalogUserSets where catalog_tenant.id =:id")
    Optional<CatalogTenant> findOneWithEagerRelationships(@Param("id") Long id);

}
