package br.ufrn.imd.repasse_v2.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LojaDTO {
    @NotBlank(message = "O nome da loja é obrigatório")
    private String nome;
}