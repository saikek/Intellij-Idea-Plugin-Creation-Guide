package com.cyberneticscore.ideapluginguide.consoleoutput.model;

import com.cyberneticscore.ideapluginguide.consoleoutput.view.ConsolePanel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.openapi.wm.ToolWindowType;
import com.intellij.ui.content.Content;
import org.jetbrains.annotations.NotNull;

public class ConsolePanelFactory implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        final Content toolContent = toolWindow.getContentManager().getFactory().createContent(
                new ConsolePanel(project), "", false);
        toolWindow.getContentManager().addContent(toolContent);

        toolWindow.setType(ToolWindowType.DOCKED, null);
    }
}
