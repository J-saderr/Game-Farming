package Character;

import Main.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MerchantNPC extends Entity {
    private BufferedImage npc1;
    public BufferedImage npc2;

    public MerchantNPC(GamePanel gp) throws IOException {
        super(gp);
        //direction = "face";
        getNPCImage();
    }

    public void getNPCImage() throws IOException {
        try
            (//InputStream inputStream00 = new FileInputStream(new File("res/merchantNPC/npc1.png"));
             InputStream inputStream01 = new FileInputStream(new File("res/merchantNPC/npc2.png"))) {
            //npc1 = ImageIO.read(inputStream00);
            npc2 = ImageIO.read(inputStream01);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = npc2;
        if (image != null) {
            g2.drawImage(image, 150, 150, gp.tileSize, gp.tileSize, null);
        }
    }
}