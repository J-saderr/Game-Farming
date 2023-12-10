package Character;

import ItemSystem.Entity;
import Locations.Location;

public class Self extends Player {

    private int stamina;
    private int health;
    private Location location;

    private Entity activeEntity;

    public int getStamina() {
        return stamina;
    }
    public void setStamina(int stamina ) { this.stamina = stamina; }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health ) { this.health = health; }

    public void modifyStamina(int staminaToAddOrSubtract) { this.stamina += staminaToAddOrSubtract; }
    public void modifyHealth(int healthToAddOrSubtract) { this.health += healthToAddOrSubtract; }

    public Entity getActiveEntity() { return this.activeEntity; }
    public void setActiveEntity(Entity entity) { this.activeEntity = entity; }

    public Self() {
        this("Hi", "");
    }

    public Self(String firstName, String lastName) {
        this.setNPC(false);
        this.setFirstName(firstName);
        this.setLastName(lastName);

        this.setInventory(new ItemSystem.Inventory(this));
    }

    @Override
    public void setLocation(int i) {
        if (i == 0) {
        }
    }
}