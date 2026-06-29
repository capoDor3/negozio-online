package it.tcweb.negozio_online.service;

import it.tcweb.negozio_online.exceptions.RisorsaNonTrovataException;
import it.tcweb.negozio_online.models.Categoria;
import it.tcweb.negozio_online.models.Prodotto;
import it.tcweb.negozio_online.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repository;

    public List<Categoria> trovaTutti(){
        return repository.findAll();
    }

    public Categoria trovaPerId(Integer id){
        return repository.findById(id).orElseThrow(()-> new RisorsaNonTrovataException(
                "Categoria con id " + id + " non trovata"
        ));
    }

    public Categoria salva(Categoria categoria){
        return repository.save(categoria);
    }

    public void elimina(Integer id) {
        repository.deleteById(id);
    }
}
