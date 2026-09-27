package org.capsulex.core.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Historical audit log item for local event records.
 */
public record AuditLogEntry(
        String timestamp,
        String eventType,
        String fromVersion,
        String toVersion,
        String status,
        String details
) {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static AuditLogEntry create(String eventType, String fromVersion, String toVersion, String status, String details) {
        return new AuditLogEntry(
                LocalDateTime.now().format(FORMATTER),
                eventType,
                fromVersion,
                toVersion,
                status,
                details
        );
    }
}
