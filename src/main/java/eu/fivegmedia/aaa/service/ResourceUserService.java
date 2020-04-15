package eu.fivegmedia.aaa.service;

import java.util.Optional;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.fivegmedia.aaa.domain.Resource;
import eu.fivegmedia.aaa.domain.ResourceUser;
import eu.fivegmedia.aaa.domain.ResourceUserLogin;
import eu.fivegmedia.aaa.domain.enumeration.ResourceEnum;
import eu.fivegmedia.aaa.repository.ResourceRepository;
import eu.fivegmedia.aaa.repository.ResourceUserLoginRepository;
import eu.fivegmedia.aaa.repository.ResourceUserRepository;
import eu.fivegmedia.aaa.service.driver.IDriverDatacenter;
import eu.fivegmedia.aaa.service.driver.IDriverUser;
import eu.fivegmedia.aaa.service.dto.ResourceUserDTO;
import eu.fivegmedia.aaa.service.mapper.ResourceUserMapper;
/**
 * Service Implementation for managing ResourceUser.
 */
@Service
@Transactional
public class ResourceUserService {

	private final Logger log = LoggerFactory.getLogger(ResourceUserService.class);

	private final ResourceUserRepository resourceUserRepository;
	private final ResourceRepository resourceRepository;

	private final ResourceUserLoginRepository resourceUserLoginRepository;

	private final ResourceUserMapper resourceUserMapper;

	public ResourceUserService(ResourceUserRepository resourceUserRepository, ResourceRepository resourceRepository, ResourceUserLoginRepository resourceUserLoginRepository, ResourceUserMapper resourceUserMapper) {
		this.resourceUserRepository = resourceUserRepository;
		this.resourceRepository = resourceRepository;
		this.resourceUserLoginRepository = resourceUserLoginRepository;
		this.resourceUserMapper = resourceUserMapper;
	}

	@Autowired
    private Environment env; 
	
	/**
	 * Save a resourceUser.
	 *
	 * @param resourceUserDTO the entity to save
	 * @return the persisted entity
	 */
	public ResourceUserDTO save(ResourceUserDTO resourceUserDTO) {
		log.debug("Request to save ResourceUser : {}", resourceUserDTO);
		ResourceUser resourceUser = resourceUserMapper.toEntity(resourceUserDTO);
		if(resourceUserDTO.getId() != null) {
			Optional<ResourceUser> resourceUserDBOptional = resourceUserRepository.findById(resourceUserDTO.getId());
			
			if(resourceUserDBOptional.isPresent()) {
				ResourceUser resourceUserDB = resourceUserDBOptional.get();
				Resource resourceDB = resourceRepository.findById(resourceUserDTO.getResourceId()).get();
				if(resourceDB != null) {
					ResourceEnum resourceType = resourceDB.getResourceEnum();
					
					if(resourceType != null) {
					
						IDriverUser aaaDriverUser = null;
						try{
							aaaDriverUser = (IDriverUser) Class.forName("eu.fivegmedia.aaa.service.driver." + resourceDB.getAuthDriver()).newInstance();
							aaaDriverUser.setApplicationProperties(env);
						}catch(Exception e) {
							throw new RuntimeException(e);
						}
						
						if(resourceType.toString().startsWith("OSM_")) {
							Long resourceUserLoginId = resourceUserDTO.getResourceUserLoginId();
			
							//DriverOSM3/4/5 to create user on OSM - triggered ONLY when resourceUser is assigned/removed
							//create user
							if(resourceUserDB.getResourceUserLogin() == null && resourceUserLoginId != null) {
								aaaDriverUser.createUser(resourceUser, resourceUserDB, resourceUserLoginId, resourceUserLoginRepository);
							}
							//delete user
							else if(resourceUserDB.getResourceUserLogin() != null && resourceUserDTO.getResourceUserLoginId() == null) {
								aaaDriverUser.deleteUser(resourceUser, resourceUserDB, resourceUserDB.getResourceUserLogin().getId(), resourceUserLoginRepository);
							}
						}
						else if(ResourceEnum.OPENSTACK.equals(resourceType)) {
							Long resourceUserLoginId = resourceUserDTO.getResourceUserLoginId();
			
							//DriverOpenStack to create user on OpenStack - triggered ONLY when resourceUser is assigned/removed
							//create user
							if(resourceUserDB.getResourceUserLogin() == null && resourceUserLoginId != null) {
								aaaDriverUser.createUser(resourceUser, resourceUserDB, resourceUserLoginId, resourceUserLoginRepository);
							}
							//delete user
							else if(resourceUserDB.getResourceUserLogin() != null && resourceUserDTO.getResourceUserLoginId() == null) {
								aaaDriverUser.deleteUser(resourceUser, resourceUserDB, resourceUserDB.getResourceUserLogin().getId(), resourceUserLoginRepository);
							}
			
							//if groupName changes => add a datacenter on the associated OSM
							//I assume changes are ONLY on VIM resources
							//groupname changed, so a new datacenter has to be attached if groupname is != null
							if(resourceUserDTO.getGroupname() != null && resourceUserDB.getGroupname() == null) {
								//a user/pass is needed in case we want to create/attach a datacenter: either resourceUserLoginId from the DTO or the existing from resourceUserDB
								ResourceUserLogin resourceUserLogin = null;
								if(resourceUserLoginId != null) {
									resourceUserLogin = resourceUserLoginRepository.findById(resourceUserLoginId).get();
								}
								else {
									resourceUserLogin = resourceUserDB.getResourceUserLogin();
								}
								if(resourceUserLogin != null) {
									
									Optional<ResourceUser> resourceUserMANOoptional = resourceUserRepository.findById(Long.parseLong(resourceUserDTO.getGroupname()));
									if(resourceUserMANOoptional.isPresent()) {
										ResourceUser resourceUserMANO = resourceUserMANOoptional.get();
										
										String resourceMANOType = resourceUserMANO.getResource().getResourceEnum().toString();
										if(resourceMANOType != null && resourceMANOType.toString().startsWith("OSM_")) {
											String osmTenant = resourceUserMANO.getTenant();
											String datacenterName = resourceUserRepository.findById(Long.parseLong(resourceUserDTO.getGroupname())).get().getName();
											String datacenterURL = new JSONObject(resourceUserDB.getResource().getAuthConf()).getString("keystone");
											String datacenterTenant = resourceUserDB.getTenant();
											String datacenterUsername = resourceUserLogin.getUsername();
											String datacenterPassword = resourceUserLogin.getPassword();
											try {
												IDriverDatacenter aaaDriverDatacenter = (IDriverDatacenter) Class.forName("eu.fivegmedia.aaa.service.driver." + resourceUserMANO.getResource().getAuthDriver()).newInstance();
												aaaDriverDatacenter.setApplicationProperties(env);
												aaaDriverDatacenter.createDatacenterAndAttach(resourceUserMANO, osmTenant, datacenterName, datacenterURL, datacenterTenant, datacenterUsername, datacenterPassword);
											}catch(Exception e) {
												throw new RuntimeException(e);
											}
										}
									}
								}
							}
							//... or to be detached if groupname is == null
							else if((resourceUserDTO.getGroupname() == null || resourceUserDTO.getGroupname().equals("null")) && resourceUserDB.getGroupname() != null) {
								//Angular BUG on option managing toString of a number
								resourceUser.setGroupname(null);
								Optional<ResourceUser> resourceUserMANOoptional = resourceUserRepository.findById(Long.parseLong(resourceUserDB.getGroupname()));
								if(resourceUserMANOoptional.isPresent()) {
									ResourceUser resourceUserMANO = resourceUserMANOoptional.get();
									
									String resourceMANOType = resourceUserMANO.getResource().getResourceEnum().toString();
									if(resourceMANOType != null && resourceMANOType.toString().startsWith("OSM_")) {
										String osmTenant = resourceUserMANO.getTenant();
										String datacenterName = resourceUserMANO.getName();
										String datacenterTenant = resourceUserDB.getTenant();
										try {
											IDriverDatacenter aaaDriverDatacenter = (IDriverDatacenter) Class.forName("eu.fivegmedia.aaa.service.driver." + resourceUserMANO.getResource().getAuthDriver()).newInstance();
											aaaDriverDatacenter.setApplicationProperties(env);
											aaaDriverDatacenter.detachDatacenterAndDelete(resourceUserMANO, osmTenant, datacenterName, datacenterTenant);
										}catch(Exception e) {
											throw new RuntimeException(e);
										}
									}
								}
							}
						}
					}
				}
	
			}
		}
		resourceUser = resourceUserRepository.save(resourceUser);
		return resourceUserMapper.toDto(resourceUser);
	}

	/**
	 * Get all the resourceUsers.
	 *
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	@Transactional(readOnly = true)
	public Page<ResourceUserDTO> findAll(Pageable pageable) {
		log.debug("Request to get all ResourceUsers");
		return resourceUserRepository.findAll(pageable)
				.map(resourceUserMapper::toDto);
	}

	/**
	 * Get all the resourceUsers by catalogUserID.
	 *
	 * @param pageable the pagination information
	 * @return the list of entities by catalogUserID
	 */
	@Transactional(readOnly = true)
	public Page<ResourceUserDTO> findAllByCatalogUser(Long id, Pageable pageable) {
		log.debug("Request to get all ResourceUsers by catalogId");
		return resourceUserRepository.findByCatalogUserId(id, pageable)
				.map(resourceUserMapper::toDto);
	}

	/**
	 * Get all the resourceUsers by resourceType.
	 *
	 * @param pageable the pagination information
	 * @return the list of entities by resourceType
	 */
	@Transactional(readOnly = true)
	public Page<ResourceUserDTO> findAllByResourceType(String resourceType, Pageable pageable) {
		log.debug("Request to get all ResourceUsers by catalogId");
		ResourceEnum resourceEnum = ResourceEnum.valueOf(resourceType);
		return resourceUserRepository.findByResourceEnum(resourceEnum, pageable)
				.map(resourceUserMapper::toDto);
	}


	/**
	 * Get one resourceUser by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Transactional(readOnly = true)
	public Optional<ResourceUserDTO> findOne(Long id) {
		log.debug("Request to get ResourceUser : {}", id);
		return resourceUserRepository.findById(id)
				.map(resourceUserMapper::toDto);
	}

	/**
	 * Delete the resourceUser by id.
	 *
	 * @param id the id of the entity
	 */
	public void delete(Long id) {
		log.debug("Request to delete ResourceUser : {}", id);
		resourceUserRepository.deleteById(id);
	}
}
