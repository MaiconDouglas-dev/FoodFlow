package com.foodflow.domain.order.exception;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public class BusinessException extends RuntimeException {

    private final BusinessErrorCode code;
    private final Map<String, Object> details;

    public BusinessException(BusinessErrorCode code, String message) {
        this(code, message, Collections.emptyMap());
    }

    public BusinessException(BusinessErrorCode code, String message, Map<String, Object> details) {
        super(Objects.requireNonNull(message, "message é obrigatório"));
        this.code = Objects.requireNonNull(code, "code é obrigatório");
        this.details = details == null ? Collections.emptyMap() : Collections.unmodifiableMap(details);
    }

    public BusinessErrorCode getCode() {
        return code;
    }

    public Map<String, Object> getDetails() {
        return details;
    }
}
