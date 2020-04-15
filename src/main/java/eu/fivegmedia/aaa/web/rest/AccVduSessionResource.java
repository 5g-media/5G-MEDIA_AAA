package eu.fivegmedia.aaa.web.rest;

import com.codahale.metrics.annotation.Timed;
import eu.fivegmedia.aaa.service.AccVduSessionService;
import eu.fivegmedia.aaa.web.rest.errors.BadRequestAlertException;
import eu.fivegmedia.aaa.web.rest.util.HeaderUtil;
import eu.fivegmedia.aaa.web.rest.util.PaginationUtil;
import eu.fivegmedia.aaa.service.dto.AccVduSessionDTO;
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
 * REST controller for managing AccVduSession.
 */
@RestController
@RequestMapping("/api")
public class AccVduSessionResource {

    private final Logger log = LoggerFactory.getLogger(AccVduSessionResource.class);

    private static final String ENTITY_NAME = "accVduSession";

    private final AccVduSessionService accVduSessionService;

    public AccVduSessionResource(AccVduSessionService accVduSessionService) {
        this.accVduSessionService = accVduSessionService;
    }

    /**
     * POST  /acc-vdu-sessions : Create a new accVduSession.
     *
     * @param accVduSessionDTO the accVduSessionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new accVduSessionDTO, or with status 400 (Bad Request) if the accVduSession has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/acc-vdu-sessions")
    @Timed
    public ResponseEntity<AccVduSessionDTO> createAccVduSession(@Valid @RequestBody AccVduSessionDTO accVduSessionDTO) throws URISyntaxException {
        log.debug("REST request to save AccVduSession : {}", accVduSessionDTO);
        if (accVduSessionDTO.getId() != null) {
            throw new BadRequestAlertException("A new accVduSession cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AccVduSessionDTO result = accVduSessionService.save(accVduSessionDTO);
        return ResponseEntity.created(new URI("/api/acc-vdu-sessions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /acc-vdu-sessions : Updates an existing accVduSession.
     *
     * @param accVduSessionDTO the accVduSessionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated accVduSessionDTO,
     * or with status 400 (Bad Request) if the accVduSessionDTO is not valid,
     * or with status 500 (Internal Server Error) if the accVduSessionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/acc-vdu-sessions")
    @Timed
    public ResponseEntity<AccVduSessionDTO> updateAccVduSession(@Valid @RequestBody AccVduSessionDTO accVduSessionDTO) throws URISyntaxException {
        log.debug("REST request to update AccVduSession : {}", accVduSessionDTO);
        if (accVduSessionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AccVduSessionDTO result = accVduSessionService.save(accVduSessionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, accVduSessionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /acc-vdu-sessions : get all the accVduSessions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of accVduSessions in body
     */
    @GetMapping("/acc-vdu-sessions")
    @Timed
    public ResponseEntity<List<AccVduSessionDTO>> getAllAccVduSessions(Pageable pageable) {
        log.debug("REST request to get a page of AccVduSessions");
        Page<AccVduSessionDTO> page = accVduSessionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/acc-vdu-sessions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /acc-vdu-sessions/:id : get the "id" accVduSession.
     *
     * @param id the id of the accVduSessionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the accVduSessionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/acc-vdu-sessions/{id}")
    @Timed
    public ResponseEntity<AccVduSessionDTO> getAccVduSession(@PathVariable Long id) {
        log.debug("REST request to get AccVduSession : {}", id);
        Optional<AccVduSessionDTO> accVduSessionDTO = accVduSessionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(accVduSessionDTO);
    }

    /**
     * DELETE  /acc-vdu-sessions/:id : delete the "id" accVduSession.
     *
     * @param id the id of the accVduSessionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/acc-vdu-sessions/{id}")
    @Timed
    public ResponseEntity<Void> deleteAccVduSession(@PathVariable Long id) {
        log.debug("REST request to delete AccVduSession : {}", id);
        accVduSessionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
