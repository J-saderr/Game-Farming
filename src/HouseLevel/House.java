package HouseLevel;

import Main.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class House extends Entity {
    public House(GamePanel gp) {
        super(gp);
        name = "House Level 1";
        collision = false;
        try {
            InputStream inputStream001 = new FileInputStream(new File("res/house/h1.png"));
            image = ImageIO.read(inputStream001);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

