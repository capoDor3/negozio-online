package it.tcweb.negozio_online.service;

import it.tcweb.negozio_online.models.Utente;
import it.tcweb.negozio_online.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository repository;

    public Utente salva(Utente utente) {
       return repository.save(utente);
    }

    public Optional<Utente> trovaPerUsername(String username){
        return repository.findByUsername(username);
    }
}
