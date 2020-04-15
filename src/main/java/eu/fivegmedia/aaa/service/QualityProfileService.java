package eu.fivegmedia.aaa.service;

import eu.fivegmedia.aaa.domain.QualityProfile;
import eu.fivegmedia.aaa.repository.QualityProfileRepository;
import eu.fivegmedia.aaa.service.dto.QualityProfileDTO;
import eu.fivegmedia.aaa.service.mapper.QualityProfileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing QualityProfile.
 */
@Service
@Transactional
public class QualityProfileService {

    private final Logger log = LoggerFactory.getLogger(QualityProfileService.class);

    private final QualityProfileRepository qualityProfileRepository;

    private final QualityProfileMapper qualityProfileMapper;

    public QualityProfileService(QualityProfileRepository qualityProfileRepository, QualityProfileMapper qualityProfileMapper) {
        this.qualityProfileRepository = qualityProfileRepository;
        this.qualityProfileMapper = qualityProfileMapper;
    }

    /**
     * Save a qualityProfile.
     *
     * @param qualityProfileDTO the entity to save
     * @return the persisted entity
     */
    public QualityProfileDTO save(QualityProfileDTO qualityProfileDTO) {
        log.debug("Request to save QualityProfile : {}", qualityProfileDTO);
        QualityProfile qualityProfile = qualityProfileMapper.toEntity(qualityProfileDTO);
        qualityProfile = qualityProfileRepository.save(qualityProfile);
        return qualityProfileMapper.toDto(qualityProfile);
    }

    /**
     * Get all the qualityProfiles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<QualityProfileDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QualityProfiles");
        return qualityProfileRepository.findAll(pageable)
            .map(qualityProfileMapper::toDto);
    }


    /**
     * Get one qualityProfile by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<QualityProfileDTO> findOne(Long id) {
        log.debug("Request to get QualityProfile : {}", id);
        return qualityProfileRepository.findById(id)
            .map(qualityProfileMapper::toDto);
    }

    /**
     * Delete the qualityProfile by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete QualityProfile : {}", id);
        qualityProfileRepository.deleteById(id);
    }
}
