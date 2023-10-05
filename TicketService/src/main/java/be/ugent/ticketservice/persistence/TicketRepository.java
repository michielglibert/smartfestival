package be.ugent.ticketservice.persistence;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import be.ugent.ticketservice.domain.Ticket;
import be.ugent.ticketservice.domain.TicketStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {

    Long countByStatus(TicketStatus status);

    @Query(value = "SELECT * FROM ticket WHERE status = 'AVAILABLE' ORDER BY random() LIMIT ?1", nativeQuery = true)
    List<Ticket> getAvailableTickets(int numberOfTickets);

    @Query(value = "SELECT * FROM ticket WHERE id = ?1 and status='SOLD'", nativeQuery = true)
    Ticket getTicketById(int id);


    @Transactional
    @Modifying
    @Query(value = "UPDATE ticket SET status = ?2, first_name = ?3, last_name = ?4, email = ?5, date_of_birth = ?6, order_id = ?7 WHERE id = ?1", nativeQuery = true)
    void updateTicket(Long id, String status, String firstName, String lastName, String email, LocalDate dateOfBirth, String orderId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE ticket SET status = 'AVAILABLE', first_name = null, last_name = null, email = null, date_of_birth = null, order_id = null WHERE order_id = ?1", nativeQuery = true)
    void cancelOrder(String orderId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE ticket SET status = 'SOLD' WHERE order_id = ?1", nativeQuery = true)
    void completeOrder(String paymentId);

    @Query(value = "SELECT * FROM ticket WHERE email = ?1 and status != 'SOLD'", nativeQuery = true)
    List<Ticket>getTicketsForEmail(String email);

    @Query(value = "SELECT * FROM ticket WHERE order_id = ?1 and status != 'SOLD'", nativeQuery = true)
    List<Ticket>getTicketsForOrderId(String orderId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE ticket SET activated = true WHERE id = ?1", nativeQuery = true)
    void activateTicket(Long id);

    @Query(value = "SELECT * from ticket WHERE status in ('SOLD', 'RESERVED')", nativeQuery = true)
    List<Ticket> getAllSoldAndReservedTickets();
}