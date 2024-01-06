package Object.Crop;
import Main.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class Potato extends Entity {
    private GamePanel gp;

    public BufferedImage Potato_seed, Potato_sprout, Potato_sapling , Potato_mature;
    public Potato(){
        super("Potato", 5.0, 20.0, 4);
    }
    public Potato(GamePanel gp) {
        super("Potato", 5.0, 20.0, 4);
        this.gp = gp;
        type = type_potato;
        down1 = setup("res/Seed/Potatoseed");
        description = "Potato Seed";
        getPotatoImage();
    }


    public void getPotatoImage() {
        try {
            InputStream inputStream103 = new FileInputStream(new File("res/Plants/3.png"));
            InputStream inputStream104 = new FileInputStream(new File("res/Plants/9.png"));
            InputStream inputStream105 = new FileInputStream(new File("res/Plants/12.png"));
            InputStream inputStream106 = new FileInputStream(new File("res/Plants/15.png"));
            Potato_seed = ImageIO.read(inputStream103);
            Potato_sprout = ImageIO.read(inputStream104);
            Potato_sapling = ImageIO.read(inputStream105);
            Potato_mature = ImageIO.read(inputStream106);

        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public void PotatoLogic(int i){
        if (gp.entities[i].waterDay[i] == 0) {
        gp.entities[i].image = Potato_seed;
    }
        if (gp.entities[i].waterDay[i] == 1& gp.entities[i].image == Potato_seed) {
            gp.entities[i].image = Potato_sprout;
        }
        if (gp.entities[i].waterDay[i] >1 && gp.entities[i].image == Potato_seed){
            gp.entities[i].waterDay[i] = 0;
        }
        if (gp.entities[i].waterDay[i] == 2 & gp.entities[i].image == Potato_sprout) {
            gp.entities[i].image = Potato_sapling;
        }
        if (gp.entities[i].waterDay[i] >2 && gp.entities[i].image == Potato_sprout) {
            gp.entities[i].waterDay[i] = 1;
        }
        if (gp.entities[i].waterDay[i] == 3 & gp.entities[i].image == Potato_sapling) {
            gp.entities[i].image = Potato_mature;
        }
        if (gp.entities[i].waterDay[i] >3 && gp.entities[i].image == Potato_sapling){
            gp.entities[i].waterDay[i] = 2;
        }
    }
    @Override
    public void update(){
    }

}
