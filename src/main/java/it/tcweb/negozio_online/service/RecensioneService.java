package it.tcweb.negozio_online.service;

import it.tcweb.negozio_online.exceptions.RisorsaNonTrovataException;
import it.tcweb.negozio_online.models.Cliente;
import it.tcweb.negozio_online.models.Prodotto;
import it.tcweb.negozio_online.models.Recensione;
import it.tcweb.negozio_online.repository.ClienteRepository;
import it.tcweb.negozio_online.repository.ProdottoRepository;
import it.tcweb.negozio_online.repository.RecensioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecensioneService {

    @Autowired
    private RecensioneRepository recensioneRepository;

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Recensione> trovaTutti(){
        return recensioneRepository.findAll();
    }

    public Recensione trovaPerId(Integer id){
        return recensioneRepository.findById(id).orElseThrow(()-> new RisorsaNonTrovataException(
                "Recensione con id " + id + " non trovata"
        ));
    }

    public List<Recensione> trovaByProdotto(Integer prodottoId){
        return recensioneRepository.findByProdotto_Id(prodottoId);
    }

    public List<Recensione> trovaByCliente(Integer clienteId){
        return recensioneRepository.findByCliente_Id(clienteId);
    }

    public Recensione salva(Recensione recensione, Integer prodottoId,Integer clienteId){

        Prodotto prodotto = prodottoRepository.findById(prodottoId).orElseThrow(()->
                new RisorsaNonTrovataException("Prodotto con id " + prodottoId + " non trovato"));

        Cliente  cliente = clienteRepository.findById(clienteId).orElseThrow(()->
                new RisorsaNonTrovataException("Cliente con id " + clienteId + " non trovato"));

        recensione.setProdotto(prodotto);
        recensione.setCliente(cliente);

        return recensioneRepository.save(recensione);
    }

    public void elimina(Integer id) {
        recensioneRepository.deleteById(id);
    }
}
