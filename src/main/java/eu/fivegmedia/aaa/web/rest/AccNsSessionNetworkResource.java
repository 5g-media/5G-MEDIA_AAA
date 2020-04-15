package eu.fivegmedia.aaa.web.rest;

import com.codahale.metrics.annotation.Timed;
import eu.fivegmedia.aaa.service.AccNsSessionNetworkService;
import eu.fivegmedia.aaa.web.rest.errors.BadRequestAlertException;
import eu.fivegmedia.aaa.web.rest.util.HeaderUtil;
import eu.fivegmedia.aaa.web.rest.util.PaginationUtil;
import eu.fivegmedia.aaa.service.dto.AccNsSessionNetworkDTO;
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
 * REST controller for managing AccNsSessionNetwork.
 */
@RestController
@RequestMapping("/api")
public class AccNsSessionNetworkResource {

    private final Logger log = LoggerFactory.getLogger(AccNsSessionNetworkResource.class);

    private static final String ENTITY_NAME = "accNsSessionNetwork";

    private final AccNsSessionNetworkService accNsSessionNetworkService;

    public AccNsSessionNetworkResource(AccNsSessionNetworkService accNsSessionNetworkService) {
        this.accNsSessionNetworkService = accNsSessionNetworkService;
    }

    /**
     * POST  /acc-ns-session-networks : Create a new accNsSessionNetwork.
     *
     * @param accNsSessionNetworkDTO the accNsSessionNetworkDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new accNsSessionNetworkDTO, or with status 400 (Bad Request) if the accNsSessionNetwork has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/acc-ns-session-networks")
    @Timed
    public ResponseEntity<AccNsSessionNetworkDTO> createAccNsSessionNetwork(@Valid @RequestBody AccNsSessionNetworkDTO accNsSessionNetworkDTO) throws URISyntaxException {
        log.debug("REST request to save AccNsSessionNetwork : {}", accNsSessionNetworkDTO);
        if (accNsSessionNetworkDTO.getId() != null) {
            throw new BadRequestAlertException("A new accNsSessionNetwork cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AccNsSessionNetworkDTO result = accNsSessionNetworkService.save(accNsSessionNetworkDTO);
        return ResponseEntity.created(new URI("/api/acc-ns-session-networks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /acc-ns-session-networks : Updates an existing accNsSessionNetwork.
     *
     * @param accNsSessionNetworkDTO the accNsSessionNetworkDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated accNsSessionNetworkDTO,
     * or with status 400 (Bad Request) if the accNsSessionNetworkDTO is not valid,
     * or with status 500 (Internal Server Error) if the accNsSessionNetworkDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/acc-ns-session-networks")
    @Timed
    public ResponseEntity<AccNsSessionNetworkDTO> updateAccNsSessionNetwork(@Valid @RequestBody AccNsSessionNetworkDTO accNsSessionNetworkDTO) throws URISyntaxException {
        log.debug("REST request to update AccNsSessionNetwork : {}", accNsSessionNetworkDTO);
        if (accNsSessionNetworkDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AccNsSessionNetworkDTO result = accNsSessionNetworkService.save(accNsSessionNetworkDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, accNsSessionNetworkDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /acc-ns-session-networks : get all the accNsSessionNetworks.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of accNsSessionNetworks in body
     */
    @GetMapping("/acc-ns-session-networks")
    @Timed
    public ResponseEntity<List<AccNsSessionNetworkDTO>> getAllAccNsSessionNetworks(Pageable pageable) {
        log.debug("REST request to get a page of AccNsSessionNetworks");
        Page<AccNsSessionNetworkDTO> page = accNsSessionNetworkService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/acc-ns-session-networks");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /acc-ns-session-networks/:id : get the "id" accNsSessionNetwork.
     *
     * @param id the id of the accNsSessionNetworkDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the accNsSessionNetworkDTO, or with status 404 (Not Found)
     */
    @GetMapping("/acc-ns-session-networks/{id}")
    @Timed
    public ResponseEntity<AccNsSessionNetworkDTO> getAccNsSessionNetwork(@PathVariable Long id) {
        log.debug("REST request to get AccNsSessionNetwork : {}", id);
        Optional<AccNsSessionNetworkDTO> accNsSessionNetworkDTO = accNsSessionNetworkService.findOne(id);
        return ResponseUtil.wrapOrNotFound(accNsSessionNetworkDTO);
    }

    /**
     * DELETE  /acc-ns-session-networks/:id : delete the "id" accNsSessionNetwork.
     *
     * @param id the id of the accNsSessionNetworkDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/acc-ns-session-networks/{id}")
    @Timed
    public ResponseEntity<Void> deleteAccNsSessionNetwork(@PathVariable Long id) {
        log.debug("REST request to delete AccNsSessionNetwork : {}", id);
        accNsSessionNetworkService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
