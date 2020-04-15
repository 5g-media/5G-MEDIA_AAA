package eu.fivegmedia.aaa.web.rest;

import com.codahale.metrics.annotation.Timed;
import eu.fivegmedia.aaa.service.AccVduResourceConsumptionService;
import eu.fivegmedia.aaa.web.rest.errors.BadRequestAlertException;
import eu.fivegmedia.aaa.web.rest.util.HeaderUtil;
import eu.fivegmedia.aaa.web.rest.util.PaginationUtil;
import eu.fivegmedia.aaa.service.dto.AccVduResourceConsumptionDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AccVduResourceConsumption.
 */
@RestController
@RequestMapping("/api")
public class AccVduResourceConsumptionResource {

    private final Logger log = LoggerFactory.getLogger(AccVduResourceConsumptionResource.class);

    private static final String ENTITY_NAME = "accVduResourceConsumption";

    private final AccVduResourceConsumptionService accVduResourceConsumptionService;

    public AccVduResourceConsumptionResource(AccVduResourceConsumptionService accVduResourceConsumptionService) {
        this.accVduResourceConsumptionService = accVduResourceConsumptionService;
    }

    /**
     * POST  /acc-vdu-resource-consumptions : Create a new accVduResourceConsumption.
     *
     * @param accVduResourceConsumptionDTO the accVduResourceConsumptionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new accVduResourceConsumptionDTO, or with status 400 (Bad Request) if the accVduResourceConsumption has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/acc-vdu-resource-consumptions")
    @Timed
    public ResponseEntity<AccVduResourceConsumptionDTO> createAccVduResourceConsumption(@Valid @RequestBody AccVduResourceConsumptionDTO accVduResourceConsumptionDTO) throws URISyntaxException {
        log.debug("REST request to save AccVduResourceConsumption : {}", accVduResourceConsumptionDTO);
        if (accVduResourceConsumptionDTO.getId() != null) {
            throw new BadRequestAlertException("A new accVduResourceConsumption cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AccVduResourceConsumptionDTO result = accVduResourceConsumptionService.save(accVduResourceConsumptionDTO);
        return ResponseEntity.created(new URI("/api/acc-vdu-resource-consumptions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /acc-vdu-resource-consumptions : Updates an existing accVduResourceConsumption.
     *
     * @param accVduResourceConsumptionDTO the accVduResourceConsumptionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated accVduResourceConsumptionDTO,
     * or with status 400 (Bad Request) if the accVduResourceConsumptionDTO is not valid,
     * or with status 500 (Internal Server Error) if the accVduResourceConsumptionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/acc-vdu-resource-consumptions")
    @Timed
    public ResponseEntity<AccVduResourceConsumptionDTO> updateAccVduResourceConsumption(@Valid @RequestBody AccVduResourceConsumptionDTO accVduResourceConsumptionDTO) throws URISyntaxException {
        log.debug("REST request to update AccVduResourceConsumption : {}", accVduResourceConsumptionDTO);
        if (accVduResourceConsumptionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AccVduResourceConsumptionDTO result = accVduResourceConsumptionService.save(accVduResourceConsumptionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, accVduResourceConsumptionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /acc-vdu-resource-consumptions : get all the accVduResourceConsumptions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of accVduResourceConsumptions in body
     */
    @GetMapping("/acc-vdu-resource-consumptions")
    @Timed
    public ResponseEntity<List<AccVduResourceConsumptionDTO>> getAllAccVduResourceConsumptions(Pageable pageable) {
        log.debug("REST request to get a page of AccVduResourceConsumptions");
        Page<AccVduResourceConsumptionDTO> page = accVduResourceConsumptionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/acc-vdu-resource-consumptions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /acc-vdu-resource-consumptions/:id : get the "id" accVduResourceConsumption.
     *
     * @param id the id of the accVduResourceConsumptionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the accVduResourceConsumptionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/acc-vdu-resource-consumptions/{id}")
    @Timed
    public ResponseEntity<AccVduResourceConsumptionDTO> getAccVduResourceConsumption(@PathVariable Long id) {
        log.debug("REST request to get AccVduResourceConsumption : {}", id);
        Optional<AccVduResourceConsumptionDTO> accVduResourceConsumptionDTO = accVduResourceConsumptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(accVduResourceConsumptionDTO);
    }

    /**
     * DELETE  /acc-vdu-resource-consumptions/:id : delete the "id" accVduResourceConsumption.
     *
     * @param id the id of the accVduResourceConsumptionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/acc-vdu-resource-consumptions/{id}")
    @Timed
    public ResponseEntity<Void> deleteAccVduResourceConsumption(@PathVariable Long id) {
        log.debug("REST request to delete AccVduResourceConsumption : {}", id);
        accVduResourceConsumptionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
