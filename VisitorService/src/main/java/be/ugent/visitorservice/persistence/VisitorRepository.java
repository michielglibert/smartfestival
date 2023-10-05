package be.ugent.visitorservice.persistence;

import be.ugent.visitorservice.domain.Visitor;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface VisitorRepository extends CrudRepository<Visitor, Long> {

    @Query(value = "SELECT * FROM Visitor WHERE id = ?1", nativeQuery = true)
    Visitor getVisitorById(int id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE visitor SET banned = true WHERE id = ?1", nativeQuery = true)
    void banVisitor(int visitorId);

    @Query(value = "SELECT * FROM visitor", nativeQuery = true)
    List<Visitor> getAllVisitors();

}
