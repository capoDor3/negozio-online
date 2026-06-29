package it.tcweb.negozio_online.models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdottoRequestDTO {

    @NotBlank(message = "Nome obbligatorio")
    @Size(min = 2, max = 100)
    private String nome;

    @NotNull
    @Positive(message = "Prezzo deve essere positivo")
    private BigDecimal prezzo;

    private Integer stock;

    private Integer categoriaId;  // solo l'id, non l'intero oggetto Categoria


}
