package Object.Crop;

import Main.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class Spinach extends Entity {
    private GamePanel gp;

    public BufferedImage Spinach_seed, Spinach_sprout, Spinach_sapling , Spinach_mature;
    public Spinach(){
        super("Spinach", 15.0, 35.0, 4);
    }
    public Spinach(GamePanel gp) {
        super("Spinach", 15.0, 35.0, 4);
        this.gp = gp;
        type = type_spinach;
        down1 = setup("res/Seed/Spinachseed");
        description = "Spinach Seed";
        getSpinachImage();
    }


    public void getSpinachImage() {
        try {
            InputStream inputStream107 = new FileInputStream(new File("res/Plants/2.png"));
            InputStream inputStream108 = new FileInputStream(new File("res/Plants/8.png"));
            InputStream inputStream109 = new FileInputStream(new File("res/Plants/11.png"));
            InputStream inputStream110 = new FileInputStream(new File("res/Plants/14.png"));
            Spinach_seed = ImageIO.read(inputStream107);
            Spinach_sprout = ImageIO.read(inputStream108);
            Spinach_sapling = ImageIO.read(inputStream109);
            Spinach_mature = ImageIO.read(inputStream110);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void SpinachLogic(int i){
        if (gp.entities[i].waterDay[i] == 0) {
            gp.entities[i].image = Spinach_seed;
        }
        if (gp.entities[i].waterDay[i] == 1& gp.entities[i].image == Spinach_seed) {
            gp.entities[i].image = Spinach_sprout;
        }
        if (gp.entities[i].waterDay[i] >1 && gp.entities[i].image == Spinach_seed){
            gp.entities[i].waterDay[i] = 0;
        }
        if (gp.entities[i].waterDay[i] == 2 & gp.entities[i].image == Spinach_sprout) {
            gp.entities[i].image = Spinach_sapling;
        }
        if (gp.entities[i].waterDay[i] >2 && gp.entities[i].image == Spinach_sprout) {
            gp.entities[i].waterDay[i] = 1;
        }
        if (gp.entities[i].waterDay[i] == 3 & gp.entities[i].image == Spinach_sapling) {
            gp.entities[i].image = Spinach_mature;
        }
        if (gp.entities[i].waterDay[i] >3 && gp.entities[i].image == Spinach_sapling){
            gp.entities[i].waterDay[i] = 2;
        }
    }
    @Override
    public void update(){
    }

}

