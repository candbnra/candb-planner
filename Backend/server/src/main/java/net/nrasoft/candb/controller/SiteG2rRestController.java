package net.nrasoft.candb.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.nrasoft.candb.exception.ResourceNotFoundException;
import net.nrasoft.candb.model.SiteG2r;
import net.nrasoft.candb.repository.SiteG2rRepositoryImpl;

import net.nrasoft.candb.repository.SiteG2rRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class SiteG2rRestController{
	
	private SiteG2rRepository siteG2rRepository = new SiteG2rRepositoryImpl();

	@GetMapping("/sites_g2r")
	public Collection<SiteG2r> findAllSites() {
		return siteG2rRepository.findAll();
	}

	@GetMapping("/sites_g2r/searchByCode")
	public Collection<SiteG2r> findSitesByCode(@RequestParam String code) {
		return siteG2rRepository.findByCode(code);
	}
	@GetMapping("/sites_g2r/searchByName")
	public Collection<SiteG2r> findSitesByName(@RequestParam String name) {
		return siteG2rRepository.findByName(name);
	}	

	@GetMapping("/sites_g2r/searchByParams")
	public Collection<SiteG2r> findByParams( @RequestParam(required = false) String code, @RequestParam(required = false) String name,
			 @RequestParam(required = false) String region, @RequestParam(required = false) String zone, @RequestParam(required = false) String area,
			 @RequestParam(required = false) String moeProjet, @RequestParam(required = false) String porteurProspection,@RequestParam(required = false) String state) throws DataAccessException {
		Map<String,String> params = new HashMap<String,String>();
		if (!StringUtils.isEmpty(code)) {
			params.put("code", "%" + code + "%");
		}
		if (!StringUtils.isEmpty(name)) {
			params.put("name", "%" + name + "%");
		}
		if (!StringUtils.isEmpty(region)) {
			params.put("region", "%" + region + "%");
		}
		if (!StringUtils.isEmpty(area)) {
			params.put("area", "%" + area + "%" );
		}
		if (!StringUtils.isEmpty(zone)) {
			params.put("zone", "%" + zone + "%" );
		}
		if (!StringUtils.isEmpty(moeProjet)) {
			params.put("moeProjet", "%" + moeProjet + "%" );
		}
		if (!StringUtils.isEmpty(porteurProspection)) {
			params.put("porteurProspection", "%" + porteurProspection + "%" );
		}
		if (!StringUtils.isEmpty(state)) {
			params.put("state", "%" + state + "%" );
		}
		return siteG2rRepository.findByParams(params);
	}


	// create site rest API
	@PostMapping("/sites_g2r")
	public SiteG2r createSite(@RequestBody SiteG2r site) {
		return siteG2rRepository.save(site);
	}

	// get site by id rest API
	@GetMapping("/sites_g2r/{id}")
	public ResponseEntity<SiteG2r> getSiteById(@PathVariable Long id) {
		SiteG2r site = siteG2rRepository.findById(id);
		if (site == null) {
				throw new ResourceNotFoundException("Site not exist with id :" + id);
		}
		return ResponseEntity.ok(site);
	}

	@PutMapping("/sites_g2r/{id}")
	public ResponseEntity<SiteG2r> updateSite(@PathVariable Long id, @RequestBody SiteG2r siteG2rDetails) {
		SiteG2r siteG2r = siteG2rRepository.findById(id);
		if (siteG2r == null) {
				throw new ResourceNotFoundException("Site G2r not exist with id :" + id);
		}
		siteG2r.setCode(siteG2rDetails.getCode());
		siteG2r.setName(siteG2rDetails.getName());
		siteG2r.setRegion(siteG2rDetails.getRegion());
		siteG2r.setZone(siteG2rDetails.getZone());
		siteG2r.setArea(siteG2rDetails.getArea());		
		siteG2r.setState(siteG2rDetails.getState());
		siteG2r.setPorteurProspection(siteG2rDetails.getPorteurProspection());
		siteG2r.setBailleur(siteG2rDetails.getBailleur());
		siteG2r.setProgramme(siteG2rDetails.getProgramme());
		siteG2r.setProjet(siteG2rDetails.getProjet());
		siteG2r.setMoeProjet(siteG2rDetails.getMoeProjet());		
		siteG2r.setAgenceMoe(siteG2rDetails.getAgenceMoe());	
		siteG2r.setEquipePlanifDsor(siteG2rDetails.getEquipePlanifDsor());
		siteG2r.setCdpPlanifDsor(siteG2rDetails.getCdpPlanifDsor());
		siteG2r.setEquipePatrimoineRegion(siteG2rDetails.getEquipePatrimoineRegion());
		siteG2r.setActeurPatrimoineRegion(siteG2rDetails.getActeurPatrimoineRegion());
		siteG2r.setRdpRegion(siteG2rDetails.getRdpRegion());
		siteG2r.setEquipeRespSite(siteG2rDetails.getEquipeRespSite());
		siteG2r.setRespSite(siteG2rDetails.getRespSite());
		siteG2r.setIngeRadio(siteG2rDetails.getIngeRadio());		
		siteG2r.setEb(siteG2rDetails.getEb());		

		SiteG2r updatedSite = siteG2rRepository.save(siteG2r);
		return ResponseEntity.ok(updatedSite);
	}

	@DeleteMapping("/sites_g2r/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteSite(@PathVariable Long id) {
		SiteG2r siteG2r = siteG2rRepository.findById(id);
		if (siteG2r == null) {
				throw new ResourceNotFoundException("Site G2r not exist with id :" + id);
		}
		siteG2rRepository.delete(siteG2r);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}



}
