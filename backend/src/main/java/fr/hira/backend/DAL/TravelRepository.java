package fr.hira.backend.DAL;

import fr.hira.backend.Entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TravelRepository extends JpaRepository<Travel, UUID> {

}
