package org.capsulex.core.model;

/**
 * Representation of an official firmware candidate package.
 */
public record FirmwareUpdate(
        String version,
        String releaseDate,
        String summary,
        String changelog,
        String targetGuid,
        String expectedSha256,
        boolean signatureVerified,
        long sizeBytes
) {}
