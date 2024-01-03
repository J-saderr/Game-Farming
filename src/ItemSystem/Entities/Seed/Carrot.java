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
        description = "Carrot Seed";
        down1 = setup("res/Seed/Carrotseed");
    }
}