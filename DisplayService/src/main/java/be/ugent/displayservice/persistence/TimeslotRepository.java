package be.ugent.displayservice.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import be.ugent.displayservice.domain.Timeslot;

@Repository
public interface TimeslotRepository extends CrudRepository<Timeslot, Long>{
	Iterable<Timeslot> findAllByOrderByStartDateTime();
	Iterable<Timeslot> findByStageOrderByStartDateTime(String stage);
	Iterable<Timeslot> findByArtistOrderByStartDateTime(String artist);
}
