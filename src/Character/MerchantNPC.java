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

    public MerchantNPC(GamePanel gp) {
        super(gp);
        speed = 0;
        direction = "down";
        collision= false;
        try {
            InputStream inputStream14 = new FileInputStream(new File("res/merchantNPC/npc.png"));
            image = ImageIO.read(inputStream14);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}