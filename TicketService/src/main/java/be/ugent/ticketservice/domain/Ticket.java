package be.ugent.ticketservice.domain;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
public class Ticket {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name="email", nullable = true)
	private String email;
	
	@Column(name="status", nullable = false)
	@Enumerated(EnumType.STRING)
	private TicketStatus status;
	
	@Column(name="first_name", nullable = true)
	private String firstName;
	
	@Column(name="last_name", nullable = true)
	private String lastName;

	@Column(name="date_of_birth", nullable = true)
	private LocalDate dateOfBirth;

	@Column(name="price", nullable = false)
	private double price;

	@Column(name="order_id", nullable = true)
	private String orderId;

	@Column(name="activated", nullable = false)
	private boolean activated;
	
	@SuppressWarnings("unused")
	public Ticket() {
		this.status = TicketStatus.AVAILABLE;
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.orderId = "";
		this.price = 0.0;

		this.dateOfBirth = LocalDate.now();
	}
	
	public Ticket(TicketStatus ticketStatus, String firstName, String lastName, LocalDate dateOfBirth, String email, double price, String orderId, boolean activated) {
		this.status = ticketStatus;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.price = price;
		this.orderId = orderId;
		this.activated = activated;
	}

	@Override
	public String toString() {
		return String.format("ID: %s\tname: %s\tdate of birth: ‰s\n", id, firstName + " " + lastName, dateOfBirth.toString());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TicketStatus getStatus() {
		return status;
	}

	public void setStatus(TicketStatus status) {
		this.status = status;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}
}
