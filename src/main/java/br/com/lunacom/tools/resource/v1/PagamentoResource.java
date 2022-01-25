package br.com.lunacom.tools.resource.v1;

import br.com.lunacom.tools.converter.PagamentoEntityToResponseConverter;
import br.com.lunacom.tools.domain.entity.PagamentoEntity;
import br.com.lunacom.tools.domain.request.PagamentoRequest;
import br.com.lunacom.tools.domain.response.PagamentoResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/v1/pagamento")
@Validated
public class PagamentoResource {

    private final ModelMapper modelMapper;
    private final PagamentoEntityToResponseConverter pagamentoEntityToResponseConverter;

    @PostMapping
    public ResponseEntity<PagamentoResponse> create(@RequestBody @Valid PagamentoRequest request) {
        PagamentoEntity pagamentoEntity = modelMapper.map(request.getTransacao(), PagamentoEntity.class);
//        final SolicitacaoFaturamento solicitacaoFaturamento = service.criar(converter.encode(request));
//        final List<SolicitacaoFaturamentoItem> itens = itemRequestConverter.encode(request.getItens());
//        service.criarDiretorio(solicitacaoFaturamento);
//        final List<SolicitacaoFaturamentoItem> atualizarItens = itemService.uploadAnexos
//                (solicitacaoFaturamento.getId(), request.getItens(), itens);
//        itemService.salvarTodos(atualizarItens, solicitacaoFaturamento);
//        itemService.atualizarSaldo(atualizarItens, solicitacaoFaturamento);
//        service.atualizarStatus(solicitacaoFaturamento, atualizarItens.get(0).getDocumento().getTipo());
//        service.enviarEmailDeCriacaoDeSolicitacaoFaturamento(solicitacaoFaturamento);
//
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}").buildAndExpand(solicitacaoFaturamento.getId()).toUri();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(uri);
//        final IdResponse response = new IdResponse(String.valueOf(solicitacaoFaturamento.getId()));
//        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
        PagamentoResponse response = pagamentoEntityToResponseConverter.encode(pagamentoEntity);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
