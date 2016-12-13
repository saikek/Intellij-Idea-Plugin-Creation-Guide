package com.cyberneticscore.ideapluginguide.settingsinideconfigpanel;

import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.components.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.locks.ReentrantLock;

import static com.cyberneticscore.ideapluginguide.settingsinideconfigpanel.SystemSettingsConfiguration.ID_PLUGIN;

@State(name = ID_PLUGIN, storages = @Storage(
        file = "SettingsPanel.xml",
        roamingType = RoamingType.DISABLED))

public class SystemSettingsConfiguration implements ExportableComponent,
        PersistentStateComponent<SystemSettingsConfiguration.ProjectSettings> {
    public static final String ID_PLUGIN = "SETTINGS-INSIDE-CONFIG-PANEL-PLUGINS";

    private final ReentrantLock storageLock = new ReentrantLock();
    private final SortedMap<String, String> storage = new ConcurrentSkipListMap<>();

    @NotNull @Override
    public File[] getExportFiles() {
        return new File[]{PathManager.getOptionsFile("settings_config")};
    }

    @NotNull @Override
    public String getPresentableName() {
        return "IDE SETTINGS PANEL CONFIG";
    }

    @Nullable @Override
    public ProjectSettings getState() {
        storageLock.lock();
        try {
            return new ProjectSettings(storage);
        } finally {
            storageLock.unlock();
        }
    }

    @Override
    public void loadState(ProjectSettings projectSettings) {
        storageLock.lock();
        try {
            storage.clear();
            if (projectSettings != null) {
                storage.putAll(projectSettings.configurationAsMap());
            }
        } finally {
            storageLock.unlock();
        }
    }

    //THE CONFIGURATION PARAMETERS THAT ARE ACTUALLY STORED

    private static final String USERNAME = "user-name";
    private static final String WEBSITE = "website";

    public void setWebsite(String resmanUrl) {
        storage.put(WEBSITE, resmanUrl);
    }

    public void setUser(String ldapUser) {
        storage.put(USERNAME, ldapUser);
    }

    public String getWebsite() {
        return getProperty(WEBSITE);
    }

    public String getUser() {
        return getProperty(USERNAME);
    }

    public String getProperty(String property) {
        String result = storage.get(property);
        if (result == null) {
            return "";
        }
        return result;
    }

    /**
     * Wrapper class for IDEA state serialisation.
     */
    public static class ProjectSettings {
        public Map<String, String> configuration;

        public ProjectSettings() {
            this.configuration = new TreeMap<>();
        }

        public ProjectSettings(final Map<String, String> configuration) {
            this.configuration = new TreeMap<>(configuration);
        }

        public Map<String, String> configurationAsMap() {
            if (configuration == null) {
                return Collections.emptyMap();
            }
            return configuration;
        }
    }
}
