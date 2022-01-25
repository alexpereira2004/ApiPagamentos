package br.com.lunacom.tools.validation;

import br.com.lunacom.tools.validation.validator.PagamentoUnicoValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PagamentoUnicoValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PagamentoUnico {
    String message() default "O Pagamento informado já existe no sistema";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
