package br.com.fiap.space.domain.valueobject;

import br.com.fiap.space.domain.entity.Sonda;

public class RelatorioSondaExploradora extends Relatorio {

    private Double areaExplorada;

    public RelatorioSondaExploradora(Sonda sonda, Double areaExplorada) {
        super(sonda);
        this.areaExplorada = areaExplorada;
    }

    public Double getAreaExplorada() {
        return areaExplorada;
    }
}
