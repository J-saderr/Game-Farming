package Character;

import Main.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MerchantNPC extends Entity {
    /**
     * The crops the store has for sale.
     */



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

}