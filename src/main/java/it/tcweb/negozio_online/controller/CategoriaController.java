package it.tcweb.negozio_online.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.tcweb.negozio_online.models.Categoria;
import it.tcweb.negozio_online.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/categorie")
@Tag(name = "Categorie", description = "Gestione delle categorie di negozio online")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping
    @Operation(
            summary = "Recupera tutti i prodotti",
            description = "Restituisce tutti i prodotti presenti nel database db_negozio_online"
    )
    public ResponseEntity<List<Categoria>> trovaTutti(){
       return ResponseEntity.ok(service.trovaTutti());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recupera un prodotto tramite l'ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Prodotto trovato"),
            @ApiResponse(responseCode = "404",description = "Prodotto non trovato"),
            @ApiResponse(responseCode = "500",description = "Errore interno del server")
    })
    public ResponseEntity<Categoria> trovaPerId(@PathVariable Integer id){
        Categoria categoria = service.trovaPerId(id);
        if(categoria == null){
            return ResponseEntity.notFound().build(); // ritorna 404
        }
        return ResponseEntity.ok(categoria); // ritorna 200 + dati
    }

    @PostMapping
    @Operation(summary = "Crea un prodotto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Prodotto creato"),
            @ApiResponse(responseCode = "400",description = "Prodotto no creato per richiesta con dati sbagliati"),
            @ApiResponse(responseCode = "500",description = "Errore interno del server")
    })
    public ResponseEntity<Categoria> salva(@Valid @RequestBody Categoria categoria){
        Categoria categoriaSalvata = service.salva(categoria);
        return ResponseEntity.status(201).body(categoriaSalvata);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> aggiorna(@PathVariable Integer id,@Valid @RequestBody Categoria categoria){
        if (service.trovaPerId(id) == null){
            return ResponseEntity.notFound().build();
        }
        categoria.setId(id);
        return ResponseEntity.ok(service.salva(categoria));
    }

    @DeleteMapping("/{id}")
//    @Hidden
    public ResponseEntity<Void> elimina(@PathVariable Integer id){
       if(service.trovaPerId(id) == null){
           return ResponseEntity.notFound().build();
       }
       service.elimina(id);
       return ResponseEntity.noContent().build();
    }
}
