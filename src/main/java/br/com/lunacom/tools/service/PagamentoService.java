package br.com.lunacom.tools.service;

import br.com.lunacom.tools.domain.entity.PagamentoEntity;
import br.com.lunacom.tools.domain.enumeration.StatusEnum;
import br.com.lunacom.tools.repository.PagamentoRepository;
import br.com.lunacom.tools.util.RandomData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PagamentoService {
    private PagamentoRepository repository;

    public PagamentoEntity salvar(PagamentoEntity pagamento) {
        pagamento.getDescricao().setNsu(RandomData.generateLimitedInteger(10));
        pagamento.getDescricao().setCodigoAutorizacao(RandomData.generateLimitedInteger(9));
        pagamento.getDescricao().setStatus(StatusEnum.AUTORIZADO);
        return repository.save(pagamento);
    }
}
