package br.com.fiap.space.domain.entity;

import br.com.fiap.space.domain.enums.Terreno;
import br.com.fiap.space.domain.exceptions.BateriaCriticaException;
import br.com.fiap.space.domain.valueobject.Coordernada;
import br.com.fiap.space.domain.valueobject.NivelEnergia;

public abstract class Sonda {

    private String idSonda;
    private NivelEnergia bateria;
    private Coordernada posicaoAtual;

    public Sonda(String idSonda, NivelEnergia bateria, Coordernada posicaoAtual) {
        this.idSonda = idSonda;
        this.bateria = bateria;
        this.posicaoAtual = posicaoAtual;
    }

    public void mover(Coordernada destino, Terreno terreno) {
        Double distancia = this.calcularDistancia(destino);
        Double consumo = this.calcularConsumoEnergia(distancia, terreno);

        this.bateria = this.bateria.consumir(consumo);
    
        this.posicaoAtual = destino;
    
    }

    public void executarRotinaAutonoma(Coordernada destino, Terreno terreno) {
        this.validarStatusBateria();
        this.mover(destino, terreno);
        this.realizarAcaoLocal();
    }

    protected void validarStatusBateria() {
        if (bateria.getCapacidadeAtual() <= 0) {
            throw new BateriaCriticaException();
        }
    }

    protected abstract void realizarAcaoLocal();

    private Double calcularDistancia(Coordernada destino) {
        Integer dx = destino.getEixoX() - posicaoAtual.getEixoX();
        Integer dy = destino.getEixoY() - posicaoAtual.getEixoY();

        return Math.sqrt(dx * dx + dy * dy);
    }

    private Double calcularConsumoEnergia(Double distancia, Terreno terreno) {
        return distancia * terreno.getMutiplicadorConsumo();
    }

    public String getIdSonda() {
        return idSonda;
    }

    public NivelEnergia getBateria() {
        return bateria;
    }

    public Coordernada getPosicaoAtual() {
        return posicaoAtual;
    }

}
