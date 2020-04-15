package eu.fivegmedia.aaa.service;

import eu.fivegmedia.aaa.domain.ResourceCost;
import eu.fivegmedia.aaa.repository.ResourceCostRepository;
import eu.fivegmedia.aaa.service.dto.ResourceCostDTO;
import eu.fivegmedia.aaa.service.mapper.ResourceCostMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing ResourceCost.
 */
@Service
@Transactional
public class ResourceCostService {

    private final Logger log = LoggerFactory.getLogger(ResourceCostService.class);

    private final ResourceCostRepository resourceCostRepository;

    private final ResourceCostMapper resourceCostMapper;

    public ResourceCostService(ResourceCostRepository resourceCostRepository, ResourceCostMapper resourceCostMapper) {
        this.resourceCostRepository = resourceCostRepository;
        this.resourceCostMapper = resourceCostMapper;
    }

    /**
     * Save a resourceCost.
     *
     * @param resourceCostDTO the entity to save
     * @return the persisted entity
     */
    public ResourceCostDTO save(ResourceCostDTO resourceCostDTO) {
        log.debug("Request to save ResourceCost : {}", resourceCostDTO);
        ResourceCost resourceCost = resourceCostMapper.toEntity(resourceCostDTO);
        resourceCost = resourceCostRepository.save(resourceCost);
        return resourceCostMapper.toDto(resourceCost);
    }

    /**
     * Get all the resourceCosts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ResourceCostDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ResourceCosts");
        return resourceCostRepository.findAll(pageable)
            .map(resourceCostMapper::toDto);
    }


    /**
     * Get one resourceCost by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ResourceCostDTO> findOne(Long id) {
        log.debug("Request to get ResourceCost : {}", id);
        return resourceCostRepository.findById(id)
            .map(resourceCostMapper::toDto);
    }

    /**
     * Delete the resourceCost by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ResourceCost : {}", id);
        resourceCostRepository.deleteById(id);
    }
}
