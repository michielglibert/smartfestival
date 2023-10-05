package be.ugent.lineupservice.persistence;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.lineupservice.adapters.messaging.EventType;
import be.ugent.lineupservice.adapters.messaging.LineUpChange;
import be.ugent.lineupservice.adapters.messaging.MessageGateway;
import be.ugent.lineupservice.domain.Timeslot;

@Service
public class TimeslotService {
	private final TimeslotRepository timeslotRepository;
	private final MessageGateway messageGateway;
	
	@Autowired
	public TimeslotService(TimeslotRepository timeslotRepository, MessageGateway messageGateway) {
		this.timeslotRepository = timeslotRepository;
		this.messageGateway = messageGateway;
	}
	
	public Iterable<Timeslot> getAll(){
		return timeslotRepository.findAllByOrderByStartDateTime();
	}
	
	public Iterable<Timeslot> getAllTimeslotsForStage(String stage){
		return timeslotRepository.findByStageOrderByStartDateTime(stage);
	}
	
	public Timeslot addTimeslot(Timeslot timeslot) {
		timeslotRepository.save(timeslot);
		messageGateway.sendLineupChangeEvent(new LineUpChange(timeslot, EventType.CREATED));
		return timeslot;
	}
	
	public void deleteTimeslot(Long id) {
		Optional<Timeslot> timeslot = timeslotRepository.findById(id);
		if(timeslot.isPresent()) {
			timeslotRepository.deleteById(id);
			messageGateway.sendLineupChangeEvent(new LineUpChange(timeslot.get(), EventType.DELETED));
		}
	}
}
