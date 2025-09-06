# MetaMask Clone - IntelliJ Plugin Development Plan

## Project Overview
**Project Name:** Metamask-Clone  
**Package:** `dev.eastgate.metamaskclone`  
**Language:** Kotlin 2.2.0  
**JDK:** 21  
**Platform:** IntelliJ Plugin  

## Objective
Create a MetaMask-like tool window in IntelliJ IDEA that enables developers to interact with EVM blockchains directly from their IDE environment.

## Core Requirements
- **Default Network:** Testnet (for safety and educational purposes)
- **Supported Chains:** BNB (default), ETH + custom RPC support
- **Features:** Multi-wallet, import/export, mnemonic support, ERC20 tokens, transactions
- **UI Location:** Right tool window (alongside Gradle, Database, Notifications)
- **Data Persistence:** Project-level configuration

---

## Dependencies & Libraries

### Core IntelliJ Plugin Dependencies
```kotlin
// build.gradle.kts
dependencies {
    // IntelliJ Platform
    implementation("com.jetbrains.intellij.platform:intellij-platform-gradle-plugin:2.1.0")
    
    // Blockchain & Crypto Libraries
    implementation("org.web3j:core:4.10.3")
    implementation("org.web3j:crypto:4.10.3")
    implementation("org.web3j:utils:4.10.3")
    implementation("org.web3j:abi:4.10.3")
    
    // Mnemonic & HD Wallet
    implementation("org.bitcoinj:bitcoinj-core:0.16.2")
    implementation("io.github.novacrypto:BIP39:2019.01.27")
    implementation("io.github.novacrypto:BIP44:2018.08.13")
    
    // JSON Handling
    implementation("com.fasterxml.jackson.core:jackson-core:2.16.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.16.1")
    
    // HTTP Client
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    
    // Coroutines for async operations
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.8.0")
    
    // BigInteger operations
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    
    // Testing
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.2.1")
}
```

### Plugin Configuration
```xml
<!-- plugin.xml -->
<idea-plugin>
    <id>dev.eastgate.metamaskclone</id>
    <name>MetaMask Clone</name>
    <vendor>EastGate</vendor>
    <description>EVM Blockchain interaction tool for IntelliJ IDEA</description>
    
    <depends>com.intellij.modules.platform</depends>
    
    <extensions defaultExtensionNs="com.intellij">
        <toolWindow id="MetaMask Clone" 
                   secondary="true" 
                   anchor="right" 
                   factoryClass="dev.eastgate.metamaskclone.ui.MetaMaskToolWindowFactory"/>
        
        <projectConfigurable parentId="tools" 
                           instance="dev.eastgate.metamaskclone.settings.MetaMaskConfigurable"
                           id="dev.eastgate.metamaskclone.settings"
                           displayName="MetaMask Clone"/>
    </extensions>
</idea-plugin>
```

---

## Development Phases

## Phase 1: Basic EVM Blockchain Function ✅ **COMPLETED**

### 🎯 Goals
- Set up basic plugin structure with tool window
- Implement wallet creation and management
- Establish project-level data persistence
- Create foundational UI components

### 📋 Features
1. **Wallet Management**
   - Create new wallet address with simple button
   - Name/rename wallet addresses
   - Import wallet via private key
   - Export wallet private key (with security warnings)

2. **Data Persistence**
   - Project-level configuration storage
   - Encrypted private key storage
   - Wallet metadata (name, creation date, etc.)

3. **UI Components**
   - Main tool window panel
   - Wallet list with names
   - Create/Import/Export buttons
   - Basic wallet details view

### 🏗️ Implementation Structure
```
src/main/kotlin/dev/eastgate/metamaskclone/
├── ui/
│   ├── MetaMaskToolWindow.kt
│   ├── MetaMaskToolWindowFactory.kt
│   ├── panels/
│   │   ├── WalletListPanel.kt
│   │   ├── WalletDetailsPanel.kt
│   │   └── CreateWalletDialog.kt
├── core/
│   ├── wallet/
│   │   ├── WalletManager.kt
│   │   ├── Wallet.kt
│   │   └── WalletGenerator.kt
│   └── storage/
│       ├── ProjectStorage.kt
│       └── EncryptionUtil.kt
├── settings/
│   ├── MetaMaskConfigurable.kt
│   └── MetaMaskSettings.kt
└── constants/
    └── Constants.kt
```

### ✅ Deliverables
- [x] Tool window appears in right panel
- [x] Create wallet with auto-generated name
- [x] Rename wallet functionality
- [x] Import wallet via private key dialog
- [x] Export wallet private key with confirmation
- [x] Data persists between IDE sessions

---

## Phase 2: Advanced EVM Blockchain Function

### 🎯 Goals
- Implement mnemonic seed phrase support
- Add network management
- Enhance wallet sharing capabilities
- Improve security measures

### 📋 Features
1. **Mnemonic Support**
   - Generate 12/24 word seed phrases
   - Import wallet from seed phrase
   - HD wallet derivation (BIP44)
   - Multiple accounts from single seed

2. **Network Management**
   - Pre-configured networks (ETH Mainnet/Testnet, BNB Mainnet/Testnet)
   - Custom RPC endpoint addition
   - Network switching functionality
   - Default to testnet networks

3. **Enhanced Wallet Features**
   - QR code generation for wallet addresses
   - Copy address to clipboard
   - Wallet address validation

### 🏗️ Implementation Structure
```
src/main/kotlin/dev/eastgate/metamaskclone/
├── core/
│   ├── mnemonic/
│   │   ├── MnemonicGenerator.kt
│   │   ├── HDWalletDerivation.kt
│   │   └── SeedPhraseValidator.kt
│   ├── network/
│   │   ├── NetworkManager.kt
│   │   ├── Network.kt
│   │   ├── PredefinedNetworks.kt
│   │   └── CustomNetworkValidator.kt
│   └── crypto/
│       ├── AddressGenerator.kt
│       └── KeyDerivation.kt
├── ui/
│   ├── dialogs/
│   │   ├── SeedPhraseDialog.kt
│   │   ├── ImportSeedDialog.kt
│   │   ├── NetworkSettingsDialog.kt
│   │   └── QRCodeDialog.kt
│   └── panels/
│       ├── NetworkSelectorPanel.kt
│       └── WalletActionsPanel.kt
```

### ✅ Deliverables
- [ ] Seed phrase generation (12/24 words)
- [ ] Import wallet from seed phrase
- [ ] Multiple accounts from single seed
- [ ] Network dropdown with ETH/BNB (Mainnet/Testnet)
- [ ] Add custom network functionality
- [ ] QR code for wallet addresses
- [ ] Enhanced wallet sharing options

---

## Phase 3: Interact with EVM Blockchain

### 🎯 Goals
- Connect to blockchain networks
- Display real-time wallet balances
- Implement token management
- Enable cryptocurrency transactions

### 📋 Features
1. **Balance Display**
   - Native token balance (ETH, BNB)
   - ERC20 token balances
   - USD value conversion
   - Real-time updates

2. **Transaction Capabilities**
   - Send native tokens
   - Send ERC20 tokens
   - Gas fee estimation
   - Transaction confirmation dialogs

3. **Token Management**
   - Add ERC20 tokens by contract address
   - Popular token presets
   - Token metadata fetching
   - Remove tokens from list

### 🏗️ Implementation Structure
```
src/main/kotlin/dev/eastgate/metamaskclone/
├── blockchain/
│   ├── Web3Service.kt
│   ├── BalanceService.kt
│   ├── TransactionService.kt
│   ├── TokenService.kt
│   └── GasEstimationService.kt
├── models/
│   ├── Balance.kt
│   ├── Token.kt
│   ├── Transaction.kt
│   └── GasPrice.kt
├── ui/
│   ├── panels/
│   │   ├── BalancePanel.kt
│   │   ├── TokenListPanel.kt
│   │   └── SendTransactionPanel.kt
│   └── dialogs/
│       ├── SendTokenDialog.kt
│       ├── AddTokenDialog.kt
│       └── TransactionConfirmDialog.kt
└── utils/
    ├── CurrencyConverter.kt
    ├── FormatUtils.kt
    └── ValidationUtils.kt
```

### ✅ Deliverables
- [ ] Real-time balance display for native tokens
- [ ] ERC20 token balance tracking
- [ ] Send native cryptocurrency functionality
- [ ] Send ERC20 tokens functionality
- [ ] Add custom ERC20 tokens
- [ ] Remove tokens from wallet
- [ ] Gas fee estimation and adjustment
- [ ] Transaction success/failure notifications

---

## Phase 4: Transaction History

### 🎯 Goals
- Implement comprehensive transaction tracking
- Provide detailed transaction information
- Create intuitive transaction browsing UI
- Add transaction status monitoring

### 📋 Features
1. **Transaction History**
   - Chronological transaction list
   - Transaction type indicators (sent/received/contract)
   - Status tracking (pending/confirmed/failed)
   - Pagination for large transaction lists

2. **Transaction Details**
   - Complete transaction information
   - Block explorer links
   - Gas usage and fees
   - Token transfer details

3. **History Management**
   - Filter by transaction type
   - Search by address/hash
   - Export transaction history
   - Refresh transaction status

### 🏗️ Implementation Structure
```
src/main/kotlin/dev/eastgate/metamaskclone/
├── history/
│   ├── TransactionHistoryService.kt
│   ├── TransactionTracker.kt
│   └── TransactionFilter.kt
├── models/
│   ├── TransactionHistory.kt
│   ├── TransactionDetail.kt
│   └── TransactionStatus.kt
├── ui/
│   ├── panels/
│   │   ├── TransactionHistoryPanel.kt
│   │   ├── TransactionDetailPanel.kt
│   │   └── TransactionFilterPanel.kt
│   └── renderers/
│       ├── TransactionListRenderer.kt
│       └── TransactionStatusRenderer.kt
└── export/
    ├── TransactionExporter.kt
    └── ExportFormat.kt
```

### ✅ Deliverables
- [ ] Transaction history list with status indicators
- [ ] Detailed transaction view with all metadata
- [ ] Transaction filtering and search
- [ ] Real-time transaction status updates
- [ ] Block explorer integration links
- [ ] Transaction history export functionality
- [ ] Pending transaction monitoring
- [ ] Transaction retry mechanism for failed transactions

---

## Security Considerations

### 🔒 Data Protection
- **Private Key Encryption:** Use AES-256 encryption for stored private keys
- **Seed Phrase Security:** Never store seed phrases in plain text
- **Memory Management:** Clear sensitive data from memory after use
- **Access Control:** Project-level isolation of wallet data

### 🛡️ Network Security
- **RPC Validation:** Validate custom RPC endpoints
- **SSL/TLS:** Enforce secure connections to blockchain networks
- **Rate Limiting:** Implement API call rate limiting
- **Input Validation:** Sanitize all user inputs

### ⚠️ User Safety
- **Testnet Default:** Always default to testnet for safety
- **Transaction Warnings:** Clear warnings for mainnet transactions
- **Backup Reminders:** Prompt users to backup seed phrases
- **Phishing Protection:** Validate transaction recipients

---

## Testing Strategy

### 🧪 Unit Testing
- Wallet generation and import functions
- Mnemonic validation and derivation
- Encryption/decryption utilities
- Network connection handling

### 🔧 Integration Testing
- Blockchain interaction services
- UI component interactions
- Data persistence functionality
- Cross-network transaction testing

### 👤 User Testing
- UI/UX workflow validation
- Error handling scenarios
- Performance under load
- Security vulnerability assessment

---

## Deployment & Distribution

### 📦 Plugin Packaging
- IntelliJ Plugin Repository submission
- Version management strategy
- Update mechanism implementation
- Compatibility matrix maintenance

### 📚 Documentation
- User guide creation
- Developer API documentation
- Security best practices guide
- Troubleshooting documentation

---

## Future Enhancements

### 🚀 Advanced Features
- **DeFi Integration:** Uniswap, PancakeSwap interaction
- **NFT Support:** NFT viewing and transfer
- **Multi-signature Wallets:** Enterprise wallet support
- **Hardware Wallet Integration:** Ledger/Trezor support
- **DApp Browser:** Embedded DApp interaction
- **Portfolio Tracking:** Investment performance analytics

### 🔧 Technical Improvements
- **Performance Optimization:** Faster balance updates
- **Offline Mode:** Limited functionality without internet
- **Advanced Gas Management:** EIP-1559 support
- **Layer 2 Support:** Polygon, Arbitrum integration
- **Cross-chain Bridges:** Token bridging functionality

---

## Timeline Estimate

| Phase | Duration | Key Milestones |
|-------|----------|----------------|
| Phase 1 | 3-4 weeks | Basic wallet management, UI foundation |
| Phase 2 | 2-3 weeks | Mnemonic support, network management |
| Phase 3 | 4-5 weeks | Blockchain integration, transactions |
| Phase 4 | 2-3 weeks | Transaction history, final polish |
| **Total** | **11-15 weeks** | Full MetaMask clone functionality |

---

## Success Metrics

### 📊 Functionality Metrics
- [ ] All wallet operations work reliably
- [ ] Transaction success rate > 99%
- [ ] Balance updates within 30 seconds
- [ ] Support for 5+ EVM networks

### 👥 User Experience Metrics
- [ ] Plugin loads in < 5 seconds
- [ ] Intuitive UI workflow
- [ ] Zero security incidents
- [ ] Positive user feedback

---

*This development plan provides a comprehensive roadmap for creating a production-ready MetaMask clone as an IntelliJ plugin. Each phase builds upon the previous one, ensuring a solid foundation while gradually adding advanced functionality.*