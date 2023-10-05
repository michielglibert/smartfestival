package be.ugent.lineupservice.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import be.ugent.lineupservice.domain.Timeslot;

@Repository
public interface TimeslotRepository extends CrudRepository<Timeslot, Long>{
	Iterable<Timeslot> findAllByOrderByStartDateTime();
	Iterable<Timeslot> findByStageOrderByStartDateTime(String stage);
	Iterable<Timeslot> findByArtistOrderByStartDateTime(String artist);
}
