package it.tcweb.negozio_online.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "clienti")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il nome è obbligatorio")
    @Size(max=30)
    private String nome;

    @NotBlank(message = "Il cognome è obbligatorio")
    @Size(max=30)
    private String cognome;

    @NotBlank(message = "L'email è obbligatoria")
    @Email(message = "L'email non è in un formato valido")
    private String email;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Recensione> recensioni;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Ordine> ordini;

}
