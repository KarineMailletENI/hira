package fr.hira.backend.Entity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * ReferencePrice is a class that represents an item with
 * an identity, a country of reference, a category
 * (such as transport, accommodation, etc.), a price,
 * a currency, and a redundant calculation for a general
 * budget.
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
    @Enumerated(EnumType.STRING)
    private Frequency frequency;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private Currency currency;

    //Constructors
    public ReferencePrice() {
    }

    public ReferencePrice(Country country, Category category, Frequency frequency, BigDecimal price, Currency currency) {
        this.country = country;
        this.category = category;
        this.frequency = frequency;
        this.price = price;
        this.currency = currency;
    }
    //Getters / Setters


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

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal unitPrice) {
        this.price = unitPrice;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}