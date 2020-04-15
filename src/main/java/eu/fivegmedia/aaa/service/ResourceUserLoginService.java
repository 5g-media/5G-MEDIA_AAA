package eu.fivegmedia.aaa.service;

import eu.fivegmedia.aaa.domain.ResourceUserLogin;
import eu.fivegmedia.aaa.repository.ResourceUserLoginRepository;
import eu.fivegmedia.aaa.service.dto.ResourceUserLoginDTO;
import eu.fivegmedia.aaa.service.mapper.ResourceUserLoginMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing ResourceUserLogin.
 */
@Service
@Transactional
public class ResourceUserLoginService {

    private final Logger log = LoggerFactory.getLogger(ResourceUserLoginService.class);

    private final ResourceUserLoginRepository resourceUserLoginRepository;

    private final ResourceUserLoginMapper resourceUserLoginMapper;

    public ResourceUserLoginService(ResourceUserLoginRepository resourceUserLoginRepository, ResourceUserLoginMapper resourceUserLoginMapper) {
        this.resourceUserLoginRepository = resourceUserLoginRepository;
        this.resourceUserLoginMapper = resourceUserLoginMapper;
    }

    /**
     * Save a resourceUserLogin.
     *
     * @param resourceUserLoginDTO the entity to save
     * @return the persisted entity
     */
    public ResourceUserLoginDTO save(ResourceUserLoginDTO resourceUserLoginDTO) {
        log.debug("Request to save ResourceUserLogin : {}", resourceUserLoginDTO);
        ResourceUserLogin resourceUserLogin = resourceUserLoginMapper.toEntity(resourceUserLoginDTO);
        resourceUserLogin = resourceUserLoginRepository.save(resourceUserLogin);
        return resourceUserLoginMapper.toDto(resourceUserLogin);
    }

    /**
     * Get all the resourceUserLogins.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ResourceUserLoginDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ResourceUserLogins");
        return resourceUserLoginRepository.findAll(pageable)
            .map(resourceUserLoginMapper::toDto);
    }


    /**
     * Get one resourceUserLogin by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ResourceUserLoginDTO> findOne(Long id) {
        log.debug("Request to get ResourceUserLogin : {}", id);
        return resourceUserLoginRepository.findById(id)
            .map(resourceUserLoginMapper::toDto);
    }

    /**
     * Delete the resourceUserLogin by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ResourceUserLogin : {}", id);
        resourceUserLoginRepository.deleteById(id);
    }
}
