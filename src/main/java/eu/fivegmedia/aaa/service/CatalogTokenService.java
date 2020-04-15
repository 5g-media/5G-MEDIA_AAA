package eu.fivegmedia.aaa.service;

import eu.fivegmedia.aaa.domain.CatalogToken;
import eu.fivegmedia.aaa.repository.CatalogTokenRepository;
import eu.fivegmedia.aaa.service.dto.CatalogTokenDTO;
import eu.fivegmedia.aaa.service.mapper.CatalogTokenMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing CatalogToken.
 */
@Service
@Transactional
public class CatalogTokenService {

    private final Logger log = LoggerFactory.getLogger(CatalogTokenService.class);

    private final CatalogTokenRepository catalogTokenRepository;

    private final CatalogTokenMapper catalogTokenMapper;

    public CatalogTokenService(CatalogTokenRepository catalogTokenRepository, CatalogTokenMapper catalogTokenMapper) {
        this.catalogTokenRepository = catalogTokenRepository;
        this.catalogTokenMapper = catalogTokenMapper;
    }

    /**
     * Save a catalogToken.
     *
     * @param catalogTokenDTO the entity to save
     * @return the persisted entity
     */
    public CatalogTokenDTO save(CatalogTokenDTO catalogTokenDTO) {
        log.debug("Request to save CatalogToken : {}", catalogTokenDTO);
        CatalogToken catalogToken = catalogTokenMapper.toEntity(catalogTokenDTO);
        catalogToken = catalogTokenRepository.save(catalogToken);
        return catalogTokenMapper.toDto(catalogToken);
    }

    /**
     * Get all the catalogTokens.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CatalogTokenDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CatalogTokens");
        return catalogTokenRepository.findAll(pageable)
            .map(catalogTokenMapper::toDto);
    }


    /**
     * Get one catalogToken by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<CatalogTokenDTO> findOne(Long id) {
        log.debug("Request to get CatalogToken : {}", id);
        return catalogTokenRepository.findById(id)
            .map(catalogTokenMapper::toDto);
    }

    /**
     * Delete the catalogToken by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete CatalogToken : {}", id);
        catalogTokenRepository.deleteById(id);
    }
}
