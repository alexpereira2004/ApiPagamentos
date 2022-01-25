package br.com.lunacom.tools.service;

import br.com.lunacom.tools.domain.entity.PagamentoEntity;
import br.com.lunacom.tools.repository.PagamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PagamentoService {
    private PagamentoRepository repository;

    public PagamentoEntity salvar(PagamentoEntity pagamento) {
        return repository.save(pagamento);
    }
}
