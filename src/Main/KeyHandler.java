package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Character.*;

public class KeyHandler implements KeyListener {
    GamePanel gp;

    public boolean up, down, left, right, enter;
    public KeyHandler(GamePanel gp){
        this.gp=gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // return the int keyCode associated with the key in this event
        if(gp.gameState == gp.playerState) {
            playerState(code);
        }
        if(gp.gameState == gp.pauseState) {
            pauseState(code);
        }
        if(gp.gameState == gp.characterState) {
            characterState(code);
        }
        if(gp.gameState == gp.titleState) {
            titleState(code);
        }
        if(gp.gameState == gp.dialogueState){
            dialogueState(code);
        }
    }
    public void dialogueState(int code){
        if(code == KeyEvent.VK_ENTER) {
            enter = true;
        }
    }
    public void playerState(int code) {
        if(code == KeyEvent.VK_W) {
            up = true;
        }
        if(code == KeyEvent.VK_S) {
            down= true;
        }
        if (code == KeyEvent.VK_A) {
            left = true;
        }
        if(code == KeyEvent.VK_D) {
            right = true;
        }
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState;
        }
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.characterState;
        }
        if (code == KeyEvent.VK_O) {
            gp.gameState = gp.playerState;
        }
    }
    public void pauseState(int code){
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.pauseState;
        }
    }
    public void characterState(int code){
        if(code == KeyEvent.VK_C){
            gp.gameState = gp.characterState;
        }
        if (code == KeyEvent.VK_W) {
            if(gp.ui.slotRow != 0){
                gp.ui.slotRow--;
            }
        }
        if (code == KeyEvent.VK_A) {
            if(gp.ui.slotCol != 0){
                gp.ui.slotCol--;
            }
        }
        if (code == KeyEvent.VK_S) {
            if(gp.ui.slotRow != 3){
                gp.ui.slotRow++;
            }
        }
        if (code == KeyEvent.VK_D) {
            if(gp.ui.slotCol != 4){
                gp.ui.slotCol++;
            }
        }
    }
    public void titleState(int code){
        if (gp.gameState == gp.titleState) {
            if(code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }
            if(code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.gameState = gp.playerState;
                    gp.playMusic(0);
                }
                if (gp.ui.commandNum == 1) {
                }
                if (gp.ui.commandNum == 3) {
                    System.exit(0);
                }
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            up = false;
        }
        if (code == KeyEvent.VK_S) {
            down = false;
        }
        if (code == KeyEvent.VK_A) {
            left = false;
        }
        if (code == KeyEvent.VK_D) {
            right = false;
        }
        if (code == KeyEvent.VK_O) {
            gp.gameState = gp.playerState;
        }
    }
}
