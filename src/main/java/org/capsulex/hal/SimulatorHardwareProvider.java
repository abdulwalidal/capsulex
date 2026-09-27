package org.capsulex.hal;

import org.capsulex.core.model.FirmwareUpdate;
import org.capsulex.core.model.SystemTelemetry;

import java.util.Collections;
import java.util.List;

/**
 * Safe simulation sandbox provider.
 * Allows risk-free demonstration and validation of safety checks and error handling.
 */
public class SimulatorHardwareProvider implements HardwareProvider {

    public enum Preset {
        DELL_UPDATE_READY("Dell XPS 15 (Update Ready)"),
        LENOVO_LOW_BATTERY("Lenovo ThinkPad (Low Battery Warning)"),
        HP_SIGNATURE_MISMATCH("HP EliteBook (Signature Tamper Alert)"),
        UP_TO_DATE("ASUS ROG (Firmware Up to Date)");

        private final String label;
        Preset(String label) { this.label = label; }
        public String getLabel() { return label; }
        @Override public String toString() { return label; }
    }

    private Preset currentPreset = Preset.DELL_UPDATE_READY;

    public void setPreset(Preset preset) {
        this.currentPreset = preset;
    }

    public Preset getPreset() {
        return currentPreset;
    }

    @Override
    public String getProviderName() {
        return "Simulation Sandbox (" + currentPreset.getLabel() + ")";
    }

    @Override
    public boolean isSimulator() {
        return true;
    }

    @Override
    public SystemTelemetry getTelemetry() {
        return switch (currentPreset) {
            case DELL_UPDATE_READY -> new SystemTelemetry(
                    "Dell Inc.",
                    "XPS 15 9520",
                    "1.15.0",
                    "2026-03-10",
                    "UEFI",
                    true,
                    true,
                    true,
                    true, // AC Connected
                    88,   // Battery 88%
                    "AC Mains Connected (Charging)"
            );
            case LENOVO_LOW_BATTERY -> new SystemTelemetry(
                    "Lenovo",
                    "ThinkPad X1 Carbon Gen 11",
                    "1.34",
                    "2025-11-20",
                    "UEFI",
                    true,
                    true,
                    true,
                    false, // AC Disconnected
                    28,    // Battery 28% -> Triggers Safety Abort
                    "Battery Discharging (Critical)"
            );
            case HP_SIGNATURE_MISMATCH -> new SystemTelemetry(
                    "HP",
                    "EliteBook 840 G10",
                    "01.08.01",
                    "2026-01-14",
                    "UEFI",
                    true,
                    true,
                    true,
                    true,
                    94,
                    "AC Mains Connected"
            );
            case UP_TO_DATE -> new SystemTelemetry(
                    "ASUSTeK COMPUTER INC.",
                    "ROG Zephyrus G14",
                    "318",
                    "2026-09-01",
                    "UEFI",
                    true,
                    true,
                    true,
                    true,
                    100,
                    "Fully Charged"
            );
        };
    }

    @Override
    public List<FirmwareUpdate> getAvailableUpdates() {
        return switch (currentPreset) {
            case DELL_UPDATE_READY -> List.of(new FirmwareUpdate(
                    "1.16.0",
                    "2026-09-18",
                    "Critical Security and Thermal Microcode Advisory",
                    "Addresses CPU thermal throttling under load, fixes Thunderbolt 4 dock reconnect anomalies, and applies security advisory patches.",
                    "c841b8a3-28b9-4822-b536-1e582883e444",
                    "a591a6d40bf420404a011733cfb7b190d62c65bf0bcda32b57b277d9ad9f146e",
                    true,
                    33554432L
            ));
            case LENOVO_LOW_BATTERY -> List.of(new FirmwareUpdate(
                    "1.38",
                    "2026-08-02",
                    "Platform Embedded Controller Update",
                    "Optimizes battery charging algorithms and resolves rare power state sleep lock.",
                    "d5843a91-44aa-4921-9981-54aa99120011",
                    "b7f83e201c8901a04e58f278912891d4e28f0901e8281084b01e82810e828101",
                    true,
                    16777216L
            ));
            case HP_SIGNATURE_MISMATCH -> List.of(new FirmwareUpdate(
                    "01.09.00",
                    "2026-09-20",
                    "Corrupted / Untrusted Test Package",
                    "Simulated payload containing invalid signature or checksum mismatch to verify pre-flight rejection.",
                    "00000000-0000-0000-0000-000000000000",
                    "invalid_hash_to_trigger_safety_engine",
                    false, // Signature failed!
                    8388608L
            ));
            case UP_TO_DATE -> Collections.emptyList();
        };
    }
}
