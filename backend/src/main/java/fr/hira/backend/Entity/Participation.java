package fr.hira.backend.Entity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * This class represents the participation of a person for a specific travel.
 */
@Entity
public class Participation {

    //Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name="id_person")
    private Person person;
    @Enumerated(EnumType.STRING)
    private Role role;
    private BigDecimal budgetShare;
    @ManyToOne
    @JoinColumn(name="id_travel")
    private Travel travel;

    //Constructors
    public Participation() {
    }

    public Participation(Role role, BigDecimal budgetShare) {
        this.role = role;
        this.budgetShare = budgetShare;
    }

    //Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public BigDecimal getBudgetShare() {
        return budgetShare;
    }

    public void setBudgetShare(BigDecimal budgetShare) {
        this.budgetShare = budgetShare;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

}
