package net.nrasoft.candb.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
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
import net.nrasoft.candb.model.Workstream;
import net.nrasoft.candb.repository.WorkstreamRepositoryImpl;
import net.nrasoft.candb.repository.WorkstreamRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class WorkstreamRestController {


	private WorkstreamRepository workstreamRepository = new WorkstreamRepositoryImpl();

	@GetMapping("/workstreams")
	public Collection<Workstream> listWorkstreams() {
		return workstreamRepository.findAll();
	}
	
	
	@GetMapping("/workstreams/findBySiteCode")
	public Collection<Workstream> findWorkstreamBySiteCode(@RequestParam String siteCode) {
		Collection<Workstream> res =  workstreamRepository.findBySiteCode(siteCode.trim());
		return res;
	}
	

	// create workstream rest API
	@PostMapping("/workstreams")
	public Workstream createWorkstream(@RequestBody Workstream workstream) {
		return workstreamRepository.save(workstream);
	}

	// get workstream by id rest API
	@GetMapping("/workstreams/{id}")
	public ResponseEntity<Workstream> getWorkstreamById(@PathVariable Long id) {
		Workstream workstream = workstreamRepository.findById(id);
		if (workstream == null) {
				throw new ResourceNotFoundException("Workstream not exist with id :" + id);
		}
		return ResponseEntity.ok(workstream);
	}

	@PutMapping("/workstreams/{id}")
	public ResponseEntity<Workstream> updateWorkstream(@PathVariable Long id, @RequestBody Workstream workstreamDetails) {
		Workstream workstream = workstreamRepository.findById(id);
		if (workstream == null) {
				throw new ResourceNotFoundException("Workstream not exist with id :" + id);
		}
		workstream.setCodeActivite(workstreamDetails.getCodeActivite());
		workstream.setCodeG2r(workstreamDetails.getCodeG2r());
		workstream.setStatus(workstreamDetails.getStatus());
		workstream.setStartDate(workstreamDetails.getStartDate());
		workstream.setEndDate(workstreamDetails.getEndDate());
		workstream.setResp(workstreamDetails.getResp());
		workstream.setRespTeam(workstreamDetails.getRespTeam());
		workstream.setNomActivite(workstreamDetails.getNomActivite());
		Workstream updatedWorkstream = workstreamRepository.save(workstream);
		return ResponseEntity.ok(updatedWorkstream);
	}

	@DeleteMapping("/workstreams/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteWorkstream(@PathVariable Long id) {
		Workstream workstream = workstreamRepository.findById(id);
		if (workstream == null) {
				throw new ResourceNotFoundException("Workstream not exist with id :" + id);
		}
		workstreamRepository.delete(workstream);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}



}
