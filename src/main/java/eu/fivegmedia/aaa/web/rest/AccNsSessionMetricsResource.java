package eu.fivegmedia.aaa.web.rest;

import com.codahale.metrics.annotation.Timed;
import eu.fivegmedia.aaa.service.AccNsSessionMetricsService;
import eu.fivegmedia.aaa.web.rest.errors.BadRequestAlertException;
import eu.fivegmedia.aaa.web.rest.util.HeaderUtil;
import eu.fivegmedia.aaa.web.rest.util.PaginationUtil;
import eu.fivegmedia.aaa.service.dto.AccNsSessionMetricsDTO;
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
 * REST controller for managing AccNsSessionMetrics.
 */
@RestController
@RequestMapping("/api")
public class AccNsSessionMetricsResource {

    private final Logger log = LoggerFactory.getLogger(AccNsSessionMetricsResource.class);

    private static final String ENTITY_NAME = "accNsSessionMetrics";

    private final AccNsSessionMetricsService accNsSessionMetricsService;

    public AccNsSessionMetricsResource(AccNsSessionMetricsService accNsSessionMetricsService) {
        this.accNsSessionMetricsService = accNsSessionMetricsService;
    }

    /**
     * POST  /acc-ns-session-metrics : Create a new accNsSessionMetrics.
     *
     * @param accNsSessionMetricsDTO the accNsSessionMetricsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new accNsSessionMetricsDTO, or with status 400 (Bad Request) if the accNsSessionMetrics has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/acc-ns-session-metrics")
    @Timed
    public ResponseEntity<AccNsSessionMetricsDTO> createAccNsSessionMetrics(@Valid @RequestBody AccNsSessionMetricsDTO accNsSessionMetricsDTO) throws URISyntaxException {
        log.debug("REST request to save AccNsSessionMetrics : {}", accNsSessionMetricsDTO);
        if (accNsSessionMetricsDTO.getId() != null) {
            throw new BadRequestAlertException("A new accNsSessionMetrics cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AccNsSessionMetricsDTO result = accNsSessionMetricsService.save(accNsSessionMetricsDTO);
        return ResponseEntity.created(new URI("/api/acc-ns-session-metrics/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /acc-ns-session-metrics : Updates an existing accNsSessionMetrics.
     *
     * @param accNsSessionMetricsDTO the accNsSessionMetricsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated accNsSessionMetricsDTO,
     * or with status 400 (Bad Request) if the accNsSessionMetricsDTO is not valid,
     * or with status 500 (Internal Server Error) if the accNsSessionMetricsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/acc-ns-session-metrics")
    @Timed
    public ResponseEntity<AccNsSessionMetricsDTO> updateAccNsSessionMetrics(@Valid @RequestBody AccNsSessionMetricsDTO accNsSessionMetricsDTO) throws URISyntaxException {
        log.debug("REST request to update AccNsSessionMetrics : {}", accNsSessionMetricsDTO);
        if (accNsSessionMetricsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AccNsSessionMetricsDTO result = accNsSessionMetricsService.save(accNsSessionMetricsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, accNsSessionMetricsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /acc-ns-session-metrics : get all the accNsSessionMetrics.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of accNsSessionMetrics in body
     */
    @GetMapping("/acc-ns-session-metrics")
    @Timed
    public ResponseEntity<List<AccNsSessionMetricsDTO>> getAllAccNsSessionMetrics(Pageable pageable) {
        log.debug("REST request to get a page of AccNsSessionMetrics");
        Page<AccNsSessionMetricsDTO> page = accNsSessionMetricsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/acc-ns-session-metrics");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /acc-ns-session-metrics/:id : get the "id" accNsSessionMetrics.
     *
     * @param id the id of the accNsSessionMetricsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the accNsSessionMetricsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/acc-ns-session-metrics/{id}")
    @Timed
    public ResponseEntity<AccNsSessionMetricsDTO> getAccNsSessionMetrics(@PathVariable Long id) {
        log.debug("REST request to get AccNsSessionMetrics : {}", id);
        Optional<AccNsSessionMetricsDTO> accNsSessionMetricsDTO = accNsSessionMetricsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(accNsSessionMetricsDTO);
    }

    /**
     * DELETE  /acc-ns-session-metrics/:id : delete the "id" accNsSessionMetrics.
     *
     * @param id the id of the accNsSessionMetricsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/acc-ns-session-metrics/{id}")
    @Timed
    public ResponseEntity<Void> deleteAccNsSessionMetrics(@PathVariable Long id) {
        log.debug("REST request to delete AccNsSessionMetrics : {}", id);
        accNsSessionMetricsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
