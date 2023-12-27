package Environment;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import Main.GamePanel;

public class Lighting {
    GamePanel gp;
    BufferedImage darknessFilter;
    int dayCounter;
    float filterAlpha = 0f;
    // day-state
    final int day = 0;
    final int dusk = 1;
    final int night = 2;
    final int dawn = 3;
    int dayState = day;

    public Lighting(GamePanel gp) {
        this.gp = gp;
        setLightSource();
    }

    public void setLightSource() {
            // create-a-buffered-image
            darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = (Graphics2D) darknessFilter.getGraphics();
            // get-the-center-x-and-y-of-the-light
            int centerX = gp.player.screenX + (gp.tileSize) / 2;
            int centerY = gp.player.screenY + (gp.tileSize) / 2;

            // create-gradation-effect
            Color color[] = new Color[6];
            float fraction[] = new float[6];

            color[0] = new Color(0, 0, 0.1f, 0f);
            color[1] = new Color(0, 0, 0.1f, 0.10f);
            color[2] = new Color(0, 0, 0.1f, 0.25f);
            color[3] = new Color(0, 0, 0.1f, 0.50f);
            color[4] = new Color(0, 0, 0.1f, 0.65f);
            color[5] = new Color(0, 0, 0.1f, 0.80f);

            fraction[0] = 0f;
            fraction[1] = 0.10f;
            fraction[2] = 0.25f;
            fraction[3] = 0.50f;
            fraction[4] = 0.75f;
            fraction[5] = 1f;

            // create-gradation-paint-settings
            RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, (350/2), fraction, color);

            // set-gradient-daya-on-g2
            g2.setPaint(gPaint);
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            g2.dispose();
    }

    public void update() {
        setLightSource();

        // check-the-state-of-the-day
        if (dayState == day) {
            dayCounter++;
            if (dayCounter > 600) {
                dayState = dusk;
                dayCounter = 0;
            }
        }
        if (dayState == dusk) {
            filterAlpha += 0.01f;
            if (filterAlpha > 1f) {
                filterAlpha = 1f;
                dayState = night;
            }
        }
        if (dayState == night) {
            dayCounter++;
            if (dayCounter > 600) {
                dayState = dawn;
                dayCounter = 0;
            }
        }
        if (dayState == dawn) {
            filterAlpha -= 0.01f;
            if (filterAlpha < 0f) {
                filterAlpha = 0f;
                dayState = day;
            }
        }
    }

    public void draw(Graphics2D g2) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
        g2.drawImage(darknessFilter, 0, 0, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));

        String situation = "";

        switch(dayState) {
            case day: situation = "Day"; break;
            case dusk: situation = "Dusk"; break;
            case night: situation = "Night"; break;
            case dawn: situation = "Dawn"; break;
        }
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(30F));
        g2.drawString(situation, 900,700);


    }

}
