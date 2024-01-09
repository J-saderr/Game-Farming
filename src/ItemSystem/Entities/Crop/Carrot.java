package ItemSystem.Entities.Crop;

import Main.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class Carrot extends Entity {
    private GamePanel gp;

    public BufferedImage Carrot_seed, Carrot_sprout, Carrot_sapling , Carrot_mature;
    public Carrot(GamePanel gp) {
        super("Carrot", 10.0, 25.0, 4);
        this.gp = gp;
        price = 50;
        type = type_carrot;
        down1 = setup("res/Seed/Carrotseed");
        description = "Carrot Seed ";
        getCarrotImage();
    }


    public void getCarrotImage() {
        try {
            InputStream inputStream99 = new FileInputStream(new File("res/Plants/1.png"));
            InputStream inputStream100 = new FileInputStream(new File("res/Plants/7.png"));
            InputStream inputStream101 = new FileInputStream(new File("res/Plants/10.png"));
            InputStream inputStream102 = new FileInputStream(new File("res/Plants/13.png"));
            Carrot_seed = ImageIO.read(inputStream99);
            Carrot_sprout = ImageIO.read(inputStream100);
            Carrot_sapling = ImageIO.read(inputStream101);
            Carrot_mature = ImageIO.read(inputStream102);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void CarrotLogic(int i){
        if (gp.entities[i].waterDay[i] == 0) {
            gp.entities[i].image = Carrot_seed;
        }
        if (gp.entities[i].waterDay[i] == 1& gp.entities[i].image == Carrot_seed) {
            gp.entities[i].image = Carrot_sprout;
            gp.entities[i].cropPeriod += 1;
        }
        if (gp.entities[i].waterDay[i] >1 && gp.entities[i].image == Carrot_seed){
            gp.entities[i].waterDay[i] = 0;
        }
        if (gp.entities[i].waterDay[i] == 2 & gp.entities[i].image == Carrot_sprout) {
            gp.entities[i].image = Carrot_sapling;
            gp.entities[i].cropPeriod += 1;
        }
        if (gp.entities[i].waterDay[i] >2 && gp.entities[i].image == Carrot_sprout) {
            gp.entities[i].waterDay[i] = 1;
        }
        if (gp.entities[i].waterDay[i] == 3 & gp.entities[i].image == Carrot_sapling) {
            gp.entities[i].image = Carrot_mature;
            gp.entities[i].cropPeriod += 1;
        }
        if (gp.entities[i].waterDay[i] >3 && gp.entities[i].image == Carrot_sapling){
            gp.entities[i].waterDay[i] = 2;
        }
    }
   @Override
    public void update() {
   }
}
