package ItemSystem.Entities;

import ItemSystem.Entity;
import ItemSystem.EntityType;

public abstract class Tool extends Entity {
    private int mainMaxStat;

    public Tool(String name, String description, int mainMaxStat) {
        this(name, description, 0.0f, 0.0f, mainMaxStat);
    }

    public Tool(String name, String description, float sellPrice, float buyPrice, int mainMaxStat) {
        super(name, description, sellPrice, buyPrice, EntityType.TOOL, 1);
    }

    public void setStandardTool(int mainMaxStat) {
        setMainMaxStat(mainMaxStat);
        this.setSellable(false);
        this.setDeletable(false);
    }

    public abstract void execute();

    public void increaseMainMaxStat(int increment) {
        setMainMaxStat(getMainMaxStat() + increment);
    }

    public void decreaseMainMaxStat(int decrement) {
        setMainMaxStat(getMainMaxStat() - decrement);
    }

    public int getMainMaxStat() { return mainMaxStat; }
    public void setMainMaxStat(int mainMaxStat) { this.mainMaxStat = mainMaxStat; }
}
