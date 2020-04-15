package eu.fivegmedia.aaa.service;

import eu.fivegmedia.aaa.domain.ResourceAdminLogin;
import eu.fivegmedia.aaa.repository.ResourceAdminLoginRepository;
import eu.fivegmedia.aaa.service.dto.ResourceAdminLoginDTO;
import eu.fivegmedia.aaa.service.mapper.ResourceAdminLoginMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing ResourceAdminLogin.
 */
@Service
@Transactional
public class ResourceAdminLoginService {

    private final Logger log = LoggerFactory.getLogger(ResourceAdminLoginService.class);

    private final ResourceAdminLoginRepository resourceAdminLoginRepository;

    private final ResourceAdminLoginMapper resourceAdminLoginMapper;

    public ResourceAdminLoginService(ResourceAdminLoginRepository resourceAdminLoginRepository, ResourceAdminLoginMapper resourceAdminLoginMapper) {
        this.resourceAdminLoginRepository = resourceAdminLoginRepository;
        this.resourceAdminLoginMapper = resourceAdminLoginMapper;
    }

    /**
     * Save a resourceAdminLogin.
     *
     * @param resourceAdminLoginDTO the entity to save
     * @return the persisted entity
     */
    public ResourceAdminLoginDTO save(ResourceAdminLoginDTO resourceAdminLoginDTO) {
        log.debug("Request to save ResourceAdminLogin : {}", resourceAdminLoginDTO);
        ResourceAdminLogin resourceAdminLogin = resourceAdminLoginMapper.toEntity(resourceAdminLoginDTO);
        resourceAdminLogin = resourceAdminLoginRepository.save(resourceAdminLogin);
        return resourceAdminLoginMapper.toDto(resourceAdminLogin);
    }

    /**
     * Get all the resourceAdminLogins.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ResourceAdminLoginDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ResourceAdminLogins");
        return resourceAdminLoginRepository.findAll(pageable)
            .map(resourceAdminLoginMapper::toDto);
    }


    /**
     * Get one resourceAdminLogin by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ResourceAdminLoginDTO> findOne(Long id) {
        log.debug("Request to get ResourceAdminLogin : {}", id);
        return resourceAdminLoginRepository.findById(id)
            .map(resourceAdminLoginMapper::toDto);
    }

    /**
     * Delete the resourceAdminLogin by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ResourceAdminLogin : {}", id);
        resourceAdminLoginRepository.deleteById(id);
    }
}
