package com.cyberneticscore.ideapluginguide.settingsinideconfigpanel;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class SystemSettingsConfigurable implements Configurable {
    private final Project project;
    private final SettingsPanelUI configPanel;

    public SystemSettingsConfigurable(final Project project) {
        this.project = project;
        configPanel = new SettingsPanelUI();
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "Your plugin settings in IDEA Configuration menu";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        reset();

        return configPanel.getIdeaConfigPanel();
    }

    @Override
    public boolean isModified() {
        if (!configPanel.getTxtUserName().equals(getConfiguration().getUser())) {
            return true;
        }
        if (!configPanel.getTxtWebsite().equals(getConfiguration().getWebsite())) {
            return true;
        }

        return false;
    }

    @Override
    public void apply() throws ConfigurationException {
        final SystemSettingsConfiguration configuration = getConfiguration();

        configuration.setUser(configPanel.getTxtUserName());
        configuration.setWebsite(configPanel.getTxtWebsite());

        reset();
    }

    @Override
    public void reset() {
        final SystemSettingsConfiguration configuration = getConfiguration();
        configPanel.setTxtUserName(configuration.getUser());
        configPanel.setTxtWebsite(configuration.getWebsite());
    }

    @Override
    public void disposeUIResources() {

    }

    private SystemSettingsConfiguration getConfiguration() {
        return ServiceManager.getService(project, SystemSettingsConfiguration.class);
    }
}
