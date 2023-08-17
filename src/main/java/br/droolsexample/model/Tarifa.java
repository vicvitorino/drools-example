package br.droolsexample.model;

import lombok.Data;

@Data
public class Tarifa {

    private Long sobretaxaNoturna = 0L;
    private Long tarifaPasseio = 0L;

    public Long getTarifaTotal() {
        return sobretaxaNoturna + tarifaPasseio;
    }

}
