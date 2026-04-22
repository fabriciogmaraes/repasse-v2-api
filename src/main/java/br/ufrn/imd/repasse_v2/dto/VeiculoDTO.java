package br.ufrn.imd.repasse_v2.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VeiculoDTO {

    @NotBlank(message = "O modelo é obrigatório")
    private String modelo;

    @NotNull(message = "O valor Fipe é obrigatório")
    @Min(value = 1, message = "O valor deve ser maior que zero")
    private Double valorFipe;

    @NotNull(message = "O valor do anúncio é obrigatório")
    private Double valorAnuncio;

    @NotNull(message = "A loja ID é obrigatória")
    private Long lojaId;
}