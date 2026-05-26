package br.com.fiap.space.domain.valueobject;

import java.util.HashMap;

import br.com.fiap.space.domain.entity.Sonda;
import br.com.fiap.space.domain.enums.Recurso;

public class RelatorioSondaMineradora extends Relatorio {

    private CompartimentoCarga carga;
    private HashMap<Recurso, Integer> recursos;

    public RelatorioSondaMineradora(Sonda sonda, CompartimentoCarga carga, HashMap<Recurso, Integer> recursos) {
        super(sonda);
        this.carga = carga;
        this.recursos = recursos;
    }

    public CompartimentoCarga getCarga() {
        return carga;
    }

    public HashMap<Recurso, Integer> getRecursos() {
        return recursos;
    }

}
