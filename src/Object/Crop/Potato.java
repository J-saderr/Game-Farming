package Object.Crop;

import Main.Crop;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class Potato extends Crop {
    private GamePanel gp;

    private BufferedImage Potato_seed, Potato_sprout, Potato_sapling , Potato_mature;
    public Potato(){
        super("Potato", 5.0, 20.0, 4);
    }
    public Potato(GamePanel gp) {
        super("Potato", 5.0, 20.0, 4);
        this.gp = gp;
        getPotatoImage();
    }


    public void getPotatoImage() {
        try {
            InputStream inputStream103 = new FileInputStream(new File("res/Plants/2.png"));
            InputStream inputStream104 = new FileInputStream(new File("res/Plants/8.png"));
            InputStream inputStream105 = new FileInputStream(new File("res/Plants/11.png"));
            InputStream inputStream106 = new FileInputStream(new File("res/Plants/14.png"));
            Potato_seed = ImageIO.read(inputStream103);
            Potato_sprout = ImageIO.read(inputStream104);
            Potato_sapling = ImageIO.read(inputStream105);
            Potato_mature = ImageIO.read(inputStream106);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setPotatoImage() {

        gp.crops[0] = new Potato(gp);
        gp.crops[0].worldX = 11 * gp.tileSize;
        gp.crops[0].worldY = 10 * gp.tileSize;
    }

    @Override
    public void update(){
        setCurrentGrown();
        if (super.getDaysPass() == 0){
            gp.crops[0].image = Potato_seed;
        }
        if (super.getDaysPass() == 1)
        {
            gp.crops[0].image = Potato_sprout;
        }
        if (super.getDaysPass() == 2)
        {
            gp.crops[0].image = Potato_sapling;
        }
        if (super.getDaysPass() == 3)
        {
            gp.crops[0].image = Potato_mature;
        }
    }
}
