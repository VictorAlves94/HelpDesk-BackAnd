package com.victor.HelpDesk.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends  StandardError {
    private static final long serialVersionUid = 1L;

    private List<FieldMessege> errors = new ArrayList<>();

    public ValidationError(long l, int value, String erroNaValidaçãoDosCampos, String message, String requestURI) {
        super();
    }

    public ValidationError(Long timestamp, Integer status, String error, String message, String path, List<FieldMessege> errors) {
        super(timestamp, status, error, message, path);
        this.errors = errors;
    }

    public List<FieldMessege> getErrors() {
        return errors;
    }

    public void addErrors(String fieldName, String fieldMessage) {
        this.errors.add(new FieldMessege(fieldName, fieldMessage));
    }
}
