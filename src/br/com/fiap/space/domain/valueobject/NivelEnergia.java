package br.com.fiap.space.domain.valueobject;

import br.com.fiap.space.domain.exceptions.BateriaCriticaException;

public class NivelEnergia {

    private Double capacidadeAtual;
    private Double capacidadeMaxima;

    public NivelEnergia() {
        this.capacidadeAtual = 100.0;
        this.capacidadeMaxima = 100.0;
    }

    private NivelEnergia(Double capacidadeAtual, Double capacidadeMaxima) {
        this.capacidadeAtual = capacidadeAtual;
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public NivelEnergia consumir(Double consumo){
        
        if (consumo > this.capacidadeAtual || consumo > this.capacidadeMaxima) {
            throw new BateriaCriticaException();
        }
            
        return new NivelEnergia(
            this.capacidadeAtual - consumo, 
            this.capacidadeMaxima
        );
    }

    public NivelEnergia recarregar() {
        return new NivelEnergia(
            this.capacidadeMaxima,
            this.capacidadeMaxima
        );
    }

    public Double getCapacidadeAtual() {
        return capacidadeAtual;
    }

    public Double getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

}
