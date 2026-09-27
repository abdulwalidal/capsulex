# CapsuleX

[![CI](https://github.com/abdulwalidal/capsulex/actions/workflows/ci.yml/badge.svg)](https://github.com/abdulwalidal/capsulex/actions/workflows/ci.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Java: 21 LTS](https://img.shields.io/badge/Java-21%20LTS-orange.svg)](https://openjdk.org/)
[![UI: FlatLaf](https://img.shields.io/badge/UI-FlatLaf%20Dark-5b5ea6.svg)](https://www.formdev.com/flatlaf/)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](CONTRIBUTING.md)
[![Security: Zero--Brick](https://img.shields.io/badge/Security-Zero--Brick-blueviolet.svg)](SECURITY.md)

A modern, universal, and risk-free desktop firmware management console designed to simplify UEFI/BIOS inspection, updates, and diagnostics with automated pre-flight safety verification.

---

## Why CapsuleX?

Managing motherboard firmware (BIOS/UEFI) has traditionally been an intimidating and risky chore for computer owners. Users are forced to navigate fragmented manufacturer support pages, parse cryptic technical jargon, format USB flash drives, and risk permanently bricking their hardware if an update fails.

**CapsuleX transforms this into a safe, transparent, and user-friendly experience:**
- **Zero-Brick Architecture**: CapsuleX strictly enforces delegated firmware execution. It **never writes directly to SPI flash chips**. Updates are safely staged into verified UEFI Capsules and handed to OEM firmware mechanisms.
- **Automated Pre-Flight Inspection**: Mandatory multi-factor safety checklist prevents updates unless AC mains power is connected, battery is $\ge 50\%$, and digital signatures are verified.
- **Vendor-Agnostic Simplicity**: One unified interface for Dell, Lenovo, HP, ASUS, and custom systems.
- **Embedded Knowledge Engine**: Every setting and security parameter includes clean, professional system descriptions—no gaming analogies, no cryptic acronyms, just clear technical facts.
- **Safe Simulation Sandbox**: Practice and test update workflows and failure modes in a risk-free virtual simulator.

---

## Core Highlights

- **Live System Telemetry (100% Read-Only)**: Instantly inspects Motherboard identification, current BIOS revision, release date, Secure Boot status, TPM 2.0 readiness, and CPU Virtualization extensions.
- **Pre-Flight Safety Verification**: Live multi-point checklist verifying AC power, reserve battery capacity, payload integrity, and device GUID matching.
- **Firmware Settings Guidance**: View and toggle supported firmware attributes with guided pre-boot authorization workflows.
- **Diagnostics & Audit History**: Local, persistent audit log documenting every firmware update event, status outcome, and version jump.
- **Privacy by Design**: Purely local execution. Zero telemetry, zero background trackers, and zero third-party data collection.

---

## How It Protects Your System

```mermaid
graph TD
    classDef safe fill:#1e293b,stroke:#10b981,stroke-width:2px,color:#f8fafc;
    classDef gate fill:#0f172a,stroke:#3b82f6,stroke-width:2px,color:#f8fafc;
    classDef hw fill:#090d16,stroke:#8b5cf6,stroke-width:2px,color:#f8fafc;
    classDef alert fill:#450a0a,stroke:#ef4444,stroke-width:2px,color:#fca5a5;

    subgraph UserSpace ["Desktop Application Layer"]
        UI["CapsuleX Modern GUI<br/>(Dashboard • Updates • Settings • Logs)"]
    end

    subgraph SafetyGate ["Pre-Flight Safety Verification Engine"]
        Check1{"1. Hardware GUID Match?"}
        Check2{"2. SHA-256 & Signature Valid?"}
        Check3{"3. AC Power & Battery >= 50%?"}
        Blocked["Update Blocked Safe State<br/>(Prevents Brick / Failure)"]
    end

    subgraph StagingLayer ["Hardware Abstraction & OS Delegation"]
        HAL["Platform Adapter Layer<br/>(Linux fwupd / Windows ESRT)"]
        Capsule["UEFI Capsule Payload Staged"]
    end

    subgraph Silicon ["Protected Motherboard Environment"]
        UEFI["Native OEM UEFI Engine<br/>(Flashes Safely During Reboot)"]
        BIOS["Updated System Firmware"]
    end

    UI --> Check1
    Check1 -- "No" --> Blocked
    Check1 -- "Yes" --> Check2
    Check2 -- "No" --> Blocked
    Check2 -- "Yes" --> Check3
    Check3 -- "No" --> Blocked
    Check3 -- "Yes (Passed)" --> HAL
    HAL --> Capsule
    Capsule --> UEFI
    UEFI --> BIOS

    class UI safe;
    class Check1,Check2,Check3 gate;
    class Blocked alert;
    class HAL,Capsule,UEFI,BIOS hw;
```

1. **Inspect**: Safely query current BIOS version, Secure Boot, and platform telemetry.
2. **Verify**: Pre-flight safety engine confirms AC power, battery reserve, and cryptographic signatures.
3. **Stage**: Firmware is handed to the official UEFI capsule mechanism for safe flashing upon reboot.

---

## Firmware Data Architecture (ERD)

The following Entity-Relationship Diagram outlines the core data domain and inspection relationships managed by CapsuleX:

```mermaid
erDiagram
    SYSTEM_DEVICE ||--|| FIRMWARE_STATE : operates
    SYSTEM_DEVICE ||--o{ UPDATE_PACKAGE : queries
    SYSTEM_DEVICE ||--o{ SAFETY_INSPECTION : validates
    UPDATE_PACKAGE ||--o{ SAFETY_INSPECTION : evaluated_by
    SAFETY_INSPECTION ||--o{ AUDIT_LOG : generates

    SYSTEM_DEVICE {
        string vendor
        string product_name
        string machine_guid
        string boot_mode
        boolean ac_connected
        int battery_capacity
    }

    FIRMWARE_STATE {
        string bios_version
        string release_date
        string secure_boot_status
        string tpm_status
        boolean virtualization_enabled
    }

    UPDATE_PACKAGE {
        string package_id
        string target_guid
        string version
        string sha256_digest
        string signature_status
        int required_battery_min
    }

    SAFETY_INSPECTION {
        uuid inspection_id
        boolean guid_match_passed
        boolean signature_verified
        boolean ac_mains_verified
        boolean battery_threshold_met
        string inspection_verdict
    }

    AUDIT_LOG {
        uuid log_id
        string timestamp
        string event_type
        string previous_version
        string staged_version
        string execution_status
    }
```

---

## Quick Start Guide

### Prerequisites
- **Java**: OpenJDK 21 LTS or higher
- **Linux** (Debian, Ubuntu, Fedora, Arch) or **Windows 10/11**

### Setup and Run

```bash
# 1. Clone the repository
git clone git@github.com:abdulwalidal/capsulex.git
cd capsulex

# 2. Compile and package executable JAR
./mvnw clean package

# 3. Launch CapsuleX desktop console
java -jar target/capsulex-0.1.0.jar
```

---

## Open Source Governance and Documentation

CapsuleX is built according to open-source best practices:

| Document | Purpose |
| :--- | :--- |
| [**Architecture & Security**](ARCHITECTURE.md) | In-depth system design, component boundaries, and threat model. |
| [**Roadmap**](ROADMAP.md) | Project milestones from `v0.1.0` to production `v1.0.0`. |
| [**Contributing Guidelines**](CONTRIBUTING.md) | Trunk-based pull request workflow and Conventional Commits. |
| [**Security Policy**](SECURITY.md) | Zero-brick commitment and responsible vulnerability reporting. |
| [**Code of Conduct**](CODE_OF_CONDUCT.md) | Contributor Covenant v2.1 community standards. |
| [**License**](LICENSE) | Permissive open-source MIT License. |

---

## License

This project is licensed under the terms of the [MIT License](LICENSE).
