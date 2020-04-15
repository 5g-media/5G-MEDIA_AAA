package eu.fivegmedia.aaa.web.rest;

import com.codahale.metrics.annotation.Timed;
import eu.fivegmedia.aaa.service.ResourceUserService;
import eu.fivegmedia.aaa.web.rest.errors.BadRequestAlertException;
import eu.fivegmedia.aaa.web.rest.util.HeaderUtil;
import eu.fivegmedia.aaa.web.rest.util.PaginationUtil;
import eu.fivegmedia.aaa.service.dto.ResourceUserDTO;
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
 * REST controller for managing ResourceUser.
 */
@RestController
@RequestMapping("/api")
public class ResourceUserResource {

    private final Logger log = LoggerFactory.getLogger(ResourceUserResource.class);

    private static final String ENTITY_NAME = "resourceUser";

    private final ResourceUserService resourceUserService;

    public ResourceUserResource(ResourceUserService resourceUserService) {
        this.resourceUserService = resourceUserService;
    }

    /**
     * POST  /resource-users : Create a new resourceUser.
     *
     * @param resourceUserDTO the resourceUserDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new resourceUserDTO, or with status 400 (Bad Request) if the resourceUser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/resource-users")
    @Timed
    public ResponseEntity<ResourceUserDTO> createResourceUser(@Valid @RequestBody ResourceUserDTO resourceUserDTO) throws URISyntaxException {
        log.debug("REST request to save ResourceUser : {}", resourceUserDTO);
        if (resourceUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new resourceUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ResourceUserDTO result = resourceUserService.save(resourceUserDTO);
        return ResponseEntity.created(new URI("/api/resource-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /resource-users : Updates an existing resourceUser.
     *
     * @param resourceUserDTO the resourceUserDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated resourceUserDTO,
     * or with status 400 (Bad Request) if the resourceUserDTO is not valid,
     * or with status 500 (Internal Server Error) if the resourceUserDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/resource-users")
    @Timed
    public ResponseEntity<ResourceUserDTO> updateResourceUser(@Valid @RequestBody ResourceUserDTO resourceUserDTO) throws URISyntaxException {
        log.debug("REST request to update ResourceUser : {}", resourceUserDTO);
        if (resourceUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ResourceUserDTO result = resourceUserService.save(resourceUserDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, resourceUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /resource-users : get all the resourceUsers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of resourceUsers in body
     */
    @GetMapping("/resource-users")
    @Timed
    public ResponseEntity<List<ResourceUserDTO>> getAllResourceUsers(Pageable pageable) {
        log.debug("REST request to get a page of ResourceUsers");
        Page<ResourceUserDTO> page = resourceUserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/resource-users");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /resource-users/filterByCatalogUser/{id} : get all the resourceUsers for catalogUser with id.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of resourceUsers in body
     */
    @GetMapping("/resource-users/filterByCatalogUser/{id}")
    @Timed
    public ResponseEntity<List<ResourceUserDTO>> getAllResourceUsersByCatalogId(@PathVariable Long id, Pageable pageable) {
        log.debug("REST request to get a page of ResourceUsers");
        Page<ResourceUserDTO> page = resourceUserService.findAllByCatalogUser(id, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/resource-users/filterByCatalogUser");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /resource-users/filterByResourceType/{resourceType} : get all the resourceUsers for catalogUser with id.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of resourceUsers in body
     */
    @GetMapping("/resource-users/filterByResourceType/{resourceType}")
    @Timed
    public ResponseEntity<List<ResourceUserDTO>> getAllResourceUsersByResourceType(@PathVariable String resourceType, Pageable pageable) {
        log.debug("REST request to get a page of ResourceUsers");
        Page<ResourceUserDTO> page = resourceUserService.findAllByResourceType(resourceType, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/resource-users/filterByResourceType");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /resource-users/:id : get the "id" resourceUser.
     *
     * @param id the id of the resourceUserDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the resourceUserDTO, or with status 404 (Not Found)
     */
    @GetMapping("/resource-users/{id}")
    @Timed
    public ResponseEntity<ResourceUserDTO> getResourceUser(@PathVariable Long id) {
        log.debug("REST request to get ResourceUser : {}", id);
        Optional<ResourceUserDTO> resourceUserDTO = resourceUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resourceUserDTO);
    }

    /**
     * DELETE  /resource-users/:id : delete the "id" resourceUser.
     *
     * @param id the id of the resourceUserDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/resource-users/{id}")
    @Timed
    public ResponseEntity<Void> deleteResourceUser(@PathVariable Long id) {
        log.debug("REST request to delete ResourceUser : {}", id);
        resourceUserService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
