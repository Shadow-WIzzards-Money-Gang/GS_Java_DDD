package br.com.fiap.space.domain.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.fiap.space.domain.entity.Sonda;
import br.com.fiap.space.domain.enums.TipoSonda;

public interface SondaRepository {
    public void salvar(Sonda sonda);
    public List<Sonda> listar();
    public List<Sonda> listarPorTipo(TipoSonda tipoSonda);
    public Optional<Sonda> buscarPorId(String idSonda);
}
