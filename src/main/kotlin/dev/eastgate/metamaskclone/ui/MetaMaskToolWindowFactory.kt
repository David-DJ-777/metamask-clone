package dev.eastgate.metamaskclone.ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

class MetaMaskToolWindowFactory : ToolWindowFactory {
    
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val contentFactory = ContentFactory.getInstance()
        val toolWindowPanel = MetaMaskToolWindow(project)
        val content = contentFactory.createContent(toolWindowPanel.getContent(), "", false)
        toolWindow.contentManager.addContent(content)
    }
}