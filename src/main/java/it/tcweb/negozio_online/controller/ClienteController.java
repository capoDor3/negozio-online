package it.tcweb.negozio_online.controller;

import it.tcweb.negozio_online.models.Cliente;
import it.tcweb.negozio_online.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/clienti")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<List<Cliente>> trovaTutti(){
        return ResponseEntity.ok(service.trovaTutti());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> trovaPerId(@PathVariable Integer id){

        Cliente cliente = service.trovaPerId(id);
        if (cliente == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<Cliente> crea(@Valid @RequestBody Cliente cliente){
        Cliente clienteSalvato = service.salva(cliente);
        return ResponseEntity.status(201).body(clienteSalvato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> aggiorna( @PathVariable Integer id,
                                              @RequestBody Cliente cliente){
        if (service.trovaPerId(id) == null){
            return ResponseEntity.notFound().build();
        }
        cliente.setId(id);  // ci serve per assicurarci che l'id del Cliente da modificare sia quello giusto
        return ResponseEntity.ok((service.salva(cliente)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> elimina(@PathVariable Integer id){
        if (service.trovaPerId(id) == null){
            return ResponseEntity.notFound().build();
        }
        service.elimina(id);
        return ResponseEntity.noContent().build();
    }
}
