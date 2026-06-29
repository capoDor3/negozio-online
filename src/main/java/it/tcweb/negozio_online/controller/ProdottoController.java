package it.tcweb.negozio_online.controller;

import it.tcweb.negozio_online.models.DTO.ProdottoRequestDTO;
import it.tcweb.negozio_online.models.DTO.ProdottoResponseDTO;
import it.tcweb.negozio_online.service.ProdottoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Il controller è la classe che riceve le richieste HTTP dall'esterno. E' la porta di ingresso, non fa altro
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/prodotti")        // prefisso comune per tutti gli endpoint
public class ProdottoController {

    @Autowired
    private ProdottoService service;

    @GetMapping
    public ResponseEntity<List<ProdottoResponseDTO>> trovaTutti(){
        return ResponseEntity.ok(service.trovaTutti());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdottoResponseDTO> trovaPerId(@PathVariable Integer id){
        return ResponseEntity.ok(service.trovaPerId(id));
    }

    @PostMapping
    public ResponseEntity<ProdottoResponseDTO> crea(@Valid @RequestBody ProdottoRequestDTO dto){
        return ResponseEntity.status(201).body(service.salva(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdottoResponseDTO> aggiorna(@PathVariable Integer id,
            @Valid @RequestBody ProdottoRequestDTO dto){
        return ResponseEntity.ok(service.aggiorna(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> elimina(@PathVariable Integer id){
        service.elimina(id);
        return ResponseEntity.noContent().build();
    }

}
