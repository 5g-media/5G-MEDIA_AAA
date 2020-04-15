package eu.fivegmedia.aaa.service;

import eu.fivegmedia.aaa.domain.AccVduSession;
import eu.fivegmedia.aaa.repository.AccVduSessionRepository;
import eu.fivegmedia.aaa.service.dto.AccVduSessionDTO;
import eu.fivegmedia.aaa.service.mapper.AccVduSessionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing AccVduSession.
 */
@Service
@Transactional
public class AccVduSessionService {

    private final Logger log = LoggerFactory.getLogger(AccVduSessionService.class);

    private final AccVduSessionRepository accVduSessionRepository;

    private final AccVduSessionMapper accVduSessionMapper;

    public AccVduSessionService(AccVduSessionRepository accVduSessionRepository, AccVduSessionMapper accVduSessionMapper) {
        this.accVduSessionRepository = accVduSessionRepository;
        this.accVduSessionMapper = accVduSessionMapper;
    }

    /**
     * Save a accVduSession.
     *
     * @param accVduSessionDTO the entity to save
     * @return the persisted entity
     */
    public AccVduSessionDTO save(AccVduSessionDTO accVduSessionDTO) {
        log.debug("Request to save AccVduSession : {}", accVduSessionDTO);
        AccVduSession accVduSession = accVduSessionMapper.toEntity(accVduSessionDTO);
        accVduSession = accVduSessionRepository.save(accVduSession);
        return accVduSessionMapper.toDto(accVduSession);
    }

    /**
     * Get all the accVduSessions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AccVduSessionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AccVduSessions");
        return accVduSessionRepository.findAll(pageable)
            .map(accVduSessionMapper::toDto);
    }


    /**
     * Get one accVduSession by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<AccVduSessionDTO> findOne(Long id) {
        log.debug("Request to get AccVduSession : {}", id);
        return accVduSessionRepository.findById(id)
            .map(accVduSessionMapper::toDto);
    }

    /**
     * Delete the accVduSession by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete AccVduSession : {}", id);
        accVduSessionRepository.deleteById(id);
    }
}
