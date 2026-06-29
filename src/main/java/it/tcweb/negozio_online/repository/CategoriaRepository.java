package it.tcweb.negozio_online.repository;

import it.tcweb.negozio_online.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {
}
