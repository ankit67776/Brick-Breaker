package org.brick;

import java.awt.*;

public class MapGenerator {
    public int[][] map;
    public int brickWidth;
    public int brickHeight;

    public MapGenerator(int row, int col) {
        map = new int[row][col];
        for(int j = 0; j < row; j++) {
            for(int k = 0; k < col; k++) {
                map[j][k] = 1;
            }
        }
        brickWidth = 540/col;
        brickHeight = 150/ row;
    }

    // draw bricks
    public void draw(Graphics2D graphics2D) {
        for(int j = 0; j < map.length; j++) {
            for(int k = 0; k < map[0].length; k++) {
                if(map[j][k] > 0) {
                    graphics2D.setColor(new Color(0XFF8787));
                    graphics2D.fillRect(k*brickWidth + 80, j * brickHeight + 50,
                            brickWidth, brickHeight);
                    graphics2D.setStroke(new BasicStroke(4));
                    graphics2D.setColor(Color.BLACK);
                    graphics2D.drawRect(k * brickWidth + 80, j * brickHeight + 50,
                            brickWidth, brickHeight);
                }
            }
        }
    }
    // set the value of brick to 0 after hitting the ball
    public void setBrickValue(int val, int row, int col) {
        map[row][col] = val;
    }

    public static void main(String[] args) {

    }
}
