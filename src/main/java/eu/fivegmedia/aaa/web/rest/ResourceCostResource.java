package eu.fivegmedia.aaa.web.rest;

import com.codahale.metrics.annotation.Timed;
import eu.fivegmedia.aaa.service.ResourceCostService;
import eu.fivegmedia.aaa.web.rest.errors.BadRequestAlertException;
import eu.fivegmedia.aaa.web.rest.util.HeaderUtil;
import eu.fivegmedia.aaa.web.rest.util.PaginationUtil;
import eu.fivegmedia.aaa.service.dto.ResourceCostDTO;
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
 * REST controller for managing ResourceCost.
 */
@RestController
@RequestMapping("/api")
public class ResourceCostResource {

    private final Logger log = LoggerFactory.getLogger(ResourceCostResource.class);

    private static final String ENTITY_NAME = "resourceCost";

    private final ResourceCostService resourceCostService;

    public ResourceCostResource(ResourceCostService resourceCostService) {
        this.resourceCostService = resourceCostService;
    }

    /**
     * POST  /resource-costs : Create a new resourceCost.
     *
     * @param resourceCostDTO the resourceCostDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new resourceCostDTO, or with status 400 (Bad Request) if the resourceCost has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/resource-costs")
    @Timed
    public ResponseEntity<ResourceCostDTO> createResourceCost(@Valid @RequestBody ResourceCostDTO resourceCostDTO) throws URISyntaxException {
        log.debug("REST request to save ResourceCost : {}", resourceCostDTO);
        if (resourceCostDTO.getId() != null) {
            throw new BadRequestAlertException("A new resourceCost cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ResourceCostDTO result = resourceCostService.save(resourceCostDTO);
        return ResponseEntity.created(new URI("/api/resource-costs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /resource-costs : Updates an existing resourceCost.
     *
     * @param resourceCostDTO the resourceCostDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated resourceCostDTO,
     * or with status 400 (Bad Request) if the resourceCostDTO is not valid,
     * or with status 500 (Internal Server Error) if the resourceCostDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/resource-costs")
    @Timed
    public ResponseEntity<ResourceCostDTO> updateResourceCost(@Valid @RequestBody ResourceCostDTO resourceCostDTO) throws URISyntaxException {
        log.debug("REST request to update ResourceCost : {}", resourceCostDTO);
        if (resourceCostDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ResourceCostDTO result = resourceCostService.save(resourceCostDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, resourceCostDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /resource-costs : get all the resourceCosts.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of resourceCosts in body
     */
    @GetMapping("/resource-costs")
    @Timed
    public ResponseEntity<List<ResourceCostDTO>> getAllResourceCosts(Pageable pageable) {
        log.debug("REST request to get a page of ResourceCosts");
        Page<ResourceCostDTO> page = resourceCostService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/resource-costs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /resource-costs/:id : get the "id" resourceCost.
     *
     * @param id the id of the resourceCostDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the resourceCostDTO, or with status 404 (Not Found)
     */
    @GetMapping("/resource-costs/{id}")
    @Timed
    public ResponseEntity<ResourceCostDTO> getResourceCost(@PathVariable Long id) {
        log.debug("REST request to get ResourceCost : {}", id);
        Optional<ResourceCostDTO> resourceCostDTO = resourceCostService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resourceCostDTO);
    }

    /**
     * DELETE  /resource-costs/:id : delete the "id" resourceCost.
     *
     * @param id the id of the resourceCostDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/resource-costs/{id}")
    @Timed
    public ResponseEntity<Void> deleteResourceCost(@PathVariable Long id) {
        log.debug("REST request to delete ResourceCost : {}", id);
        resourceCostService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
