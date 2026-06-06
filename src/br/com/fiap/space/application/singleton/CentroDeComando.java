package br.com.fiap.space.application.singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.fiap.space.domain.entity.Sonda;
import br.com.fiap.space.domain.enums.TipoSonda;
import br.com.fiap.space.domain.exceptions.RelatorioInvalidoException;
import br.com.fiap.space.domain.interfaces.SondaRepository;
import br.com.fiap.space.domain.valueobject.Relatorio;
import br.com.fiap.space.infra.SondaRepositoryImpl;

public class CentroDeComando {

    private static final CentroDeComando instancia = new CentroDeComando();
    private SondaRepository sondaRepository;
    private List<Relatorio> relatorios;

    private CentroDeComando() {
        this.sondaRepository = SondaRepositoryImpl.getInstancia();
        this.relatorios = new ArrayList<>();
    }

    public static CentroDeComando getInstancia() {
        return instancia;
    }

    public void cadastrarSonda(Sonda sonda) {
        this.sondaRepository.salvar(sonda);
    }

    public List<Sonda> listarSondas() {
        return this.sondaRepository.listar();
    }

    public List<Sonda> listarSondasPorTipo(TipoSonda tipoSonda) {
        return this.sondaRepository.listarPorTipo(tipoSonda);
    }

    public Optional<Sonda> buscarSondaPorId(String idSonda) {
        return this.sondaRepository.buscarPorId(idSonda);
    }

    public void receberRelatorio(Relatorio relatorio) {
        if (relatorio == null) {
            throw new RelatorioInvalidoException();
        }
        this.relatorios.add(relatorio);
    }

}
