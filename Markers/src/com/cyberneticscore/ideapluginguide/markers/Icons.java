package com.cyberneticscore.ideapluginguide.markers;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

public class Icons {
    public static Icon load(String path) {
        return IconLoader.getIcon(path, Icons.class);
    }

    public static final Icon Plus = load("/general/add.png");
    public static final Icon Lock = load("/general/locate.png");
    public static final Icon Pin = load("/general/pin_tab.png");

}
