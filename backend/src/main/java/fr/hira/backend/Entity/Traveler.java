package fr.hira.backend.Entity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

/**
 * This class represents the traveler identity with the lastname, the firstname and the date of birth.
 * Provides age calculation based on date of birth.
 */
@Entity
public class Traveler {

    //Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    @ManyToOne
    @JoinColumn(name="travel_id")
    private Travel travel;

    //Constructeurs
    public Traveler() {
    }
    public Traveler(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    //Getter and Setter
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    //Méthodes
    /**
     * Method returns the age of traveler.
     * @return age of traveler
     */
    public int getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }
}
