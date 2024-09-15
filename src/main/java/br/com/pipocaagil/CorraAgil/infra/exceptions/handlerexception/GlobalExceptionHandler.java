package br.com.pipocaagil.CorraAgil.infra.exceptions.handlerexception;

import br.com.pipocaagil.CorraAgil.infra.exceptions.EntityErrorResponse;
import br.com.pipocaagil.CorraAgil.infra.exceptions.ValidacaoException;
import br.com.pipocaagil.CorraAgil.infra.exceptions.ValidationError;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> error404(
            EntityNotFoundException ex,
            WebRequest request) {

        String errorMessage = ex.getMessage();

        log.error("Erro ao salvar novo cadastro com problema(s): " + errorMessage, ex);

        return buildErrorResponse(
                ex,
                errorMessage,
                HttpStatus.NOT_FOUND,
                request);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity tratarErroBadCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity tratarErroAuthentication() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity tratarErroAcessoNegado() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<List<ValidationError>> error400(
            MethodArgumentNotValidException ex) {
        // Extrai os erros de campo da exceção
        var errors = ex.getFieldErrors();

        // Registra os erros de validação no log
        log.warn("Validation failed: {}", errors);

        // Converte a lista de FieldError para ValidationError e retorna a resposta
        List<ValidationError> validationErrors = errors.stream()
                .map(ValidationError::new)
                .toList();
        return ResponseEntity.badRequest().body(validationErrors);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException methodArgumentNotValidException,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        EntityErrorResponse errorResponse = new EntityErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Validation error. Check 'errors' field for details.");

        for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            errorResponse.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    // HttpStatus 500 Internal Server Error
    public ResponseEntity<Object> handleAllUncaughtException(
            Exception ex,
            WebRequest request) {

        final String errorMessage = "An unexpected error occurred.";
        log.error("An unexpected error occurred: ", ex);

        return buildErrorResponse(
                ex,
                errorMessage,
                HttpStatus.INTERNAL_SERVER_ERROR,
                request);
    }

    private ResponseEntity<Object> buildErrorResponse(
            Exception ex,
            String errorMessage,
            HttpStatus httpStatus,
            WebRequest request) {

        EntityErrorResponse errorResponse = new EntityErrorResponse(httpStatus.value(), errorMessage);

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    // Error 409 Conflict
    public ResponseEntity<Object> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex,
            WebRequest request) {

        String errorMessage = ex.getMostSpecificCause().getMessage();

        log.error("Erro ao salvar novo cadastro com problema(s): " + errorMessage, ex);

        return buildErrorResponse(
                ex,
                errorMessage,
                HttpStatus.CONFLICT,
                request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    // Error 422 Unprocessable Entity
    public ResponseEntity<Object> handleConstraintViolationException(
            ConstraintViolationException ex,
            WebRequest request) {

        log.error("Erro de validação do elemento " + ex);

        return buildErrorResponse(
                ex,
                HttpStatus.UNPROCESSABLE_ENTITY,
                request);
    }

    private ResponseEntity<Object> buildErrorResponse(
            Exception ex,
            HttpStatus httpStatus,
            WebRequest request) {
        return buildErrorResponse(ex, ex.getMessage(), httpStatus, request);
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity tratarErroValidacao(ValidacaoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
