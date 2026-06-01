package br.com.fiap.space.domain.valueobject;

import java.util.HashMap;

import br.com.fiap.space.domain.enums.Recurso;
import br.com.fiap.space.domain.exceptions.CargaExcedidaException;

public class CompartimentoCarga {

    private Double volumeOcupado;
    private Double volumeMaximo;

    private HashMap<Recurso, Integer> recursos;

    public CompartimentoCarga(Double volumeMaximo) {
        this.volumeOcupado = 0.0;
        this.volumeMaximo = volumeMaximo;
        this.recursos = new HashMap<>();
    }

    public CompartimentoCarga(Double volumeOcupado, Double volumeMaximo, HashMap<Recurso, Integer> recursos) {
        this.volumeOcupado = volumeOcupado;
        this.volumeMaximo = volumeMaximo;
        this.recursos = recursos;
    }

    public CompartimentoCarga adicionarCarga(Recurso recurso, Integer quantidade) {
        Double pesoTotal = recurso.getPesoPorUnidade() * quantidade;

        if (this.volumeOcupado + pesoTotal > this.volumeMaximo) {
            throw new CargaExcedidaException();
        }

        HashMap<Recurso, Integer> novosRecursos = new HashMap<>(this.recursos);
        novosRecursos.put(recurso, novosRecursos.getOrDefault(recurso, 0) + quantidade);

        return new CompartimentoCarga(this.volumeOcupado + pesoTotal, this.volumeMaximo, novosRecursos);
    }

    public Double getVolumeOcupado() {
        return volumeOcupado;
    }

    public Double getVolumeMaximo() {
        return volumeMaximo;
    }

    public HashMap<Recurso, Integer> getRecursos() {
        return recursos;
    }

}
