/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.brickbreakergame;

import javax.swing.JFrame;

/**
 *
 * @author feroz
 */
public class BrickBreakerGame {

       public static void main(String[] args) {
       // System.out.println("Hello World!");
       JFrame obj = new JFrame();
       Gameplay gameplay = new Gameplay();
       obj.setBounds(10, 10, 1024,768);
       obj.setTitle("Brick Breaker");
       obj.setResizable(false);
       obj.setVisible(true);
       obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       obj.add(gameplay); // adds panel to the frame;
    }
}
