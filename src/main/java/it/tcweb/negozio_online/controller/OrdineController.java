package it.tcweb.negozio_online.controller;

import it.tcweb.negozio_online.models.Ordine;
import it.tcweb.negozio_online.service.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/ordini")
public class OrdineController {

    @Autowired
    private OrdineService service;

    @GetMapping
    public ResponseEntity<List<Ordine>> trovaTutti(){
        return ResponseEntity.ok(service.trovaTutti());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ordine> trovaPerId(@PathVariable Integer id){

        Ordine ordine = service.trovaPerId(id);
        if (ordine == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ordine);
    }

    @PostMapping
    public ResponseEntity<Ordine> crea(@RequestBody Ordine ordine,
                                        @RequestParam Integer clienteId){
        Ordine ordineSalvato = service.salva(ordine, clienteId);
        return ResponseEntity.status(201).body(ordineSalvato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ordine> aggiorna(@PathVariable Integer id,
                                            @RequestBody Ordine ordine,
                                            @RequestParam Integer clienteId){
        if (service.trovaPerId(id) == null){
            return ResponseEntity.notFound().build();
        }
        ordine.setId(id);  // ci serve per assicurarci che l'id del Ordine da modificare sia quello giusto
        return ResponseEntity.ok(service.salva(ordine, clienteId));
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
