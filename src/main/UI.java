package main;
import java.awt.*;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    public boolean messageOn = false;
    public String message = "";
    int messageCount = 0;
    public boolean gameFinished = false;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("0.00");

    public UI(GamePanel gp) {
        this.gp =gp;
        //Font chu trong game
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        if (gp.gameState == gp.playerState) {
        }

        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
    }
    //Display game paused
    public void drawPauseScreen() {
        String text = "GAME PAUSE";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text,x,y);
    }

    public int getXforCenteredText ( String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}