package org.capsulex.core.knowledge;

import java.util.Map;

/**
 * Standardized, professional technical definitions and architectural roles
 * for system firmware parameters.
 */
public final class FirmwareKnowledge {

    public record KnowledgeTopic(
            String title,
            String technicalDefinition,
            String systemPurpose,
            String riskLevel
    ) {}

    private static final Map<String, KnowledgeTopic> TOPICS = Map.of(
            "secure_boot", new KnowledgeTopic(
                    "Secure Boot",
                    "A UEFI security standard that cryptographically verifies the digital signatures of the OS bootloader, kernel, and initial ramdisk before execution.",
                    "Protects the pre-boot integrity of the system against unauthorized bootkits, kernel-level rootkits, and corrupted startup images.",
                    "Low risk when enabled. Disabling allows unsigned third-party drivers or custom OS kernels to boot."
            ),
            "tpm", new KnowledgeTopic(
                    "TPM 2.0 (Trusted Platform Module)",
                    "A dedicated hardware cryptographic coprocessor integrated into the motherboard chipset or processor.",
                    "Provides hardware-bound key storage, platform integrity measurements (PCR banks), and secure credential protection for disk encryption.",
                    "Low risk when active. Critical for hardware-backed encryption security."
            ),
            "virtualization", new KnowledgeTopic(
                    "CPU Virtualization (VT-x / AMD-V)",
                    "Hardware extensions in the CPU silicon designed to enable direct hypervisor acceleration and memory paging virtualization.",
                    "Allows virtual machines, containers, and hypervisor-enforced security layers to run with near-native hardware performance.",
                    "Zero risk. Safe and recommended to keep enabled on modern systems."
            ),
            "bios", new KnowledgeTopic(
                    "BIOS / UEFI System Firmware",
                    "Low-level hardware initialization code executing immediately upon system power-up during the Power-On Self-Test (POST).",
                    "Initializes motherboard chipset, memory controllers, and CPU microcode before transferring control to the OS bootloader.",
                    "Medium-High. Updates must always be validated via pre-flight checks before staging."
            ),
            "boot_mode", new KnowledgeTopic(
                    "UEFI Boot Architecture",
                    "Modern 64-bit firmware interface replacing legacy 16-bit BIOS / MBR architecture.",
                    "Enables GUID Partition Tables (GPT) for drives exceeding 2 TB, fast startup handoffs, and hardware security features.",
                    "Standard modern configuration."
            )
    );

    private FirmwareKnowledge() {}

    public static KnowledgeTopic get(String key) {
        return TOPICS.getOrDefault(key, new KnowledgeTopic(
                "System Component",
                "Low-level hardware component or firmware setting.",
                "Provides hardware management and platform execution interfaces.",
                "Standard operation."
        ));
    }
}
