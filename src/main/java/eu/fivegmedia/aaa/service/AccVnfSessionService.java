package eu.fivegmedia.aaa.service;

import eu.fivegmedia.aaa.domain.AccVnfSession;
import eu.fivegmedia.aaa.repository.AccVnfSessionRepository;
import eu.fivegmedia.aaa.service.dto.AccVnfSessionDTO;
import eu.fivegmedia.aaa.service.mapper.AccVnfSessionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing AccVnfSession.
 */
@Service
@Transactional
public class AccVnfSessionService {

    private final Logger log = LoggerFactory.getLogger(AccVnfSessionService.class);

    private final AccVnfSessionRepository accVnfSessionRepository;

    private final AccVnfSessionMapper accVnfSessionMapper;

    public AccVnfSessionService(AccVnfSessionRepository accVnfSessionRepository, AccVnfSessionMapper accVnfSessionMapper) {
        this.accVnfSessionRepository = accVnfSessionRepository;
        this.accVnfSessionMapper = accVnfSessionMapper;
    }

    /**
     * Save a accVnfSession.
     *
     * @param accVnfSessionDTO the entity to save
     * @return the persisted entity
     */
    public AccVnfSessionDTO save(AccVnfSessionDTO accVnfSessionDTO) {
        log.debug("Request to save AccVnfSession : {}", accVnfSessionDTO);
        AccVnfSession accVnfSession = accVnfSessionMapper.toEntity(accVnfSessionDTO);
        accVnfSession = accVnfSessionRepository.save(accVnfSession);
        return accVnfSessionMapper.toDto(accVnfSession);
    }

    /**
     * Get all the accVnfSessions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AccVnfSessionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AccVnfSessions");
        return accVnfSessionRepository.findAll(pageable)
            .map(accVnfSessionMapper::toDto);
    }
  //TEST#7
    
    /**
     * Get all the accVnfSessions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AccVnfSessionDTO> findAllByNS(Pageable pageable, long nsSessionId) {
        log.debug("Request to get all AccVnfSessions");
        return accVnfSessionRepository.findAllByNS(pageable, nsSessionId)
            .map(accVnfSessionMapper::toDto);
    }

    /**
     * Get one accVnfSession by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<AccVnfSessionDTO> findOne(Long id) {
        log.debug("Request to get AccVnfSession : {}", id);
        return accVnfSessionRepository.findById(id)
            .map(accVnfSessionMapper::toDto);
    }

    /**
     * Delete the accVnfSession by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete AccVnfSession : {}", id);
        accVnfSessionRepository.deleteById(id);
    }
}
