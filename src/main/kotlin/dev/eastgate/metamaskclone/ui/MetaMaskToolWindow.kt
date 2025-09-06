package dev.eastgate.metamaskclone.ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.ui.components.JBLabel
import dev.eastgate.metamaskclone.core.wallet.WalletManager
import dev.eastgate.metamaskclone.models.Wallet
import dev.eastgate.metamaskclone.ui.panels.SimpleWalletListPanel
import dev.eastgate.metamaskclone.ui.panels.WalletInfoPanel
import dev.eastgate.metamaskclone.ui.panels.ActionButtonPanel
import dev.eastgate.metamaskclone.ui.dialogs.CreateWalletDialog
import dev.eastgate.metamaskclone.ui.dialogs.ImportWalletDialog
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import java.awt.BorderLayout
import java.awt.Font
import javax.swing.*

class MetaMaskToolWindow(private val project: Project) {
    
    private val walletManager = WalletManager.getInstance(project)
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    
    private val mainPanel = SimpleToolWindowPanel(false, true)
    private val walletListPanel = SimpleWalletListPanel()
    private val walletInfoPanel = WalletInfoPanel()
    private val actionButtonPanel = ActionButtonPanel()
    
    init {
        setupUI()
        observeWalletChanges()
        setupEventListeners()
    }
    
    private fun setupUI() {
        mainPanel.layout = BorderLayout()
        mainPanel.border = BorderFactory.createEmptyBorder(10, 15, 15, 15)
        
        // Title
        val titleLabel = JBLabel("MetaMask Clone")
        titleLabel.font = titleLabel.font.deriveFont(Font.BOLD, 16f)
        val titlePanel = JPanel(BorderLayout())
        titlePanel.border = BorderFactory.createEmptyBorder(0, 0, 15, 0)
        titlePanel.add(titleLabel, BorderLayout.CENTER)
        
        // Content panel with better layout management
        val contentPanel = JPanel(BorderLayout())
        
        // Top section with wallet list
        contentPanel.add(walletListPanel, BorderLayout.NORTH)
        
        // Middle section with wallet info (limited height)
        val middlePanel = JPanel(BorderLayout())
        middlePanel.add(walletInfoPanel, BorderLayout.NORTH)
        contentPanel.add(middlePanel, BorderLayout.CENTER)
        
        // Bottom section with action buttons
        contentPanel.add(actionButtonPanel, BorderLayout.SOUTH)
        
        mainPanel.add(titlePanel, BorderLayout.NORTH)
        mainPanel.add(contentPanel, BorderLayout.CENTER)
    }
    
    private fun observeWalletChanges() {
        scope.launch {
            walletManager.wallets.collect { wallets ->
                SwingUtilities.invokeLater {
                    walletListPanel.updateWallets(wallets)
                }
            }
        }
        
        scope.launch {
            walletManager.selectedWallet.collect { wallet ->
                SwingUtilities.invokeLater {
                    walletInfoPanel.updateWallet(wallet)
                    actionButtonPanel.setSelectedWallet(wallet)
                }
            }
        }
    }
    
    private fun setupEventListeners() {
        // Wallet selection
        walletListPanel.onWalletSelected = { wallet ->
            walletManager.selectWallet(wallet.address)
        }
        
        // Action buttons
        actionButtonPanel.onCreateWallet = {
            showCreateWalletDialog()
        }
        
        actionButtonPanel.onImportWallet = {
            showImportWalletDialog()
        }
        
        actionButtonPanel.onExportPrivateKey = { wallet ->
            walletInfoPanel.exportPrivateKey(wallet, walletManager)
        }
        
        actionButtonPanel.onDeleteWallet = { wallet ->
            deleteWallet(wallet)
        }
    }
    
    private fun showCreateWalletDialog() {
        val dialog = CreateWalletDialog(project, walletManager)
        dialog.show()
    }
    
    private fun showImportWalletDialog() {
        val dialog = ImportWalletDialog(project, walletManager)
        dialog.show()
    }
    
    private fun deleteWallet(wallet: Wallet) {
        val result = Messages.showYesNoDialog(
            "Are you sure you want to delete wallet '${wallet.name}'?\n\nThis action cannot be undone!",
            "Delete Wallet",
            Messages.getWarningIcon()
        )
        
        if (result == Messages.YES) {
            walletManager.deleteWallet(wallet.address)
        }
    }
    
    fun getContent(): JComponent {
        return mainPanel
    }
    
    fun dispose() {
        scope.cancel()
    }
}