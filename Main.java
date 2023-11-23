package org.brick;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        JFrame obj = new JFrame();
        GamePlay gamePlay = new GamePlay();
        obj.setTitle("Brick Breaker");
        obj.setBounds(10, 10, 700, 600);
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gamePlay);
    }
}