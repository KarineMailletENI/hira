package fr.hira.backend.DAL;

import fr.hira.backend.Entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TravelRepositoryTest {

    @Autowired
    private TravelRepository travelRepository;

    @Autowired
    private TestEntityManager em;

    private Travel t;

    @BeforeEach
    void setUp() {
        t = new Travel(
                    "Mon voyage à Tokyo",
                    Country.JAPON,
                    LocalDate.of(2027, 3, 15),
                    LocalDate.of(2027, 3, 17)
                );
        em.persist(t);

        Person marge = new Person("Marge", "Simpson", LocalDate.now().minusYears(35));
        em.persist(marge);
        Person bart = new Person("Bart", "Simpson", LocalDate.now().minusYears(10));
        em.persist(bart);

        Participation participationMarge = new Participation(Role.ORGANISATEUR, BigDecimal.ZERO);
        participationMarge.setPerson(marge);
        participationMarge.setTravel(t);
        em.persist(participationMarge);

        Participation participationBart = new Participation(Role.PARTICIPANT, BigDecimal.ZERO);
        participationBart.setPerson(bart);
        participationBart.setTravel(t);
        em.persist(participationBart);

        List<Participation> participations = new ArrayList<>();
        participations.add(participationMarge);
        participations.add(participationBart);
        t.setParticipations(participations);

        em.flush();
        em.clear();
    }

    @Test
    void shouldFindTravelById(){
        //Arrange -- setUp()
        Travel expected = t;

        //Act
        Optional<Travel> result = travelRepository.findById(expected.getId());

        //Assert
        assertTrue(result.isPresent());
        assertEquals(expected.getName(),result.orElseThrow().getName());
        assertEquals(expected.getCountry(),result.orElseThrow().getCountry());
        assertEquals(expected.getDepartureDate(),result.orElseThrow().getDepartureDate());
        assertEquals(expected.getArrivalDate(),result.orElseThrow().getArrivalDate());
        List<UUID> idsExpected = expected.getParticipations().stream().map(Participation::getId).toList();
        List<UUID> idsResult = result.orElseThrow().getParticipations().stream().map(Participation::getId).toList();
        assertThat(idsResult).containsExactlyInAnyOrderElementsOf(idsExpected);
    }

    @Test
    void shouldFindAllTravels(){
        //Arrange -- setUp()
        Travel newTravel = new Travel(
                "Mon voyage de noce à Osaka",
                Country.JAPON,
                LocalDate.of(2013,3,22),
                LocalDate.of(2013,4,6)
                );
        em.persist(newTravel);
        List<Travel> expected = new ArrayList<>();
        expected.add(t);
        expected.add(newTravel);
        em.flush();
        em.clear();

        //Act
        List<Travel> result = travelRepository.findAll();

        //Assert
        assertEquals(2,result.size());
        List<UUID> idsExpected = expected.stream().map(Travel::getId).toList();
        List<UUID> idsResult = result.stream().map(Travel::getId).toList();
        assertThat(idsResult).containsExactlyInAnyOrderElementsOf(idsExpected);
    }
    @Test
    void shouldSaveTravel(){
        //Arrange -- setUp()
        Travel expected = new Travel(
                "L'opéra de Tokyo",
                Country.JAPON,
                LocalDate.of(2027, 3, 13),
                LocalDate.of(2027, 3, 15)
        );
        UUID idBeforeSaveTravel = expected.getId();

        Person bc = new Person("Bianca", "Castafiore", LocalDate.now().minusYears(45));
        em.persistAndFlush(bc); //Not cascade so manually

        Participation participationBc = new Participation(Role.ORGANISATEUR, BigDecimal.ZERO);
        UUID idBeforeSaveParticipation = participationBc.getId();
        participationBc.setPerson(bc);
        participationBc.setTravel(expected);

        List<Participation> participationsBc = new ArrayList<>();
        participationsBc.add(participationBc);
        expected.setParticipations(participationsBc);

        //Act
        Travel result = travelRepository.save(expected);

        //Assert
        assertNull(idBeforeSaveTravel);
        assertNotNull(expected.getId());
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getCountry(), result.getCountry());
        assertEquals(expected.getDepartureDate(), result.getDepartureDate());
        assertEquals(expected.getArrivalDate(), result.getArrivalDate());
        //--Verification of Cascade
        assertNull(idBeforeSaveParticipation);
        assertNotNull(expected.getParticipations().getFirst().getId());
    }
    @Test
    void shouldDeleteTravelAndCascadeToParticipations(){
        //Arrange -- setUp()
        List<UUID> idListParticipationBeforeDelete = t.getParticipations()
                                                        .stream()
                                                        .map(Participation::getId)
                                                        .toList();
        UUID idTravelBeforeDelete = t.getId();

        //Act
        travelRepository.delete(t);

        //Assert
        assertEquals(Optional.empty(), travelRepository.findById(idTravelBeforeDelete));
        for(UUID idBeforeDelete : idListParticipationBeforeDelete) assertNull(em.find(Participation.class,idBeforeDelete));

    }

    @Test
    void shouldUpdateTravel(){
        //Arrange
        UUID idBeforeUpdated = t.getId();
        t.setDepartureDate(LocalDate.of(2027,3,10));
        Travel expected = t;

        //Act
        Travel result = travelRepository.save(expected);

        //Assert
        assertEquals(idBeforeUpdated, result.getId());
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getCountry(), result.getCountry());
        assertEquals(LocalDate.of(2027,3,10), result.getDepartureDate());
        assertEquals(expected.getArrivalDate(), result.getArrivalDate());
    }
}
