package br.com.lunacom.tools.validation.validator;

import br.com.lunacom.tools.domain.entity.PagamentoEntity;
import br.com.lunacom.tools.repository.PagamentoRepository;
import br.com.lunacom.tools.validation.PagamentoUnico;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class PagamentoUnicoValidator implements ConstraintValidator<PagamentoUnico, String> {

   private final PagamentoRepository repository;

   public void initialize(PagamentoUnico constraint) {
   }

   public boolean isValid(String valor, ConstraintValidatorContext context) {
      if (Objects.isNull(valor)) {
         return true;
      }
      Optional<PagamentoEntity> list = repository.findById(Long.valueOf(valor));
      return list.isEmpty();
   }
}
