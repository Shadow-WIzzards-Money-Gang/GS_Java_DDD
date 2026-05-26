package br.com.fiap.space.domain.entity;

import java.util.Random;

import br.com.fiap.space.application.singleton.CentroDeComando;
import br.com.fiap.space.domain.enums.Recurso;
import br.com.fiap.space.domain.enums.TipoSonda;
import br.com.fiap.space.domain.valueobject.CompartimentoCarga;
import br.com.fiap.space.domain.valueobject.Coordernada;
import br.com.fiap.space.domain.valueobject.NivelEnergia;
import br.com.fiap.space.domain.valueobject.Relatorio;
import br.com.fiap.space.domain.valueobject.RelatorioSondaMineradora;

public class SondaMineradora extends Sonda {

    private CompartimentoCarga carga;

    public SondaMineradora(String idSonda, NivelEnergia bateria, Coordernada posicaoAtual, CompartimentoCarga carga) {
        super(idSonda, bateria, posicaoAtual);
        this.carga = carga;
    }

    @Override
    protected void realizarAcaoLocal() {
        Random random = new Random();
        for (Recurso recurso : Recurso.values()) {
            // Gera um número aleatório entre 3 e 7
            Integer quantidade = random.nextInt(4) + 3;
            minerar(recurso, quantidade);
        }
    }

    @Override
    protected void enviarRelatorio() {
        Relatorio relatorio = new RelatorioSondaMineradora(this, this.carga, this.carga.getRecursos());
        CentroDeComando.getInstancia().receberRelatorio(relatorio);
    }

    public void minerar(Recurso recurso, Integer quantidade) {
        this.consumirEnergia(10.0 + (recurso.getPesoPorUnidade() * quantidade));
        this.carga = this.carga.adicionarCarga(recurso, quantidade);
    }

    @Override
    public TipoSonda getTipoSonda() {
        return TipoSonda.MINERADORA;
    }

    public CompartimentoCarga getCarga() {
        return carga;
    }
}
