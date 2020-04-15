package eu.fivegmedia.aaa.service;

import eu.fivegmedia.aaa.domain.AccNsSessionNetwork;
import eu.fivegmedia.aaa.repository.AccNsSessionNetworkRepository;
import eu.fivegmedia.aaa.service.dto.AccNsSessionNetworkDTO;
import eu.fivegmedia.aaa.service.mapper.AccNsSessionNetworkMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing AccNsSessionNetwork.
 */
@Service
@Transactional
public class AccNsSessionNetworkService {

    private final Logger log = LoggerFactory.getLogger(AccNsSessionNetworkService.class);

    private final AccNsSessionNetworkRepository accNsSessionNetworkRepository;

    private final AccNsSessionNetworkMapper accNsSessionNetworkMapper;

    public AccNsSessionNetworkService(AccNsSessionNetworkRepository accNsSessionNetworkRepository, AccNsSessionNetworkMapper accNsSessionNetworkMapper) {
        this.accNsSessionNetworkRepository = accNsSessionNetworkRepository;
        this.accNsSessionNetworkMapper = accNsSessionNetworkMapper;
    }

    /**
     * Save a accNsSessionNetwork.
     *
     * @param accNsSessionNetworkDTO the entity to save
     * @return the persisted entity
     */
    public AccNsSessionNetworkDTO save(AccNsSessionNetworkDTO accNsSessionNetworkDTO) {
        log.debug("Request to save AccNsSessionNetwork : {}", accNsSessionNetworkDTO);
        AccNsSessionNetwork accNsSessionNetwork = accNsSessionNetworkMapper.toEntity(accNsSessionNetworkDTO);
        accNsSessionNetwork = accNsSessionNetworkRepository.save(accNsSessionNetwork);
        return accNsSessionNetworkMapper.toDto(accNsSessionNetwork);
    }

    /**
     * Get all the accNsSessionNetworks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AccNsSessionNetworkDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AccNsSessionNetworks");
        return accNsSessionNetworkRepository.findAll(pageable)
            .map(accNsSessionNetworkMapper::toDto);
    }


    /**
     * Get one accNsSessionNetwork by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<AccNsSessionNetworkDTO> findOne(Long id) {
        log.debug("Request to get AccNsSessionNetwork : {}", id);
        return accNsSessionNetworkRepository.findById(id)
            .map(accNsSessionNetworkMapper::toDto);
    }

    /**
     * Delete the accNsSessionNetwork by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete AccNsSessionNetwork : {}", id);
        accNsSessionNetworkRepository.deleteById(id);
    }
}
