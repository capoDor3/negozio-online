package it.tcweb.negozio_online.service;

import it.tcweb.negozio_online.exceptions.RisorsaNonTrovataException;
import it.tcweb.negozio_online.models.Cliente;
import it.tcweb.negozio_online.models.Ordine;
import it.tcweb.negozio_online.repository.ClienteRepository;
import it.tcweb.negozio_online.repository.OrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdineService {

    @Autowired
    private OrdineRepository ordineRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Ordine> trovaTutti(){
        return ordineRepository.findAll();
    }

    public Ordine trovaPerId(Integer id){
        return ordineRepository.findById(id).orElseThrow(()-> new RisorsaNonTrovataException(
                "Ordine con id " + id + " non trovato"
        ));
    }

    public List<Ordine> trovaByCliente(Integer clienteId){
        return ordineRepository.findByCliente_Id(clienteId);
    }

    public Ordine salva(Ordine ordine, Integer clienteId){

        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(()->
                new RisorsaNonTrovataException("Cliente con id " + clienteId + " non trovato"));

        ordine.setCliente(cliente);

        return ordineRepository.save(ordine);
    }

    public void elimina(Integer id) {
        ordineRepository.deleteById(id);
    }
}
