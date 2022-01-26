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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/v1/pagamento")
@Validated
public class PagamentoResource {

    private final ModelMapper modelMapper;
    private final PagamentoEntityToResponseConverter pagamentoEntityToResponseConverter;
    private final PagamentoService service;


    @PostMapping
    public ResponseEntity<PagamentoResponse> criar(@RequestBody @Valid PagamentoRequest request) {
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

    @PatchMapping("/{id}/estorno")
    public ResponseEntity<PagamentoResponse> estornar(@PathVariable String id) {
        PagamentoEntity entity = service.estornar(Long.valueOf(id));
        final PagamentoResponse response = pagamentoEntityToResponseConverter
                .encode(entity);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<PagamentoResponse> pesquisarPorId (@Valid @PathVariable @NotEmpty
                                                                          @Digits(integer = 16, fraction = 0, message = "Valor informado como ID não é válido") String id) {
        final Optional<PagamentoEntity> optional = service.pesquisarPorId(Long.valueOf(id));
        final PagamentoEntity entity = optional.orElseThrow(NoSuchElementException::new);
        final PagamentoResponse response = pagamentoEntityToResponseConverter
                .encode(entity);
        return ResponseEntity.ok(response);
    }
}
