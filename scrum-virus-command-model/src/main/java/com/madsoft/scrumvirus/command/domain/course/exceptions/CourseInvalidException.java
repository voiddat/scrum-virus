package com.madsoft.scrumvirus.command.domain.course.exceptions;

import lombok.Data;

public class CourseInvalidException extends RuntimeException {

    public final JsonException jsonException;

    public CourseInvalidException(String message) {
        super(message);
        this.jsonException = new JsonException(message);
    }

    @Data
    public static class JsonException {
        public final String message;
    }
}
