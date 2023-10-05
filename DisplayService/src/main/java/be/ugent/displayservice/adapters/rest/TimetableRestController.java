package be.ugent.displayservice.adapters.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.displayservice.persistence.TimeslotService;
import be.ugent.displayservice.domain.Timeslot;

@RestController
@RequestMapping(path = "/display/timetable")
@CrossOrigin(origins = "*")
public class TimetableRestController {

	private TimeslotService timeslotService;
	
	@Autowired
	public TimetableRestController(TimeslotService timeslotService) {
		this.timeslotService = timeslotService;
	}
	
	/**
	 * Returns the complete timetable ordered by the starting date and time of each slot.
	 */
	@GetMapping("/all")
	public Iterable<Timeslot> getAll(){
		return timeslotService.getAll();
	}
	
	/**
	 * Returns the timetable for a specific stage ordered by the starting date and time of each slot.
	 */
	@GetMapping("/stage/{stage}")
	public Iterable<Timeslot> getAllTimeslotsForStage(@PathVariable String stage){
		return timeslotService.getAllTimeslotsForStage(stage);
	}
	
	/**
	 * Returns the timetable for a specific artist ordered by the starting date and time of each slot.
	 */
	@GetMapping("/artist/{artist}")
	public Iterable<Timeslot> getAllTimeslotsForArtist(@PathVariable String artist){
		return timeslotService.getAllTimeslotsForArtist(artist);
	}
}
