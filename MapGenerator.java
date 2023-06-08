/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brickbreakergame;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

public class MapGenerator {
    public int map[][];
    public int brickWidth;
    public int brickHeight;
    // we are generating the bricks using 2d array (Mapgenerator function) where we get 1 value, create red color brick, else black color.
    public MapGenerator(int row, int col){
        map = new int[row][col];
        //initially we are taking whole red block
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                map[i][j] = 1;
            }
        }
        
        brickWidth = 850 / col;  // we can try play with this value, but on net these values were used.
        brickHeight = 150 / row;
    }
    public void draw(Graphics2D g){ //to specify what kind of graphics we need while drawing the bricks, we are passing Graphics2D obj
          for(int i=0; i<map.length; i++){
              for(int j=0;j<map[0].length;j++){
                  if(map[i][j] > 0){
                      g.setColor(Color.red);
                      g.fillRect(j*brickWidth + 80, i*brickHeight+50, brickWidth, brickHeight); // 80, 50 value came from the brickwidth and height
                      
                      g.setStroke(new BasicStroke(2));
                      g.setColor(Color.cyan);
                       g.drawRect(j*brickWidth + 80, i*brickHeight+50, brickWidth, brickHeight); 
                  }
              }
          }
    }

    void setBricksValue(int value, int row, int col) {
        map[row][col] = value;
    }
}
