package be.ugent.displayservice.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import be.ugent.displayservice.domain.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long>{
	Iterable<Message> findByDisplayedFalseOrderByPriorityDescCreatedDateTimeAsc();
	Message findTopByDisplayedFalseOrderByPriorityDescCreatedDateTimeAsc();
}
