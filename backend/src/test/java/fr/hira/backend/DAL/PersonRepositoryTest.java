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
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TestEntityManager em;

    //Attributs
    Person marge, bart;

    @BeforeEach
    void setUp() {
        Travel travel = new Travel("Voyage truqué", Country.JAPON, LocalDate.of(2026,10,14), LocalDate.of(2026,10,25));
        em.persist(travel);

        marge = new Person("Marge", "Simpson", LocalDate.now().minusYears(35));
        em.persist(marge);
        bart = new Person("Bart", "Simpson", LocalDate.now().minusYears(10));
        em.persist(bart);

        Participation pMarge = new Participation(Role.ORGANISATEUR, BigDecimal.ZERO);
        List<Participation> listPartMarge = new ArrayList<>();
        listPartMarge.add(pMarge);
        em.persist(pMarge);
        Participation pBart = new Participation(Role.PARTICIPANT, BigDecimal.ZERO);
        List<Participation> listPartBart = new ArrayList<>();
        listPartBart.add(pBart);
        em.persist(pBart);

        pMarge.setTravel(travel);
        pMarge.setPerson(marge);
        pBart.setTravel(travel);
        pBart.setPerson(bart);

        marge.setParticipations(listPartMarge);
        bart.setParticipations(listPartBart);

        em.flush();
        em.clear();
    }

    private static void basicCheckForPerson(Person expected, Person actual) {
        assertEquals(expected.getFirstName(),actual.getFirstName());
        assertEquals(expected.getLastName(),actual.getLastName());
        assertEquals(expected.getDateOfBirth(),actual.getDateOfBirth());
    }

    @Test
    void shouldFindPersonById(){
        //Arrange -- setUp()
        Person expected = marge;

        //Act
        Optional<Person> result = personRepository.findById(marge.getId());

        //Assert
        assertTrue(result.isPresent());
        Person actual = result.orElseThrow();
        basicCheckForPerson(expected,actual);
    }

    @Test
    void shouldFindAllPeople(){
        //Arrange -- setUp()
        List<Person> listExpected = new ArrayList<>();
        listExpected.add(marge);
        listExpected.add(bart);
        em.clear();

        //Act
        List<Person> listActual = personRepository.findAll();

        //Assert
        assertEquals(2,listActual.size());
        assertThat(
                listActual.stream().map(Person::getId).toList()
        ).containsExactlyInAnyOrderElementsOf(
                listExpected.stream().map(Person::getId).toList()
        );
    }

    @Test
    void shouldSavePerson(){
        //Arrange -- setUp()
        Person expected = new Person("Bianca", "Castafiore", LocalDate.now().minusYears(45));
        UUID idExpected = expected.getId();

        //Act
        Person actual = personRepository.save(expected);

        //Assert
        assertNull(idExpected);
        basicCheckForPerson(expected,actual);
    }

    @Test
    void shouldDeletePersonAndCascadeToParticipations(){
        //Arrange -- setUp()
        UUID idBartBeforeDelete = bart.getId();
        List<UUID> idsParticipationBartBeforeDelete = bart.getParticipations().stream().map(Participation::getId).toList();

        //Act
        personRepository.delete(bart);

        //Assert
        assertNull(em.find(Person.class,idBartBeforeDelete));
        for(UUID idParticipationBartBeforeDelete : idsParticipationBartBeforeDelete) {
            assertNull(em.find(Participation.class, idParticipationBartBeforeDelete));
        }
    }

    @Test
    void shouldUpdatePerson(){
        //Arrange -- setUp()
        Person expected = marge;
        UUID idBeforeUpdate = expected.getId();
        expected.setLastName("Bouvier");

        //Act
        Person actual = personRepository.save(expected);
        UUID idAfterUpdate = actual.getId();

        //Assert
        assertEquals(idBeforeUpdate,idAfterUpdate);
        basicCheckForPerson(expected,actual);
    }
}
