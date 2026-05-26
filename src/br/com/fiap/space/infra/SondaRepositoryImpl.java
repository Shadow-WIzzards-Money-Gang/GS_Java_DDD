package br.com.fiap.space.infra;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.fiap.space.domain.entity.Sonda;
import br.com.fiap.space.domain.enums.TipoSonda;
import br.com.fiap.space.domain.exceptions.SondaInvalidaException;
import br.com.fiap.space.domain.interfaces.SondaRepository;

public class SondaRepositoryImpl implements SondaRepository {

    private static SondaRepositoryImpl instancia;
    private List<Sonda> sondas;

    private SondaRepositoryImpl() {
        this.sondas = new ArrayList<>();
    }

    public static SondaRepositoryImpl getInstancia() {
        if (instancia == null) {
            instancia = new SondaRepositoryImpl();
        }
        return instancia;
    }

    @Override
    public void salvar(Sonda sonda) {
        if (sonda == null) {
            throw new SondaInvalidaException();
        }
        
        this.sondas.add(sonda);
    }

    @Override
    public List<Sonda> listar() {
        return this.sondas;
    }

    @Override
    public List<Sonda> listarPorTipo(TipoSonda tipoSonda) {
        return this.sondas.stream()
                    .filter(sonda -> sonda.getTipoSonda() == tipoSonda)
                    .collect(Collectors.toList());
    }

    @Override
    public Optional<Sonda> buscarPorId(String idSonda) {
        return this.sondas.stream()
                    .filter(sonda -> sonda.getIdSonda().equals(idSonda))
                    .findFirst();
    }
}
