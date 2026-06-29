package it.tcweb.negozio_online.repository;

import it.tcweb.negozio_online.models.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

// Il repository è l'interfaccia che parla con il database, ed è l'unica che sa fare le query
public interface ProdottoRepository extends JpaRepository<Prodotto, Integer> {
    // niente da scrivere, il CRUD è già tutto qui dentro
    /* metodi che abbiamo già gratuitamente:
    findAll() -> restituisce tutti i record
    findById(id) -> restituisce un record cercato per chiave
    save(prodotto) -> inserisce O aggiorna un entità
    deleteById(id) -> cancella un entità per chiave primaria
    existsById(id) -> controlla se esiste
    count() -> conta i record
         */

    // Vogliamo un metodo che cerca un prodotto tramite il nome
    List<Prodotto> findByNome(String nome);

    // L'implementazione di un metodo di ricerca in Spring è estremamente semplice, basta seguire la convenzione:
    // find + by + nome del campo + eventuale condizione

    // Metodo che cerca prodotti dove il prezzo è minore di x
    List<Prodotto> findByPrezzoLessThan(BigDecimal prezzo);

    // Metodo che cerca prodotti con una sottostringa specifica nel nome
    List<Prodotto> findByNomeContaining(String testo);

}
