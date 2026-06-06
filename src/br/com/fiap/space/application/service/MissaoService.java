package br.com.fiap.space.application.service;

import java.util.List;
import java.util.Optional;

import br.com.fiap.space.application.factory.SondaFactory;
import br.com.fiap.space.application.singleton.CentroDeComando;
import br.com.fiap.space.domain.entity.Sonda;
import br.com.fiap.space.domain.enums.Terreno;
import br.com.fiap.space.domain.enums.TipoSonda;
import br.com.fiap.space.domain.exceptions.SondaNaoEncontradaException;
import br.com.fiap.space.domain.valueobject.Coordenada;

public class MissaoService {

    private CentroDeComando centroDeComando;

    public MissaoService() {
        this.centroDeComando = CentroDeComando.getInstancia();
    }

    public void criarSonda(TipoSonda tipoSonda, Integer eixoX, Integer eixoY, Double nivelBateria, Double capacidadeMaxima, Double alcanceSensor, Double volumeMaximo) {
        
        /* volumeMaximo ou alcanceSensor será null dependendo do tipo de sonda */

        Sonda sonda = SondaFactory.criarSonda(tipoSonda, eixoX, eixoY, nivelBateria, capacidadeMaxima, alcanceSensor, volumeMaximo);
        this.centroDeComando.cadastrarSonda(sonda);
    }

    public void executarRotinaAutonoma(String idSonda, Integer eixoX, Integer eixoY, Terreno terreno) {
        Sonda sonda = this.buscarSondaPorId(idSonda);
        sonda.executarRotinaAutonoma(new Coordenada(eixoX, eixoY), terreno);
    }

    public List<Sonda> listarSondas() {
        return this.centroDeComando.listarSondas();
    }

    public void conectarBase(String idSonda) {
        Sonda sonda = this.buscarSondaPorId(idSonda);
        sonda.conectarBase();
    }

    private Sonda buscarSondaPorId(String idSonda) {
        Optional<Sonda> sondaOptional = this.centroDeComando.buscarSondaPorId(idSonda);

        if (!sondaOptional.isPresent()) {
            throw new SondaNaoEncontradaException();
        }

        Sonda sonda = sondaOptional.get();
        return sonda;
    }
}
