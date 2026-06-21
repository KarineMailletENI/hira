package fr.hira.backend.Entity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * This class represents the budget for a specific travel category.
 * For example, the transport budget for a trip to Japan.
 */
@Entity
public class Budget {

    //Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Enumerated(EnumType.STRING)
    private Category category;
    private BigDecimal amount;
    private String description;
    @ManyToOne
    @JoinColumn(name = "travel_id")
    private Travel travel;

    //Constructeurs
    public Budget() {
    }
    public Budget(Category category, BigDecimal amount, String description) {
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    //Getter and Setter
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }
}
