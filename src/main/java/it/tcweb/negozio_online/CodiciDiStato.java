package it.tcweb.negozio_online;

public class CodiciDiStato {
    /*
    Ogni risposta HTTP porta con sè un numero che riassume come è andata.
    200 OK è quello di default, significa "tutto bene, ecco i dati"
    201 Created significa "Ho creato la risorsa che mi hai chiesto". Lo si usa dopo un POST
    204 No content significa "Operazione riuscita, ma non ho niente da restituirti". Tipico dopo un DELETE

    ERRORI

    400 Bad Request significa "La tua richiesta è fatta male, mancano dati o sono sbagliati"
    404 Not found significa "Quello che cerchi non esiste"

    500 Internal Server Error significa "Errore interno del server"

    Per ricevere una risposta più interessante dai controller in Spring boot, si utilizza ResponseEntity

    Di default in caso di errori imprevisti, Spring rimanda al client una risposta che contiene lo stack trace
    Lo stack trace è la lista completa degli errori interni.

    E' un problema per due motivi:
    1) è brutto da vedere per chi usa l'API
    2) E' un problema di sicurezza : stiamo regalando a chiunque i dettagli di come è fatto il nostro codice dentro

    Per un prodotto che non esiste , invede di restituire null, lanciamo un'eccezione.
     */
}
