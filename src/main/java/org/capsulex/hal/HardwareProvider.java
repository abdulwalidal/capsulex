package org.capsulex.hal;

import org.capsulex.core.model.FirmwareUpdate;
import org.capsulex.core.model.SystemTelemetry;

import java.util.List;

/**
 * Universal hardware abstraction interface for cross-platform telemetry and simulation.
 */
public interface HardwareProvider {

    /**
     * Human-readable identifier of the telemetry source.
     */
    String getProviderName();

    /**
     * Safely reads current system telemetry snapshot.
     */
    SystemTelemetry getTelemetry();

    /**
     * Queries available candidate firmware updates.
     */
    List<FirmwareUpdate> getAvailableUpdates();

    /**
     * Indicates whether this provider operates in safe simulation mode.
     */
    boolean isSimulator();
}
