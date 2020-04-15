package eu.fivegmedia.aaa.service;

import eu.fivegmedia.aaa.domain.ResourceToken;
import eu.fivegmedia.aaa.repository.ResourceTokenRepository;
import eu.fivegmedia.aaa.service.dto.ResourceTokenDTO;
import eu.fivegmedia.aaa.service.mapper.ResourceTokenMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing ResourceToken.
 */
@Service
@Transactional
public class ResourceTokenService {

    private final Logger log = LoggerFactory.getLogger(ResourceTokenService.class);

    private final ResourceTokenRepository resourceTokenRepository;

    private final ResourceTokenMapper resourceTokenMapper;

    public ResourceTokenService(ResourceTokenRepository resourceTokenRepository, ResourceTokenMapper resourceTokenMapper) {
        this.resourceTokenRepository = resourceTokenRepository;
        this.resourceTokenMapper = resourceTokenMapper;
    }

    /**
     * Save a resourceToken.
     *
     * @param resourceTokenDTO the entity to save
     * @return the persisted entity
     */
    public ResourceTokenDTO save(ResourceTokenDTO resourceTokenDTO) {
        log.debug("Request to save ResourceToken : {}", resourceTokenDTO);
        ResourceToken resourceToken = resourceTokenMapper.toEntity(resourceTokenDTO);
        resourceToken = resourceTokenRepository.save(resourceToken);
        return resourceTokenMapper.toDto(resourceToken);
    }

    /**
     * Get all the resourceTokens.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ResourceTokenDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ResourceTokens");
        return resourceTokenRepository.findAll(pageable)
            .map(resourceTokenMapper::toDto);
    }


    /**
     * Get one resourceToken by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ResourceTokenDTO> findOne(Long id) {
        log.debug("Request to get ResourceToken : {}", id);
        return resourceTokenRepository.findById(id)
            .map(resourceTokenMapper::toDto);
    }

    /**
     * Delete the resourceToken by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ResourceToken : {}", id);
        resourceTokenRepository.deleteById(id);
    }
}
