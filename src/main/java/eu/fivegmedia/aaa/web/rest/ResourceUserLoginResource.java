package eu.fivegmedia.aaa.web.rest;

import com.codahale.metrics.annotation.Timed;
import eu.fivegmedia.aaa.service.ResourceUserLoginService;
import eu.fivegmedia.aaa.web.rest.errors.BadRequestAlertException;
import eu.fivegmedia.aaa.web.rest.util.HeaderUtil;
import eu.fivegmedia.aaa.web.rest.util.PaginationUtil;
import eu.fivegmedia.aaa.service.dto.ResourceUserLoginDTO;
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
 * REST controller for managing ResourceUserLogin.
 */
@RestController
@RequestMapping("/api")
public class ResourceUserLoginResource {

    private final Logger log = LoggerFactory.getLogger(ResourceUserLoginResource.class);

    private static final String ENTITY_NAME = "resourceUserLogin";

    private final ResourceUserLoginService resourceUserLoginService;

    public ResourceUserLoginResource(ResourceUserLoginService resourceUserLoginService) {
        this.resourceUserLoginService = resourceUserLoginService;
    }

    /**
     * POST  /resource-user-logins : Create a new resourceUserLogin.
     *
     * @param resourceUserLoginDTO the resourceUserLoginDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new resourceUserLoginDTO, or with status 400 (Bad Request) if the resourceUserLogin has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/resource-user-logins")
    @Timed
    public ResponseEntity<ResourceUserLoginDTO> createResourceUserLogin(@Valid @RequestBody ResourceUserLoginDTO resourceUserLoginDTO) throws URISyntaxException {
        log.debug("REST request to save ResourceUserLogin : {}", resourceUserLoginDTO);
        if (resourceUserLoginDTO.getId() != null) {
            throw new BadRequestAlertException("A new resourceUserLogin cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ResourceUserLoginDTO result = resourceUserLoginService.save(resourceUserLoginDTO);
        return ResponseEntity.created(new URI("/api/resource-user-logins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /resource-user-logins : Updates an existing resourceUserLogin.
     *
     * @param resourceUserLoginDTO the resourceUserLoginDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated resourceUserLoginDTO,
     * or with status 400 (Bad Request) if the resourceUserLoginDTO is not valid,
     * or with status 500 (Internal Server Error) if the resourceUserLoginDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/resource-user-logins")
    @Timed
    public ResponseEntity<ResourceUserLoginDTO> updateResourceUserLogin(@Valid @RequestBody ResourceUserLoginDTO resourceUserLoginDTO) throws URISyntaxException {
        log.debug("REST request to update ResourceUserLogin : {}", resourceUserLoginDTO);
        if (resourceUserLoginDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ResourceUserLoginDTO result = resourceUserLoginService.save(resourceUserLoginDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, resourceUserLoginDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /resource-user-logins : get all the resourceUserLogins.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of resourceUserLogins in body
     */
    @GetMapping("/resource-user-logins")
    @Timed
    public ResponseEntity<List<ResourceUserLoginDTO>> getAllResourceUserLogins(Pageable pageable) {
        log.debug("REST request to get a page of ResourceUserLogins");
        Page<ResourceUserLoginDTO> page = resourceUserLoginService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/resource-user-logins");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /resource-user-logins/:id : get the "id" resourceUserLogin.
     *
     * @param id the id of the resourceUserLoginDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the resourceUserLoginDTO, or with status 404 (Not Found)
     */
    @GetMapping("/resource-user-logins/{id}")
    @Timed
    public ResponseEntity<ResourceUserLoginDTO> getResourceUserLogin(@PathVariable Long id) {
        log.debug("REST request to get ResourceUserLogin : {}", id);
        Optional<ResourceUserLoginDTO> resourceUserLoginDTO = resourceUserLoginService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resourceUserLoginDTO);
    }

    /**
     * DELETE  /resource-user-logins/:id : delete the "id" resourceUserLogin.
     *
     * @param id the id of the resourceUserLoginDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/resource-user-logins/{id}")
    @Timed
    public ResponseEntity<Void> deleteResourceUserLogin(@PathVariable Long id) {
        log.debug("REST request to delete ResourceUserLogin : {}", id);
        resourceUserLoginService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
