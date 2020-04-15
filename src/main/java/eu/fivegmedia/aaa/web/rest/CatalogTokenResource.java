package eu.fivegmedia.aaa.web.rest;

import com.codahale.metrics.annotation.Timed;
import eu.fivegmedia.aaa.service.CatalogTokenService;
import eu.fivegmedia.aaa.web.rest.errors.BadRequestAlertException;
import eu.fivegmedia.aaa.web.rest.util.HeaderUtil;
import eu.fivegmedia.aaa.web.rest.util.PaginationUtil;
import eu.fivegmedia.aaa.service.dto.CatalogTokenDTO;
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
 * REST controller for managing CatalogToken.
 */
@RestController
@RequestMapping("/api")
public class CatalogTokenResource {

    private final Logger log = LoggerFactory.getLogger(CatalogTokenResource.class);

    private static final String ENTITY_NAME = "catalogToken";

    private final CatalogTokenService catalogTokenService;

    public CatalogTokenResource(CatalogTokenService catalogTokenService) {
        this.catalogTokenService = catalogTokenService;
    }

    /**
     * POST  /catalog-tokens : Create a new catalogToken.
     *
     * @param catalogTokenDTO the catalogTokenDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new catalogTokenDTO, or with status 400 (Bad Request) if the catalogToken has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/catalog-tokens")
    @Timed
    public ResponseEntity<CatalogTokenDTO> createCatalogToken(@Valid @RequestBody CatalogTokenDTO catalogTokenDTO) throws URISyntaxException {
        log.debug("REST request to save CatalogToken : {}", catalogTokenDTO);
        if (catalogTokenDTO.getId() != null) {
            throw new BadRequestAlertException("A new catalogToken cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CatalogTokenDTO result = catalogTokenService.save(catalogTokenDTO);
        return ResponseEntity.created(new URI("/api/catalog-tokens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /catalog-tokens : Updates an existing catalogToken.
     *
     * @param catalogTokenDTO the catalogTokenDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated catalogTokenDTO,
     * or with status 400 (Bad Request) if the catalogTokenDTO is not valid,
     * or with status 500 (Internal Server Error) if the catalogTokenDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/catalog-tokens")
    @Timed
    public ResponseEntity<CatalogTokenDTO> updateCatalogToken(@Valid @RequestBody CatalogTokenDTO catalogTokenDTO) throws URISyntaxException {
        log.debug("REST request to update CatalogToken : {}", catalogTokenDTO);
        if (catalogTokenDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CatalogTokenDTO result = catalogTokenService.save(catalogTokenDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, catalogTokenDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /catalog-tokens : get all the catalogTokens.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of catalogTokens in body
     */
    @GetMapping("/catalog-tokens")
    @Timed
    public ResponseEntity<List<CatalogTokenDTO>> getAllCatalogTokens(Pageable pageable) {
        log.debug("REST request to get a page of CatalogTokens");
        Page<CatalogTokenDTO> page = catalogTokenService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/catalog-tokens");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /catalog-tokens/:id : get the "id" catalogToken.
     *
     * @param id the id of the catalogTokenDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the catalogTokenDTO, or with status 404 (Not Found)
     */
    @GetMapping("/catalog-tokens/{id}")
    @Timed
    public ResponseEntity<CatalogTokenDTO> getCatalogToken(@PathVariable Long id) {
        log.debug("REST request to get CatalogToken : {}", id);
        Optional<CatalogTokenDTO> catalogTokenDTO = catalogTokenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(catalogTokenDTO);
    }

    /**
     * DELETE  /catalog-tokens/:id : delete the "id" catalogToken.
     *
     * @param id the id of the catalogTokenDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/catalog-tokens/{id}")
    @Timed
    public ResponseEntity<Void> deleteCatalogToken(@PathVariable Long id) {
        log.debug("REST request to delete CatalogToken : {}", id);
        catalogTokenService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
