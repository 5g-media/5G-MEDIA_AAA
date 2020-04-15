package eu.fivegmedia.aaa.web.rest;

import com.codahale.metrics.annotation.Timed;
import eu.fivegmedia.aaa.service.EndpointService;
import eu.fivegmedia.aaa.web.rest.errors.BadRequestAlertException;
import eu.fivegmedia.aaa.web.rest.util.HeaderUtil;
import eu.fivegmedia.aaa.web.rest.util.PaginationUtil;
import eu.fivegmedia.aaa.service.dto.EndpointDTO;
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
 * REST controller for managing Endpoint.
 */
@RestController
@RequestMapping("/api")
public class EndpointResource {

    private final Logger log = LoggerFactory.getLogger(EndpointResource.class);

    private static final String ENTITY_NAME = "endpoint";

    private final EndpointService endpointService;

    public EndpointResource(EndpointService endpointService) {
        this.endpointService = endpointService;
    }

    /**
     * POST  /endpoints : Create a new endpoint.
     *
     * @param endpointDTO the endpointDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new endpointDTO, or with status 400 (Bad Request) if the endpoint has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/endpoints")
    @Timed
    public ResponseEntity<EndpointDTO> createEndpoint(@Valid @RequestBody EndpointDTO endpointDTO) throws URISyntaxException {
        log.debug("REST request to save Endpoint : {}", endpointDTO);
        if (endpointDTO.getId() != null) {
            throw new BadRequestAlertException("A new endpoint cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EndpointDTO result = endpointService.save(endpointDTO);
        return ResponseEntity.created(new URI("/api/endpoints/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /endpoints : Updates an existing endpoint.
     *
     * @param endpointDTO the endpointDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated endpointDTO,
     * or with status 400 (Bad Request) if the endpointDTO is not valid,
     * or with status 500 (Internal Server Error) if the endpointDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/endpoints")
    @Timed
    public ResponseEntity<EndpointDTO> updateEndpoint(@Valid @RequestBody EndpointDTO endpointDTO) throws URISyntaxException {
        log.debug("REST request to update Endpoint : {}", endpointDTO);
        if (endpointDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EndpointDTO result = endpointService.save(endpointDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, endpointDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /endpoints : get all the endpoints.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of endpoints in body
     */
    @GetMapping("/endpoints")
    @Timed
    public ResponseEntity<List<EndpointDTO>> getAllEndpoints(Pageable pageable) {
        log.debug("REST request to get a page of Endpoints");
        Page<EndpointDTO> page = endpointService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/endpoints");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /endpoints/:id : get the "id" endpoint.
     *
     * @param id the id of the endpointDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the endpointDTO, or with status 404 (Not Found)
     */
    @GetMapping("/endpoints/{id}")
    @Timed
    public ResponseEntity<EndpointDTO> getEndpoint(@PathVariable Long id) {
        log.debug("REST request to get Endpoint : {}", id);
        Optional<EndpointDTO> endpointDTO = endpointService.findOne(id);
        return ResponseUtil.wrapOrNotFound(endpointDTO);
    }

    /**
     * DELETE  /endpoints/:id : delete the "id" endpoint.
     *
     * @param id the id of the endpointDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/endpoints/{id}")
    @Timed
    public ResponseEntity<Void> deleteEndpoint(@PathVariable Long id) {
        log.debug("REST request to delete Endpoint : {}", id);
        endpointService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
