package br.com.lunacom.tools.resource.v1;

import br.com.lunacom.tools.converter.PagamentoEntityToResponseConverter;
import br.com.lunacom.tools.domain.entity.PagamentoEntity;
import br.com.lunacom.tools.domain.request.PagamentoRequest;
import br.com.lunacom.tools.domain.response.PagamentoResponse;
import br.com.lunacom.tools.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/v1/pagamento")
@Validated
public class PagamentoResource {

    private final ModelMapper modelMapper;
    private final PagamentoEntityToResponseConverter pagamentoEntityToResponseConverter;
    private final PagamentoService service;


    @PostMapping
    public ResponseEntity<PagamentoResponse> create(@RequestBody @Valid PagamentoRequest request) {
        final PagamentoEntity pagamentoEntity = modelMapper.map(request.getTransacao(), PagamentoEntity.class);

        PagamentoEntity entityResponse = service.salvar(pagamentoEntity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(entityResponse.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);

        final PagamentoResponse response = pagamentoEntityToResponseConverter
                .encode(entityResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
