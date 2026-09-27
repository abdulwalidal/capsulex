# Architecture and Security Specification

This document details the system architecture, component boundaries, and security model of **CapsuleX**.

---

## 1. System Architecture

```text
               +----------------------------------------------------+
               |             Desktop Presentation Layer             |
               |       (Dashboard | Updates | Settings | Logs)      |
               +-------------------------+--------------------------+
                                         |
                            Inter-Process Communication
                                         |
               +-------------------------v--------------------------+
               |                  CapsuleX Core                     |
               |                                                    |
               |  +--------------------+    +--------------------+  |
               |  |  Telemetry Engine  |    | Pre-Flight Engine  |  |
               |  +--------------------+    +--------------------+  |
               |  |  Knowledge Base    |    | Audit & Log Store  |  |
               |  +--------------------+    +--------------------+  |
               +-------------------------+--------------------------+
                                         |
                        Hardware Abstraction Layer (HAL)
                                         |
         +-------------------------------+-------------------------------+
         |                               |                               |
+--------v---------+            +--------v---------+            +--------v---------+
| Live HAL (Linux) |            | Live HAL (Win32) |            | Sandbox Simulator|
| sysfs / efivars  |            | WMI / Firmware   |            | (Safe Test Lab)  |
+--------+---------+            +--------+---------+            +------------------+
         |                               |
         +---------------+---------------+
                         |
               +---------v----------+
               |  OS Kernel & APIs  |
               +---------+----------+
                         |
               +---------v----------+
               |    UEFI Capsule    |
               | System Firmware    |
               +--------------------+
```

---

## 2. Component Responsibilities

### Presentation Layer (`src/renderer/` or `ui/`)
- Renders the responsive desktop interface (Dashboard, Update Wizard, Settings, Diagnostics).
- Displays clear, professional technical notes explaining each firmware component and its system purpose.
- Emits user action intents across the isolated IPC boundary without possessing direct host-level disk write privileges.

### CapsuleX Core (`src/core/`)
- **Telemetry Coordinator:** Aggregates motherboard identification, BIOS revisions, and security subsystem states (Secure Boot, TPM 2.0, CPU Virtualization).
- **Pre-Flight Safety Engine:** Performs rigorous, automated multi-factor validation before any firmware staging operation is permitted.
- **Knowledge Engine:** Houses standardized, professional explanations of firmware components and security parameters.
- **Audit & History Store:** Maintains local append-only logs of past firmware modifications, version transitions, and diagnostic runs.

### Hardware Abstraction Layer (HAL) (`src/hal/`)
- **Base Interface:** Defines universal contracts for reading firmware metadata, power status, and staging updates.
- **Live Linux Provider:** Reads `/sys/class/dmi/id/`, `/sys/class/power_supply/`, and `/sys/firmware/efi/efivars/` using safe, read-only system semantics.
- **Sandbox Simulator:** Emulates full hardware setups (Dell, Lenovo, HP, ASUS) and failure scenarios (low battery abort, signature mismatch, update progression) for zero-risk testing and demonstration.

---

## 3. The Zero-Brick Security Model

> [!IMPORTANT]
> **Strict Delegation Principle**: CapsuleX strictly enforces delegated firmware execution. The application process **never directly writes to physical BIOS or SPI flash chips**.

All firmware updates are staged into the operating system's native UEFI Capsule update pipeline (`fwupd` on Linux, UEFI ESRT / Capsule API on Windows). The actual flash sequence executes exclusively within the motherboard manufacturer's verified pre-boot UEFI environment upon restart.

---

## 4. Pre-Flight Safety Verification Matrix

Before any update action transitions from *Discovered* to *Staged*, the Pre-Flight Engine must pass 100% of the following checklist:

| Verification Check | Verification Standard | Failure Consequence |
| :--- | :--- | :--- |
| **Hardware ID Match** | DMI System Vendor & Product Name must match payload manifest GUID. | Update blocked; prevents flashing incompatible OEM firmware. |
| **Cryptographic Signature** | Payload SHA-256 digest and OEM PKCS#7 / Authenticode signature must validate. | Update blocked; prevents corrupted or tampered binaries. |
| **AC Mains Power** | AC power adapter status must report `online == 1`. | Update blocked; prevents unexpected power loss during preparation. |
| **Battery Threshold** | System battery capacity must be $\ge 50\%$ (recommended $\ge 80\%$). | Update blocked; ensures emergency reserve if AC power disconnects. |
| **Lockout & Concurrency** | No active capsule pending reboot; no conflicting firmware process running. | Update blocked; prevents multi-update collision. |
| **User Confirmation** | Explicit user acknowledgement of automatic system restart requirement. | Update paused pending confirmation. |

---

## 5. Threat Model and Mitigations

| Threat | Risk Level | Architectural Mitigation |
| :--- | :--- | :--- |
| **Corrupted Firmware Flashing** | Critical | Dual-hash verification (SHA-256 + vendor signature). Raw flashing disabled. |
| **Mid-Update Power Loss** | High | Mandatory pre-flight AC mains and minimum battery threshold validation. |
| **Malicious Firmware Injection** | Critical | Only official OEM update endpoints and signed UEFI capsules are processed. |
| **Silent Security Downgrade** | High | Secure Boot changes require physical presence confirmation or admin authorization on reboot. |
| **Information Disclosure** | Low | Zero external telemetry. Telemetry collection is purely local and on-demand. |
