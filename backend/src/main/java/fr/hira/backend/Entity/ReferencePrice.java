package fr.hira.backend.Entity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * ReferencePrice is a class which references
 * unit prices per category and per country,
 * used to estimate the total cost of travel.
 */
@Entity
public class ReferencePrice{

    //Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Enumerated(EnumType.STRING)
    private Country country;
    @Enumerated(EnumType.STRING)
    private Category category;
    private BigDecimal unitPrice;
    private String unit;

    //Constructeur
    public ReferencePrice() {
    }
    public ReferencePrice(Country country, Category category, BigDecimal unitPrice, String unit) {
        this.country = country;
        this.category = category;
        this.unit = unit;
        this.unitPrice = unitPrice;
    }

    //Getter / Setter

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}