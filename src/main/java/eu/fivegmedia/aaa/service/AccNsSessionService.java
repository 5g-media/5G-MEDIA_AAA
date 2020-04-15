package eu.fivegmedia.aaa.service;

import eu.fivegmedia.aaa.domain.AccNsSession;
import eu.fivegmedia.aaa.repository.AccNsSessionRepository;
import eu.fivegmedia.aaa.service.dto.AccNsSessionDTO;
import eu.fivegmedia.aaa.service.mapper.AccNsSessionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing AccNsSession.
 */
@Service
@Transactional
public class AccNsSessionService {

    private final Logger log = LoggerFactory.getLogger(AccNsSessionService.class);

    private final AccNsSessionRepository accNsSessionRepository;

    private final AccNsSessionMapper accNsSessionMapper;

    public AccNsSessionService(AccNsSessionRepository accNsSessionRepository, AccNsSessionMapper accNsSessionMapper) {
        this.accNsSessionRepository = accNsSessionRepository;
        this.accNsSessionMapper = accNsSessionMapper;
    }

    /**
     * Save a accNsSession.
     *
     * @param accNsSessionDTO the entity to save
     * @return the persisted entity
     */
    public AccNsSessionDTO save(AccNsSessionDTO accNsSessionDTO) {
        log.debug("Request to save AccNsSession : {}", accNsSessionDTO);
        AccNsSession accNsSession = accNsSessionMapper.toEntity(accNsSessionDTO);
        accNsSession = accNsSessionRepository.save(accNsSession);
        return accNsSessionMapper.toDto(accNsSession);
    }

    /**
     * Get all the accNsSessions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AccNsSessionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AccNsSessions");
        return accNsSessionRepository.findAll(pageable)
            .map(accNsSessionMapper::toDto);
    }


    /**
     * Get one accNsSession by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<AccNsSessionDTO> findOne(Long id) {
        log.debug("Request to get AccNsSession : {}", id);
        return accNsSessionRepository.findById(id)
            .map(accNsSessionMapper::toDto);
    }

    /**
     * Delete the accNsSession by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete AccNsSession : {}", id);
        accNsSessionRepository.deleteById(id);
    }
}
