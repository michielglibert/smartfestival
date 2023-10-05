package be.ugent.lineupservice.adapters.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.lineupservice.persistence.TimeslotRepository;
import be.ugent.lineupservice.persistence.TimeslotService;
import be.ugent.lineupservice.domain.Timeslot;

@RestController
@RequestMapping(path = "/lineup")
@CrossOrigin(origins = "*")
public class TimeslotRestController {

	private TimeslotService timeslotService;
	
	@Autowired
	public TimeslotRestController(TimeslotService timeslotService) {
		this.timeslotService = timeslotService;
	}
	
	@GetMapping("/all")
	public Iterable<Timeslot> getAll(){
		return timeslotService.getAll();
	}
	
	@GetMapping("/stage/{stage}")
	public Iterable<Timeslot> getAllTimeslotsForStage(@PathVariable String stage){
		return timeslotService.getAllTimeslotsForStage(stage);
	}
	
	@PostMapping(path="/create", consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Timeslot addTimeslot(@RequestBody Timeslot timeslot) {
		return timeslotService.addTimeslot(timeslot);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void deleteTimeslot(@PathVariable Long id) {
		timeslotService.deleteTimeslot(id);
	}
}
