package Object.Crop;

import Main.Crop;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class Carrot extends Crop {
    private GamePanel gp;

    private BufferedImage Carrot_seed, Carrot_sprout, Carrot_sapling , Carrot_mature;
    public Carrot(){
        super("Carrot", 10.0, 25.0, 4);
    }
    public Carrot(GamePanel gp) {
        super("Carrot", 10.0, 25.0, 4);
        this.gp = gp;
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
    public void setCarrotImage() {

        gp.crops[0] = new Carrot(gp);
        gp.crops[0].worldX = 10 * gp.tileSize;
        gp.crops[0].worldY = 10 * gp.tileSize;
    }

    public void checkWatering() {
        for(int i = 0; i<=23; i++){
            if(gp.obj[i].name == "wateredSoil"){
                count += 1;
                if (count == 1){
                waterDay[i] += 1;}

            }
            count =0;
        }
    }
   @Override
    public void update() {
       setCurrentGrown();
           if (waterDay[0] == 0) {
               gp.crops[0].image = Carrot_seed;
           }
           if (waterDay[0] == 1  & gp.crops[0].image == Carrot_seed) {
               gp.crops[0].image = Carrot_sprout;
           }
           if (waterDay[0] == 2 & gp.crops[0].image == Carrot_sprout) {
               gp.crops[0].image = Carrot_sapling;
           }
           if (waterDay[0] == 3 & gp.crops[0].image == Carrot_sapling) {
               gp.crops[0].image = Carrot_mature;
           }

   }
}
