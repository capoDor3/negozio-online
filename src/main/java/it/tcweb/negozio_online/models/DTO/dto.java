package it.tcweb.negozio_online.models.DTO;

public class dto {
    /*
    DTO sta per Data Transfer Object - oggetto per il trasferimento dei dati
    Far gestire ai controller direttamente l'entity collagata al database è una cattiva idea, per 3 motivi:
    1) La sicurezza, l'entity rappresenta esattamente la tabella, con tutti i campi. Particolarmente problematico
       quando abbiamo un campo utente con password oppure dati sensibili
    2) La flessibilità , la struttura dell'entity è decisa dal database, ma la struttura della risposta API dovrebbe
       essere decisa dalle esigenze di chi usa l'API.
    3) Il loop JSON, con i DTO il problema sparisce perchè siamo noi a decidere cosa mettere nella risposta.

    Un DTO è una semplice classe Java che rappresenta solo i dati che vogliamo scambiare con il client.
    Niente annotazioni JPA, niente relazioni. Solo campi, costruttori e getter.

    Solitamente per un endpoint si usano 2 DTO :
    - Il dto della richiesta che è quello in ingresso, contiene i campi
      che il client può inviare, con sopra le annotazioni di validazione eccettera..
    - Il dto della risposta che è quello in uscita e contiene i campi che vogliamo mostrare, per esempio
      di un prodotto vogliamo mostrare solo nome, prezzo e categoria
     */
}
