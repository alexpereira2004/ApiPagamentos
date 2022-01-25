package br.com.lunacom.tools.converter;

import br.com.lunacom.tools.domain.entity.PagamentoEntity;
import br.com.lunacom.tools.domain.response.DescricaoResponse;
import br.com.lunacom.tools.domain.response.FormaPagamentoResponse;
import br.com.lunacom.tools.domain.response.PagamentoResponse;
import br.com.lunacom.tools.domain.response.TransacaoResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PagamentoEntityToResponseConverter
        implements Converter<PagamentoEntity, PagamentoResponse> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PagamentoResponse encode(PagamentoEntity input) {
        DescricaoResponse descricaoResponse = modelMapper.map(input.getDescricao(), DescricaoResponse.class);
        FormaPagamentoResponse formaPagamentoResponse
                = modelMapper.map(input.getFormaPagamento(), FormaPagamentoResponse.class);
        TransacaoResponse transacaoResponse = TransacaoResponse
                .builder()
                .cartao(input.getCartao())
                .id(String.valueOf(input.getId()))
                .descricao(descricaoResponse)
                .formaPagamento(formaPagamentoResponse)
                .build();

        return PagamentoResponse.builder()
                .transacao(transacaoResponse)
                .build();
    }
}
