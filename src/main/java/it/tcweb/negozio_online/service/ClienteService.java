package it.tcweb.negozio_online.service;

import it.tcweb.negozio_online.exceptions.RisorsaNonTrovataException;
import it.tcweb.negozio_online.models.Cliente;
import it.tcweb.negozio_online.models.Prodotto;
import it.tcweb.negozio_online.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public List<Cliente> trovaTutti(){
        return repository.findAll();
    }

    public Cliente trovaPerId(Integer id){
        return repository.findById(id).orElseThrow(()-> new RisorsaNonTrovataException(
                "Cliente con id " + id + " non trovato"
        ));
    }

    public Cliente salva(Cliente cliente){
        return repository.save(cliente);
    }

    public void elimina(Integer id) {
        repository.deleteById(id);
    }
}
