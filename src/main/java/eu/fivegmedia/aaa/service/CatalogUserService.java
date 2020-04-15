package eu.fivegmedia.aaa.service;

import java.util.Optional;

import org.keycloak.admin.client.Keycloak;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.fivegmedia.aaa.domain.CatalogUser;
import eu.fivegmedia.aaa.domain.User;
import eu.fivegmedia.aaa.repository.CatalogUserRepository;
import eu.fivegmedia.aaa.repository.UserRepository;
import eu.fivegmedia.aaa.service.driver.UtilKeycloak;
import eu.fivegmedia.aaa.service.dto.CatalogUserDTO;
import eu.fivegmedia.aaa.service.mapper.CatalogUserMapper;
/**
 * Service Implementation for managing CatalogUser.
 */
@Service
@Transactional
public class CatalogUserService {

    private final Logger log = LoggerFactory.getLogger(CatalogUserService.class);

    private final UserRepository userRepository;
    
    private final CatalogUserRepository catalogUserRepository;

    private final CatalogUserMapper catalogUserMapper;

    public CatalogUserService(UserRepository userRepository, CatalogUserRepository catalogUserRepository, CatalogUserMapper catalogUserMapper) {
        this.userRepository = userRepository;
    		this.catalogUserRepository = catalogUserRepository;
        this.catalogUserMapper = catalogUserMapper;
    }
    
    @Autowired
    private Environment applicationProperties; 

    /**
     * Save a catalogUser.
     *
     * @param catalogUserDTO the entity to save
     * @return the persisted entity
     */
    public CatalogUserDTO save(CatalogUserDTO catalogUserDTO) {
        log.debug("Request to save CatalogUser : {}", catalogUserDTO);
        
        String keycloakUrl = applicationProperties.getProperty("keycloak.url");
        String keycloakRealm = applicationProperties.getProperty("keycloak.realm");
        String keycloakAdminUser = applicationProperties.getProperty("keycloak.adminUser");
        String keycloakAdminPass = applicationProperties.getProperty("keycloak.adminPass");
        
        Optional<User> userOptional = userRepository.findById(catalogUserDTO.getUserId());
        if(!userOptional.isPresent()) {
        		throw new RuntimeException("User not found " + catalogUserDTO.getUserId());
        }
        	String username = userOptional.get().getLogin();
		String password = userOptional.get().getPassword();
        
        Keycloak keycloakClient = UtilKeycloak.getAdminKeycloakClient(keycloakUrl, keycloakRealm, keycloakAdminUser, keycloakAdminPass);
        UtilKeycloak.createKeycloakUserIfNotExists(keycloakClient, keycloakRealm, username, password);
        
        CatalogUser catalogUser = catalogUserMapper.toEntity(catalogUserDTO);
        catalogUser = catalogUserRepository.save(catalogUser);
        return catalogUserMapper.toDto(catalogUser);
    }

    /**
     * Get all the catalogUsers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CatalogUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CatalogUsers");
        return catalogUserRepository.findAll(pageable)
            .map(catalogUserMapper::toDto);
    }


    /**
     * Get one catalogUser by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<CatalogUserDTO> findOne(Long id) {
        log.debug("Request to get CatalogUser : {}", id);
        return catalogUserRepository.findById(id)
            .map(catalogUserMapper::toDto);
    }

    /**
     * Delete the catalogUser by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete CatalogUser : {}", id);
        
        String keycloakUrl = applicationProperties.getProperty("keycloak.url");
        String keycloakRealm = applicationProperties.getProperty("keycloak.realm");
        String keycloakAdminUser = applicationProperties.getProperty("keycloak.adminUser");
        String keycloakAdminPass = applicationProperties.getProperty("keycloak.adminPass");
        
        Optional<CatalogUser> catalogUserOptional = catalogUserRepository.findById(id);
        if(catalogUserOptional.isPresent()) {
	        	String username = catalogUserOptional.get().getUser().getLogin();
	        
	        Keycloak keycloakClient = UtilKeycloak.getAdminKeycloakClient(keycloakUrl, keycloakRealm, keycloakAdminUser, keycloakAdminPass);
	        UtilKeycloak.deleteKeycloakUserIfExists(keycloakClient, keycloakRealm, username);
        }
        
        catalogUserRepository.deleteById(id);
    }
    
   
}
