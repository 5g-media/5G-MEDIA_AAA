package eu.fivegmedia.aaa.service;

import eu.fivegmedia.aaa.domain.AccVduResourceConsumption;
import eu.fivegmedia.aaa.repository.AccVduResourceConsumptionRepository;
import eu.fivegmedia.aaa.service.dto.AccVduResourceConsumptionDTO;
import eu.fivegmedia.aaa.service.mapper.AccVduResourceConsumptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing AccVduResourceConsumption.
 */
@Service
@Transactional
public class AccVduResourceConsumptionService {

    private final Logger log = LoggerFactory.getLogger(AccVduResourceConsumptionService.class);

    private final AccVduResourceConsumptionRepository accVduResourceConsumptionRepository;

    private final AccVduResourceConsumptionMapper accVduResourceConsumptionMapper;

    public AccVduResourceConsumptionService(AccVduResourceConsumptionRepository accVduResourceConsumptionRepository, AccVduResourceConsumptionMapper accVduResourceConsumptionMapper) {
        this.accVduResourceConsumptionRepository = accVduResourceConsumptionRepository;
        this.accVduResourceConsumptionMapper = accVduResourceConsumptionMapper;
    }

    /**
     * Save a accVduResourceConsumption.
     *
     * @param accVduResourceConsumptionDTO the entity to save
     * @return the persisted entity
     */
    public AccVduResourceConsumptionDTO save(AccVduResourceConsumptionDTO accVduResourceConsumptionDTO) {
        log.debug("Request to save AccVduResourceConsumption : {}", accVduResourceConsumptionDTO);
        AccVduResourceConsumption accVduResourceConsumption = accVduResourceConsumptionMapper.toEntity(accVduResourceConsumptionDTO);
        accVduResourceConsumption = accVduResourceConsumptionRepository.save(accVduResourceConsumption);
        return accVduResourceConsumptionMapper.toDto(accVduResourceConsumption);
    }

    /**
     * Get all the accVduResourceConsumptions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AccVduResourceConsumptionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AccVduResourceConsumptions");
        return accVduResourceConsumptionRepository.findAll(pageable)
            .map(accVduResourceConsumptionMapper::toDto);
    }


    /**
     * Get one accVduResourceConsumption by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<AccVduResourceConsumptionDTO> findOne(Long id) {
        log.debug("Request to get AccVduResourceConsumption : {}", id);
        return accVduResourceConsumptionRepository.findById(id)
            .map(accVduResourceConsumptionMapper::toDto);
    }

    /**
     * Delete the accVduResourceConsumption by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete AccVduResourceConsumption : {}", id);
        accVduResourceConsumptionRepository.deleteById(id);
    }
}
