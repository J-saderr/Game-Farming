package Entity;

import main.GamePanel;

public class MerchantNPC extends Entity{

    public MerchantNPC(GamePanel gp) {
        super(gp);
        speed = 0;
        direction = "up";
        getNPCimage();
        setDialogue();
        speak();
    }
    public void getNPCimage(){
        up1 = setup("res/merchantNPC/npc");
        up2 = setup("res/merchantNPC/npc");
        up3 = setup("res/merchantNPC/npc");
        down1 = setup("res/merchantNPC/npc");
        down2 = setup("res/merchantNPC/npc");
        down3 = setup("res/merchantNPC/npc");
        left1 = setup("res/merchantNPC/npc");
        left2 = setup("res/merchantNPC/npc");
        left3 = setup("res/merchantNPC/npc");
        right1 = setup("res/merchantNPC/npc");
        right2 = setup("res/merchantNPC/npc");
        right3 = setup("res/merchantNPC/npc");
    }
    public void setDialogue() {
        //DISPLAY TEXT IN MULTIPLE LINES -> \n
        dialogues[0] = "sup bro?";
        dialogues[1] = "muon j ?";
        dialogues[2] = "mua hay ban ?";
        dialogues[3] = "gudbai";

    }
    public void speak() {

        super.speak();
    }
}
