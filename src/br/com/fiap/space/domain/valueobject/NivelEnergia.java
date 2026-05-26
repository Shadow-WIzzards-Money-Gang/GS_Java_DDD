package br.com.fiap.space.domain.valueobject;

import br.com.fiap.space.domain.exceptions.BateriaCriticaException;
import br.com.fiap.space.domain.exceptions.CapacidadeBateriaInvalidaException;

public class NivelEnergia {

    private Double capacidadeAtual;
    private Double capacidadeMaxima;

    public NivelEnergia(Double capacidadeAtual, Double capacidadeMaxima) {

        if (capacidadeAtual > capacidadeMaxima) {
            throw new CapacidadeBateriaInvalidaException();
        }

        if (capacidadeAtual < 0 || capacidadeMaxima < 0) {
            throw new CapacidadeBateriaInvalidaException();
        }

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
