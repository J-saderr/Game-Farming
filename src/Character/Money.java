package Character;

import ItemSystem.UtilityTool;
import Main.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Money extends Entity {
    public int amount;

    public Money(GamePanel gp) {
        super(gp);
        getImage();
        //setAmount(500);
    }

//    public int getAmount() { return amount; }
//    public void setAmount(int amount) { this.amount = amount; }
    public void getImage() {
        try {
            InputStream input01 = new FileInputStream(new File("res/Money/moneybar.png"));
            InputStream input02 = new FileInputStream(new File("res/Money/coin.png"));
            monicon = ImageIO.read(input02);
            monbar = ImageIO.read(input01);

            //uTool.scaleImage(image, 50, 50);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
