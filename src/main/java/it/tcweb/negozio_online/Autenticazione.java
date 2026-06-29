package it.tcweb.negozio_online;

public class Autenticazione {

    // Quando effetuiamo il login su un sito tradizionale, il server si ricorda di noi : questa cosa si chiama sessione
    // La sessione è un approccio di tipo "stateful", ovvero il server mantiene uno "stato", si ricorda chi siamo tra
    // una richiesta e l'altra.

    // Le API REST sono "stateless", il server non si ricorda nulla tra una richiesta e l'altra. Ogni richiesta è indipendente.
    // Questo crea un problema : se il server non si ricorda che siamo loggati, come fa a sapere chi siamo alla richiesta successiva?

    // La risposta è la seguente : invece di farci ricordare dal server, siamo noi (il client) che ad ogni richiesta
    // ci portiamo dietro "una prova" di chi siamo. Questa prova si chiama "token".
    // Il token che useremo noi si chiama JWT (JSON Web Token".
    // Nella pratica è una stringa di testo, lunga e apparentemente incomprensibile.

    // Il token JWT viene consegnato dal server al client dopo il login. Il client mostra il token ad ogni richiesta successiva

    // Il token JWT è composto da tre sezioni separate da un punto :

    // 1) La prima sezione è l'header (intestazione), dice come è fatto il token, ad esempio con quale algoritmo

    // 2) La seconda sezione è il payload, ovvero il corpo effettivo del token. Le informazioni presenti nel payload
    // per noi sono le più interessanti : username, durata del token ecc..

    // 3) La terza sezione è la firma del token. La firma è una specie di sigillo che solo il nostro server sa creare.
    // Serve a rendere il token sicuro; se qualcuno prova a modificare il payload, ad esempio cambiandone i dati, la firma
    // non corrisponde più e il server se ne accorge subito, rifiutando il token.

    // Se il token è mancante, mal formato oppure falsificato il server blocca le chiamate e restituisce lo status code 401 Not authorized

    // Possiamo fare tutto questo grazie a uno strumento chiamato Spring Security. E' una libreria che si occupa di tutta la parte di sicurezza.
    // Di default, Spring Security blocca OGNI endpoint e chiede le credenziali.

    // Regola da non violare MAI : le password non si salvano MAI in chiaro nel database.

    // Quello che si fa è salvare la password "hashata" : l'hashing è una trasformazione a senso unico, prende la password
    // e la trasforma in una stringa incomprensibile.
























}

