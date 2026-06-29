package it.tcweb.negozio_online.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice // intercetta le eccezione di TUTTI i controller
public class GlobalExceptionHandler {

    @ExceptionHandler(RisorsaNonTrovataException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(RisorsaNonTrovataException ex){
        ErrorResponse body = new ErrorResponse(404,ex.getMessage());
        return ResponseEntity.status(404).body(body);
    }

    @ExceptionHandler(Exception.class) // gestiamo qualunque altro errore imprevisto
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex){
        ErrorResponse body = new ErrorResponse(500,ex.getMessage());
        return ResponseEntity.status(500).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex){

        Map<String, String> errori = new LinkedHashMap<>();

        ex.getBindingResult()       //getBindingResult contiene il risultato della validazione
                .getFieldErrors()   // contiene la lista degli errori, uno per campo
                .forEach(err ->
                        errori.put(err.getField(),          // inseriamo nella nostra mappa il nome del campo che ha dato errore
                                err.getDefaultMessage()));  // e poi l'errore specifico

        return ResponseEntity.badRequest().body(errori);
    }
}
