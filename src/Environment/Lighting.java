package Environment;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import Character.Player;

import ItemSystem.Entities.Crop.Carrot;
import Main.Entity;
import Main.GamePanel;
import Main.KeyHandler;

public class Lighting {
    GamePanel gp;
    BufferedImage darknessFilter;
    public int dayCounter;
    public float filterAlpha = 0f;
    public final int day = 0;
    public final int dusk = 1;
    public final int night = 2;
    public final int dawn = 3;
    public int dayState = day;
    private Entity entity = new Entity(gp);
    private Carrot carrot = new Carrot(gp);

    Font upheaval, minecraftia;

    public Lighting(GamePanel gp) {
        this.gp = gp;
        //Font chu trong game
        try {
            InputStream is = getClass().getResourceAsStream("/font/Minecraftia-Regular.ttf");
            minecraftia = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/font/upheavtt.ttf");
            upheaval = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setLightSource();
    }

    public void setLightSource() {
            // create-a-buffered-image
            darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = (Graphics2D) darknessFilter.getGraphics();

            //create a screen-sized rectangle area
            Area screenArea = new Area(new Rectangle2D.Double(0, 0, gp.screenWidth, gp.screenHeight));

            // get-the-center-x-and-y-of-the-light
            int centerX = gp.player.screenX + (gp.tileSize) / 2;
            int centerY = gp.player.screenY + (gp.tileSize) / 2;

            // top left x and y of light circle
            double x = centerX - 175;
            double y = centerY - 175;

            // create light circle shape
            Shape circleShape = new Ellipse2D.Double(x,y, 350,350);

            // create light circle area
            Area lightArea = new Area(circleShape);

            // Subtract light circle from screen rectangle
            screenArea.subtract(lightArea);

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
            RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, 175, fraction, color);

            // set-gradient-daya-on-g2
            g2.setPaint(gPaint);

            g2.fill(lightArea);

            g2.fill(screenArea);


            g2.setPaint(gPaint);
            g2.dispose();
    }

    public void update() {

        //setLightSource();

        // check-the-state-of-the-day
        if (dayState == day) {
            dayCounter++;
            if (dayCounter==1){
                for ( int i = 0 ; i<= 23; i++) {
                    gp.player.checkWatering(i);
                    if(gp.entities[i] != null) {
                        if (gp.entities[i].type == entity.type_carrot) {
                            gp.carrot.CarrotLogic(i);}
                        else if (gp.entities[i].type == entity.type_potato) {
                            gp.potato.PotatoLogic(i);}
                        else if (gp.entities[i].type == entity.type_spinach) {
                            gp.spinach.SpinachLogic(i);}
                    }
                }
                gp.resetSoil();
            }
            else if (dayCounter > 600) {
                dayState = dusk;
                dayCounter = 0;
            }
        }
        if (dayState == dusk) {
            filterAlpha += 0.001f;
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
            filterAlpha -= 0.001f;
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
        Color c = new Color(164, 76, 69);
        g2.setColor(c);
        g2.setFont(upheaval);
        g2.setFont(g2.getFont().deriveFont(20F));
        g2.drawString(situation, 670,550);
        c = new Color(243, 229, 215);
        g2.setColor(c);
        g2.drawString(situation, 671, 551);

    }

}
