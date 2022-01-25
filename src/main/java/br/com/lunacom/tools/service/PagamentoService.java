package br.com.lunacom.tools.service;

import br.com.lunacom.tools.domain.entity.PagamentoEntity;
import br.com.lunacom.tools.domain.enumeration.StatusEnum;
import br.com.lunacom.tools.repository.PagamentoRepository;
import br.com.lunacom.tools.resource.v1.DescricaoRepository;
import br.com.lunacom.tools.util.RandomData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
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
            throw new NotFoundException(String.format("Pagamento informado pelo id %s não existe.", id));
        }

        PagamentoEntity pagamentoEntity = optional.get();
        descricaoRepository.atualizarStatus(StatusEnum.CANCELADO, pagamentoEntity.getDescricao().getId());
        pagamentoEntity.getDescricao().setStatus(StatusEnum.CANCELADO);
        return pagamentoEntity;
    }
}
