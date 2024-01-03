package Character;

import ItemSystem.Entities.Seed.Carrot;
import ItemSystem.Entities.Seed.Potato;
import ItemSystem.Entities.Seed.Spinach;
import Main.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MerchantNPC extends Entity {
    private BufferedImage npc1;
    public BufferedImage npc2;

    public MerchantNPC(GamePanel gp) {
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
        dialogues[0] = "sup bro?";
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