package ItemSystem.Entities.Crop;

import Main.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class Spinach extends Entity {

    public BufferedImage Spinach_seed, Spinach_sprout, Spinach_sapling , Spinach_mature;

    public Spinach(GamePanel gp) {
        super(gp);
        name = "Spinach";
        type = type_spinach;
        price = 100;
        daysToGrow = 4;
        down1 = setup("res/Plants/2");
        description = "Spinach Seed";
        getSpinachImage();
        stackable = true;
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
            gp.entities[i].name = "Spinach seed";
        }
        if (gp.entities[i].waterDay[i] == 1 && gp.entities[i].name == "Spinach seed") {
            gp.entities[i].name = "Spinach sprout";
            gp.entities[i].image = Spinach_sprout;
            gp.entities[i].cropPeriod += 1;
        }
        if (gp.entities[i].waterDay[i] >1 && gp.entities[i].name == "Spinach seed"){
            gp.entities[i].waterDay[i] = 0;
        }
        if (gp.entities[i].waterDay[i] == 2 & gp.entities[i].name == "Spinach sprout") {
            gp.entities[i].name = "Spinach sapling";
            gp.entities[i].image = Spinach_sapling;
            gp.entities[i].cropPeriod += 1;
        }
        if (gp.entities[i].waterDay[i] >2 && gp.entities[i].name == "Spinach sprout") {
            gp.entities[i].waterDay[i] = 1;
        }
        if (gp.entities[i].waterDay[i] == 3 & gp.entities[i].name == "Spinach sapling") {
            gp.entities[i].name = "Spinach mature";
            gp.entities[i].image = Spinach_mature;
            gp.entities[i].cropPeriod += 1;
            gp.entities[i].type = type_spinach_mature;
        }
        if (gp.entities[i].waterDay[i] >3 && gp.entities[i].name == "Spinach sapling"){
            gp.entities[i].waterDay[i] = 2;
        }
    }
    @Override
    public void update(){
    }

}

