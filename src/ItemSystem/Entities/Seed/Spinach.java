package ItemSystem.Entities.Seed;

import Main.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Spinach extends Entity{
    public Spinach(GamePanel gp){
        super(gp);
        name = "Spinach";
        price = 100;
        try {
            InputStream inputStream30 = new FileInputStream(new File("res/Seed/Spinachseed.png"));
            image = ImageIO.read(inputStream30);
        } catch (IOException e) {
            e.printStackTrace();
        }
        description = "Spinach Seed";
    }
}
