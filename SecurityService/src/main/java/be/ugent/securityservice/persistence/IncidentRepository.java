package be.ugent.securityservice.persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import be.ugent.securityservice.domain.Incident;

public interface IncidentRepository extends MongoRepository<Incident, String> {
	@Query("{'users' : ?0}")
	List<Incident> findIncidentsByUser(String userId);
}
