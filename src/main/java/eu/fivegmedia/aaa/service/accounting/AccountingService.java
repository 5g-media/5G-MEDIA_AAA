package eu.fivegmedia.aaa.service.accounting;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import eu.fivegmedia.aaa.domain.AccNsSession;
import eu.fivegmedia.aaa.domain.AccNsSessionMetrics;
import eu.fivegmedia.aaa.domain.AccNsSessionNetwork;
import eu.fivegmedia.aaa.domain.AccVduResourceConsumption;
import eu.fivegmedia.aaa.domain.AccVduSession;
import eu.fivegmedia.aaa.domain.AccVnfSession;
import eu.fivegmedia.aaa.domain.CatalogTenant;
import eu.fivegmedia.aaa.domain.CatalogUser;
import eu.fivegmedia.aaa.domain.ResourceUser;
import eu.fivegmedia.aaa.domain.enumeration.VduResourceEnum;
import eu.fivegmedia.aaa.domain.enumeration.VduTypeEnum;
import eu.fivegmedia.aaa.repository.AccNsSessionMetricsRepository;
import eu.fivegmedia.aaa.repository.AccNsSessionNetworkRepository;
import eu.fivegmedia.aaa.repository.AccNsSessionRepository;
import eu.fivegmedia.aaa.repository.AccVduResourceConsumptionRepository;
import eu.fivegmedia.aaa.repository.AccVduSessionRepository;
import eu.fivegmedia.aaa.repository.AccVnfSessionRepository;
import eu.fivegmedia.aaa.repository.CatalogUserRepository;
import eu.fivegmedia.aaa.repository.ResourceRepository;
import eu.fivegmedia.aaa.service.accounting.dto.AccNsSessionDTO;
import eu.fivegmedia.aaa.service.accounting.dto.AccVduConsumptionDTO;
import eu.fivegmedia.aaa.service.accounting.dto.AccVduSessionDTO;
import eu.fivegmedia.aaa.service.accounting.dto.AccVnfSessionDTO;
import eu.fivegmedia.aaa.service.accounting.dto.ChargeSessionDTO;
import eu.fivegmedia.aaa.service.accounting.dto.CloseSessionDTO;
import eu.fivegmedia.aaa.service.accounting.dto.ResourceDTO;
import eu.fivegmedia.aaa.service.accounting.dto.UserResourceDTO;
import eu.fivegmedia.aaa.service.dto.AccNsSessionMetricsDTO;
import eu.fivegmedia.aaa.service.dto.AccNsSessionNetworkDTO;


@RestController
@RequestMapping("/api/accounting")
@Secured("ROLE_ACCOUNTING")

@Service
@Transactional
public class AccountingService {
	public static final String VERSION = "acc.v2";

    private final Logger log = LoggerFactory.getLogger(AccountingService.class);
    
    private final ResourceRepository resourceRepository;
    private final CatalogUserRepository catalogUserRepository;
    private final AccNsSessionRepository accNsSessionRepository;
    private final AccVnfSessionRepository accVnfSessionRepository;
    private final AccVduSessionRepository accVduSessionRepository;
    private final AccNsSessionMetricsRepository accNsSessionMetricsRepository;
    private final AccNsSessionNetworkRepository accNsSessionNetworkRepository;

    private final AccVduResourceConsumptionRepository accVduResourceConsumptionRepository;


    public AccountingService(ResourceRepository resourceRepository, CatalogUserRepository catalogUserRepository, AccNsSessionRepository accNsSessionRepository, AccVnfSessionRepository accVnfSessionRepository, AccVduSessionRepository accVduSessionRepository, AccVduResourceConsumptionRepository accVduResourceConsumptionRepository, AccNsSessionMetricsRepository accNsSessionMetricsRepository, AccNsSessionNetworkRepository accNsSessionNetworkRepository) {
    		this.resourceRepository = resourceRepository;    
    		this.catalogUserRepository = catalogUserRepository;
        this.accNsSessionRepository = accNsSessionRepository;
        this.accVnfSessionRepository = accVnfSessionRepository;
        this.accVduSessionRepository = accVduSessionRepository;
        this.accVduResourceConsumptionRepository = accVduResourceConsumptionRepository;
        this.accNsSessionMetricsRepository = accNsSessionMetricsRepository;
        this.accNsSessionNetworkRepository = accNsSessionNetworkRepository;
    }
    
    @GetMapping("/version")
    public String getVersion() {
    		return VERSION;
    }
    
    @GetMapping("/availableUserResourceList")
	@Timed
    public List<UserResourceDTO> availableUserResourceList(){
    		List<UserResourceDTO> result = new ArrayList<UserResourceDTO>();
		
    		List<ResourceDTO> resourceList = new ArrayList<ResourceDTO>();
    		for(CatalogUser catalogUser : catalogUserRepository.findAll()) {
    			for(CatalogTenant catalogTenant : catalogUser.getCatalogTenantSets()) {
	    			UserResourceDTO accUserResource = new UserResourceDTO();
	    			accUserResource.catalog_user = catalogUser.getName();
	    			accUserResource.catalog_tenant = catalogTenant.getName();
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
	    			result.add(accUserResource);
    			}
    		}
    		
    		return result;
    }
    
    
    @PostMapping("/openNsSession")
	@Timed
    public Long openNsSession(@RequestBody AccNsSessionDTO nsSession){
		Long result = -1L;
		//TODO change to manage multiple tenants
    		CatalogUser catalogUser = catalogUserRepository.findByName(nsSession.catalog_user);
		if(catalogUser != null) {
    	
	    		AccNsSession accNsSession = new AccNsSession();
	    		accNsSession.setTimestampStart(nsSession.timestamp_sec==null?System.currentTimeMillis() : 1000L*nsSession.timestamp_sec);
	    		accNsSession.setManoId(nsSession.mano_id);
	    		accNsSession.setManoProject(nsSession.mano_project);
	    		accNsSession.setManoUser(nsSession.mano_user);
	    		accNsSession.setNsId(nsSession.ns_id);
	    		accNsSession.setNsName(nsSession.ns_name);
	    		accNsSession.setCatalogUser(catalogUser);
	    		accNsSession.setQualityProfile(nsSession.quality_profile);
	    		accNsSession.setOptimisation(nsSession.optimisation);

	
	    		AccNsSession accNsSessionSaved = accNsSessionRepository.save(accNsSession);
	    		result = accNsSessionSaved.getId();
		}
    		return result;
    }
    
    @PostMapping("/chargeNsSession")
	@Timed
    public Long chargeNsSession(@RequestBody ChargeSessionDTO chargeSession){
    		Long result = -1L;
		Optional<AccNsSession> accNsSessionOptional = accNsSessionRepository.findById(chargeSession.id);
    		if(accNsSessionOptional.isPresent()) {
    			accNsSessionOptional.get().setResourceCost(chargeSession.resourceCost);
    			accNsSessionOptional.get().setQualityProfile(chargeSession.qualityProfile);
    			accNsSessionOptional.get().setOptimisation(chargeSession.optimisation);
    			accNsSessionRepository.save(accNsSessionOptional.get());
    			result = 0L;
    		}
    		return result;
    }
    
    @PostMapping("/closeNsSession")
	@Timed
    public Long closeNsSession(@RequestBody CloseSessionDTO closeSession){
		Long result = -1L;
		Optional<AccNsSession> accNsSessionOptional = accNsSessionRepository.findById(closeSession.id);
    		if(accNsSessionOptional.isPresent()) {
    			accNsSessionOptional.get().setTimestampStop(closeSession.timestamp_sec == null ? System.currentTimeMillis() : 1000L*closeSession.timestamp_sec);
    			accNsSessionRepository.save(accNsSessionOptional.get());
    			result = 0L;
    		}
    		return result;
    }
    
    @PostMapping("/openVnfSession")
	@Timed
    public Long openVnfSession(@RequestBody AccVnfSessionDTO vnfSession){
    		Long result = -1L;
    		Optional<AccNsSession> accNsSessionOptional = accNsSessionRepository.findById(vnfSession.ns_session_id);
    		if(accNsSessionOptional.isPresent()) {
    			AccVnfSession accVnfSession = new AccVnfSession();
    			accVnfSession.setAccNsSession(accNsSessionOptional.get());
	    		accVnfSession.setTimestampStart(vnfSession.timestamp_sec==null?System.currentTimeMillis() : 1000L*vnfSession.timestamp_sec);
			accVnfSession.setVnfId(vnfSession.vnf_id);
			accVnfSession.setVnfName(vnfSession.vnf_name);
			AccVnfSession accVnfSessionSaved = accVnfSessionRepository.save(accVnfSession);
			result = accVnfSessionSaved.getId();
    		}
    	
    		return result;
    }
    
    @PostMapping("/closeVnfSession")
	@Timed
    public Long closeVnfSession(@RequestBody CloseSessionDTO closeSession){
		Long result = -1L;
		Optional<AccVnfSession> accVnfSessionOptional = accVnfSessionRepository.findById(closeSession.id);
		if(accVnfSessionOptional.isPresent()) {
			accVnfSessionOptional.get().setTimestampStop(closeSession.timestamp_sec == null ? System.currentTimeMillis() : 1000L*closeSession.timestamp_sec);
			accVnfSessionRepository.save(accVnfSessionOptional.get());
			result = 0L;
		}
		return result;
    }
    
    @PostMapping("/openVduSession")
	@Timed
    public Long openVduSession(@RequestBody AccVduSessionDTO vduSession){
    		Long result = -1L;
		Optional<AccVnfSession> accVnfSessionOptional = accVnfSessionRepository.findById(vduSession.vnf_session_id);
		if(accVnfSessionOptional.isPresent()) {
			AccVduSession accVduSession = new AccVduSession();
			accVduSession.setAccVnfSession(accVnfSessionOptional.get());
			accVduSession.setTimestampStart(vduSession.timestamp_sec==null?System.currentTimeMillis() : 1000L*vduSession.timestamp_sec);
			accVduSession.setVduId(vduSession.vdu_id);
			accVduSession.setNfvipopId(vduSession.nfvipop_id);
			accVduSession.setFlavorCpuCount(vduSession.flavorCpuCount);
			accVduSession.setFlavorMemoryMb(vduSession.flavorMemoryMb);
			accVduSession.setFlavorDiskGb(vduSession.flavorDiskGb);
			accVduSession.setVduTypeEnum(VduTypeEnum.valueOf(vduSession.vdu_type));
			
			AccVduSession accVduSessionSaved = accVduSessionRepository.save(accVduSession);
			result = accVduSessionSaved.getId();
		}
	
		return result;
    }
    
    @PostMapping("/closeVduSession")
	@Timed
    public Long closeVduSession(@RequestBody CloseSessionDTO closeSession){
    		Long result = -1L;
    		Optional<AccVduSession> accVduSessionOptional = accVduSessionRepository.findById(closeSession.id);
		if(accVduSessionOptional.isPresent()) {
			accVduSessionOptional.get().setTimestampStop(closeSession.timestamp_sec == null ? System.currentTimeMillis() : 1000L*closeSession.timestamp_sec);
			accVduSessionRepository.save(accVduSessionOptional.get());
			result = 0L;
		}
		return result;
    }
    
    @PostMapping("/logVduConsumption")
	@Timed
    public Long logVduConsumption(@RequestBody AccVduConsumptionDTO vduConsumption){
		Long result = -1L;
		Optional<AccVduSession> accVduSessionOptional = accVduSessionRepository.findById(vduConsumption.vdu_session_id);
		if(accVduSessionOptional.isPresent()) {
			AccVduResourceConsumption resourceConsumption = new AccVduResourceConsumption();
			resourceConsumption.setAccVduSession(accVduSessionOptional.get());
			resourceConsumption.setTimestamp(vduConsumption.timestamp_sec == null ? System.currentTimeMillis() : 1000L*vduConsumption.timestamp_sec);
			resourceConsumption.setResourceType(VduResourceEnum.valueOf(vduConsumption.consumption_type));
			resourceConsumption.setValue(vduConsumption.consumption_value);
			
			accVduResourceConsumptionRepository.save(resourceConsumption);
			result = 0L;
		}
		return result;
    }
    
    
    @PostMapping("/createNsSessionMetrics")
	@Timed
    public Long createNsSessionMetrics(@RequestBody AccNsSessionMetricsDTO nsSessionMetrics){
    		Long result = -1L;
		Optional<AccNsSession> accNsSessionOptional = accNsSessionRepository.findById(nsSessionMetrics.getAccNsSessionId());
		if(accNsSessionOptional.isPresent()) {
			AccNsSessionMetrics accNsSessionMetrics = new AccNsSessionMetrics();
			
			accNsSessionMetrics.setAccNsSession(accNsSessionOptional.get());
			accNsSessionMetrics.setNfvipopId(nsSessionMetrics.getNfvipopId());
			accNsSessionMetrics.setSessionLengthSec(nsSessionMetrics.getSessionLengthSec());
			accNsSessionMetrics.setEntropyTI(nsSessionMetrics.getEntropyTI());
			accNsSessionMetrics.setEntropySI(nsSessionMetrics.getEntropySI());
			accNsSessionMetrics.setUserCount(nsSessionMetrics.getUserCount());
			accNsSessionMetrics.setBandwidthAllocatedMbps(nsSessionMetrics.getBandwidthAllocatedMbps());
			accNsSessionMetrics.setBitrateKbps(nsSessionMetrics.getBitrateKbps());
			accNsSessionMetrics.setCompressionLevel(nsSessionMetrics.getCompressionLevel());
			accNsSessionMetrics.setBlur(nsSessionMetrics.getBlur());
			accNsSessionMetrics.setExpectedQoe(nsSessionMetrics.getExpectedQoe());
			accNsSessionMetrics.setMeasuredQoe(nsSessionMetrics.getMeasuredQoe());
			accNsSessionMetrics.setQualityProfile(nsSessionMetrics.getQualityProfile());

			accNsSessionMetrics.setTimestamp(nsSessionMetrics.getTimestamp() == null ? System.currentTimeMillis() : 1000L*nsSessionMetrics.getTimestamp());
			
			AccNsSessionMetrics accNsSessionMetricsSaved = accNsSessionMetricsRepository.save(accNsSessionMetrics);
			result = accNsSessionMetricsSaved.getId();
		}
	
		return result;
    }
    
    @PostMapping("/createNsSessionNetwork")
	@Timed
    public Long createNsSessionNetwork(@RequestBody AccNsSessionNetworkDTO nsSessionNetworkDTO){
    		Long result = -1L;
		Optional<AccNsSession> accNsSessionOptional = accNsSessionRepository.findById(nsSessionNetworkDTO.getAccNsSessionId());
		if(accNsSessionOptional.isPresent()) {
			AccNsSessionNetwork accNsSessionNetwork = new AccNsSessionNetwork();
			
			accNsSessionNetwork.setAccNsSession(accNsSessionOptional.get());
			accNsSessionNetwork.setNfvipopId(nsSessionNetworkDTO.getNfvipopId());
			accNsSessionNetwork.setUsedBandwidthMbps(nsSessionNetworkDTO.getUsedBandwidthMbps());
			accNsSessionNetwork.setMaxBandwidthMbps(nsSessionNetworkDTO.getMaxBandwidthMbps());
			accNsSessionNetwork.setTimestamp(nsSessionNetworkDTO.getTimestamp() == null ? System.currentTimeMillis() : 1000L*nsSessionNetworkDTO.getTimestamp());
			accNsSessionNetwork.setSessionLengthSec(nsSessionNetworkDTO.getSessionLengthSec());

			AccNsSessionNetwork accNsSessionNetworkSaved = accNsSessionNetworkRepository.save(accNsSessionNetwork);
			result = accNsSessionNetworkSaved.getId();
		}
	
		return result;
    }
}

