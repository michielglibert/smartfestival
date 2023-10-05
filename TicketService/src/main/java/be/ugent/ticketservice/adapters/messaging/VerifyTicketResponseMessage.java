package be.ugent.ticketservice.adapters.messaging;

import java.time.LocalDate;

public class VerifyTicketResponseMessage {

    private int ticketId;
    private boolean verified;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    public VerifyTicketResponseMessage(int ticketId, boolean verified, String firstName, String lastName, LocalDate dateOfBirth) {
        this.ticketId = ticketId;
        this.verified = verified;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
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
}
