package eu.fivegmedia.aaa.service;

import eu.fivegmedia.aaa.domain.Endpoint;
import eu.fivegmedia.aaa.repository.EndpointRepository;
import eu.fivegmedia.aaa.service.dto.EndpointDTO;
import eu.fivegmedia.aaa.service.mapper.EndpointMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Endpoint.
 */
@Service
@Transactional
public class EndpointService {

    private final Logger log = LoggerFactory.getLogger(EndpointService.class);

    private final EndpointRepository endpointRepository;

    private final EndpointMapper endpointMapper;

    public EndpointService(EndpointRepository endpointRepository, EndpointMapper endpointMapper) {
        this.endpointRepository = endpointRepository;
        this.endpointMapper = endpointMapper;
    }

    /**
     * Save a endpoint.
     *
     * @param endpointDTO the entity to save
     * @return the persisted entity
     */
    public EndpointDTO save(EndpointDTO endpointDTO) {
        log.debug("Request to save Endpoint : {}", endpointDTO);
        Endpoint endpoint = endpointMapper.toEntity(endpointDTO);
        endpoint = endpointRepository.save(endpoint);
        return endpointMapper.toDto(endpoint);
    }

    /**
     * Get all the endpoints.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<EndpointDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Endpoints");
        return endpointRepository.findAll(pageable)
            .map(endpointMapper::toDto);
    }


    /**
     * Get one endpoint by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<EndpointDTO> findOne(Long id) {
        log.debug("Request to get Endpoint : {}", id);
        return endpointRepository.findById(id)
            .map(endpointMapper::toDto);
    }

    /**
     * Delete the endpoint by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Endpoint : {}", id);
        endpointRepository.deleteById(id);
    }
}
