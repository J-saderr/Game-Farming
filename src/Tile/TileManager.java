package Tile;
import Character.GamePanel;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;

public class TileManager {
    GamePanel gp;
    Tile[] tile;

    int Map[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[16]; //5 types of tile
        Map = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileManager();
        loadMap("res/Map/Map.txt");
    }

    public void getTileManager() {

        String filepath00 = "res/tile/00.png";
        String filepath05 = "res/tile/05.png";
        try (InputStream inputStream00 = new FileInputStream(new File(filepath00));
             InputStream inputStream05 = new FileInputStream(new File(filepath05))){

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(inputStream00);

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(inputStream05);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath) {
        try (InputStream is = new FileInputStream(new File(filePath));
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            int col = 0;
            int row = 0;
            String line;

            while ((line = br.readLine()) != null && row < gp.maxWorldRow) {
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                String numbers[] = line.split(" ");

                // Make sure the array is not longer than maxWorldCol
                int numCount = Math.min(numbers.length, gp.maxWorldCol);

                for (col = 0; col < numCount; col++) {
                    int num = Integer.parseInt(numbers[col]);
                    Map[col][row] = num;
                }
                col = 0;
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {

        int worldCol = 0;

        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = Map[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;

            int worldY = worldRow * gp.tileSize;

            int screenX = worldX - gp.player.worldX + gp.player.screenX;

            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize> gp.player.worldX - gp.player.screenX && worldX -gp.tileSize < gp.player.worldX + gp.player.screenX && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize< gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            }
            worldCol++;
            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
