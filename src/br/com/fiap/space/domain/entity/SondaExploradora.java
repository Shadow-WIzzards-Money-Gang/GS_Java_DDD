package br.com.fiap.space.domain.entity;

import br.com.fiap.space.domain.enums.TipoSonda;
import br.com.fiap.space.domain.valueobject.Coordernada;
import br.com.fiap.space.domain.valueobject.NivelEnergia;

public class SondaExploradora extends Sonda {

    private Double alcanceSensor;

    public SondaExploradora(String idSonda, NivelEnergia bateria, Coordernada posicaoAtual, Double alcanceSensor) {
        super(idSonda, bateria, posicaoAtual);
        this.alcanceSensor = alcanceSensor;
    }

    @Override
    protected void realizarAcaoLocal() {
        this.consumirEnergia(10.0);
    }

    @Override
    public TipoSonda getTipoSonda() {
        return TipoSonda.EXPLORADORA;
    }

    public Double getAlcanceSensor() {
        return alcanceSensor;
    }

}
