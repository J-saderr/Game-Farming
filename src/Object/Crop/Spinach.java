package Object.Crop;

import Main.Crop;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class Spinach extends Crop {
    private GamePanel gp;

    private BufferedImage Spinach_seed, Spinach_sprout, Spinach_sapling , Spinach_mature;
    public Spinach(){
        super("Spinach", 15.0, 35.0, 4);
    }
    public Spinach(GamePanel gp) {
        super("Spinach", 15.0, 35.0, 4);
        this.gp = gp;
        getSpinachImage();
    }


    public void getSpinachImage() {
        try {
            InputStream inputStream107 = new FileInputStream(new File("res/Plants/3.png"));
            InputStream inputStream108 = new FileInputStream(new File("res/Plants/9.png"));
            InputStream inputStream109 = new FileInputStream(new File("res/Plants/12.png"));
            InputStream inputStream110 = new FileInputStream(new File("res/Plants/15.png"));
            Spinach_seed = ImageIO.read(inputStream107);
            Spinach_sprout = ImageIO.read(inputStream108);
            Spinach_sapling = ImageIO.read(inputStream109);
            Spinach_mature = ImageIO.read(inputStream110);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setSpinachImage() {

        gp.crops[2] = new Spinach(gp);
        gp.crops[2].worldX = 12 * gp.tileSize;
        gp.crops[2].worldY = 10 * gp.tileSize;
    }

    @Override
    public void update(){
        setCurrentGrown();
        if (super.getDaysPass() == 0){
            gp.crops[2].image = Spinach_seed;
        }
        if (super.getDaysPass() == 1)
        {
            gp.crops[2].image = Spinach_sprout;
        }
        if (super.getDaysPass() == 2)
        {
            gp.crops[2].image = Spinach_sapling;
        }
        if (super.getDaysPass() == 3)
        {
            gp.crops[2].image = Spinach_mature;
        }
    }
}

