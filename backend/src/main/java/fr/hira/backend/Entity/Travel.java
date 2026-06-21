package fr.hira.backend.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

/**
 * Travel is a class representing travel in a specific country
 * for a specific duration and with a list of travelers
 * and provides budget estimation based on reference prices.
 */
@Entity
public class Travel {

    //Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Country country;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "travel")
    private List<Traveler> travelers;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "travel")
    private List<Budget> budgets;

    //Constructeurs
    public Travel() {
    }

    public Travel(String name, Country country, LocalDate departureDate, LocalDate arrivalDate, List<Traveler> travelers) {
        this.name = name;
        this.country = country;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.travelers = travelers;
    }

    //Getter and Setter
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public List<Traveler> getTravelers() {
        return travelers;
    }

    public void setTravelers(List<Traveler> travelers) {
        this.travelers = travelers;
    }

    public List<Budget> getBudgets() {
        return budgets;
    }

    public void setBudgets(List<Budget> budgets) {
        this.budgets = budgets;
    }

    //Méthodes

    /**
     * getDuration() is a method returning the total number of travel days.
     * @return total number of travel days
     */
    public long getDuration() {
        return ChronoUnit.DAYS.between(departureDate,arrivalDate);
    }

    /**
     * This method returns an estimation of the trip budget.
     * @param referencePrices
     * @return estimation
     */
    public BigDecimal getEstimation(List<ReferencePrice> referencePrices) {
        BigDecimal estimation = BigDecimal.ZERO;
        BigDecimal nbTravelers = new BigDecimal(this.travelers.size());
        BigDecimal nbDays = new BigDecimal(this.getDuration());
        for(ReferencePrice referencePrice : referencePrices) {
            estimation = switch (referencePrice.getCategory()) {
                case TRANSPORT, EXTRAS-> estimation.add(referencePrice.getUnitPrice()
                        .multiply(nbTravelers));
                case ACCOMMODATION, ACTIVITIES -> estimation.add(referencePrice.getUnitPrice()
                        .multiply(nbTravelers)
                        .multiply(nbDays));
                case FOOD -> estimation.add(referencePrice.getUnitPrice()
                        .multiply(nbTravelers)
                        .multiply(nbDays)
                        .multiply(BigDecimal.valueOf(3)));
            };
        }
        return estimation;
    }

}
