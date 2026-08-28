package fr.hira.backend.DAL;

import fr.hira.backend.Entity.Traveler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TravelerRepository extends JpaRepository<Traveler, UUID> {

}
