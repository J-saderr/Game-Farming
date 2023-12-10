package ItemSystem;

import ItemSystem.Entities.Tools.*;
import Character.Player;

public class Inventory implements Comparable<String> {
    private InventorySlot[] inventory;

    public InventorySlot[] getInventory() { return inventory; }

    public Inventory(Player player) {
        if (player.isNPC()) inventory = new InventorySlot[4];
        else setUpStartingInventory(player);
    }

    private void setUpStartingInventory(Player player) {
        inventory = new InventorySlot[24];
        player.setInventory(this);
        ItemSystem.Inventory inventory = player.getInventory();

        ItemSystem.Entity[] starterItems = new ItemSystem.Entity[] {
                new Axe(), new Hoe(), new WateringCan()
        };

        for (ItemSystem.Entity e : starterItems) {
            this.addToInventory(e);
        }
    }

    public void addToInventory(Entity e) {
        if (e.isStackable() && entityInInventory(e)) {
            addToStack(e);
        }
        else if (inventoryFull()) {
            return;
        }
        else {
            addNewEntity(e);
        }
    }

    private void addNewEntity(Entity e) {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == null) {
                inventory[i] = new InventorySlot(e);
                break;
            }
        }
    }

    private boolean inventoryFull() {
        for (InventorySlot slot : inventory) {
            if (slot == null) {
                return false;
            }
        }
        return true;
    }

    private boolean entityInInventory(Entity e) {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null && inventory[i].getEntity().getName() == e.getName()) {
                return true;
            }
        }
        return false;
    }

    private void addToStack(Entity e) {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null && inventory[i].getEntity().getName() == e.getName()) {
                inventory[i].getEntity().setNumberOfEntities(inventory[i].getEntity().getNumberOfEntities() + e.getNumberOfEntities());
            }
        }
    }

    public void removeEntityFromPosition(int i) {
        this.inventory[i] = null;
    }

    public void sort() {
        boolean swapped = true;

        while (swapped) {
            swapped = false;
            for (int i = 0; i < this.inventory.length - 1; i++) {
                if (this.inventory[i] != null && this.inventory[i].getEntity() != null && this.inventory[i + 1] != null && this.inventory[i + 1].getEntity() != null) {
                    if (this.inventory[i].getEntity().getName().compareTo(this.inventory[i + 1].getEntity().getName()) > 0) {
                        Entity tmp = inventory[i].getEntity();
                        this.inventory[i].setEntity(inventory[i + 1].getEntity());
                        this.inventory[i + 1].setEntity(tmp);

                        swapped = true;
                    }
                }
            }
        }
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }
}