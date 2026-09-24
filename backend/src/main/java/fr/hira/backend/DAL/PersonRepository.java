package fr.hira.backend.DAL;

import fr.hira.backend.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
}
