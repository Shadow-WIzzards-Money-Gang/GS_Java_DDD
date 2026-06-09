package br.com.fiap.space.domain.entity;

import br.com.fiap.space.domain.enums.Terreno;
import br.com.fiap.space.domain.enums.TipoSonda;
import br.com.fiap.space.domain.exceptions.BateriaCriticaException;
import br.com.fiap.space.domain.exceptions.TerrenoInvalidoException;
import br.com.fiap.space.domain.interfaces.Recarregavel;
import br.com.fiap.space.domain.valueobject.Coordenada;
import br.com.fiap.space.domain.valueobject.NivelEnergia;
import br.com.fiap.space.util.SondaUtils;

public abstract class Sonda implements Recarregavel {

    private String idSonda;
    private NivelEnergia bateria;
    private Coordenada posicaoAtual;

    public Sonda(NivelEnergia bateria, Coordenada posicaoAtual) {
        this.idSonda = SondaUtils.gerarIdSonda();
        
        this.bateria = bateria;
        this.posicaoAtual = posicaoAtual;
    }

    public void conectarBase() {
        this.bateria = this.bateria.recarregar();
    }

    public void mover(Coordenada destino, Terreno terreno) {
        this.validarTerreno(terreno);

        Double distancia = this.calcularDistancia(destino);
        Double consumo = this.calcularConsumoEnergia(distancia, terreno);

        this.consumirEnergia(consumo);

        this.posicaoAtual = destino;

    }

    protected void validarTerreno(Terreno terreno) {
        if (this.possuiRodas() && terreno == Terreno.CRATERA) {
            throw new TerrenoInvalidoException();
        }
    }

    public void executarRotinaAutonoma(Coordenada destino, Terreno terreno) {
        this.validarStatusBateria();
        this.mover(destino, terreno);
        this.realizarAcaoLocal();
        this.enviarRelatorio();
    }

    protected void validarStatusBateria() {
        if (bateria.getCapacidadeAtual() <= 0) {
            throw new BateriaCriticaException();
        }
    }

    protected abstract void realizarAcaoLocal();
    protected abstract void enviarRelatorio();
    public abstract TipoSonda getTipoSonda();
    protected abstract boolean possuiRodas();

    private Double calcularDistancia(Coordenada destino) {
        Integer dx = destino.getEixoX() - posicaoAtual.getEixoX();
        Integer dy = destino.getEixoY() - posicaoAtual.getEixoY();

        return Math.sqrt(dx * dx + dy * dy);
    }

    protected void consumirEnergia(Double consumo) {
        this.bateria = this.bateria.consumir(consumo);
    }

    private Double calcularConsumoEnergia(Double distancia, Terreno terreno) {
        return distancia * terreno.getMutiplicadorConsumo();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sonda)) return false;
        Sonda sonda = (Sonda) o;
        return idSonda.equals(sonda.idSonda);
    }

    @Override
    public int hashCode() {
        return idSonda.hashCode();
    }

    public String getIdSonda() {
        return idSonda;
    }

    public NivelEnergia getBateria() {
        return bateria;
    }

    public Coordenada getPosicaoAtual() {
        return posicaoAtual;
    }

}
