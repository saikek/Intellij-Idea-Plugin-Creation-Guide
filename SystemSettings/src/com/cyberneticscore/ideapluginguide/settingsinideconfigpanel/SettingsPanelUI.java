package com.cyberneticscore.ideapluginguide.settingsinideconfigpanel;

import javax.swing.*;

public class SettingsPanelUI {
    private JTextField txtUserName;
    private JTextField txtWebsite;
    private JPanel ideaConfigPanel;

    public String getTxtUserName() {
        return txtUserName.getText();
    }

    public void setTxtUserName(String userName) {
        this.txtUserName.setText(userName);
    }

    public String getTxtWebsite() {
        return txtWebsite.getText();
    }

    public void setTxtWebsite(String webSite) {
        this.txtWebsite.setText(webSite);
    }

    public JPanel getIdeaConfigPanel() {
        return ideaConfigPanel;
    }
}
