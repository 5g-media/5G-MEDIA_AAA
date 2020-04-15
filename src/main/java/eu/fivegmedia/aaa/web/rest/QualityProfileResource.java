package eu.fivegmedia.aaa.web.rest;

import com.codahale.metrics.annotation.Timed;
import eu.fivegmedia.aaa.service.QualityProfileService;
import eu.fivegmedia.aaa.web.rest.errors.BadRequestAlertException;
import eu.fivegmedia.aaa.web.rest.util.HeaderUtil;
import eu.fivegmedia.aaa.web.rest.util.PaginationUtil;
import eu.fivegmedia.aaa.service.dto.QualityProfileDTO;
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
 * REST controller for managing QualityProfile.
 */
@RestController
@RequestMapping("/api")
public class QualityProfileResource {

    private final Logger log = LoggerFactory.getLogger(QualityProfileResource.class);

    private static final String ENTITY_NAME = "qualityProfile";

    private final QualityProfileService qualityProfileService;

    public QualityProfileResource(QualityProfileService qualityProfileService) {
        this.qualityProfileService = qualityProfileService;
    }

    /**
     * POST  /quality-profiles : Create a new qualityProfile.
     *
     * @param qualityProfileDTO the qualityProfileDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new qualityProfileDTO, or with status 400 (Bad Request) if the qualityProfile has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quality-profiles")
    @Timed
    public ResponseEntity<QualityProfileDTO> createQualityProfile(@Valid @RequestBody QualityProfileDTO qualityProfileDTO) throws URISyntaxException {
        log.debug("REST request to save QualityProfile : {}", qualityProfileDTO);
        if (qualityProfileDTO.getId() != null) {
            throw new BadRequestAlertException("A new qualityProfile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QualityProfileDTO result = qualityProfileService.save(qualityProfileDTO);
        return ResponseEntity.created(new URI("/api/quality-profiles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quality-profiles : Updates an existing qualityProfile.
     *
     * @param qualityProfileDTO the qualityProfileDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated qualityProfileDTO,
     * or with status 400 (Bad Request) if the qualityProfileDTO is not valid,
     * or with status 500 (Internal Server Error) if the qualityProfileDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quality-profiles")
    @Timed
    public ResponseEntity<QualityProfileDTO> updateQualityProfile(@Valid @RequestBody QualityProfileDTO qualityProfileDTO) throws URISyntaxException {
        log.debug("REST request to update QualityProfile : {}", qualityProfileDTO);
        if (qualityProfileDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QualityProfileDTO result = qualityProfileService.save(qualityProfileDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qualityProfileDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quality-profiles : get all the qualityProfiles.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of qualityProfiles in body
     */
    @GetMapping("/quality-profiles")
    @Timed
    public ResponseEntity<List<QualityProfileDTO>> getAllQualityProfiles(Pageable pageable) {
        log.debug("REST request to get a page of QualityProfiles");
        Page<QualityProfileDTO> page = qualityProfileService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quality-profiles");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /quality-profiles/:id : get the "id" qualityProfile.
     *
     * @param id the id of the qualityProfileDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the qualityProfileDTO, or with status 404 (Not Found)
     */
    @GetMapping("/quality-profiles/{id}")
    @Timed
    public ResponseEntity<QualityProfileDTO> getQualityProfile(@PathVariable Long id) {
        log.debug("REST request to get QualityProfile : {}", id);
        Optional<QualityProfileDTO> qualityProfileDTO = qualityProfileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qualityProfileDTO);
    }

    /**
     * DELETE  /quality-profiles/:id : delete the "id" qualityProfile.
     *
     * @param id the id of the qualityProfileDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quality-profiles/{id}")
    @Timed
    public ResponseEntity<Void> deleteQualityProfile(@PathVariable Long id) {
        log.debug("REST request to delete QualityProfile : {}", id);
        qualityProfileService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
