package ItemSystem.Entities;

import ItemSystem.Entity;
import ItemSystem.EntityType;

public abstract class Crops extends Entity {
    private int state;
    private int maxState;
    private int growthTime; 
    private int healthRegen;
    private int staminaRegen;
    private boolean handPicked;

    public Crops(String name, String description, float sellPrice, float buyPrice, int healthRegen, int staminaRegen) {
        super(name, description, sellPrice, buyPrice, EntityType.CROPS, 1);
    }

    public void harvest() {
        if (state == maxState && !this.isHandPicked()) {
            return;
        }
    }

    public void harvest(Tool tool) {
        if (state == maxState && this.isHandPicked()) {
            return;
        }
    }

    public int getHealthRegen() { return healthRegen; }
    public void setHealthRegen(int healthRegen) { this.healthRegen = healthRegen; }

    public int getStaminaRegen() { return staminaRegen; }
    public void setStaminaRegen(int staminaRegen) { this.staminaRegen = staminaRegen; }

    public int getState() { return state; }
    public void setState(int state) { this.state = state; }

    public int getMaxState() { return maxState; }
    public void setMaxState(int maxState) { this.maxState = maxState; }

    public boolean isHandPicked() { return handPicked; }
    public void setHandPicked(boolean handPicked) { this.handPicked = handPicked; }

    public int getGrowthTime() { return growthTime; }
    public void setGrowthTime(int growthTime) { this.growthTime = growthTime; }
}