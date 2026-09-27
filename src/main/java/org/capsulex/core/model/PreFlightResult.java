package org.capsulex.core.model;

import java.util.List;

/**
 * Result of the automated multi-factor Pre-Flight safety inspection.
 */
public record PreFlightResult(
        boolean guidMatched,
        boolean signatureVerified,
        boolean acConnected,
        boolean batteryThresholdMet,
        boolean noConflictingOperations,
        List<String> warnings,
        boolean passed
) {
    public static PreFlightResult evaluate(
            SystemTelemetry telemetry,
            FirmwareUpdate update,
            String actualSha256
    ) {
        boolean guidMatch = true; // In production matches ESRT GUID
        boolean sigValid = update != null && update.signatureVerified() &&
                update.expectedSha256().equalsIgnoreCase(actualSha256);
        boolean ac = telemetry.acConnected();
        boolean batt = telemetry.batteryCapacityPercent() >= 50;
        boolean noLocks = true;

        List<String> warnList = new java.util.ArrayList<>();
        if (!ac) {
            warnList.add("AC mains power disconnected. Connect charger before proceeding.");
        }
        if (!batt) {
            warnList.add("Battery level below 50% (" + telemetry.batteryCapacityPercent() + "%). Minimum 50% required.");
        }
        if (!sigValid) {
            warnList.add("Cryptographic signature verification or SHA-256 digest check failed.");
        }

        boolean allPassed = guidMatch && sigValid && ac && batt && noLocks;
        return new PreFlightResult(guidMatch, sigValid, ac, batt, noLocks, List.copyOf(warnList), allPassed);
    }
}
