package Entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MerchantNPC extends Entity {
    private BufferedImage npc;

    public MerchantNPC(GamePanel gp) throws IOException {
        super(gp);
        //direction = "face";
        getNPCImage();
    }

    public void getNPCImage() throws IOException {
        try (InputStream inputStream00 = new FileInputStream(new File("res/merchantNPC/npc.png"))) {
            npc = ImageIO.read(inputStream00);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = npc;
        if (image != null) {
            g2.drawImage(image, 150, 150, gp.tileSize, gp.tileSize, null);
        }
    }
}
