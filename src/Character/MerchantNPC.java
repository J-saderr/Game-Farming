package Character;

import Main.*;
import Object.Crop.Carrot;
import Object.Crop.Potato;
import Object.Crop.Spinach;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MerchantNPC extends Entity {
    private BufferedImage npc1;
    public BufferedImage npc2;
    /**
     * The crops the store has for sale.
     */
    private ArrayList<Crop> cropsForSale = new ArrayList<Crop>();

    public MerchantNPC(GamePanel gp) throws IOException {
        super(gp);
        //direction = "face";
        getNPCImage();

        //crops
        cropsForSale.add(new Carrot());
        cropsForSale.add(new Spinach());
        cropsForSale.add(new Potato());
    }
    /**
     * Returns the cropsForSale ArrayList.
     * @return The cropsForSale.
     */
    public ArrayList<Crop> getCropsForSale() {
        return cropsForSale;
    }
    /**
     * return Crop at <code>index</code> to be purchased.
     * @param index Index of crop to buy.
     * @return The Crop at specified <code>index</code>.
     */
    public Crop buyCrops(int index) {
        return cropsForSale.get(index);
    }
    public void getNPCImage() throws IOException {
        try
                (//InputStream inputStream00 = new FileInputStream(new File("res/merchantNPC/npc1.png"));
                 InputStream inputStream01 = new FileInputStream(new File("res/merchantNPC/npc2.png"))) {
            //npc1 = ImageIO.read(inputStream00);
            npc2 = ImageIO.read(inputStream01);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = npc2;
        if (image != null) {
            g2.drawImage(image, 150, 150, gp.tileSize, gp.tileSize, null);
        }
    }
}