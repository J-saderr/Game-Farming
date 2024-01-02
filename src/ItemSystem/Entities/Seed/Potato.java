package ItemSystem.Entities.Seed;

import Main.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Potato extends Entity{
    public Potato (GamePanel gp){
        super(gp);
        name = "Potato";
        price = 75;
        try {
            InputStream inputStream31 = new FileInputStream(new File("res/Seed/Potatoseed.png"));
            image = ImageIO.read(inputStream31);
        } catch (IOException e) {
            e.printStackTrace();
        }
        description = "Potato Seed";
    }
}