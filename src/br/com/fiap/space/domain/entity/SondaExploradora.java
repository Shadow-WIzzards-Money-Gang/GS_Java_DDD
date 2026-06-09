package br.com.fiap.space.domain.entity;

import br.com.fiap.space.application.singleton.CentroDeComando;
import br.com.fiap.space.domain.enums.TipoSonda;
import br.com.fiap.space.domain.exceptions.AlcanceSensorInvalidoException;
import br.com.fiap.space.domain.valueobject.Coordenada;
import br.com.fiap.space.domain.valueobject.NivelEnergia;
import br.com.fiap.space.domain.valueobject.Relatorio;
import br.com.fiap.space.domain.valueobject.RelatorioSondaExploradora;

public class SondaExploradora extends Sonda {

    private Double alcanceSensor;

    public SondaExploradora(NivelEnergia bateria, Coordenada posicaoAtual, Double alcanceSensor) {
        super(bateria, posicaoAtual);

        if (alcanceSensor == null || alcanceSensor <= 0) {
            throw new AlcanceSensorInvalidoException();
        }

        this.alcanceSensor = alcanceSensor;
    }

    @Override
    protected void realizarAcaoLocal() {
        // Mapeia/varre a area circular dentro do raio do sensor.
        this.consumirEnergia(10.0);
    }

    @Override
    protected void enviarRelatorio() {
        Double areaVarrida = Math.PI * this.alcanceSensor * this.alcanceSensor;
        Relatorio relatorio = new RelatorioSondaExploradora(this, areaVarrida);
        CentroDeComando.getInstancia().receberRelatorio(relatorio);
    }

    @Override
    public TipoSonda getTipoSonda() {
        return TipoSonda.EXPLORADORA;
    }

    @Override
    protected boolean possuiRodas() {
        return true;
    }

    public Double getAlcanceSensor() {
        return alcanceSensor;
    }

}
