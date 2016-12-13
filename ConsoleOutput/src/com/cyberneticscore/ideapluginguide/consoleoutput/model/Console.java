package com.cyberneticscore.ideapluginguide.consoleoutput.model;

import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.project.Project;

import javax.swing.*;

public class Console {
    private static ConsoleView myConsole;

    public Console(Project project) {
        myConsole = TextConsoleBuilderFactory.getInstance().createBuilder(project).getConsole();

        demo();
    }

    public JComponent getComponent(){
        return myConsole.getComponent();
    }

    public static void print(String text){
        myConsole.print(text + "\r\n", ConsoleViewContentType.NORMAL_OUTPUT);
    }

    public static void print(String text, ConsoleViewContentType outputType){
        myConsole.print(text+ "\r\n", outputType);
    }

    private void demo(){
        print("HELLO CONSOLE");
        print("HELLO ERROR", ConsoleViewContentType.ERROR_OUTPUT);
        print("HELLO SYSTEM", ConsoleViewContentType.SYSTEM_OUTPUT);
        print("HELLO USER_INPUT", ConsoleViewContentType.USER_INPUT);
    }



}
