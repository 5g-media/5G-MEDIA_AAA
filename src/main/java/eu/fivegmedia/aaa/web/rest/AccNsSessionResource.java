package eu.fivegmedia.aaa.web.rest;

import com.codahale.metrics.annotation.Timed;
import eu.fivegmedia.aaa.service.AccNsSessionService;
import eu.fivegmedia.aaa.web.rest.errors.BadRequestAlertException;
import eu.fivegmedia.aaa.web.rest.util.HeaderUtil;
import eu.fivegmedia.aaa.web.rest.util.PaginationUtil;
import eu.fivegmedia.aaa.service.dto.AccNsSessionDTO;
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
 * REST controller for managing AccNsSession.
 */
@RestController
@RequestMapping("/api")
public class AccNsSessionResource {

    private final Logger log = LoggerFactory.getLogger(AccNsSessionResource.class);

    private static final String ENTITY_NAME = "accNsSession";

    private final AccNsSessionService accNsSessionService;

    public AccNsSessionResource(AccNsSessionService accNsSessionService) {
        this.accNsSessionService = accNsSessionService;
    }

    /**
     * POST  /acc-ns-sessions : Create a new accNsSession.
     *
     * @param accNsSessionDTO the accNsSessionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new accNsSessionDTO, or with status 400 (Bad Request) if the accNsSession has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/acc-ns-sessions")
    @Timed
    public ResponseEntity<AccNsSessionDTO> createAccNsSession(@Valid @RequestBody AccNsSessionDTO accNsSessionDTO) throws URISyntaxException {
        log.debug("REST request to save AccNsSession : {}", accNsSessionDTO);
        if (accNsSessionDTO.getId() != null) {
            throw new BadRequestAlertException("A new accNsSession cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AccNsSessionDTO result = accNsSessionService.save(accNsSessionDTO);
        return ResponseEntity.created(new URI("/api/acc-ns-sessions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /acc-ns-sessions : Updates an existing accNsSession.
     *
     * @param accNsSessionDTO the accNsSessionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated accNsSessionDTO,
     * or with status 400 (Bad Request) if the accNsSessionDTO is not valid,
     * or with status 500 (Internal Server Error) if the accNsSessionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/acc-ns-sessions")
    @Timed
    public ResponseEntity<AccNsSessionDTO> updateAccNsSession(@Valid @RequestBody AccNsSessionDTO accNsSessionDTO) throws URISyntaxException {
        log.debug("REST request to update AccNsSession : {}", accNsSessionDTO);
        if (accNsSessionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AccNsSessionDTO result = accNsSessionService.save(accNsSessionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, accNsSessionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /acc-ns-sessions : get all the accNsSessions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of accNsSessions in body
     */
    @GetMapping("/acc-ns-sessions")
    @Timed
    public ResponseEntity<List<AccNsSessionDTO>> getAllAccNsSessions(Pageable pageable) {
        log.debug("REST request to get a page of AccNsSessions");
        Page<AccNsSessionDTO> page = accNsSessionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/acc-ns-sessions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /acc-ns-sessions/:id : get the "id" accNsSession.
     *
     * @param id the id of the accNsSessionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the accNsSessionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/acc-ns-sessions/{id}")
    @Timed
    public ResponseEntity<AccNsSessionDTO> getAccNsSession(@PathVariable Long id) {
        log.debug("REST request to get AccNsSession : {}", id);
        Optional<AccNsSessionDTO> accNsSessionDTO = accNsSessionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(accNsSessionDTO);
    }

    /**
     * DELETE  /acc-ns-sessions/:id : delete the "id" accNsSession.
     *
     * @param id the id of the accNsSessionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/acc-ns-sessions/{id}")
    @Timed
    public ResponseEntity<Void> deleteAccNsSession(@PathVariable Long id) {
        log.debug("REST request to delete AccNsSession : {}", id);
        accNsSessionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
