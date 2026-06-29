package it.tcweb.negozio_online.repository;

import it.tcweb.negozio_online.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
}
