package br.com.lunacom.tools.service;

import br.com.lunacom.tools.domain.entity.PagamentoEntity;
import br.com.lunacom.tools.domain.enumeration.StatusEnum;
import br.com.lunacom.tools.repository.DescricaoRepository;
import br.com.lunacom.tools.repository.PagamentoRepository;
import br.com.lunacom.tools.util.RandomData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PagamentoService {
    private PagamentoRepository repository;
    private DescricaoRepository descricaoRepository;

    public PagamentoEntity salvar(PagamentoEntity pagamento) {
        pagamento.getDescricao().setNsu(RandomData.generateLimitedInteger(10));
        pagamento.getDescricao().setCodigoAutorizacao(RandomData.generateLimitedInteger(9));
        pagamento.getDescricao().setStatus(StatusEnum.AUTORIZADO);
        return repository.save(pagamento);
    }

    public PagamentoEntity estornar(Long id) {
        Optional<PagamentoEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ValidationException(String.format("Pagamento informado pelo id %s não existe.", id));
        }

        PagamentoEntity pagamentoEntity = optional.get();
        descricaoRepository.atualizarStatus(StatusEnum.CANCELADO, pagamentoEntity.getDescricao().getId());
        pagamentoEntity.getDescricao().setStatus(StatusEnum.CANCELADO);
        return pagamentoEntity;
    }

    public Optional<PagamentoEntity> pesquisarPorId(Long id) {
        return repository.findById(id);
    }

    public List<PagamentoEntity> pesquisarTodos() {
        return repository.findAll();
    }
}
