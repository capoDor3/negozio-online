package it.tcweb.negozio_online.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categorie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il nome della categoria è obbligatorio")
    @Size(min = 2 , max = 30)
    private String nome;

    @Size(max = 200, message = "La descrizione non può superare i 200 caratteri")
    private String descrizione;

    // OneToMany sfrutta il parametro mappedBy per dire che la relazione è già gestita dal campo marcato ManyToOne
    // In questo modo non verranno create colonne nuove
    // Il mappedBy va sul lato "one" e punta al nome del campo sul lato "many"
    @OneToMany(mappedBy = "categoria")
    // Lazy significa pigro, i dati vengono caricati solo quando li chiediamo davvero
    // è il comportamento di default per OneToMany
    // EAGER invece fa l'opposto, i dati vengono caricati subito, insieme all'oggetto principale
    // è il comportamento di default per ManyToOne
    @JsonIgnore                        // evita il loop infinito nel JSON
    private List<Prodotto> prodotti;
}
