package br.com.fiap.space.domain.entity;

import java.util.Random;

import br.com.fiap.space.application.singleton.CentroDeComando;
import br.com.fiap.space.domain.enums.Recurso;
import br.com.fiap.space.domain.enums.TipoSonda;
import br.com.fiap.space.domain.exceptions.CargaExcedidaException;
import br.com.fiap.space.domain.valueobject.CompartimentoCarga;
import br.com.fiap.space.domain.valueobject.Coordenada;
import br.com.fiap.space.domain.valueobject.NivelEnergia;
import br.com.fiap.space.domain.valueobject.Relatorio;
import br.com.fiap.space.domain.valueobject.RelatorioSondaMineradora;

public class SondaMineradora extends Sonda {

    private CompartimentoCarga carga;

    public SondaMineradora(NivelEnergia bateria, Coordenada posicaoAtual, CompartimentoCarga carga) {
        super(bateria, posicaoAtual);
        this.carga = carga;
    }

    @Override
    protected void realizarAcaoLocal() {
        Random random = new Random();
        boolean minerouAlgo = false;

        for (Recurso recurso : Recurso.values()) {
            Integer quantidade = random.nextInt(4) + 3;
            if (this.carga.temCapacidade(recurso, quantidade)) {
                minerar(recurso, quantidade);
                minerouAlgo = true;
            }
        }

        // Carga cheia: nenhum recurso coube. O operador deve ser avisado.
        if (!minerouAlgo) {
            throw new CargaExcedidaException();
        }
    }

    @Override
    protected void enviarRelatorio() {
        Relatorio relatorio = new RelatorioSondaMineradora(this, this.carga, this.carga.getRecursos());
        CentroDeComando.getInstancia().receberRelatorio(relatorio);
    }

    public void minerar(Recurso recurso, Integer quantidade) {
        if (!this.carga.temCapacidade(recurso, quantidade)) {
            throw new CargaExcedidaException();
        }
        this.consumirEnergia(10.0 + (recurso.getPesoPorUnidade() * quantidade));
        this.carga = this.carga.adicionarCarga(recurso, quantidade);
    }

    @Override
    public TipoSonda getTipoSonda() {
        return TipoSonda.MINERADORA;
    }

    @Override
    public void conectarBase() {
        super.conectarBase();
        this.carga = this.carga.descarregar();
    }

    @Override
    protected boolean possuiRodas() {
        return true;
    }

    public CompartimentoCarga getCarga() {
        return carga;
    }
}
