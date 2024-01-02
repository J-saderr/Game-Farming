package Money;

import Main.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Money extends Entity {
    public int amount;
    public GamePanel gp;
    public Money (GamePanel gp) {
        super(gp);
        setAmount();
        getMoneyBarImage();
    }

    public int getAmount() { return amount; }
    public void setAmount() { this.amount = 700; }
    public void getMoneyBarImage() {
        try {
            InputStream input = new FileInputStream(new File("res/Money/moneybar.png"));
            moneybar = ImageIO.read(input);
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
}
