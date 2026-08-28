package fr.hira.backend.DAL;

import fr.hira.backend.Entity.Traveler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TravelerRepositoryTest  {

    @Autowired
    private TravelerRepository travelerRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    void shouldFindTravelerById(){
        //Arrange
        LocalDate dateOfBirth = LocalDate.now().minusYears(24);
        Traveler travelerTest = em.persist(new Traveler("Jean", "Dupont", dateOfBirth));
        em.flush();
        UUID idTest = travelerTest.getId();

        //Act
        Traveler traveler = travelerRepository.findById(idTest).orElseThrow();

        //Assert
        assertEquals(traveler.getId(), travelerTest.getId());
        assertEquals(traveler.getFirstName(), travelerTest.getFirstName());
        assertEquals(traveler.getLastName(), travelerTest.getLastName());
        assertEquals(traveler.getDateOfBirth(), travelerTest.getDateOfBirth());
        assertEquals(traveler.getAge(), travelerTest.getAge());
    }

    @Test
    void shouldFindAllTravelers(){
        //Arrange
        LocalDate dateOfBirth1 = LocalDate.now().minusYears(24);
        Traveler travelerTest1 = em.persist(new Traveler("Jean", "Dupont", dateOfBirth1));
        em.flush();
        UUID idTest1 = travelerTest1.getId();

        LocalDate dateOfBirth2 = LocalDate.now().minusYears(20);
        Traveler travelerTest2 = em.persist(new Traveler("Michelle", "Bonarien", dateOfBirth2));
        em.flush();
        UUID idTest2 = travelerTest2.getId();

        LocalDate dateOfBirth3 = LocalDate.now().minusYears(18);
        Traveler travelerTest3 = em.persist(new Traveler("Emile", "Souque", dateOfBirth3));
        em.flush();
        UUID idTest3 = travelerTest3.getId();

        //Act
        List <Traveler> list = travelerRepository.findAll();

        //Assert
        assertFalse(list.isEmpty());
        assertEquals(3,list.size());
        assertEquals(travelerTest1, list.getFirst());
        assertEquals(travelerTest2, list.get(1));
        assertEquals(travelerTest3, list.getLast());
    }

    @Test
    void shouldSaveTraveler(){
        //Arrange
        LocalDate dateOfBirth = LocalDate.now().minusYears(12);
        Traveler travelerTest = em.persist(new Traveler("Jean", "Dupont", dateOfBirth));
        em.flush();
        UUID idTest = travelerTest.getId();

        //Act
        Traveler traveler = travelerRepository.save(travelerTest);
        em.flush();

        //Assert
        assertEquals(traveler,travelerTest);
    }

    @Test
    void shouldDeleteTravelerById(){
        //Arrange
        LocalDate dateOfBirth = LocalDate.now().minusYears(15);
        Traveler travelerTest = em.persist(new Traveler("Lisa", "Simpson", dateOfBirth));
        em.flush();
        UUID idTest = travelerTest.getId();

        //Act
        travelerRepository.deleteById(idTest);
        em.flush();

        //Assert
        assertFalse(travelerRepository.findById(idTest).isPresent());
    }

    @Test
    void shouldUpdateTraveler(){
        //Arrange
        LocalDate dateOfBirth = LocalDate.now().minusYears(25);
        Traveler travelerTest = em.persist(new Traveler("Lisa", "Simpson", dateOfBirth));
        em.flush();
        UUID idTest = travelerTest.getId();

        //Act
        travelerTest.setFirstName("Marge");
        travelerRepository.save(travelerTest);
        em.flush();

        //Assert
        Traveler traveler = travelerRepository.findById(idTest).orElseThrow();
        assertEquals("Marge", traveler.getFirstName());
    }
}
