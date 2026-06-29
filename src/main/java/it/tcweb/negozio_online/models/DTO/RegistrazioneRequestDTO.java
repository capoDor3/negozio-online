package it.tcweb.negozio_online.models.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrazioneRequestDTO {
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private String email;
}
