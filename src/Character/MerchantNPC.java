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
    private Carrot carrotNPC = new Carrot(gp);
    private Potato potatoNPC = new Potato(gp);

    private Spinach spinachNPC = new Spinach(gp);



    public MerchantNPC(GamePanel gp){
        super(gp);
        speed = 0;
        type = type_npc;
        direction = "down";
        collision= false;
        try {
            InputStream inputStream14 = new FileInputStream(new File("res/tile/npc.png"));
            image = ImageIO.read(inputStream14);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setDialogue();
        setItems();
    }
    public void setDialogue() {
        //DISPLAY TEXT IN MULTIPLE LINES -> \n
        dialogues[0] = "Welcome to BetterFarming player! \nI'm Ms.Doxep. How can I help you ?";
    }
    public void speak() {
        super.speak();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }

    private void setDescription(){
        carrotNPC.description = "Carrot seed";
        potatoNPC.description = "Potato seed";
        spinachNPC.description = "Spinach seed";

    }
    public void setItems(){
        setDescription();
        inventory.add(carrotNPC);
        inventory.add(potatoNPC);
        inventory.add(spinachNPC)  ;
    }
}