package eu.fivegmedia.aaa.web.rest;

import com.codahale.metrics.annotation.Timed;
import eu.fivegmedia.aaa.service.AccVnfSessionService;
import eu.fivegmedia.aaa.web.rest.errors.BadRequestAlertException;
import eu.fivegmedia.aaa.web.rest.util.HeaderUtil;
import eu.fivegmedia.aaa.web.rest.util.PaginationUtil;
import eu.fivegmedia.aaa.service.dto.AccVnfSessionDTO;
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
 * REST controller for managing AccVnfSession.
 */
@RestController
@RequestMapping("/api")
public class AccVnfSessionResource {
	//TEST#7
    private final Logger log = LoggerFactory.getLogger(AccVnfSessionResource.class);

    private static final String ENTITY_NAME = "accVnfSession";

    private final AccVnfSessionService accVnfSessionService;

    public AccVnfSessionResource(AccVnfSessionService accVnfSessionService) {
        this.accVnfSessionService = accVnfSessionService;
    }

    /**
     * POST  /acc-vnf-sessions : Create a new accVnfSession.
     *
     * @param accVnfSessionDTO the accVnfSessionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new accVnfSessionDTO, or with status 400 (Bad Request) if the accVnfSession has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/acc-vnf-sessions")
    @Timed
    public ResponseEntity<AccVnfSessionDTO> createAccVnfSession(@Valid @RequestBody AccVnfSessionDTO accVnfSessionDTO) throws URISyntaxException {
        log.debug("REST request to save AccVnfSession : {}", accVnfSessionDTO);
        if (accVnfSessionDTO.getId() != null) {
            throw new BadRequestAlertException("A new accVnfSession cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AccVnfSessionDTO result = accVnfSessionService.save(accVnfSessionDTO);
        return ResponseEntity.created(new URI("/api/acc-vnf-sessions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /acc-vnf-sessions : Updates an existing accVnfSession.
     *
     * @param accVnfSessionDTO the accVnfSessionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated accVnfSessionDTO,
     * or with status 400 (Bad Request) if the accVnfSessionDTO is not valid,
     * or with status 500 (Internal Server Error) if the accVnfSessionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/acc-vnf-sessions")
    @Timed
    public ResponseEntity<AccVnfSessionDTO> updateAccVnfSession(@Valid @RequestBody AccVnfSessionDTO accVnfSessionDTO) throws URISyntaxException {
        log.debug("REST request to update AccVnfSession : {}", accVnfSessionDTO);
        if (accVnfSessionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AccVnfSessionDTO result = accVnfSessionService.save(accVnfSessionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, accVnfSessionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /acc-vnf-sessions : get all the accVnfSessions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of accVnfSessions in body
     */
    @GetMapping("/acc-vnf-sessions")
    @Timed
    public ResponseEntity<List<AccVnfSessionDTO>> getAllAccVnfSessions(Pageable pageable) {
        log.debug("REST request to get a page of AccVnfSessions");
        Page<AccVnfSessionDTO> page = accVnfSessionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/acc-vnf-sessions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /acc-vnf-sessions : get all the accVnfSessions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of accVnfSessions in body
     */
    @GetMapping("/acc-vnf-sessions-by-ns/{id}")
    @Timed
    public ResponseEntity<List<AccVnfSessionDTO>> getAllAccVnfSessionsByNS(Pageable pageable, @PathVariable Long id) {
        log.debug("REST request to get a page of AccVnfSessions");
        Page<AccVnfSessionDTO> page = accVnfSessionService.findAllByNS(pageable, id);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/acc-vnf-sessions-by-ns");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /acc-vnf-sessions/:id : get the "id" accVnfSession.
     *
     * @param id the id of the accVnfSessionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the accVnfSessionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/acc-vnf-sessions/{id}")
    @Timed
    public ResponseEntity<AccVnfSessionDTO> getAccVnfSession(@PathVariable Long id) {
        log.debug("REST request to get AccVnfSession : {}", id);
        Optional<AccVnfSessionDTO> accVnfSessionDTO = accVnfSessionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(accVnfSessionDTO);
    }

    /**
     * DELETE  /acc-vnf-sessions/:id : delete the "id" accVnfSession.
     *
     * @param id the id of the accVnfSessionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/acc-vnf-sessions/{id}")
    @Timed
    public ResponseEntity<Void> deleteAccVnfSession(@PathVariable Long id) {
        log.debug("REST request to delete AccVnfSession : {}", id);
        accVnfSessionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
