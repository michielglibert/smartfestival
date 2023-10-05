package be.ugent.securityservice.domain;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.securityservice.persistence.IncidentRepository;

@Service
public class SecurityService {
	private static Logger logger = LoggerFactory.getLogger(SecurityService.class);
	private final IncidentRepository incidentRepository;
	private final CheckIncidentSaga checkIncidentSaga;
	
	@Autowired
	public SecurityService(IncidentRepository incidentRepository, CheckIncidentSaga checkIncidentSaga) {
		this.incidentRepository = incidentRepository;
		this.checkIncidentSaga = checkIncidentSaga;
	}
	
	public void createIncident(Incident incident) {
		incidentRepository.save(incident);
		incident.getUsers().forEach(visitorId -> {
			List<Incident> incidents = incidentRepository.findIncidentsByUser(visitorId);
			checkIncidentSaga.checkIncidents(incidents, visitorId);
		});
	}
	
	public List<Incident> getAllIncidents() {
		return incidentRepository.findAll();
	}
}
