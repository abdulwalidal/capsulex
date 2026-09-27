# CapsuleX 🚀
> **Next-Gen UEFI Firmware Console & Pre-Flight Safety Engine**

CapsuleX is an open, modern, vendor-agnostic desktop utility designed to make BIOS/UEFI firmware inspection, management, and updates safe, understandable, and accessible to everyone.

---

## 🌟 Key Features

* **Safe Live Inspection (100% Read-Only):** Safely queries system firmware telemetry (Motherboard model, BIOS version, Secure Boot, TPM 2.0, CPU Virtualization, and battery state) without modifying any hardware.
* **Automated Pre-Flight Safety Engine ("Zero-Brick" Guarantee):** Rigorously validates AC power connection, battery thresholds, device match, and cryptographic package integrity before allowing any firmware update stage.
* **Firmware Knowledge Base:** Built-in plain-language technical explanations for every low-level parameter and security feature—strictly professional, clear, and without confusing jargon.
* **Safe Simulation Sandbox:** Practice and test firmware update flows, low-battery aborts, and checksum failures in a zero-risk virtual simulator.
* **Universal Hardware Architecture:** Hardware Abstraction Layer (HAL) built to support modern UEFI capsule interfaces across vendors (Dell, Lenovo, HP, ASUS, etc.).

---

## 📁 Project Architecture

```text
capsulex/
├── app.py                 # Application launcher & main window
├── core/
│   ├── models.py          # Data classes (SystemInfo, FirmwareUpdate, SafetyCheck)
│   ├── engine.py          # Pre-flight safety evaluation engine
│   └── knowledge.py       # Professional technical definitions & system roles
├── hal/                   # Hardware Abstraction Layer
│   ├── base.py            # Base abstract telemetry provider
│   ├── linux_reader.py    # Safe Linux read-only hardware reader
│   └── simulator.py       # Safe sandbox preset simulator
└── ui/                    # Desktop GUI components
    ├── theme.py           # Modern dark-mode styling & color palette
    ├── dashboard.py       # System overview & security status cards
    ├── updates.py         # Update center & pre-flight inspection wizard
    ├── settings.py        # Firmware toggles & guided reboot setup
    └── diagnostics.py     # System integrity check & update audit history
```

---

## 🚀 Quick Start

### Prerequisites
* Python 3.9+
* Linux / Windows

### Installation
```bash
# Clone the repository
git clone https://github.com/your-username/capsulex.git
cd capsulex

# Install dependencies
pip install -r requirements.txt

# Run CapsuleX
python3 -m capsulex.app
```

---

## 🛡️ Safety Principle
CapsuleX strictly adheres to the principle of **delegated firmware execution**:
The application **never** directly writes to physical BIOS/SPI flash chips. All firmware deployment is staged through official UEFI Capsule and OEM firmware mechanisms.

---

## 📄 License
MIT License
