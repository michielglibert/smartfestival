package be.ugent.displayservice.persistence;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.displayservice.domain.Timeslot;
import be.ugent.displayservice.adapters.messaging.EventType;
import be.ugent.displayservice.adapters.messaging.LineUpChange;

@Service
public class TimeslotService {
	private final TimeslotRepository timeslotRepository;
	
	@Autowired
	public TimeslotService(TimeslotRepository timeslotRepository) {
		this.timeslotRepository = timeslotRepository;
	}
	
	public Iterable<Timeslot> getAll(){
		return timeslotRepository.findAllByOrderByStartDateTime();
	}
	
	public Iterable<Timeslot> getAllTimeslotsForStage(String stage){
		return timeslotRepository.findByStageOrderByStartDateTime(stage);
	}
	
	public Iterable<Timeslot> getAllTimeslotsForArtist(String artist){
		return timeslotRepository.findByArtistOrderByStartDateTime(artist);
	}
	
	public void editTimetable(LineUpChange change) {
		if(change.getType() == EventType.CREATED) {
			this.addTimeslot(new Timeslot(change.getTimeslotId(), change.getArtist(), change.getStage(), change.getStartDateTime(), change.getEndDateTime()));
		} else {
			this.deleteTimeslot(change.getTimeslotId());
		}
	}
	
	private Timeslot addTimeslot(Timeslot timeslot) {
		timeslotRepository.save(timeslot);
		return timeslot;
	}
	
	private void deleteTimeslot(Long id) {
		Optional<Timeslot> timeslot = timeslotRepository.findById(id);
		if(timeslot.isPresent()) {
			timeslotRepository.deleteById(id);
		}
	}
}
