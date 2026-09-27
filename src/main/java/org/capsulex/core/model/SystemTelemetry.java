package org.capsulex.core.model;

/**
 * Immutable snapshot of system hardware and firmware telemetry.
 */
public record SystemTelemetry(
        String vendor,
        String productName,
        String biosVersion,
        String biosDate,
        String bootMode,
        boolean secureBootEnabled,
        boolean tpmActive,
        boolean virtualizationEnabled,
        boolean acConnected,
        int batteryCapacityPercent,
        String powerStatus
) {
    public boolean isSafePowerState() {
        return acConnected && batteryCapacityPercent >= 50;
    }
}
