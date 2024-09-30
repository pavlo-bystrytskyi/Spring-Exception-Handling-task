package de.neuefische.springexceptionhandlingtask.dto;

import java.time.Instant;


public record ErrorMessage(String message, Instant timestamp) {
}
