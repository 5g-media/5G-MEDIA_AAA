package eu.fivegmedia.aaa.web.rest;

import com.codahale.metrics.annotation.Timed;
import eu.fivegmedia.aaa.service.ResourceTokenService;
import eu.fivegmedia.aaa.web.rest.errors.BadRequestAlertException;
import eu.fivegmedia.aaa.web.rest.util.HeaderUtil;
import eu.fivegmedia.aaa.web.rest.util.PaginationUtil;
import eu.fivegmedia.aaa.service.dto.ResourceTokenDTO;
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
 * REST controller for managing ResourceToken.
 */
@RestController
@RequestMapping("/api")
public class ResourceTokenResource {

    private final Logger log = LoggerFactory.getLogger(ResourceTokenResource.class);

    private static final String ENTITY_NAME = "resourceToken";

    private final ResourceTokenService resourceTokenService;

    public ResourceTokenResource(ResourceTokenService resourceTokenService) {
        this.resourceTokenService = resourceTokenService;
    }

    /**
     * POST  /resource-tokens : Create a new resourceToken.
     *
     * @param resourceTokenDTO the resourceTokenDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new resourceTokenDTO, or with status 400 (Bad Request) if the resourceToken has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/resource-tokens")
    @Timed
    public ResponseEntity<ResourceTokenDTO> createResourceToken(@Valid @RequestBody ResourceTokenDTO resourceTokenDTO) throws URISyntaxException {
        log.debug("REST request to save ResourceToken : {}", resourceTokenDTO);
        if (resourceTokenDTO.getId() != null) {
            throw new BadRequestAlertException("A new resourceToken cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ResourceTokenDTO result = resourceTokenService.save(resourceTokenDTO);
        return ResponseEntity.created(new URI("/api/resource-tokens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /resource-tokens : Updates an existing resourceToken.
     *
     * @param resourceTokenDTO the resourceTokenDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated resourceTokenDTO,
     * or with status 400 (Bad Request) if the resourceTokenDTO is not valid,
     * or with status 500 (Internal Server Error) if the resourceTokenDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/resource-tokens")
    @Timed
    public ResponseEntity<ResourceTokenDTO> updateResourceToken(@Valid @RequestBody ResourceTokenDTO resourceTokenDTO) throws URISyntaxException {
        log.debug("REST request to update ResourceToken : {}", resourceTokenDTO);
        if (resourceTokenDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ResourceTokenDTO result = resourceTokenService.save(resourceTokenDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, resourceTokenDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /resource-tokens : get all the resourceTokens.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of resourceTokens in body
     */
    @GetMapping("/resource-tokens")
    @Timed
    public ResponseEntity<List<ResourceTokenDTO>> getAllResourceTokens(Pageable pageable) {
        log.debug("REST request to get a page of ResourceTokens");
        Page<ResourceTokenDTO> page = resourceTokenService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/resource-tokens");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /resource-tokens/:id : get the "id" resourceToken.
     *
     * @param id the id of the resourceTokenDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the resourceTokenDTO, or with status 404 (Not Found)
     */
    @GetMapping("/resource-tokens/{id}")
    @Timed
    public ResponseEntity<ResourceTokenDTO> getResourceToken(@PathVariable Long id) {
        log.debug("REST request to get ResourceToken : {}", id);
        Optional<ResourceTokenDTO> resourceTokenDTO = resourceTokenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resourceTokenDTO);
    }

    /**
     * DELETE  /resource-tokens/:id : delete the "id" resourceToken.
     *
     * @param id the id of the resourceTokenDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/resource-tokens/{id}")
    @Timed
    public ResponseEntity<Void> deleteResourceToken(@PathVariable Long id) {
        log.debug("REST request to delete ResourceToken : {}", id);
        resourceTokenService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
