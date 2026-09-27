# Project Roadmap

This roadmap outlines the milestones, planned capabilities, and development trajectory for **CapsuleX**.

---

## Vision

Provide an open-source, universal, and risk-free firmware management utility that simplifies UEFI/BIOS interaction through an intuitive desktop interface, automated pre-flight safety checks, and transparent technical guidance.

---

## Milestone 1: Foundation & Simulation Prototype (`v0.1.0`) — Current Focus
- [x] Initial repository structure and open-source governance.
- [x] Technical architecture specification and zero-brick safety model.
- [ ] Safe read-only Hardware Abstraction Layer (HAL) for Linux sysfs (`/sys/class/dmi/id`, `/sys/class/power_supply`).
- [ ] Sandbox Simulator with customizable presets (Dell, Lenovo, HP) and simulated failure states.
- [ ] Embedded Knowledge Base providing concise, professional explanations for all firmware parameters.
- [ ] Modern dark-themed dashboard: System Overview, Update Center, Firmware Settings, and Diagnostics.
- [ ] Automated Pre-Flight Safety Verification UI with live checklist.

## Milestone 2: Native Telemetry & Staging (`v0.2.0`)
- [ ] Linux `fwupd` / LVFS D-Bus integration for querying official vendor updates.
- [ ] UEFI Capsule staging engine via standard Linux `/sys/firmware/efi/efivars`.
- [ ] Windows WMI & ESRT telemetry provider for cross-platform support.
- [ ] Real-time AC power and battery level watcher with desktop notifications.
- [ ] Local encrypted audit log tracking firmware revision history.

## Milestone 3: Advanced OEM Adaptation & Diagnostics (`v0.3.0`)
- [ ] Vendor-specific adapter plugins (Dell Command, Lenovo WMI, HP WMI).
- [ ] Firmware attribute configuration for enterprise systems (Virtualization, Boot Order).
- [ ] Guided UEFI Setup Reboot helper (`systemctl reboot --firmware-setup`).
- [ ] Integrated hardware diagnostic test runner.

## Milestone 4: Production Release & Packaging (`v1.0.0`)
- [ ] Native AppImage, Deb, and RPM packaging for Linux distributions.
- [ ] Windows MSIX / Portable installer.
- [ ] Comprehensive automated test suite with simulated UEFI virtual machines (QEMU/OVMF).
- [ ] Multi-language localization for technical explanations.
