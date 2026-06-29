package it.tcweb.negozio_online.repository;

import it.tcweb.negozio_online.models.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente,Integer> {
   Optional<Utente> findByUsername(String username);
}
