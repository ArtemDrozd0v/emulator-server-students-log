package org.example.validators.request;

import java.util.List;

public interface RequestValidator<T> {
    List<String> validate(T request);
}
