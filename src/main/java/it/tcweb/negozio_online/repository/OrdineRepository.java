package it.tcweb.negozio_online.repository;

import it.tcweb.negozio_online.models.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdineRepository extends JpaRepository<Ordine,Integer> {
    List<Ordine> findByCliente_Id(Integer clienteId);
}
