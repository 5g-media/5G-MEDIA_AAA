package eu.fivegmedia.aaa.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import eu.fivegmedia.aaa.domain.Authority;
import eu.fivegmedia.aaa.domain.User;
import eu.fivegmedia.aaa.service.CatalogUserService;
import eu.fivegmedia.aaa.service.UserService;
import eu.fivegmedia.aaa.service.dto.CatalogUserDTO;
import eu.fivegmedia.aaa.service.security.dto.UserDTO;
import eu.fivegmedia.aaa.web.rest.errors.BadRequestAlertException;
import eu.fivegmedia.aaa.web.rest.util.HeaderUtil;
import eu.fivegmedia.aaa.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing CatalogUser.
 */
@RestController
@RequestMapping("/api")
public class CatalogUserResource {

    private final Logger log = LoggerFactory.getLogger(CatalogUserResource.class);

    private static final String ENTITY_NAME = "catalogUser";

    private final CatalogUserService catalogUserService;
    private final UserService userService;


    public CatalogUserResource(CatalogUserService catalogUserService, UserService userService) {
        this.catalogUserService = catalogUserService;
        this.userService = userService;

    }

    /**
     * POST  /catalog-users : Create a new catalogUser.
     *
     * @param catalogUserDTO the catalogUserDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new catalogUserDTO, or with status 400 (Bad Request) if the catalogUser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/catalog-users")
    @Timed
    public ResponseEntity<CatalogUserDTO> createCatalogUser(@Valid @RequestBody CatalogUserDTO catalogUserDTO) throws URISyntaxException {
        log.debug("REST request to save CatalogUser : {}", catalogUserDTO);
        if (catalogUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new catalogUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CatalogUserDTO result = catalogUserService.save(catalogUserDTO);
        return ResponseEntity.created(new URI("/api/catalog-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /catalog-users : Updates an existing catalogUser.
     *
     * @param catalogUserDTO the catalogUserDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated catalogUserDTO,
     * or with status 400 (Bad Request) if the catalogUserDTO is not valid,
     * or with status 500 (Internal Server Error) if the catalogUserDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/catalog-users")
    @Timed
    public ResponseEntity<CatalogUserDTO> updateCatalogUser(@Valid @RequestBody CatalogUserDTO catalogUserDTO) throws URISyntaxException {
        log.debug("REST request to update CatalogUser : {}", catalogUserDTO);
        if (catalogUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CatalogUserDTO result = catalogUserService.save(catalogUserDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, catalogUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /catalog-users : get all the catalogUsers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of catalogUsers in body
     */
    @GetMapping("/catalog-users")
    @Timed
    public ResponseEntity<List<CatalogUserDTO>> getAllCatalogUsers(Pageable pageable) {
        log.debug("REST request to get a page of CatalogUsers");
        Page<CatalogUserDTO> page = catalogUserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/catalog-users");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /catalog-users/login/:id : get the "id" user associated to catalogUser.
     *
     * @param id the id of the userDTO associated to the catalogUserDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userDTO, or with status 404 (Not Found)
     */
    @GetMapping("/catalog-users/user/{id}")
    @Timed
    public ResponseEntity<UserDTO> getCatalogUserLogin(@PathVariable Long id) {
        log.debug("REST request to get CatalogUserLogin : {}", id);
        Optional<User> user = userService.getUserWithAuthorities(id);
        
        UserDTO userDTO = null;
       
        if(user.isPresent()) {
        		userDTO= new UserDTO();
        		userDTO.login = user.get().getLogin();
        		StringBuilder roles = new StringBuilder("[ ");
        		for(Authority authority : user.get().getAuthorities()) {
        			roles.append(authority.getName());
        			roles.append(" ");
        		}
        		roles.append("]");
        		userDTO.roles = roles.toString();
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(userDTO));
    }

    /**
     * GET  /catalog-users/:id : get the "id" catalogUser.
     *
     * @param id the id of the catalogUserDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the catalogUserDTO, or with status 404 (Not Found)
     */
    @GetMapping("/catalog-users/{id}")
    @Timed
    public ResponseEntity<CatalogUserDTO> getCatalogUser(@PathVariable Long id) {
        log.debug("REST request to get CatalogUser : {}", id);
        Optional<CatalogUserDTO> catalogUserDTO = catalogUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(catalogUserDTO);
    }

    /**
     * DELETE  /catalog-users/:id : delete the "id" catalogUser.
     *
     * @param id the id of the catalogUserDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/catalog-users/{id}")
    @Timed
    public ResponseEntity<Void> deleteCatalogUser(@PathVariable Long id) {
        log.debug("REST request to delete CatalogUser : {}", id);
        catalogUserService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
