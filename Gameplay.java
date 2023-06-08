package com.mycompany.brickbreakergame;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.swing.JPanel;
import javax.swing.Timer;
//import javax.swing.*;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener; // records action events on the screen
import java.awt.event.ActionEvent; // which action is happening 
import java.awt.event.KeyEvent; // records key events
import java.awt.event.KeyListener; // which key is creating the action

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 30;
    private Timer Timer;
    private int delay = 8;
    private int playerX = 310;
    private int ballposX = 200;
    private int ballposY = 480;
    private int ballXdir = -1; //displacement factor since the frame is rectangle, x and y displacement factors will not be same
    private int ballYdir = -2; // so taking disY as -2;
    private MapGenerator map;
    private boolean gameStarted = false;

    
    public Gameplay(){
        map = new MapGenerator(3, 10);
        addKeyListener(this);
        setFocusable(true);
        Timer = new Timer(delay, this);
        Timer.start();

    }
    @Override
    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(1, 1, 1015, 760); // 700-8 (width-delay8s, height - 7)
        
        map.draw((Graphics2D) g); // typecasting with graphics2d, g is the normal graphics
        
        g.setColor(Color.CYAN); // borders
        g.fillRect(0, 0, 1015, 3);
        g.fillRect(0, 0, 3, 760);
        g.fillRect(1007, 3, 3, 760);
       // g.fillRect(0, 0, 3, 592);
       
       g.setColor(Color.white); // score
       g.setFont(new Font("serif", Font.BOLD, 25));
       g.drawString(""+ score, 965,30);
       
       g.setColor(Color.yellow); // slider
       g.fillRect(playerX, 721, 100, 9);
       
       g.setColor(Color.green); // ball
       g.fillOval(ballposX, ballposY, 20, 20);
       
        if(!gameStarted){
            g.setColor(Color.CYAN);
            g.setFont(new Font("arial", Font.BOLD, 30));
            g.drawString(" Move left or right key to start!! " , 230, 350);
        }
       
       if(ballposY > 720){ // crossing the bottom boundary
           play=false;
           ballXdir = 0;
           ballYdir =0;
           g.setColor(Color.red);
           g.setFont(new Font("arial", Font.BOLD, 30));
           g.drawString(" Game Over. Score is : " +  score, 285, 300);
           
           g.setFont(new Font("arial", Font.BOLD, 30));
           g.drawString(" Press Enter to Restart! ", 285, 340);
       }
       if(totalBricks ==0){
           play=false;
           ballXdir = -1; // we are fixing the displacement factors to initial value as we are resetting
           ballYdir =2;
           g.setColor(Color.red);
           g.setFont(new Font("arial", Font.BOLD, 30));
           g.drawString(" Game Over. Score is : " +  score, 190, 300);
           
           g.setFont(new Font("arial", Font.BOLD, 30));
           g.drawString(" Press Enter to Restart! ", 285, 340);
       }
       g.dispose(); // graphics disposing.. kind of resetting the panel.
    }
    //we are overriding to define our own motion
    @Override
    public void actionPerformed(ActionEvent e){
        Timer.start(); // as the game starts, timer shud start... 
        if(play){
            // when our ball inersects with slider ball changes y direction
          if(new Rectangle(ballposX, ballposY,20,20).intersects(new Rectangle(playerX,720,100,8))) {
              ballYdir = - ballYdir;
          } 
          A:
          for(int i=0;i<map.map.length;i++){
              for(int j=0;j<map.map[0].length;j++){
                   if(map.map[i][j] > 0){
                  int brickX = (j*map.brickWidth) + map.brickWidth;
                  int brickY = (i*map.brickHeight) + map.brickHeight;
                  int brickWidth = map.brickWidth;
                  int brickHeight = map.brickHeight;
                  
                  Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                  Rectangle ballRect = new Rectangle(ballposX, ballposY, 20,20);
                  Rectangle brickRect = rect; // using brickrect as a temp thing to use rect
                  
                  if(ballRect.intersects(brickRect)){
                      map.setBricksValue(0,i,j);
                      totalBricks--;
                      score+=5;
                     // g.setColor(Color.RED);
                     if (ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickWidth) {
                            ballXdir = -ballXdir;
                        } else {
                            ballYdir = -ballYdir;
                        } 
                      break A;
                  }
                }
              }
          }
          
                  ballposX +=ballXdir;
                ballposY +=ballYdir;
                if(ballposX < 0){
                    ballXdir = - ballXdir;
                }
                if(ballposY < 0){
                    ballYdir = - ballYdir;
                }
                if(ballposX > 990){
                    ballXdir = - ballXdir; 
                }
        }
        

            repaint(); // when play ios false or it will run when variable is true everytime
    }

    @Override
    public void keyTyped(KeyEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
      //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
           
      if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 1000) {
                playerX = 1000;
            } else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                score = 0;
                playerX = 310;
                totalBricks = 21;
                map = new MapGenerator(3, 10);

                repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    private void moveRight() {
            play = true;
            gameStarted = true;
            playerX += 20;
    }

    private void moveLeft() {
            play = true;
            gameStarted = true;
            playerX -= 20;
    }
    
}
