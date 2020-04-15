package eu.fivegmedia.aaa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eu.fivegmedia.aaa.domain.CatalogToken;


/**
 * Spring Data  repository for the CatalogToken entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CatalogTokenRepository extends JpaRepository<CatalogToken, Long> {

	public List<CatalogToken> findAllByValueOrderByIdDesc(String value);
}
