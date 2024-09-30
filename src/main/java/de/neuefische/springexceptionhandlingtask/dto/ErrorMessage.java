package de.neuefische.springexceptionhandlingtask.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class ErrorMessage {
    private String message;
    private Instant timestamp;

    public ErrorMessage(String message) {
        this.message = message;
        this.timestamp = Instant.now();
    }

    public ErrorMessage(String message, Instant timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

}
