package it.tcweb.negozio_online.controller;

import it.tcweb.negozio_online.models.Recensione;
import it.tcweb.negozio_online.service.RecensioneService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/recensioni")
public class RecensioneController {
    
    @Autowired
    private RecensioneService service;

    @GetMapping
    public ResponseEntity<List<Recensione>> trovaTutti(){
        return ResponseEntity.ok(service.trovaTutti());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recensione> trovaPerId(@PathVariable Integer id){

        Recensione recensione = service.trovaPerId(id);
        if (recensione == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(recensione);
    }

    @PostMapping
    public ResponseEntity<Recensione> crea(@RequestBody Recensione recensione,
                                  @RequestParam Integer prodottoId,
                                  @RequestParam Integer clienteId){
        Recensione recensioneSalvato = service.salva(recensione,prodottoId,clienteId);
        return ResponseEntity.status(201).body(recensioneSalvato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recensione> aggiorna( @PathVariable Integer id,
                                                @Valid @RequestBody Recensione recensione,
                                                @RequestParam Integer prodottoId,
                                                @RequestParam Integer clienteId){
        if (service.trovaPerId(id) == null){
            return ResponseEntity.notFound().build();
        }
        recensione.setId(id);  // ci serve per assicurarci che l'id del Recensione da modificare sia quello giusto
        return ResponseEntity.ok((service.salva(recensione,prodottoId,clienteId)));
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
