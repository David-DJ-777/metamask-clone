# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a **MetaMask Clone IntelliJ Plugin** that provides EVM blockchain wallet management directly within IntelliJ IDEA. Built with Kotlin 2.2.10, targeting JDK 21, and IntelliJ Platform 2025.1.

**Package:** `dev.eastgate.metamaskclone`  
**Plugin ID:** `dev.eastgate.metamaskclone`

## Common Commands

```bash
# Build the project
./gradlew build

# Run the plugin in test IntelliJ instance (DO NOT USE after code changes - resource intensive)
./gradlew runIde

# Clean build
./gradlew clean build
```

**Important:** Avoid running `./gradlew runIde` after making code changes as it's resource-intensive. Only use `./gradlew build` to verify compilation.

## Architecture Overview

### Core Components

**WalletManager** (`core/wallet/WalletManager.kt`)
- Singleton pattern per IntelliJ project
- Manages wallet CRUD operations using Kotlin StateFlow for reactive updates
- Integrates with ProjectStorage for persistence

**ProjectStorage** (`core/storage/ProjectStorage.kt`) 
- IntelliJ service for project-level data persistence
- Stores encrypted wallet data in XML format using IntelliJ's state management
- Implements `PersistentStateComponent` pattern

**UI Architecture** (`ui/MetaMaskToolWindow.kt`)
- Simple BorderLayout with three main sections:
  - Title bar (North)
  - SimpleWalletListPanel (North of content)
  - WalletInfoPanel (Center of content) 
  - ActionButtonPanel (South of content)
- Uses Kotlin Coroutines with StateFlow for reactive UI updates
- SwingUtilities.invokeLater for thread-safe UI updates

### Key Patterns

**Reactive State Management:**
- WalletManager exposes `StateFlow<List<Wallet>>` for wallet list
- UI components observe state changes via coroutines
- All UI updates happen on EDT using SwingUtilities.invokeLater

**Encryption & Security:**
- Private keys encrypted with AES-256 via EncryptionUtil
- Project-level data isolation 
- Password-protected operations

**Plugin Integration:**
- Tool window registered in plugin.xml as right-side panel
- Project service for storage
- Settings configurable via IntelliJ settings

## Critical Dependencies

```kotlin
// Blockchain libraries - Web3j for EVM interaction
implementation("org.web3j:core:4.10.3")

// Encryption - BouncyCastle
implementation("org.bouncycastle:bcprov-jdk18on:1.78") 

// Wallet generation - BitcoinJ (excludes conflicting BouncyCastle)
implementation("org.bitcoinj:bitcoinj-core:0.16.2") {
    exclude(group = "org.bouncycastle")
}
```

**Coroutines:** Do NOT add explicit coroutine dependencies - they're provided by IntelliJ Platform and adding them causes `CoroutineExceptionHandler` conflicts.

## Development Phase Status

**Phase 1 - Complete:** Basic wallet management with clean, professional UI
- Simple wallet list showing names only
- Basic wallet info panel (name, address, created date)  
- Three action buttons: Create, Import, Export Private Key
- All data persisted at project level with encryption

**Future Phases:** Mnemonic support, network management, blockchain integration, transaction history

## UI Design Principles

The current UI follows a clean, minimal design:
- Single-panel layout (no complex split panes)
- Professional appearance with proper spacing
- Essential functionality only for Phase 1
- Titled borders for section separation
- Consistent button sizing and tooltips