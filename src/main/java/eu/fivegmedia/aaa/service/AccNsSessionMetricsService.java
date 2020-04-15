package eu.fivegmedia.aaa.service;

import eu.fivegmedia.aaa.domain.AccNsSessionMetrics;
import eu.fivegmedia.aaa.repository.AccNsSessionMetricsRepository;
import eu.fivegmedia.aaa.service.dto.AccNsSessionMetricsDTO;
import eu.fivegmedia.aaa.service.mapper.AccNsSessionMetricsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing AccNsSessionMetrics.
 */
@Service
@Transactional
public class AccNsSessionMetricsService {

    private final Logger log = LoggerFactory.getLogger(AccNsSessionMetricsService.class);

    private final AccNsSessionMetricsRepository accNsSessionMetricsRepository;

    private final AccNsSessionMetricsMapper accNsSessionMetricsMapper;

    public AccNsSessionMetricsService(AccNsSessionMetricsRepository accNsSessionMetricsRepository, AccNsSessionMetricsMapper accNsSessionMetricsMapper) {
        this.accNsSessionMetricsRepository = accNsSessionMetricsRepository;
        this.accNsSessionMetricsMapper = accNsSessionMetricsMapper;
    }

    /**
     * Save a accNsSessionMetrics.
     *
     * @param accNsSessionMetricsDTO the entity to save
     * @return the persisted entity
     */
    public AccNsSessionMetricsDTO save(AccNsSessionMetricsDTO accNsSessionMetricsDTO) {
        log.debug("Request to save AccNsSessionMetrics : {}", accNsSessionMetricsDTO);
        AccNsSessionMetrics accNsSessionMetrics = accNsSessionMetricsMapper.toEntity(accNsSessionMetricsDTO);
        accNsSessionMetrics = accNsSessionMetricsRepository.save(accNsSessionMetrics);
        return accNsSessionMetricsMapper.toDto(accNsSessionMetrics);
    }

    /**
     * Get all the accNsSessionMetrics.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AccNsSessionMetricsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AccNsSessionMetrics");
        return accNsSessionMetricsRepository.findAll(pageable)
            .map(accNsSessionMetricsMapper::toDto);
    }


    /**
     * Get one accNsSessionMetrics by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<AccNsSessionMetricsDTO> findOne(Long id) {
        log.debug("Request to get AccNsSessionMetrics : {}", id);
        return accNsSessionMetricsRepository.findById(id)
            .map(accNsSessionMetricsMapper::toDto);
    }

    /**
     * Delete the accNsSessionMetrics by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete AccNsSessionMetrics : {}", id);
        accNsSessionMetricsRepository.deleteById(id);
    }
}
