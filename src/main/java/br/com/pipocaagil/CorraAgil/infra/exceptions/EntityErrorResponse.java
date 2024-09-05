package br.com.pipocaagil.CorraAgil.infra.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
public class EntityErrorResponse {
    private final int httpStatus;
    private final String message;
    private String stackTrace;
    private List<ValidationError> validationErrors;

    public void addValidationError(String field, String message) {
        if (Objects.isNull(validationErrors)) {
            this.validationErrors = new ArrayList<>();
        }
        this.validationErrors.add(new ValidationError(field, message));
    }
}
