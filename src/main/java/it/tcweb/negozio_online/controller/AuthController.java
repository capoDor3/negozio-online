package it.tcweb.negozio_online.controller;

import it.tcweb.negozio_online.JwtService;
import it.tcweb.negozio_online.exceptions.RisorsaNonTrovataException;
import it.tcweb.negozio_online.models.Cliente;
import it.tcweb.negozio_online.models.DTO.RegistrazioneRequestDTO;
import it.tcweb.negozio_online.models.Utente;
import it.tcweb.negozio_online.repository.ClienteRepository;
import it.tcweb.negozio_online.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UtenteService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrazioneRequestDTO dati){
        // 1. Creiamo e salviamo il Cliente
        Cliente cliente = new Cliente();
        cliente.setNome(dati.getNome());
        cliente.setCognome(dati.getCognome());
        cliente.setEmail(dati.getEmail());
        Cliente clienteSalvato = clienteRepository.save(cliente);

        // 2. Creiamo l'Utente collegato al Cliente appena salvato
        Utente utente = new Utente();
        utente.setUsername(dati.getUsername());
        utente.setPassword(passwordEncoder.encode(dati.getPassword()));
        utente.setRuolo("USER");
        utente.setCliente(clienteSalvato);
        service.salva(utente);

        return ResponseEntity.status(201).body("Utente registrato");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Utente credenziali){
        Utente utente = service.trovaPerUsername(credenziali.getUsername()).orElseThrow(()->
                new RisorsaNonTrovataException("Utente non trovato"));

        boolean passwordCorretta = passwordEncoder.matches(credenziali.getPassword(), utente.getPassword());

        if (!passwordCorretta)
            return ResponseEntity.status(401).body("Credenziali errate");

        Integer clienteId = utente.getCliente() != null ? utente.getCliente().getId() : null;
        String token = jwtService.generaToken(utente.getUsername(), clienteId);
        return ResponseEntity.ok(Map.of("token", token));
    }


}
