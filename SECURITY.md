# Security Policy

## Supported Versions

Only the latest release of CapsuleX receives security updates.

| Version | Supported          |
| ------- | ------------------ |
| v0.1.x  | :white_check_mark: |
| < v0.1  | :x:                |

---

## The Zero-Brick Security Commitment

CapsuleX is designed from the ground up around hardware safety. Under no circumstances does the application directly flash, erase, or write to raw motherboard SPI flash chips. All firmware staging delegates strictly to OEM-signed capsules and the platform's native UEFI update environment.

Any proposed code change that attempts to bypass:
1. Cryptographic signature verification,
2. Hardware ID / vendor validation, or
3. Pre-flight AC power and battery safety thresholds,

will be rejected immediately.

---

## Reporting a Vulnerability

If you discover a security vulnerability or a potential risk condition that could lead to hardware failure or bypass of safety checks, **please do not disclose it in a public GitHub issue.**

Instead, please report it via private disclosure:
* Open a [Private Security Advisory](https://github.com/abdulwalidal/capsulex/security/advisories/new) on GitHub.
* Or email the maintainers directly.

### What to include in your report:
* A detailed description of the vulnerability.
* Steps to reproduce the issue or a proof-of-concept script.
* System specifications (Operating System, kernel version, motherboard model, and UEFI revision).
* Any potential mitigations or patches you have identified.

### Response Timeline
* **Initial Acknowledgement:** Within 48 hours.
* **Triage & Assessment:** Within 5 business days.
* **Resolution & Patch:** Coordinated release and public advisory following verification.
