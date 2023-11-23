package org.brick;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
    public boolean play = true;
    public int score = 0;
    public int totalBricks  = 21;
    public Timer timer;
    public int delay = 8;
    public int playerX = 310;

    public int ballposX = 120;
    public int ballposY = 350;
    public int ballXdir = -1;
    public int ballYdir = -2;

    public MapGenerator map;

    public GamePlay() {
        map = new MapGenerator(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics graphics) {
        graphics.setColor(Color.YELLOW);
        graphics.fillRect(1, 1, 692, 592);
        map.draw((Graphics2D) graphics);
        graphics.fillRect(0, 0, 3, 592);
        graphics.fillRect(0, 0, 692, 3);
        graphics.fillRect(691, 0, 3, 592);
        graphics.setColor(Color.BLUE);
        graphics.fillRect(playerX, 550, 100, 12);

        graphics.setColor(Color.RED);
        graphics.fillOval(ballposX, ballposY, 20, 20);
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("MV Boli", Font.BOLD, 25));
        graphics.drawString("Score:" + score, 520, 30);


        if(totalBricks <= 0) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            graphics.setColor(new Color(0XFF6464));
            graphics.setFont(new Font("MV Boli", Font.BOLD, 30));
            graphics.drawString("You Won, Score: " + score, 190, 300);

            graphics.setFont(new Font("MV Boli", Font.BOLD, 20));
            graphics.drawString("Press Enter to Restart.", 230, 350);
        }
            if(ballposY > 570) {
                play = false;
                ballXdir = 0;
                ballYdir = 0;
                graphics.setColor(Color.BLACK);
                graphics.setFont(new Font("MV Boli", Font.BOLD, 30));
                graphics.drawString("Game Over, Score" + score, 190, 300);
                graphics.drawString("Press Enter to Restart.", 230, 350);
            }

            graphics.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (play) {
            if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(
                    playerX, 550, 100, 8
            ))) {
                ballYdir = -ballYdir;
            }
            for (int j = 0; j < map.map.length; j++) {
                for (int k = 0; k < map.map[0].length; k++) {
                    if (map.map[j][k] > 0) {
                        int brickX = k * map.brickWidth + 80;
                        int brickY = j * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle rectangle = new Rectangle(brickX, brickY, brickWidth,
                                brickHeight);
                        Rectangle ballRectangle = new Rectangle(ballposX, ballposY
                                , 20, 20);
                        Rectangle brickRectangle = rectangle;

                        if (ballRectangle.intersects(brickRectangle)) {
                            map.setBrickValue(0, j, k);
                            totalBricks--;
                            score += 5;

                            if (ballposX + 19 <= brickRectangle.x || ballposX + 1 >= brickRectangle
                                    .x + brickRectangle.width) {
                                ballXdir = -ballXdir;
                            } else {
                                ballYdir = -ballYdir;
                            }
                        }
                    }
                }
            }

            ballposX += ballXdir;
            ballposY += ballYdir;
            if (ballposX < 0) {
                ballXdir = -ballXdir;
            }
            if (ballposY < 0) {
                ballYdir = -ballYdir;
            }
            if (ballposX > 670) {
                ballXdir = -ballXdir;
            }
        }
        repaint();
    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(playerX >= 600) {
                playerX = 600;
            }else{
                moveRight();
            }
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            if(playerX < 10) {
                playerX = 10;
            }else{
                moveLeft();
            }
        }

        if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
             if(!play) {
                 play = true;
                 ballposX = 120; ballposY = 350; ballXdir = -1; ballYdir = -2;
                 score = 0; totalBricks = 21; 
                 map = new MapGenerator(3, 7);

                 repaint();
             }
        }
    }

    public void moveRight() {
        play = true;
        playerX += 50;
    }
    public void moveLeft() {
        play = true;
        playerX -= 50;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
    public static void main(String[] args) {
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
