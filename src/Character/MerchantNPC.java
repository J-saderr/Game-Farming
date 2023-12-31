package Character;

import ItemSystem.Crop;
import Main.*;
import ItemSystem.Entities.Crop.Carrot;
import ItemSystem.Entities.Crop.Potato;
import ItemSystem.Entities.Crop.Spinach;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MerchantNPC extends Entity {
    /**
     * The crops the store has for sale.
     */
    private ArrayList<Crop> cropsForSale = new ArrayList<Crop>();

    public MerchantNPC(GamePanel gp){
        super(gp);
        speed = 0;
        type = type_npc;
        direction = "down";
        collision= false;
        try {
            InputStream inputStream14 = new FileInputStream(new File("res/merchantNPC/npc.png"));
            image = ImageIO.read(inputStream14);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setDialogue();
        setItems();
    }
    public void setDialogue() {
        //DISPLAY TEXT IN MULTIPLE LINES -> \n
        dialogues[0] = "Welcome to BetterFarming \nI'm Ms.Doxep. How can I help you ?";
    }
    public void speak() {
        super.speak();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }
    public void setItems(){
        inventory.add(new Carrot(gp));
        inventory.add(new Potato(gp));
        inventory.add(new Spinach(gp));
    }
}