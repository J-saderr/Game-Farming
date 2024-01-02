package ItemSystem.Entities.Seed;

import Main.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Carrot extends Entity{
    public Carrot(GamePanel gp){
        super(gp);
        name = "Carrot";
        price = 50;
        try {
            InputStream inputStream32 = new FileInputStream(new File("res/Seed/Carrotseed.png"));
            image = ImageIO.read(inputStream32);
        } catch (IOException e) {
            e.printStackTrace();
        }
        description = "Carrot Seed";
    }
}