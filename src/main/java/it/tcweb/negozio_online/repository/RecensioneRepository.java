package it.tcweb.negozio_online.repository;

import it.tcweb.negozio_online.models.Recensione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecensioneRepository extends JpaRepository<Recensione,Integer> {
    List<Recensione> findByProdotto_Id(Integer prodottoId);
    List<Recensione> findByCliente_Id(Integer prodottoId);
}
