package be.ugent.securityservice.adapters.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.securityservice.domain.Incident;
import be.ugent.securityservice.domain.SecurityService;

@RestController
@RequestMapping("/security")
@CrossOrigin(origins = "*")
public class SecurityRestController {
	private final SecurityService securityService;
	
	@Autowired
	public SecurityRestController(SecurityService securityService) {
		this.securityService = securityService;
	}
	
	@GetMapping("/all")
	public List<Incident> getAllIncidents() {		
		return securityService.getAllIncidents();
	}
	
	@PostMapping("/create_incident")
	public void createIncident(@RequestBody Incident incident) {
		securityService.createIncident(incident);
	}
}
