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
        down1 = setup("res/Seed/Spinachseed");
        description = "Spinach Seed";
    }
}
