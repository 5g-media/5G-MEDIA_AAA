package eu.fivegmedia.aaa.web.rest;

import com.codahale.metrics.annotation.Timed;
import eu.fivegmedia.aaa.service.CatalogTenantService;
import eu.fivegmedia.aaa.web.rest.errors.BadRequestAlertException;
import eu.fivegmedia.aaa.web.rest.util.HeaderUtil;
import eu.fivegmedia.aaa.web.rest.util.PaginationUtil;
import eu.fivegmedia.aaa.service.dto.CatalogTenantDTO;
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
 * REST controller for managing CatalogTenant.
 */
@RestController
@RequestMapping("/api")
public class CatalogTenantResource {

    private final Logger log = LoggerFactory.getLogger(CatalogTenantResource.class);

    private static final String ENTITY_NAME = "catalogTenant";

    private final CatalogTenantService catalogTenantService;

    public CatalogTenantResource(CatalogTenantService catalogTenantService) {
        this.catalogTenantService = catalogTenantService;
    }

    /**
     * POST  /catalog-tenants : Create a new catalogTenant.
     *
     * @param catalogTenantDTO the catalogTenantDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new catalogTenantDTO, or with status 400 (Bad Request) if the catalogTenant has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/catalog-tenants")
    @Timed
    public ResponseEntity<CatalogTenantDTO> createCatalogTenant(@Valid @RequestBody CatalogTenantDTO catalogTenantDTO) throws URISyntaxException {
        log.debug("REST request to save CatalogTenant : {}", catalogTenantDTO);
        if (catalogTenantDTO.getId() != null) {
            throw new BadRequestAlertException("A new catalogTenant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CatalogTenantDTO result = catalogTenantService.save(catalogTenantDTO);
        return ResponseEntity.created(new URI("/api/catalog-tenants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /catalog-tenants : Updates an existing catalogTenant.
     *
     * @param catalogTenantDTO the catalogTenantDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated catalogTenantDTO,
     * or with status 400 (Bad Request) if the catalogTenantDTO is not valid,
     * or with status 500 (Internal Server Error) if the catalogTenantDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/catalog-tenants")
    @Timed
    public ResponseEntity<CatalogTenantDTO> updateCatalogTenant(@Valid @RequestBody CatalogTenantDTO catalogTenantDTO) throws URISyntaxException {
        log.debug("REST request to update CatalogTenant : {}", catalogTenantDTO);
        if (catalogTenantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CatalogTenantDTO result = catalogTenantService.save(catalogTenantDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, catalogTenantDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /catalog-tenants : get all the catalogTenants.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of catalogTenants in body
     */
    @GetMapping("/catalog-tenants")
    @Timed
    public ResponseEntity<List<CatalogTenantDTO>> getAllCatalogTenants(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of CatalogTenants");
        Page<CatalogTenantDTO> page;
        if (eagerload) {
            page = catalogTenantService.findAllWithEagerRelationships(pageable);
        } else {
            page = catalogTenantService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/catalog-tenants?eagerload=%b", eagerload));
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /catalog-tenants/:id : get the "id" catalogTenant.
     *
     * @param id the id of the catalogTenantDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the catalogTenantDTO, or with status 404 (Not Found)
     */
    @GetMapping("/catalog-tenants/{id}")
    @Timed
    public ResponseEntity<CatalogTenantDTO> getCatalogTenant(@PathVariable Long id) {
        log.debug("REST request to get CatalogTenant : {}", id);
        Optional<CatalogTenantDTO> catalogTenantDTO = catalogTenantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(catalogTenantDTO);
    }

    /**
     * DELETE  /catalog-tenants/:id : delete the "id" catalogTenant.
     *
     * @param id the id of the catalogTenantDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/catalog-tenants/{id}")
    @Timed
    public ResponseEntity<Void> deleteCatalogTenant(@PathVariable Long id) {
        log.debug("REST request to delete CatalogTenant : {}", id);
        catalogTenantService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
