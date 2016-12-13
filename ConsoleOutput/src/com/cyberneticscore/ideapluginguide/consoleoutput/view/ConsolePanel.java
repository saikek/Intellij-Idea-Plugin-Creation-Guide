package com.cyberneticscore.ideapluginguide.consoleoutput.view;

import com.cyberneticscore.ideapluginguide.consoleoutput.model.Console;
import com.intellij.openapi.project.Project;

import javax.swing.*;
import java.awt.*;


public class ConsolePanel extends JPanel {
    private final Project project;

    public ConsolePanel(final Project project) {
        super(new BorderLayout());
        this.project = project;

        add(createToolPanel(), BorderLayout.CENTER);
    }

    private JComponent createToolPanel() {
        Console tasConsole = new Console(project);

        return tasConsole.getComponent();
    }
}