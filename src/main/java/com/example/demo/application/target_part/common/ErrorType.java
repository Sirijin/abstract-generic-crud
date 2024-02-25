package com.example.demo.application.target_part.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {
    ENTITY_NOT_FOUND("Entity not found by id: %s"),
    ENTITY_NOT_SAVED("Entity not saved: %s"),
    ENTITY_NOT_UPDATED("Entity not updated: %s");

    private final String description;
}