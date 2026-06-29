package it.tcweb.negozio_online.service;

import it.tcweb.negozio_online.exceptions.RisorsaNonTrovataException;
import it.tcweb.negozio_online.models.Categoria;
import it.tcweb.negozio_online.models.DTO.ProdottoRequestDTO;
import it.tcweb.negozio_online.models.DTO.ProdottoResponseDTO;
import it.tcweb.negozio_online.models.Prodotto;
import it.tcweb.negozio_online.repository.CategoriaRepository;
import it.tcweb.negozio_online.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Il service è una classe che contiene la logica: regole, controlli, decisioni
// Es. "Prima di salvare un prodotto, controlla che il prezzo non sia negativo"
@Service
public class ProdottoService {

    @Autowired // Autowired è un decoratore che serve a Spring per iniettare il repository nel service
    private ProdottoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<ProdottoResponseDTO> trovaTutti(){
        return repository.findAll().stream().map(this::toResponseDTO).toList();
    }

    public ProdottoResponseDTO trovaPerId(Integer id){
        return toResponseDTO(repository.findById(id).orElseThrow(()-> new RisorsaNonTrovataException(
                "Prodotto con id " + id + " non trovato"
        )));
    }

    public ProdottoResponseDTO salva(ProdottoRequestDTO dto){
        Prodotto prodotto = new Prodotto();
        prodotto.setNome(dto.getNome());
        prodotto.setPrezzo(dto.getPrezzo());
        prodotto.setStock(dto.getStock());

        if (dto.getCategoriaId() != null){
            Categoria cat = categoriaRepository.findById(dto.getCategoriaId()).orElseThrow(
                    () -> new RisorsaNonTrovataException("Categoria non trovata"));
            prodotto.setCategoria(cat);
        }

        return toResponseDTO(repository.save(prodotto));
    }

    public ProdottoResponseDTO aggiorna(Integer id, ProdottoRequestDTO dto){
        Prodotto prodotto = repository.findById(id).orElseThrow(()->
                new RisorsaNonTrovataException("Prodotto con id " + id + " non trovato"));

        prodotto.setNome(dto.getNome());
        prodotto.setPrezzo(dto.getPrezzo());
        prodotto.setStock(dto.getStock());

        if (dto.getCategoriaId() != null){
            Categoria cat = categoriaRepository.findById(dto.getCategoriaId()).orElseThrow(
                    () -> new RisorsaNonTrovataException("Categoria non trovata"));
            prodotto.setCategoria(cat);
        } else {
            prodotto.setCategoria(null);
        }

        return toResponseDTO(repository.save(prodotto));
    }

    public void elimina(Integer id) {
        repository.deleteById(id);
    }

    private ProdottoResponseDTO toResponseDTO(Prodotto p){
        ProdottoResponseDTO dto = new ProdottoResponseDTO();
        dto.setId(p.getId());
        dto.setNome(p.getNome());
        dto.setPrezzo(p.getPrezzo());
        dto.setStock(p.getStock());
        if (p.getCategoria() != null){
            dto.setCategoriaId(p.getCategoria().getId());
            dto.setCategoriaNome(p.getCategoria().getNome());
        }
        return dto;
    }
}
