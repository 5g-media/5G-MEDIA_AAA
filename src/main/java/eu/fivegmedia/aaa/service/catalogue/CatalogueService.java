package eu.fivegmedia.aaa.service.catalogue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import eu.fivegmedia.aaa.domain.CatalogUser;
import eu.fivegmedia.aaa.domain.ResourceToken;
import eu.fivegmedia.aaa.domain.ResourceUser;
import eu.fivegmedia.aaa.repository.CatalogUserRepository;
import eu.fivegmedia.aaa.repository.ResourceTokenRepository;
import eu.fivegmedia.aaa.service.accounting.dto.ResourceDTO;
import eu.fivegmedia.aaa.service.accounting.dto.UserResourceDTO;
import eu.fivegmedia.aaa.service.catalogue.driver.TokenProvider;
import eu.fivegmedia.aaa.service.catalogue.dto.CatalogueUserResourceDTO;
import eu.fivegmedia.aaa.service.catalogue.dto.ResourceCredentialsDTO;

@RestController
@RequestMapping("/api/catalogue")
@Secured("ROLE_CATALOG_INTERNAL")

@Service
@Transactional
public class CatalogueService {
	public static final String VERSION = "cat.v2";

    private final Logger log = LoggerFactory.getLogger(CatalogueService.class);
    
    @Autowired
    private CatalogUserRepository catalogUserRepository;
    
    @Autowired
    private ResourceTokenRepository resourceTokenRepository;

    
    public CatalogueService(CatalogUserRepository catalogUserRepository) {
    		this.catalogUserRepository = catalogUserRepository;
    }
    
    @GetMapping("/version")
    public String getVersion() {
    		return VERSION;
    }
    
    @GetMapping("/availableUserResources/{catalogue_tenant}/{catalogue_user}")
	@Timed
    public UserResourceDTO availableUserResources(@PathVariable String catalogue_tenant, @PathVariable String catalogue_user){
    		UserResourceDTO result = null;
    		//TODO change to manage multiple tenants
    		CatalogUser catalogUser = catalogUserRepository.findByName(catalogue_user);
    		if(catalogUser != null) {
    			UserResourceDTO accUserResource = new UserResourceDTO();
    			accUserResource.catalog_user = catalogue_user; 
    			accUserResource.catalog_tenant = catalogue_tenant;
    			List<ResourceDTO> resourceList = new ArrayList<ResourceDTO>();
    			for(ResourceUser resourceUser : catalogUser.getResourceUserSets()) {
    				if(resourceUser.isEnabled()){
    					if(resourceUser.getResourceUserLogin() != null) {
		    				ResourceDTO resourceDTO = new ResourceDTO();
		    				resourceDTO.resource_id = resourceUser.getResource().getResourceid();
		    				resourceDTO.resource_type = resourceUser.getResource().getResourceEnum().name();
		    				resourceDTO.resource_user = resourceUser.getResourceUserLogin().getUsername(); 
		    				resourceDTO.resource_tenant = resourceUser.getTenant();
		    				resourceDTO.resource_conf = resourceUser.getResource().getAuthConf();
		    				resourceList.add(resourceDTO);
    					}
    					else if(resourceUser.getResource().getResourceAdminLogin() != null) {
    						ResourceDTO resourceDTO = new ResourceDTO();
		    				resourceDTO.resource_id = resourceUser.getResource().getResourceid();
		    				resourceDTO.resource_type = resourceUser.getResource().getResourceEnum().name();
		    				resourceDTO.resource_user = resourceUser.getResource().getResourceAdminLogin().getUsername(); 
		    				resourceDTO.resource_tenant = resourceUser.getTenant();
		    				resourceDTO.resource_conf = resourceUser.getResource().getAuthConf();
		    				resourceList.add(resourceDTO);
    					}
    				}
    			}
    			
    			accUserResource.resources = resourceList.toArray(new ResourceDTO[] {});
    			result = accUserResource;
    		}
    		
    		return result;
    }
    
    @PostMapping("/getResourceCredentialsByCatalogUserResource")
	@Timed
	public ResourceCredentialsDTO getResourceCredentialsByCatalogUserResource(@RequestBody CatalogueUserResourceDTO catalogueUserResource) throws IOException {
    		ResourceCredentialsDTO result = null;
    		//change to manage multiple tenants
    		CatalogUser catalogueUser = catalogUserRepository.findByName(catalogueUserResource.catalog_user);
		if(catalogueUser != null) {
			for(ResourceUser resourceUser : catalogueUser.getResourceUserSets()) {
				if(resourceUser.isEnabled() && resourceUser.getResource().getResourceid().equals(catalogueUserResource.resource_id)) {
					result = new ResourceCredentialsDTO();
					result.catalog_user = catalogueUserResource.catalog_user;
					result.catalog_tenant = catalogueUserResource.catalog_tenant;
					result.resource_type = resourceUser.getResource().getResourceEnum().name();
					List<ResourceToken> resourceTokenList = resourceTokenRepository.findByResourceUserAndValid(resourceUser, true);

					boolean needToCreateToken = true;
					for(ResourceToken resourceToken : resourceTokenList) {
						if(resourceToken.getTimestampExpiration() < System.currentTimeMillis()) {
							resourceToken.setValid(false);
							resourceTokenRepository.saveAndFlush(resourceToken);
						}
						else {
							result.auth_info = resourceToken.getValue();
							needToCreateToken = false;
						}
					}
					if(needToCreateToken) {
						TokenProvider.addCredentials(resourceTokenRepository, resourceUser, result);
					}

				}
			}

		}
		return result;
	}
    
}
