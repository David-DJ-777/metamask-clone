# MetaMask Clone - IntelliJ IDEA Plugin

A demonstration project showcasing how to build an EVM blockchain wallet management plugin for IntelliJ IDEA, inspired by MetaMask functionality.

## 🎯 Overview

This is a **MetaMask Clone** built as an IntelliJ IDEA plugin using **Kotlin**. It provides blockchain wallet management capabilities directly within your IDE, demonstrating how to integrate Web3 functionality into development tools.

## 🛠️ Tech Stack

- **Language:** Kotlin 2.2.10
- **Platform:** IntelliJ IDEA Plugin SDK
- **Target JDK:** 21
- **Target IntelliJ:** 2025.1
- **Build Tool:** Gradle
- **Blockchain:** Web3j for EVM interaction

## 📺 Video Tutorial

🎥 **YouTube Tutorial Available!** 

Learn how to build this plugin step-by-step with our comprehensive video tutorial series.

[Watch the Tutorial Series](#) *(Link coming soon)*

## ✨ Features

### Phase 1 (Current)
- ✅ Create new wallets
- ✅ Import existing wallets via private key
- ✅ Export private keys (password-protected)
- ✅ Secure wallet storage with AES-256 encryption
- ✅ Project-level data persistence
- ✅ Clean, professional UI integrated as IntelliJ tool window

### Upcoming Features
- 🔜 Mnemonic phrase support (BIP39)
- 🔜 Multiple network support (Mainnet, Testnet, etc.)
- 🔜 Transaction history
- 🔜 Send/receive transactions
- 🔜 Token balance display
- 🔜 Smart contract interaction

## 🚀 Getting Started

### Prerequisites
- IntelliJ IDEA 2025.1 or later
- JDK 21
- Gradle 8.x

### Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/metamask-clone.git
cd metamask-clone
```

2. Build the plugin:
```bash
./gradlew build
```

3. Run the plugin in a test IntelliJ instance:
```bash
./gradlew runIde
```

### Usage

1. After installation, find the **MetaMask Clone** tool window on the right side of IntelliJ IDEA
2. Click **Create Wallet** to generate a new wallet
3. Use **Import Wallet** to add an existing wallet using a private key
4. Select any wallet to view its details
5. Use **Export Private Key** to backup your wallet (password required)

## 🏗️ Project Structure

```
metamask-clone/
├── src/main/kotlin/dev/eastgate/metamaskclone/
│   ├── core/
│   │   ├── wallet/        # Wallet management logic
│   │   ├── storage/       # Data persistence
│   │   └── crypto/        # Encryption utilities
│   ├── ui/
│   │   └── MetaMaskToolWindow.kt  # Main UI component
│   └── actions/           # Plugin actions
├── src/main/resources/
│   └── META-INF/
│       └── plugin.xml     # Plugin configuration
└── build.gradle.kts       # Build configuration
```

## 🔧 Development

### Building
```bash
# Clean build
./gradlew clean build

# Build without tests
./gradlew build -x test
```

### Key Dependencies
- **Web3j** - Ethereum blockchain interaction
- **BouncyCastle** - Cryptographic operations
- **BitcoinJ** - HD wallet generation
- **Kotlin Coroutines** - Async operations (provided by IntelliJ Platform)

## 📝 License

This project is for educational purposes and demonstration only.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📧 Contact

For questions about the tutorial or project, please open an issue on GitHub.

## ⚠️ Disclaimer

This is a demonstration project for educational purposes. It should not be used for managing real cryptocurrency assets without proper security auditing and testing.

---

**Made with ❤️ for the Kotlin & IntelliJ Plugin Development Community**