package it.tcweb.negozio_online.models;

// Classe che rappresenta un entità di prodotto nel database
// Per far parlare in Java oggetti e righe del DB si utilizza l'ORM (Object-Relational-Mapping)
// Traduce automaticamente righe della tabella in oggetti Java e gli oggetti Java in righe della tabella
// JPA (Jakarta Persistence API) è uno standard. Un insieme di regole che dice come deve funzionare un ORM in Java: quali annotazioni usare,
// come comportarsi. JPA è solo un "contratto", specifica le regole sulla carta, da solo non fa niente.
// Hibernate è invece l'implementazione concreta, un programma vero che fa il lavoro seguendo le regole di JPA
// Spring boot usa hibernate di default


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

// La prima classe che ci serve in un progetto di questo tipo si chiama Entity (una classe Java che corrisponde a
// una tabella del database)
@Entity
@Table(name = "prodotti") // specifica il nome esatto della tabella. Se il nome della classe e quello della tabella
                          // coincidono, possiamo anche ometterla, è comunque buona abitudine essere espliciti.
//@Data LOMBOK : genera automaticamente getter, setter, equals, hashcode e tostring
// Questi metodi generati in automatico possono causare problemi con le relazioni JPA
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prodotto {

    @Id  // specifica che questa è la chiave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // specifica che il campo ID è auto increment
    // ci sono altre strategies :
    // IDENTITY : specifica che il database genera l'id
    // SEQUENCE : specifica che il campo usa una sequenza del database
    private Integer id;

    //@NotBlank(message = "Il nome è obbligatorio")
    //Il campo deve per forza avere un valore, si usa per le stringhe : il valore
    // non può essere vuoto o fatto di soli spazi
    //@Size(min = 2, max = 100)
    // Controlla la lunghezza, nel nostro caso il valore dovrà essere tra 2 e 100 caratteri
    private String nome;

    //@NotNull(message = "Il prezzo è obbligatorio")
    // Equivalente del not blank ma basta che il valore non sia null
    //@Positive(message = "Il prezzo deve essere positivo")
    private BigDecimal prezzo;
    private Integer stock;

    //private Integer categoria_id;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToMany(mappedBy = "prodotto")
    @JsonIgnore
    private List<Recensione> recensioni;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prodotto prodotto)) return false;
        return stock == prodotto.stock && Objects.equals(id, prodotto.id) && Objects.equals(nome, prodotto.nome) && Objects.equals(prezzo, prodotto.prezzo) && Objects.equals(categoria, prodotto.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, prezzo, stock, categoria);
    }

    @Override
    public String toString() {
        return "Prodotto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", prezzo=" + prezzo +
                ", stock=" + stock +
                ", categoria=" + categoria +
                '}';
    }
}
