package fr.hira.backend.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

/**
 * Travel is a class representing travel in a specific country
 * for a specific duration and with participations of person
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
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "travel")
    private List<Participation> participations;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "travel")
    private List<Budget> budgets;

    //Constructors
    public Travel() {
    }

    public Travel(String name, Country country, LocalDate departureDate, LocalDate arrivalDate) {
        this.name = name;
        this.country = country;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
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

    public List<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(List<Participation> participations) {
        this.participations = participations;
    }

    public List<Budget> getBudgets() {
        return budgets;
    }

    public void setBudgets(List<Budget> budgets) {
        this.budgets = budgets;
    }

    //Methods

    /**
     * This method returns an estimation of the trip budget.
     * @param referencePrices A list of reference prices to establish a budget estimate
     * @return estimation
     */
    public BigDecimal getEstimation(List<ReferencePrice> referencePrices) {
        BigDecimal estimation = BigDecimal.ZERO;
        BigDecimal nbParticipations = new BigDecimal(this.getParticipations().size());

        for (ReferencePrice rf : referencePrices) {
            estimation = estimation.add(rf.getPrice()
                                    .multiply(getMultiplierCoefficient(rf.getFrequency())
                                    .multiply(nbParticipations)));
        }
        return estimation;
    }

    /**
     * This method returns the multiplier coefficient in fonction of the frequency.
     * Example : If the during of your travel is 7 days, and the frequency passed in parameter is PER_HOURS,
     * the multiplier coefficient will be 168.00 (because 7 * 24 = 128)
     * @param frequency the frequency of Enumtype Frequency
     * @return a multiplier coefficient
     */
    public BigDecimal getMultiplierCoefficient(Frequency frequency) {
        long coefficient = switch (frequency) {
            case Frequency.ONE_TIME -> 1;
            case Frequency.PER_HOUR -> ChronoUnit.DAYS.between(departureDate, arrivalDate) * 24;
            case Frequency.PER_DAY -> ChronoUnit.DAYS.between(departureDate, arrivalDate);
            case Frequency.PER_WEEK -> ChronoUnit.WEEKS.between(departureDate, arrivalDate);
            case Frequency.PER_MONTH -> ChronoUnit.MONTHS.between(departureDate, arrivalDate);
            case Frequency.PER_YEAR -> ChronoUnit.YEARS.between(departureDate, arrivalDate);
            default -> 0;
        };

        if(coefficient == 0) coefficient = 1 ;

        return BigDecimal.valueOf(coefficient);
    }

}
