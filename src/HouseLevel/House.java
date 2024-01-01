package HouseLevel;

import Main.Entity;
import Main.GamePanel;
import Main.KeyHandler;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class House extends Entity {
    public GamePanel gp;
    KeyHandler keyH;
    public House(GamePanel gp) {
        super(gp);
        this.keyH = keyH;
        collision = false;
        houseLevel = 1;
        getHouseImage();
    }

    private void getHouseImage() {
        try {
            InputStream inputStream001 = new FileInputStream(new File("res/house/h1.png"));
            InputStream inputStream002 = new FileInputStream(new File("res/house/h2.png"));
            InputStream inputStream003 = new FileInputStream(new File("res/house/h3.png"));
            InputStream inputStream004 = new FileInputStream(new File("res/house/h4.png"));
            InputStream inputStream005 = new FileInputStream(new File("res/house/h5.png"));
            house1 = ImageIO.read(inputStream001);
            house2 = ImageIO.read(inputStream002);
            house3 = ImageIO.read(inputStream003);
            house4 = ImageIO.read(inputStream004);
            house5 = ImageIO.read(inputStream005);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public void update() {
//        houseLevel = 5;
//    }
}

