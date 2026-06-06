package br.com.fiap.space.domain.valueobject;

import java.time.LocalDateTime;

import br.com.fiap.space.domain.entity.Sonda;

public abstract class Relatorio {

    private String idSonda;
    private NivelEnergia nivelEnergia;
    private Coordenada coordernada;

    private LocalDateTime dataHoraGeracao;
    
    public Relatorio(Sonda sonda) {
        this.idSonda = sonda.getIdSonda();
        this.nivelEnergia = sonda.getBateria();
        this.coordernada = sonda.getPosicaoAtual();

        this.dataHoraGeracao = LocalDateTime.now();
    }

    public String getIdSonda() {
        return idSonda;
    }

    public NivelEnergia getNivelEnergia() {
        return nivelEnergia;
    }

    public Coordenada getCoordenada() {
        return coordernada;
    }

    public LocalDateTime getDataHoraGeracao() {
        return dataHoraGeracao;
    }
}
