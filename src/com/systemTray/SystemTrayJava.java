/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systemTray;

import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JMenu;

/**
 *
 * @author Yasitha
 */
public class SystemTrayJava {

    //more information: https://docs.oracle.com/javase/7/docs/api/java/awt/TrayIcon.html
    static TrayIcon trayIcon;

    public static void main(String args[]) {
        if (SystemTray.isSupported()) {
            show();
        } else {
            System.out.println("Not Support System Tray");
        }
    }

    private static void show() {

        final SystemTray systemTray = SystemTray.getSystemTray();

        trayIcon = new TrayIcon(getImage("res/world.png"));
        trayIcon.setImageAutoSize(true);

        trayIcon.setToolTip("This is ToolTip Message");

        //-------------PopupMenu-----------------------
        PopupMenu popupMenu = new PopupMenu();

        Menu about = new Menu("About");
        MenuItem aboutPC = new MenuItem("About PC");
        MenuItem aboutMe = new MenuItem("About ME");
        about.add(aboutPC);
        about.add(aboutMe);

        MenuItem exit = new MenuItem("Exit");

        popupMenu.add(about);
        popupMenu.addSeparator();
        popupMenu.add(exit);

        aboutPC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("about PC clicked");
            }
        });

        aboutMe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("about Me clicked");
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        trayIcon.setPopupMenu(popupMenu);
        //-------------------------------------------------

        
        //-----------------icon Cliked---------------------
        trayIcon.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    System.out.println("Mouse Button Clicked | Button Value : " + e.getButton());
                }
            }
        });
        //-------------------------------------------------
        
        
        try {
            systemTray.add(trayIcon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
    }

    private static Image getImage(String path) {
        ImageIcon icon = new ImageIcon(path);
        return icon.getImage();
    }
}
