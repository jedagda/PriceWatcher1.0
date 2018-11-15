package main.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AppCloser extends WindowAdapter {
    public void windowClosing(WindowEvent event){
        System.exit(0);
    }

}
