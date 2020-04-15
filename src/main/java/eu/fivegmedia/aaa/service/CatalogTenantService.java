package eu.fivegmedia.aaa.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.keycloak.admin.client.Keycloak;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.fivegmedia.aaa.domain.CatalogTenant;
import eu.fivegmedia.aaa.domain.CatalogUser;
import eu.fivegmedia.aaa.domain.User;
import eu.fivegmedia.aaa.repository.CatalogTenantRepository;
import eu.fivegmedia.aaa.repository.CatalogUserRepository;
import eu.fivegmedia.aaa.repository.UserRepository;
import eu.fivegmedia.aaa.service.driver.UtilKeycloak;
import eu.fivegmedia.aaa.service.dto.CatalogTenantDTO;
import eu.fivegmedia.aaa.service.dto.CatalogUserDTO;
import eu.fivegmedia.aaa.service.mapper.CatalogTenantMapper;
/**
 * Service Implementation for managing CatalogTenant.
 */
@Service
@Transactional
public class CatalogTenantService {

    private final Logger log = LoggerFactory.getLogger(CatalogTenantService.class);

    private final CatalogTenantRepository catalogTenantRepository;
    
    private final CatalogUserRepository catalogUserRepository;

    private final CatalogTenantMapper catalogTenantMapper;
    
    private final UserRepository userRepository;

    
    @Autowired
    private Environment applicationProperties; 

    public CatalogTenantService(CatalogTenantRepository catalogTenantRepository, CatalogUserRepository catalogUserRepository, CatalogTenantMapper catalogTenantMapper, UserRepository userRepository) {
        this.catalogTenantRepository = catalogTenantRepository;
        this.catalogUserRepository = catalogUserRepository;
        this.catalogTenantMapper = catalogTenantMapper;
        this.userRepository = userRepository;
    }

    /**
     * Save a catalogTenant.
     *
     * @param catalogTenantDTO the entity to save
     * @return the persisted entity
     */
    public CatalogTenantDTO save(CatalogTenantDTO catalogTenantDTO) {
        log.debug("Request to save CatalogTenant : {}", catalogTenantDTO);
		String keycloakUrl = applicationProperties.getProperty("keycloak.url");
        String keycloakRealm = applicationProperties.getProperty("keycloak.realm");
        String keycloakAdminUser = applicationProperties.getProperty("keycloak.adminUser");
        String keycloakAdminPass = applicationProperties.getProperty("keycloak.adminPass");

        CatalogTenant catalogTenant = catalogTenantMapper.toEntity(catalogTenantDTO);
        if(catalogTenantDTO.getId() != null) {
	        HashSet<String> oldUserLoginSet = new HashSet<String>();
	        HashSet<String> newUserLoginSet = new HashSet<String>();
	        
	        Optional<CatalogTenant> catalogTenantOptional = catalogTenantRepository.findById(catalogTenantDTO.getId());
	        if(catalogTenantOptional.isPresent()) {
	        		for(CatalogUser catalogUser : catalogTenantOptional.get().getCatalogUserSets()) {
	        			oldUserLoginSet.add(catalogUser.getUser().getLogin());
	        		}
	        }
	        
	        for(CatalogUser catalogUser : catalogTenant.getCatalogUserSets()) {
	        		Optional<User> userOptional = userRepository.findById(catalogUser.getUser().getId());
	        		if(userOptional.isPresent()) {
	        			newUserLoginSet.add(userOptional.get().getLogin());
	        		}
	        }
	        updateAttributesOnKeycloak(oldUserLoginSet, newUserLoginSet, catalogTenant.getName(), keycloakUrl, keycloakRealm, keycloakAdminUser, keycloakAdminPass);
        }

        catalogTenant = catalogTenantRepository.save(catalogTenant);
        return catalogTenantMapper.toDto(catalogTenant);
    }

    /**
     * Get all the catalogTenants.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CatalogTenantDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CatalogTenants");
        return catalogTenantRepository.findAll(pageable)
            .map(catalogTenantMapper::toDto);
    }

    /**
     * Get all the CatalogTenant with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<CatalogTenantDTO> findAllWithEagerRelationships(Pageable pageable) {
        return catalogTenantRepository.findAllWithEagerRelationships(pageable).map(catalogTenantMapper::toDto);
    }
    

    /**
     * Get one catalogTenant by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<CatalogTenantDTO> findOne(Long id) {
        log.debug("Request to get CatalogTenant : {}", id);
        return catalogTenantRepository.findOneWithEagerRelationships(id)
            .map(catalogTenantMapper::toDto);
    }

    /**
     * Delete the catalogTenant by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete CatalogTenant : {}", id);

		String keycloakUrl = applicationProperties.getProperty("keycloak.url");
        String keycloakRealm = applicationProperties.getProperty("keycloak.realm");
        String keycloakAdminUser = applicationProperties.getProperty("keycloak.adminUser");
        String keycloakAdminPass = applicationProperties.getProperty("keycloak.adminPass");
        Optional<CatalogTenant> catalogTenantOptional = catalogTenantRepository.findById(id);
        
        if(catalogTenantOptional.isPresent()) {
            HashSet<String> oldUserLoginSet = new HashSet<String>();
            HashSet<String> newUserLoginSet = new HashSet<String>();

        		for(CatalogUser catalogUser : catalogTenantOptional.get().getCatalogUserSets()) {
        			oldUserLoginSet.add(catalogUser.getUser().getLogin());
        		}
        		
        		updateAttributesOnKeycloak(oldUserLoginSet, newUserLoginSet, catalogTenantOptional.get().getName(), keycloakUrl, keycloakRealm, keycloakAdminUser, keycloakAdminPass);
        }
        
        catalogTenantRepository.deleteById(id);
        
        
    }
    
    private void updateAttributesOnKeycloak(Set<String> oldUserSet, Set<String> newUserSet, String catalogTenant, String keycloakUrl, String keycloakRealm, String keycloakAdminUser, String keycloakAdminPass) {
		Keycloak keycloakClient = UtilKeycloak.getAdminKeycloakClient(keycloakUrl, keycloakRealm, keycloakAdminUser, keycloakAdminPass);
		try{
			for(String oldUser : oldUserSet) {
				UtilKeycloak.setAttributeOnUser(keycloakClient, keycloakRealm, oldUser, "project", catalogTenant, false);	
			}
			for(String newUser : newUserSet) {
				UtilKeycloak.setAttributeOnUser(keycloakClient, keycloakRealm, newUser, "project", catalogTenant, true);	
			}
			
		}
		finally {
			if(keycloakClient != null) {
				keycloakClient.close();
			}
		}
    }
}
