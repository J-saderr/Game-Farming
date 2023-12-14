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

        try (InputStream inputStream00 = new FileInputStream(new File("res/tile/00.png"));
             InputStream inputStream05 = new FileInputStream(new File("res/tile/05.png"));
             InputStream inputStreamup = new FileInputStream(new File("res/tile/up.png"));
             InputStream inputStreamleftup = new FileInputStream(new File("res/tile/leftup.png"));
             InputStream inputStreamleft = new FileInputStream(new File("res/tile/left.png"));
             InputStream inputStreamleftbottom = new FileInputStream(new File("res/tile/leftbottom.png"));
             InputStream inputStreambottom = new FileInputStream(new File("res/tile/bottom.png"));
             InputStream inputStreamrightbottom = new FileInputStream(new File("res/tile/rightbottom.png"));
             InputStream inputStreamright = new FileInputStream(new File("res/tile/right.png"));
             InputStream inputStreamrightup = new FileInputStream(new File("res/tile/rightup.png"));
             InputStream inputStreamroad1up = new FileInputStream(new File("res/tile/road1up.png"));
             InputStream inputStreamroad1down = new FileInputStream(new File("res/tile/road1down.png"));
             InputStream inputStreamroad2up = new FileInputStream(new File("res/tile/road2up.png"));
             InputStream inputStreamroad2down = new FileInputStream(new File("res/tile/road2down.png"));
             InputStream inputStreamroad3up = new FileInputStream(new File("res/tile/road3up.png"));
             InputStream inputStreamroad3down = new FileInputStream(new File("res/tile/road3down.png"))) {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(inputStream00);

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(inputStream05);

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(inputStreamup);

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(inputStreamleftup);

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(inputStreamleft);

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(inputStreamleftbottom);

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(inputStreambottom);

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(inputStreamrightbottom);

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(inputStreamright);

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(inputStreamrightup);

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(inputStreamroad1up);

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(inputStreamroad1down);

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(inputStreamroad2up);

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(inputStreamroad2down);

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(inputStreamroad3up);

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(inputStreamroad3down);

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
