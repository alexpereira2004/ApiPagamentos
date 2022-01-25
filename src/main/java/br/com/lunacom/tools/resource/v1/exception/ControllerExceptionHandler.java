package br.com.lunacom.tools.resource.v1.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.ResourceAccessException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = ResourceAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public StandardError resourceAccessException(ResourceAccessException e, HttpServletRequest request) {
        return new StandardError("Recurso não disponível", e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ValidationError validation(MethodArgumentNotValidException e, HttpServletRequest request) {

        List<String> list = new ArrayList<>();
        ValidationError err = new ValidationError(
                "Verifique os seguintes itens antes de avançar",
                request.getRequestURI());
        for (ObjectError x : e.getBindingResult().getAllErrors()) {
            list.add(String.format("%s", x.getDefaultMessage()));
        }
        err.setDetalhe(list);
        return err;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    public final ValidationError handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        List<String> details = ex.getConstraintViolations()
                .parallelStream()
                .map(v -> String.format("%s value '%s' %s", v.getPropertyPath(), v.getInvalidValue(), v.getMessage()))
                .collect(Collectors.toList());
        return new ValidationError("É necessário revisar a requisição", details, request.getRequestURI());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    public final ValidationError handleConstraintViolation(HttpMessageNotReadableException ex, HttpServletRequest request) {
        final HttpInputMessage httpInputMessage = ex.getHttpInputMessage();
        final String detail = String.format("É necessário revisar a requisição: %s", ex.getMessage());
        return new ValidationError(detail, request.getRequestURI());
    }

    @ExceptionHandler(SecurityException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    public final ValidationError handleSecurityException(SecurityException ex, HttpServletRequest request) {
        final String detail = String.format("Ocorreu uma falha ao tentar criar a estrutura do projeto: %s", ex.getMessage());
        return new ValidationError(detail, request.getRequestURI());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    public final StandardError handleValidationException(ValidationException ex, HttpServletRequest request) {
        return new StandardError("Ocorreu este erro de validação", ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    public final StandardError handleDateTimeParseException(DateTimeParseException ex, HttpServletRequest request) {
        return new StandardError("Formato de data inválido", ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(value = FileNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public StandardError exception(FileNotFoundException e, HttpServletRequest request) {
        log.warn("FileNotFoundException >> {}",e.getMessage());
        return new StandardError("Aconteceu uma exceção", "Arquivo não existe", request.getRequestURI());
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public StandardError exception(NoSuchElementException e, HttpServletRequest request) {
        return new StandardError("Aconteceu uma exceção", "O conteúdo que você está pesquisando não foi encontrado", request.getRequestURI());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public StandardError exception(Exception e, HttpServletRequest request) {
        return new StandardError("Aconteceu uma exceção", e.getMessage(), request.getRequestURI());
    }
}
