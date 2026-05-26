package br.com.fiap.space.domain.entity;

import br.com.fiap.space.application.singleton.CentroDeComando;
import br.com.fiap.space.domain.enums.TipoSonda;
import br.com.fiap.space.domain.valueobject.Coordernada;
import br.com.fiap.space.domain.valueobject.NivelEnergia;
import br.com.fiap.space.domain.valueobject.Relatorio;
import br.com.fiap.space.domain.valueobject.RelatorioSondaExploradora;

public class SondaExploradora extends Sonda {

    private Double alcanceSensor;

    public SondaExploradora(NivelEnergia bateria, Coordernada posicaoAtual, Double alcanceSensor) {
        super(bateria, posicaoAtual);
        this.alcanceSensor = alcanceSensor;
    }

    @Override
    protected void realizarAcaoLocal() {
        this.consumirEnergia(10.0);
    }

    @Override
    protected void enviarRelatorio() {
        Relatorio relatorio = new RelatorioSondaExploradora(this, this.alcanceSensor);
        CentroDeComando.getInstancia().receberRelatorio(relatorio);
    }

    @Override
    public TipoSonda getTipoSonda() {
        return TipoSonda.EXPLORADORA;
    }

    public Double getAlcanceSensor() {
        return alcanceSensor;
    }

}
