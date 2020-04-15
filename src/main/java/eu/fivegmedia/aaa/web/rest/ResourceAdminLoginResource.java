package eu.fivegmedia.aaa.web.rest;

import com.codahale.metrics.annotation.Timed;
import eu.fivegmedia.aaa.service.ResourceAdminLoginService;
import eu.fivegmedia.aaa.web.rest.errors.BadRequestAlertException;
import eu.fivegmedia.aaa.web.rest.util.HeaderUtil;
import eu.fivegmedia.aaa.web.rest.util.PaginationUtil;
import eu.fivegmedia.aaa.service.dto.ResourceAdminLoginDTO;
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
 * REST controller for managing ResourceAdminLogin.
 */
@RestController
@RequestMapping("/api")
public class ResourceAdminLoginResource {

    private final Logger log = LoggerFactory.getLogger(ResourceAdminLoginResource.class);

    private static final String ENTITY_NAME = "resourceAdminLogin";

    private final ResourceAdminLoginService resourceAdminLoginService;

    public ResourceAdminLoginResource(ResourceAdminLoginService resourceAdminLoginService) {
        this.resourceAdminLoginService = resourceAdminLoginService;
    }

    /**
     * POST  /resource-admin-logins : Create a new resourceAdminLogin.
     *
     * @param resourceAdminLoginDTO the resourceAdminLoginDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new resourceAdminLoginDTO, or with status 400 (Bad Request) if the resourceAdminLogin has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/resource-admin-logins")
    @Timed
    public ResponseEntity<ResourceAdminLoginDTO> createResourceAdminLogin(@Valid @RequestBody ResourceAdminLoginDTO resourceAdminLoginDTO) throws URISyntaxException {
        log.debug("REST request to save ResourceAdminLogin : {}", resourceAdminLoginDTO);
        if (resourceAdminLoginDTO.getId() != null) {
            throw new BadRequestAlertException("A new resourceAdminLogin cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ResourceAdminLoginDTO result = resourceAdminLoginService.save(resourceAdminLoginDTO);
        return ResponseEntity.created(new URI("/api/resource-admin-logins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /resource-admin-logins : Updates an existing resourceAdminLogin.
     *
     * @param resourceAdminLoginDTO the resourceAdminLoginDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated resourceAdminLoginDTO,
     * or with status 400 (Bad Request) if the resourceAdminLoginDTO is not valid,
     * or with status 500 (Internal Server Error) if the resourceAdminLoginDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/resource-admin-logins")
    @Timed
    public ResponseEntity<ResourceAdminLoginDTO> updateResourceAdminLogin(@Valid @RequestBody ResourceAdminLoginDTO resourceAdminLoginDTO) throws URISyntaxException {
        log.debug("REST request to update ResourceAdminLogin : {}", resourceAdminLoginDTO);
        if (resourceAdminLoginDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ResourceAdminLoginDTO result = resourceAdminLoginService.save(resourceAdminLoginDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, resourceAdminLoginDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /resource-admin-logins : get all the resourceAdminLogins.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of resourceAdminLogins in body
     */
    @GetMapping("/resource-admin-logins")
    @Timed
    public ResponseEntity<List<ResourceAdminLoginDTO>> getAllResourceAdminLogins(Pageable pageable) {
        log.debug("REST request to get a page of ResourceAdminLogins");
        Page<ResourceAdminLoginDTO> page = resourceAdminLoginService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/resource-admin-logins");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /resource-admin-logins/:id : get the "id" resourceAdminLogin.
     *
     * @param id the id of the resourceAdminLoginDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the resourceAdminLoginDTO, or with status 404 (Not Found)
     */
    @GetMapping("/resource-admin-logins/{id}")
    @Timed
    public ResponseEntity<ResourceAdminLoginDTO> getResourceAdminLogin(@PathVariable Long id) {
        log.debug("REST request to get ResourceAdminLogin : {}", id);
        Optional<ResourceAdminLoginDTO> resourceAdminLoginDTO = resourceAdminLoginService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resourceAdminLoginDTO);
    }

    /**
     * DELETE  /resource-admin-logins/:id : delete the "id" resourceAdminLogin.
     *
     * @param id the id of the resourceAdminLoginDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/resource-admin-logins/{id}")
    @Timed
    public ResponseEntity<Void> deleteResourceAdminLogin(@PathVariable Long id) {
        log.debug("REST request to delete ResourceAdminLogin : {}", id);
        resourceAdminLoginService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
