package Tile;
import Character.GamePanel;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class TileManager {
    GamePanel gp;
    Tile[] tile;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[5]; //5 types of tile
        getTileManager();
    }

    public void getTileManager() {
        String filePath00 = "res/tile/00.png";
        String filePath05 = "res/tile/05.png";

        try (InputStream inputStream00 = new FileInputStream(new File(filePath00));
             InputStream inputStream05 = new FileInputStream(new File(filePath05))) {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(inputStream00);

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(inputStream05);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            g2.drawImage(tile[0].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
